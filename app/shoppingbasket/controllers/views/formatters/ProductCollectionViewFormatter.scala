package shoppingbasket.controllers.views.formatters

import play.api.libs.json.Json
import shoppingbasket.controllers.views.{ProductCollectionView, Link}

/**
  * Created by cristina on 04.05.2016.
  */
object ProductCollectionViewFormatter {
  import shoppingbasket.controllers.views.formatters.LinkFormatter._

  implicit val jsonProductCollectionView = Json.writes[ProductCollectionView]
}
