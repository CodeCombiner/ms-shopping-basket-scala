package shoppingbasket.controllers

import akka.actor.ActorRef
import akka.pattern.ask
import akka.util.Timeout
import com.google.inject.Inject
import com.google.inject.name.Named
import play.api.i18n.{I18nSupport, MessagesApi}
import play.api.libs.json.{JsError, Json}
import play.api.mvc.{Action, Controller, Request}

import shoppingbasket.controllers.forms.PostBasketItemForm
import shoppingbasket.controllers.forms.formatters.BasketFormsFormatters._
import shoppingbasket.controllers.mappers.services.ShoppingBasketCreateItemSingleMapper
import shoppingbasket.controllers.mappers.views.ShoppingBasketItemDisplayViewMapper
import shoppingbasket.services.items._

import scala.concurrent.{ExecutionContext, Future}

/**
  * Created by cristina on 04.05.2016.
  */
class BasketItemController @Inject()(val messagesApi: MessagesApi,
                                     @Named("shopping-basket-actor") shoppingBasketActor: ActorRef)
                                    (implicit ec: ExecutionContext) extends Controller with I18nSupport {

  import scala.concurrent.duration._

  implicit val timeout: Timeout = 5.seconds

  /**
    * Handler for GET /shoppingbaskets/:id/items/:id
    */
  def itemByBasket(basketId: String, itemId: String) = Action.async { implicit request =>
    (shoppingBasketActor ? ShoppingBasketItemView(basketId, itemId)).mapTo[Option[ShoppingBasketItemDisplay]].map {
      case Some(display) =>
        import shoppingbasket.controllers.views.formatters.ShoppingBasketDisplayFormatter._
        Ok(Json.toJson(new ShoppingBasketItemDisplayViewMapper(basketId)(request).toView(display)))
      case None => NotFound
    }
  }

  /**
    * Handler for DELETE /shoppingbaskets/:id/items/:id
    */
  def deleteItemFromBasket(basketId: String, itemId: String) = Action.async { implicit request =>
    (shoppingBasketActor ? ShoppingBasketItemDelete(basketId, itemId)).mapTo[Option[Unit]].map {
      case Some(_) =>
        Ok
      case None => NotFound
    }
  }

  /**
    * Handler for POST /shoppingbaskets/:id/items
    */
  def addItemToBasket(basketId: String) = Action.async { implicit request =>
    request.body.asJson.map {
      json =>
        json.validate[PostBasketItemForm].map {
          form =>

            (shoppingBasketActor ? new ShoppingBasketCreateItemSingleMapper(basketId).toServiceObj(form)).mapTo[Option[Either[ServiceErrors, String]]].map {
              case Some(either) =>
                either match {
                  case Right(itemId) => Created.withHeaders((CONTENT_TYPE, itemLocationURL(basketId, itemId)))
                  case Left(errors) => BadRequest(errors.toString)
                }
              case None => NotFound
            }
        }.recoverTotal(e => Future.successful(BadRequest("Detected error: " + JsError.toJson(e))))
    }.getOrElse {
      Future.successful(BadRequest("Expecting Json Data"))
    }
  }

  def itemLocationURL(basketId: String, itemId: String)(implicit req: Request[_]) =
    shoppingbasket.controllers.routes.BasketItemController.itemByBasket(basketId, itemId).absoluteURL()(req).stripSuffix("/").trim
}
