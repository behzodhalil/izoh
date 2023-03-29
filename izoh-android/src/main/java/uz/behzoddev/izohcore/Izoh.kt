package uz.behzoddev.izohcore

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
  public fun logger(platform: Platform, tagLength: Int) {
    when (platform) {
      Platform.Kotlin -> {
        KotlinIzohher()
      }
      Platform.Android -> {
        AndroidIzohher(tagLength)
      }
    }
  }

  @JvmStatic
  public fun install(initializer: Izoh.() -> Unit) {
    synchronized(this) {
      initializer.invoke(this)
      if (configuration.isEnabled) {
        failure("Failed the installation of izoh logger library") {
          "The logger $currentIzohher is already installed but you've tried to install a new logger:\""
        }
      }
      if (configuration.izohher != null) {
        currentIzohher = requireNotNull(configuration.izohher)
      }
    }
  }

  @JvmStatic
  public fun uninstall() {
    synchronized(this) {
      currentIzohher = EmptyIzohher()
      configuration.isEnabled = false
    }
  }

  @JvmStatic
  public inline fun verbose(tag: String, message: () -> String) {
    currentIzohher.log(Level.VERBOSE, tag = tag, message = message())
  }

  @JvmStatic
  public inline fun debug(tag: String, message: () -> String) {
    currentIzohher.log(Level.DEBUG, tag = tag, message = message())
  }

  @JvmStatic
  public inline fun warning(tag: String, message: () -> String) {
    currentIzohher.log(Level.WARNING, tag = tag, message = message())
  }

  @JvmStatic
  public inline fun info(tag: String, message: () -> String) {
    currentIzohher.log(Level.INFO, tag = tag, message = message())
  }

  @JvmStatic
  public inline fun failure(tag: String, throwable: Throwable?, message: () -> String) {
    currentIzohher.log(Level.FAILURE, tag = tag, message = message(), throwable = throwable)
  }

  @JvmStatic
  public inline fun failure(tag: String, message: () -> String) {
    currentIzohher.log(Level.FAILURE, tag = tag, message = message())
  }

  @JvmStatic
  public inline fun log(
    level: Level,
    tag: String,
    message: () -> String,
    throwable: Throwable? = null
  ) {
    if (throwable != null) {
      failure(tag, message)
    } else {
      when (level) {
        Level.VERBOSE -> verbose(tag, message)
        Level.DEBUG -> debug(tag, message)
        Level.INFO -> info(tag, message)
        Level.WARNING -> warning(tag, message)
        Level.FAILURE -> failure(tag, message)
      }
    }
  }
}
