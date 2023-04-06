package uz.behzoddev.izohandroid.core

import uz.behzoddev.izohcommon.level.LogLevel

/**
 * A logger interface with low-level functionality.
 */
interface Izohher {
  /**
   * Performs a log-level logging operation.
   * @param logLevel The level of log message.
   * @param tag A tag used to identify the source of the log message.
   * @param message A message to be written to the log.
   * @param throwable An optional exception to be logged along with the message.
   */
  fun log(logLevel: LogLevel, tag: String, message: String, throwable: Throwable? = null)
}
