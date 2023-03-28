package uz.behzoddev.izohcore

import uz.behzoddev.izohcore.Izohher
import uz.behzoddev.izohcore.Level

class EmptyIzohher : Izohher {
  override fun log(level: Level, tag: String, message: String, throwable: Throwable?) {}
}
