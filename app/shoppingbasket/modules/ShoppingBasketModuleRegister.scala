package shoppingbasket.modules

import com.google.inject.AbstractModule
import play.api.libs.concurrent.AkkaGuiceSupport

import shoppingbasket.services.akka.ShoppingBasketActor

/**
  * Created by cristina on 04.05.2016.
  */
class ShoppingBasketModuleRegister extends AbstractModule with AkkaGuiceSupport {
  def configure = {
    bindActor[ShoppingBasketActor](ShoppingBasketModuleRegister.ShoppingBasketRegisterName)
  }
}

object ShoppingBasketModuleRegister {
  val ShoppingBasketRegisterName = "shopping-basket-actor"
}