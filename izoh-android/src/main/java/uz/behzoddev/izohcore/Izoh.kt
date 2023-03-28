package uz.behzoddev.izohcore

import uz.behzoddev.Configuration
import uz.behzoddev.FailureIzohher

object Izoh {

  private val configuration: Configuration = Configuration()

  @Volatile
  @PublishedApi
  internal var currentIzohher: Izohher = FailureIzohher()
    private set

  @JvmStatic
  public fun enabled() {
    configuration.isEnabled = true
  }

  @JvmStatic
  public fun logger() {
  }

  @JvmStatic
  public fun verbose(tag: String, message: () -> String) {
    currentIzohher.log(LoggingLevel.VERBOSE, tag = tag, message = message())
  }

  @JvmStatic
  public fun debug(tag: String, message: () -> String) {
    currentIzohher.log(LoggingLevel.DEBUG, tag = tag, message = message())
  }

  @JvmStatic
  public fun warning(tag: String, message: () -> String) {
    currentIzohher.log(LoggingLevel.WARNING, tag = tag, message = message())
  }
}
