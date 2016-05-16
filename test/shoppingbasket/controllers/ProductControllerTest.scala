package shoppingbasket.controllers

import org.junit.runner._
import org.specs2.runner._
import play.api.test._

/**
  * Created by cristina on 07.05.2016.
  */
@RunWith(classOf[JUnitRunner])
class ProductControllerTest extends PlaySpecification {

  "ProductsController#products" should {

    "return the products page" in new WithApplication {
      val response = route(FakeRequest(GET, "/api/products"))

      response must beSome.which(status(_) == OK)
    }
  }
}
