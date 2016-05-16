package shoppingbasket.controllers.forms.formatters

import play.api.libs.json.Json
import shoppingbasket.controllers.forms.{PostBasketForms, PostBasketItemForm, PostBasketProductForm}

/**
  * Created by cristina on 05.05.2016.
  */
object BasketFormsFormatters {
  implicit val postBasketProductFormFormat = Json.format[PostBasketProductForm]
  implicit val postBasketItemFormFormat = Json.format[PostBasketItemForm]
  implicit val postBasketFormsFormat = Json.format[PostBasketForms]
}
