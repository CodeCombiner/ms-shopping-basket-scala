package shoppingbasket.dao.entities

/**
  * Created by cristina on 03.05.2016.
  */
case class Product(id: String, name: String, description: String, price: BigDecimal, currency: String, var stock: Long) {

  def isAvailable = stock > 0

}