package shoppingbasket.controllers.views.formatters

import play.api.libs.json.{Writes, Json}
import shoppingbasket.controllers.views.{CollectionView, PageInfoView}

/**
  * Created by cristina on 04.05.2016.
  */
object CollectionViewFormatter {
  implicit val jsonPageInfoView = Json.format[PageInfoView]

  implicit def jsonCollectionViewWrites[T](implicit innerWrites: Writes[T]): Writes[CollectionView[T]] =
    new Writes[CollectionView[T]] {
      def writes(c: CollectionView[T]) = Json.obj(
        "pageInfo" -> c.pageInfo,
        "data" -> c.data
      )
    }
}
