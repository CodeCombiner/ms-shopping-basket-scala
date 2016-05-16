package shoppingbasket.controllers.views.formatters

import play.api.libs.json.Json

import shoppingbasket.controllers.views.{ShoppingBasketDisplayView, ProductDisplayView, ShoppingBasketItemDisplayView}

/**
  * Created by cristina on 04.05.2016.
  */
object ShoppingBasketDisplayFormatter {
  import shoppingbasket.controllers.views.formatters.LinkFormatter._

  implicit val jsonProductDisplayView = Json.writes[ProductDisplayView]
  implicit val jsonShoppingBasketItemDisplayView = Json.writes[ShoppingBasketItemDisplayView]
  implicit val jsonShoppingBasketDisplayView = Json.writes[ShoppingBasketDisplayView]
}
