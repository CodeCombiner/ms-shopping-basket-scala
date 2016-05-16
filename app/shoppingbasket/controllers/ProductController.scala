package shoppingbasket.controllers

import com.google.inject.Inject
import play.api.Logger
import play.api.i18n.{I18nSupport, MessagesApi}
import play.api.libs.json.Json
import play.api.mvc.{Action, Controller}

import shoppingbasket.controllers.mappers.views.{ProductCollectionMappers, ProductViewMapper}
import shoppingbasket.controllers.views.formatters._
import shoppingbasket.services.ProductService

import scala.concurrent.ExecutionContext

/**
  * Created by cristina on 04.05.2016.
  */
class ProductController @Inject()(val productService: ProductService, val messagesApi: MessagesApi)
                                 (implicit ec: ExecutionContext) extends Controller with I18nSupport {

  def products(page: Int, pageSize: Int, available: Option[Boolean] = None) = Action.async { implicit request =>
    productService.products(page, pageSize, available).map { data =>
      import CollectionViewFormatter._
      import ProductCollectionViewFormatter._

      Logger.debug(data.toString)
      Ok(Json.toJson(new ProductCollectionMappers(request).toView(data)))
    }
  }

  def product(id: String) = Action.async { implicit request =>
    productService.product(id).map { productOpt =>
      import ProductCollectionViewFormatter._

      productOpt.fold(NotFound("")){
        product => Ok(Json.toJson(new ProductViewMapper(request).toView(product)))
      }
    }
  }
}
