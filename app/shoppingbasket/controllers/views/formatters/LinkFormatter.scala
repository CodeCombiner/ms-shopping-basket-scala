package shoppingbasket.controllers.views.formatters

import play.api.libs.json.{Json, Writes}
import shoppingbasket.controllers.views.Link

/**
  * Created by cristina on 04.05.2016.
  */
object LinkFormatter {

  implicit def jsonLinkWrites: Writes[Link] =
    new Writes[Link] {
      def writes(c: Link) = Json.obj(
        "rel" -> c.rel,
        "href" -> c.href
      )
    }
}
