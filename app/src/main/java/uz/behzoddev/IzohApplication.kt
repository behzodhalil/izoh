package uz.behzoddev

import android.app.Application
import uz.behzoddev.izohcore.AndroidIzohher

class IzohApplication : Application() {

  override fun onCreate() {
    super.onCreate()
    AndroidIzohher.install(this)
  }
}
