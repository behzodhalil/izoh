package uz.behzoddev.izohandroid

import uz.behzoddev.izohcommon.level.LogLevel

inline fun Any.success(
  logLevel: LogLevel = LogLevel.DEBUG,
  throwable: Throwable? = null,
  tag: String? = null,
  message: () -> String
) {
  val tagOrCaller = tag ?: outerClassSimpleTagName()
  Izoh.log(logLevel = logLevel, tag = tagOrCaller, throwable = throwable, message = { "\uD83D\uDFE1 ${message()}" })
}

inline fun Any.failure(
  logLevel: LogLevel = LogLevel.FAILURE,
  throwable: Throwable? = null,
  tag: String? = null,
  message: () -> String
) {
  val tagOrCaller = tag ?: outerClassSimpleTagName()
  Izoh.log(logLevel = logLevel, tag = tagOrCaller, throwable = throwable, message = { "\uD83D\uDD34 ${message()}" })
}

inline fun Any.debug(
  logLevel: LogLevel = LogLevel.DEBUG,
  throwable: Throwable? = null,
  tag: String? = null,
  message: () -> String
) {
  val tagOrCaller = tag ?: outerClassSimpleTagName()
  Izoh.log(logLevel = logLevel, throwable = throwable, tag = tagOrCaller, message = { "\uD83D\uDD35 ${message()}" })
}

@PublishedApi
internal fun Any.outerClassSimpleTagName(): String {
  val javaClass = this::class.java
  val fullClassName = javaClass.name
  val outerClassName = fullClassName.substringBefore('$')
  val simplerOuterClassName = outerClassName.substringAfterLast('.')
  return if (simplerOuterClassName.isEmpty()) {
    fullClassName
  } else {
    simplerOuterClassName.removeSuffix("Kt")
  }
}
