package shoppingbasket.controllers.views

import org.joda.time.DateTime

/**
  * Created by cristina on 04.05.2016.
  */
case class ProductDisplayView(id: String, name: String, description: String, price: BigDecimal, currency: String,
                              stock: Long, link: Link)

case class ShoppingBasketItemDisplayView(id: String, product: ProductDisplayView, addedDt: DateTime, capacity: Int, link: Link) extends View

case class ShoppingBasketDisplayView(id: String, items: Seq[ShoppingBasketItemDisplayView], link: Link) extends View
