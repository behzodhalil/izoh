package uz.behzoddev.izohcore

object EmptyIzohLogger : IzohLogger {
  override fun log(priority: Priority, tag: String, message: String, failure: Throwable?) {}
}
