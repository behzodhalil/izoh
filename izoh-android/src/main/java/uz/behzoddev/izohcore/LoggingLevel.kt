package uz.behzoddev.izohcore

enum class LoggingLevel(
    val value: Int
) {
    VERBOSE(value = 1),
    DEBUG(value = 2),
    INFO(value = 3),
    WARNING(value = 4),
    FAILURE(value = 5)
}