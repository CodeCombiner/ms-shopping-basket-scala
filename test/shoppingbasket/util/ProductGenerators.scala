package shoppingbasket.util

import shoppingbasket.dao.entities.Product
import shoppingbasket.dao.entities.gen.ProductGen

/**
  * Created by cristina on 03.05.2016.
  */
object ProductGenerators {
  def generateProducts(numOfProducts: Int = 5): scala.collection.immutable.List[Product] =
    (1 to numOfProducts).map(i => ProductGen.getNewProduct)(collection.breakOut)
}
