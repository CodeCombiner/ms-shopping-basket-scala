package shoppingbasket.controllers.views

import play.api.libs.json.Json

/**
  * Created by cristina on 03.05.2016.
  */
case class ProductCollectionView(id: String, name: String, description: String, price: BigDecimal, currency: String,
                                 stock: Long, available: Boolean, link: Link) extends View
