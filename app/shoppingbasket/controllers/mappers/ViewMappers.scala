package shoppingbasket.controllers.mappers

import shoppingbasket.controllers.views.View

/**
  * Created by cristina on 04.05.2016.
  */
trait ViewMappers[T, V <: View] {
  def toView(obj: T): V
}
