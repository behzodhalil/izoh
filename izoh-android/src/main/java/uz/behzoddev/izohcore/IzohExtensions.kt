package uz.behzoddev.izohcore

inline fun Any.izoh(
  level: Level = Level.DEBUG,
  throwable: Throwable? = null,
  tag: String? = null,
  message: () -> String
) {
  val tagOrCaller = tag ?: outerClassSimpleTagName()
  Izoh.log(level = level, tag = tagOrCaller, throwable = throwable, message = message)
}

/*
inline fun Any.izoh(
  priority: Priority = Priority.DEBUG,
  failure: Throwable? = null,
  tag: String? = null,
  message: () -> String
) {
  if (failure != null && tag != null) {
    with(IzohLog) { failure(tag = tag, message = message, throwable = failure) }
  } else if (tag != null) {
    IzohLog.log(tag = tag, priority = priority, message = message)
  }
}

inline fun Any.debug(
  message: () -> String
) {
  IzohLog.debug("Test", message)
}

inline fun Any.failure(
  message: () -> String
) {
  IzohLog.failure("Test", message)
}
*/
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
