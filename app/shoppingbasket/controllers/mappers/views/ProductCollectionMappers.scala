package shoppingbasket.controllers.mappers.views

import play.api.mvc.Request

import shoppingbasket.controllers.mappers.ViewMappers
import shoppingbasket.controllers.views.{CollectionView, Link, ProductCollectionView}
import shoppingbasket.dao.entities.Product
import shoppingbasket.services.items.Item

/**
  * Created by cristina on 03.05.2016.
  */
final class ProductViewMapper(req: Request[_]) extends ViewMappers[Product, ProductCollectionView] {

  override def toView(obj: Product): ProductCollectionView =
    ProductCollectionView(
      obj.id,
      obj.name,
      obj.description,
      obj.price,
      obj.currency,
      obj.stock,
      obj.isAvailable,
      Link.self(shoppingbasket.controllers.routes.ProductController.product(obj.id), req)
    )

}

final class ProductCollectionMappers(req: Request[_]) extends ViewMappers[Item[Product], CollectionView[ProductCollectionView]] {

  override def toView(obj: Item[Product]): CollectionView[ProductCollectionView] =
    CollectionView[ProductCollectionView](
      pageInfo = PageInfoMapper.toView(obj.pagination),
      data = obj.data.map(new ProductViewMapper(req).toView)
    )
}
