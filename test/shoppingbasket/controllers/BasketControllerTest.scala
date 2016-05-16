package shoppingbasket.controllers

import org.junit.runner.RunWith
import org.specs2.runner.JUnitRunner
import play.api.libs.json.Json
import play.api.mvc.Result
import play.api.test.{FakeHeaders, FakeRequest, PlaySpecification, WithApplication}
import shoppingbasket.dao.entities.gen.ProductGen

import scala.concurrent.Future

/**
  * Created cristina on 07.05.2016.
  */
@RunWith(classOf[JUnitRunner])
class BasketControllerTest extends PlaySpecification {

  private def getResponse(jsonString: String): Option[Future[Result]] =
    route(
      FakeRequest(
        POST,
        "/api/shoppingbaskets",
        FakeHeaders(Seq(("Content-Type", "application/json"))),
        Json.parse(jsonString))
    )

  "BasketController#post" should {

    "return not create a shopping basket for no json body with product item" in new WithApplication {
      val response = route(FakeRequest(POST, "/api/shoppingbaskets"))

      response must beSome.which(status(_) == BAD_REQUEST)
    }

    "return create the shopping basket request with a single available item" in new WithApplication {

      val firstProduct = ProductGen.getProducts.head
      // make sure that this product is still available
      ProductGen.getProducts.head.copy(stock = 10)

      val jsonString =
        s""" {
          | "items": [
          |        {
          |            "product": {
          |                "id": "${firstProduct.id}"
          |            },
          |            "capacity": 2
          |        }
          |    ]
          | }
        """.stripMargin

      val response = getResponse(jsonString)

      response must beSome.which(status(_) == CREATED)
    }

    "return an error if the product is not available" in new WithApplication {

      val firstProduct = ProductGen.getProducts.head
      // make sure that this product is not available
      ProductGen.getProducts.head.stock = 0

      val jsonString =
        s""" {
            | "items": [
            |        {
            |            "product": {
            |                "id": "${firstProduct.id}"
            |            },
            |            "capacity": 2
            |        }
            |    ]
            | }
        """.stripMargin


      val response = getResponse(jsonString)

      response must beSome.which(status(_) == BAD_REQUEST)
    }

    "return an error if no product is available in the request" in new WithApplication {

      val jsonString =
        """ {
          | "items": [
          |        {
          |            "capacity": 2
          |        }
          |    ]
          | }
        """.stripMargin


      val response = getResponse(jsonString)

      response must beSome.which(status(_) == BAD_REQUEST)
    }

    "return an error if the product does not exists" in new WithApplication {

      val jsonString =
        """ {
          | "items": [
          |        {
          |            "product": {
          |                "id": "ps4"
          |            },
          |            "capacity": 2
          |        }
          |    ]
          | }
        """.stripMargin


      val response = getResponse(jsonString)

      response must beSome.which(status(_) == BAD_REQUEST)
    }
  }
}