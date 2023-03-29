package uz.behzoddev.izohcore

import android.app.Application
import android.content.pm.ApplicationInfo
import android.os.Build
import android.util.Log
import androidx.annotation.ChecksSdkIntAtLeast

class AndroidIzohher(
  private val maxTagLength: Int = MAX_TAG_LENGTH
) : Izohher {

  override fun log(level: Level, tag: String, message: String, throwable: Throwable?) {
    androidLog(level = level, tag = tag, message = message, throwable = throwable)
  }

  private fun androidLog(level: Level, tag: String, message: String, throwable: Throwable?) {
    val currentPriority = level.asAndroidLevel()
    val currentTag = checkAndReplaceTag(tag)
    val currentMessage = composedAndPrint(message)
    val lastMessage = lastMessage(currentMessage, throwable)

    Log.println(currentPriority, currentTag, lastMessage)
  }

  private fun lastMessage(message: String, failure: Throwable?): String {
    return failure?.let {
      "$message\n${it.stringify()}"
    } ?: message
  }

  private fun checkAndReplaceTag(tag: String): String {
    return tag.takeIf { it.length > maxTagLength && !isNougatOrHigher() }
      ?.substring(0, maxTagLength)
      ?: tag
  }

  private fun composedAndPrint(message: String): String {
    val currentThread = runCurrentThread()
    return "($currentThread) $message"
  }

  private fun runCurrentThread(): String {
    return Thread.currentThread().run { "$name:$id" }
  }

  @ChecksSdkIntAtLeast(api = Build.VERSION_CODES.N)
  private fun isNougatOrHigher() = Build.VERSION.SDK_INT >= Build.VERSION_CODES.N

  public companion object {
    private val Application.isDebuggableApp: Boolean
      get() = (applicationInfo.flags and ApplicationInfo.FLAG_DEBUGGABLE) != 0

    public fun install(
      application: Application,
      level: Level = Level.DEBUG,
      maxTagLength: Int = MAX_TAG_LENGTH
    ) {
      if (application.isDebuggableApp) {
        Izoh.install {
          logger(Platform.Android, maxTagLength)
          enabled()
        }
      }
    }

    private const val MAX_TAG_LENGTH = 23
  }
}
