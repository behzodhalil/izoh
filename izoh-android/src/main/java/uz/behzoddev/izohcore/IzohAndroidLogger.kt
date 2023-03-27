package uz.behzoddev.izohcore

import android.os.Build
import android.os.Build.VERSION.SDK_INT
import android.os.Build.VERSION_CODES.N
import android.util.Log
import androidx.annotation.ChecksSdkIntAtLeast

public class IzohAndroidLogger(
    private val maxTagLength: Int = MAX_TAG_LENGTH
) : IzohLogger {

    override fun log(priority: Priority, tag: String, message: String, failure: Throwable?) {
        androidLog(priority, tag, message, failure)
    }

    private fun androidLog(priority: Priority, tag: String, message: String, failure: Throwable?) {
        val currentPriority = priority.toAndroidPriority()
        val currentTag = checkAndReplaceTag(tag)
        val currentMessage = composedAndPrint(message)
        val lastMessage = lastMessage(message, failure)

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
    private fun isNougatOrHigher() = SDK_INT >= N

    companion object {
        private const val MAX_TAG_LENGTH = 23
    }
}

fun Priority.toAndroidPriority(): Int {
    return when (this) {
        Priority.VERBOSE -> Log.VERBOSE
        Priority.DEBUG -> Log.DEBUG
        Priority.INFO -> Log.INFO
        Priority.WARNING -> Log.WARN
        Priority.FAILURE -> Log.ERROR
        else -> Log.ERROR
    }
}