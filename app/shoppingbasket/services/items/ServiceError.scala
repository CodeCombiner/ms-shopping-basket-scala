package shoppingbasket.services.items

/**
  * Created by cristina on 04.05.2016.
  */
case class ServiceError(field: String, message: String) extends ServiceDto

case class ServiceErrors(errors: Seq[ServiceError])

object ServiceErrors {

  def apply(field: String, message: String): ServiceErrors =
    ServiceErrors(Seq(ServiceError(field, message)))
}