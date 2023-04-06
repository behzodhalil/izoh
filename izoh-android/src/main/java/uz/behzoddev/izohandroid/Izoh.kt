package uz.behzoddev.izohandroid

import uz.behzoddev.izohandroid.core.Izohher
import uz.behzoddev.izohcommon.level.LogLevel

object Izoh {

  @JvmStatic
  public var isInstalled: Boolean = false
    private set

  @Volatile
  @PublishedApi
  internal var currentIzohher: Izohher = FailureIzohher()
    private set(value) {
      isInstalled = true
      field = value
    }

  @JvmStatic
  public fun install(izohher: Izohher) {
    synchronized(this) {
      if (isInstalled) {
        failure("Izohher") {
          "Although you have attempted to install the new logger [$izohher], it appears that $currentIzohher is already installed," +
            "You may need to uninstall the current logger before attempting to install the new one."
        }
      }
      currentIzohher = izohher
    }
  }

  @JvmStatic
  public fun uninstall() {
    synchronized(this) {
      currentIzohher = EmptyIzohher()
      isInstalled = false
    }
  }

  @JvmStatic
  public inline fun verbose(tag: String, message: () -> String) {
    currentIzohher.log(LogLevel.VERBOSE, tag = tag, message = message())
  }

  @JvmStatic
  public inline fun debug(tag: String, message: () -> String) {
    currentIzohher.log(LogLevel.DEBUG, tag = tag, message = message())
  }

  @JvmStatic
  public inline fun warning(tag: String, message: () -> String) {
    currentIzohher.log(LogLevel.WARNING, tag = tag, message = message())
  }

  @JvmStatic
  public inline fun info(tag: String, message: () -> String) {
    currentIzohher.log(LogLevel.INFO, tag = tag, message = message())
  }

  @JvmStatic
  public inline fun failure(tag: String, throwable: Throwable?, message: () -> String) {
    currentIzohher.log(LogLevel.FAILURE, tag = tag, message = message(), throwable = throwable)
  }

  @JvmStatic
  public inline fun failure(tag: String, message: () -> String) {
    currentIzohher.log(LogLevel.FAILURE, tag = tag, message = message())
  }

  @JvmStatic
  public inline fun log(
    logLevel: LogLevel,
    tag: String,
    message: () -> String,
    throwable: Throwable? = null
  ) {
    if (throwable != null) {
      failure(tag, message)
    } else {
      when (logLevel) {
        LogLevel.VERBOSE -> verbose(tag, message)
        LogLevel.DEBUG -> debug(tag, message)
        LogLevel.INFO -> info(tag, message)
        LogLevel.WARNING -> warning(tag, message)
        LogLevel.FAILURE -> failure(tag, message)
      }
    }
  }
}
