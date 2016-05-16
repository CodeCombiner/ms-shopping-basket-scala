package shoppingbasket.dao

import com.google.inject.Singleton
import shoppingbasket.dao.entities.Product
import shoppingbasket.dao.entities.gen.ProductGen

import scala.concurrent.Future
import scala.util.{Failure, Success, Try}

/**
  * Created by  cristina on 03.05.2016.
  */
@Singleton
class ProductRepository {

  /**
    * In the bellow code the block passed to [[scala.concurrent.Future]] will be executed by the default Dispatcher,
    * with the return value of the block used to complete the Future (in this case, the result would be
    * an in-memory scala.collection.immutable.List[shoppingbasket.dao.entities.Product]").
    * Unlike a Future that is returned from an Actor, we avoid the overhead of managing an Actor.
    * You can also create already completed Futures using the Future companion, which can be either successes or failures.
    */
  def collection = Future.successful(ProductGen.getProducts)

  /** Retrieve a product from the list */
  def item(id: String): Future[Option[Product]] = Future.successful(ProductGen.getProducts.find(_.id == id))
}
