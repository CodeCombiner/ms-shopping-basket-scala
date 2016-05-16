package shoppingbasket.controllers.mappers

import shoppingbasket.services.items.ServiceDto

/**
  * Created by cristina on 05.05.2016.
  */
trait ServiceMappers[T, V <: ServiceDto] {
  def toServiceObj(obj: T): V
}
