package uz.behzoddev.izohcore

public interface IzohLogger {
  public fun log(priority: Priority, tag: String, message: String, failure: Throwable? = null)
}
