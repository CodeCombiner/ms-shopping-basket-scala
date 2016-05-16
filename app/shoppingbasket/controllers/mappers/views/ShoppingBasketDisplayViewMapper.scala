package shoppingbasket.controllers.mappers.views

import play.api.mvc.Request

import shoppingbasket.controllers.mappers.ViewMappers
import shoppingbasket.controllers.views.{Link, ProductDisplayView, ShoppingBasketDisplayView, ShoppingBasketItemDisplayView}
import shoppingbasket.services.items.{ShoppingBasketDisplay, ShoppingBasketItemDisplay}

/**
  * Created by cristina on 04.05.2016.
  */
final class ShoppingBasketDisplayViewMapper(req: Request[_]) extends ViewMappers[ShoppingBasketDisplay, ShoppingBasketDisplayView] {
  override def toView(obj: ShoppingBasketDisplay): ShoppingBasketDisplayView =
    ShoppingBasketDisplayView(
      obj.id,
      obj.items.map {
        i => new ShoppingBasketItemDisplayViewMapper(obj.id)(req).toView(i)
      },
      Link.self(shoppingbasket.controllers.routes.BasketController.get(obj.id), req)
    )
}


final class ShoppingBasketItemDisplayViewMapper(basketId: String)(req: Request[_]) extends ViewMappers[ShoppingBasketItemDisplay, ShoppingBasketItemDisplayView] {
  override def toView(obj: ShoppingBasketItemDisplay): ShoppingBasketItemDisplayView = ShoppingBasketItemDisplayView(
    obj.id,
    ProductDisplayView(
      obj.product.id,
      obj.product.name,
      obj.product.description,
      obj.product.price,
      obj.product.currency,
      obj.product.stock,
      Link.self(shoppingbasket.controllers.routes.ProductController.product(obj.product.id), req)
    ),
    obj.addedDt,
    obj.capacity,
    Link.self(shoppingbasket.controllers.routes.BasketItemController.itemByBasket(basketId, obj.id), req)
  )
}