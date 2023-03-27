package uz.behzoddev.izohcore

object IzohLog {

    @JvmStatic
    public var isEnabled: Boolean = false
        private set

    internal val config: IzohLogConfig = IzohLogConfig()

    @Volatile
    @PublishedApi
    internal var currentLogger: IzohLogger = FailureIzohLogger
        private set

    @JvmStatic
    public fun enabled() {
        config.isEnabled = true
    }

    @JvmStatic
    public fun logger(platform: Platform) {
        when(platform) {
            Platform.KOTLIN -> {
                config.logger = IzohAndroidLogger()
            }
            Platform.ANDROID -> {
                config.logger = IzohAndroidLogger()
            }
        }
    }


    @JvmStatic
    public fun install(initializer: IzohLog.() -> Unit) {
        synchronized(this) {
            initializer.invoke(this)
            if (config.isEnabled) {
                failure("Failed the installation of izoh logger library") {
                    "The logger $currentLogger is already installed but you've tried to install a new logger:\""
                }
            }
            if (config.logger != null) {
                currentLogger = requireNotNull(config.logger)
            }
        }
    }

    @JvmStatic
    public fun uninstall() {
        synchronized(this) {
            currentLogger = EmptyIzohLogger
            isEnabled = false
        }
    }

    @JvmStatic
    public inline fun verbose(tag: String, message: () -> String) {
        currentLogger.log(priority = Priority.VERBOSE, tag = tag, message = message())
    }

    @JvmStatic
    public inline fun debug(tag: String, message: () -> String) {
        currentLogger.log(priority = Priority.DEBUG, tag = tag, message = message())
    }

    @JvmStatic
    public inline fun info(tag: String, message: () -> String) {
        currentLogger.log(Priority.INFO, tag = tag, message = message())
    }

    @JvmStatic
    public inline fun failure(tag: String, throwable: Throwable?, message: () -> String) {
        currentLogger.log(Priority.FAILURE, tag = tag, message = message(), failure = throwable)
    }

    @JvmStatic
    public inline fun failure(tag: String, message: () -> String) {
        currentLogger.log(Priority.FAILURE, tag = tag, message = message())
    }

    @JvmStatic
    public inline fun warning(tag: String, message: () -> String) {
        currentLogger.log(priority = Priority.WARNING, tag = tag, message = message())
    }

    @JvmStatic
    public inline fun log(
        priority: Priority,
        tag: String,
        message: () -> String,
        failure: Throwable? = null
    ) {
        if (failure != null) {
            failure(tag, message)
        } else {
            when (priority) {
                Priority.VERBOSE -> verbose(tag, message)
                Priority.DEBUG -> debug(tag, message)
                Priority.INFO -> info(tag, message)
                Priority.WARNING -> warning(tag, message)
                Priority.FAILURE -> failure(tag, message)
            }
        }

    }
}

enum class Platform {
    KOTLIN,
    ANDROID
}