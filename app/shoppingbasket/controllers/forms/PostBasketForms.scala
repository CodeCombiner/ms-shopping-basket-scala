package shoppingbasket.controllers.forms

/**
  * Created by cristina on 05.05.2016.
  */
case class PostBasketProductForm(id: String)

case class PostBasketItemForm(product: PostBasketProductForm, capacity: Int)

case class PostBasketForms(items: Seq[PostBasketItemForm])