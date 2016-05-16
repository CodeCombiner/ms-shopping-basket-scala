package shoppingbasket.controllers.mappers.services

import shoppingbasket.controllers.forms.PostBasketForms
import shoppingbasket.controllers.mappers.ServiceMappers
import shoppingbasket.services.items.{ShoppingBasketCreate, ShoppingBasketCreateItem, ShoppingBasketCreateProductItem}

/**
  * Created by cristina on 05.05.2016.
  */
object BasketCreationMapper extends ServiceMappers[PostBasketForms, ShoppingBasketCreate] {
  override def toServiceObj(obj: PostBasketForms): ShoppingBasketCreate = ShoppingBasketCreate(
    items = obj.items.map{
      item =>
        ShoppingBasketCreateItem(ShoppingBasketCreateProductItem(item.product.id), item.capacity)
    }
  )
}
