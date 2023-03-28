package uz.behzoddev.izoh_common.date

import java.text.DateFormat
import java.text.SimpleDateFormat
import java.util.Locale

object DateUtils {

  private const val CURRENT_DATE_FORMAT = "yyyy-MM-dd HH:mm:ss''SSS"

  fun simpleCurrentTime(): DateFormat {
    return SimpleDateFormat(CURRENT_DATE_FORMAT, Locale.ENGLISH)
  }

  fun now(): Long {
    return System.currentTimeMillis()
  }
}
