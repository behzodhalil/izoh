package uz.behzoddev.izohcore

public enum class Priority(
  public val level: Int
) {
  VERBOSE(level = 1),
  DEBUG(level = 2),
  INFO(level = 3),
  WARNING(level = 4),
  FAILURE(level = 5)
}
