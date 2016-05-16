package shoppingbasket.services.items

/**
  * Created by cristina on 04.05.2016.
  */
case class Item[T](pagination: PaginationItem, data: Seq[T])