package shoppingbasket.services

import org.junit.runner.RunWith
import org.specs2.concurrent.ExecutionEnv
import org.specs2.mock.Mockito
import org.specs2.mutable._
import org.specs2.runner.JUnitRunner

import shoppingbasket.dao.ProductRepository
import shoppingbasket.dao.entities.Product
import shoppingbasket.services.items.{Item, PaginationItem}
import shoppingbasket.util.ProductGenerators._

import scala.concurrent.Future

/**
  * Created by cristina on 07.05.2016.
  */
@RunWith(classOf[JUnitRunner])
class ProductServiceTest extends Specification with Mockito {

  "ProductServiceTest#products" should {

    "return the products page with no results" in { implicit ee: ExecutionEnv =>
      val (service, mock) = serviceWithDependencies

      (mock.collection).returns(Future.successful(generateProducts(0)))

      val products = service.products()

      products must beLike[Item[Product]] { case result =>
        result.pagination.size must be_==(0)
        result.data must beEmpty
      }.await
    }

    val generatedProductsNum: Int = 5

    s"return the products page with $generatedProductsNum results" in { implicit ee: ExecutionEnv =>
      val (service, mock) = serviceWithDependencies

      (mock.collection).returns(Future.successful(generateProducts(generatedProductsNum)))

      val products = service.products()

      products must beLike[Item[Product]] { case result =>
        result.pagination must be_==(PaginationItem(generatedProductsNum, 1, generatedProductsNum))
        result.data must have size(generatedProductsNum)
      }.await
    }

    s"return only available products" in { implicit ee: ExecutionEnv =>
      val (service, mock) = serviceWithDependencies
      val productsGenerated = generateProducts(generatedProductsNum)
      // set the first element from this list to 0 stock and all after it to 10
      // and reassemble the collection
      val productsWithAtLeastOneNotAvailable =
        productsGenerated.splitAt(1)._2.map(_.copy(stock = 10)) :+ productsGenerated(0).copy(stock = 0)


      (mock.collection).returns(Future.successful(productsWithAtLeastOneNotAvailable))
      val products = service.products(available = Some(true))

      products must beLike[Item[Product]] { case result =>

        result.pagination must be_==(PaginationItem(generatedProductsNum - 1, 1, generatedProductsNum - 1))
        result.data must have size(generatedProductsNum - 1)
      }.await
    }

  }

  def serviceWithDependencies = {
    val productRepositoryMock = mock[ProductRepository]

    (new ProductService(productRepositoryMock), productRepositoryMock)
  }
}