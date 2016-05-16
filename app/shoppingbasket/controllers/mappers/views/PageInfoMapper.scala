package shoppingbasket.controllers.mappers.views

import shoppingbasket.controllers.mappers.ViewMappers
import shoppingbasket.controllers.views.PageInfoView
import shoppingbasket.services.items.PaginationItem


/**
  * Created by cristina on 03.05.2016.
  */
object PageInfoMapper extends ViewMappers[PaginationItem, PageInfoView] {
  override def toView(obj: PaginationItem): PageInfoView = PageInfoView(obj.page, obj.pageSize, obj.size)
}
