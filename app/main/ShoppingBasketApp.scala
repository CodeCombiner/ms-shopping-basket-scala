package main

import play.api.mvc._

class ShoppingBasketApp extends Controller {
  /**
    * Handles the home page GET / URI.
    */
  def index = Action {
    Ok(views.html.index("Shopping Basket REST application is ready."))
  }

}