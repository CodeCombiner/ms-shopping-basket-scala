package shoppingbasket.controllers.mappers.services

import shoppingbasket.controllers.forms.PostBasketItemForm
import shoppingbasket.controllers.mappers.ServiceMappers
import shoppingbasket.services.items.{ShoppingBasketCreateItem, ShoppingBasketCreateItemSingle, ShoppingBasketCreateProductItem}

/**
  * Created by cristina on 04.05.2016.
  */
class ShoppingBasketCreateItemSingleMapper(basketId: String) extends ServiceMappers[PostBasketItemForm, ShoppingBasketCreateItemSingle] {
  override def toServiceObj(obj: PostBasketItemForm): ShoppingBasketCreateItemSingle =
    ShoppingBasketCreateItemSingle(basketId, ShoppingBasketCreateItem(ShoppingBasketCreateProductItem(obj.product.id), obj.capacity))
}
