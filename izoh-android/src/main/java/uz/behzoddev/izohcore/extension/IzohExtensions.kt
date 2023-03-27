package uz.behzoddev.izohcore.extension

import uz.behzoddev.izohcore.IzohLog
import uz.behzoddev.izohcore.Priority

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