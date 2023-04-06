package uz.behzoddev

import android.app.Application
import uz.behzoddev.izohandroid.AndroidIzohher

class IzohApplication : Application() {

  override fun onCreate() {
    super.onCreate()
    AndroidIzohher.install(this)
  }
}
