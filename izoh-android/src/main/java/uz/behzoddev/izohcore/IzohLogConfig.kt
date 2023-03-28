package uz.behzoddev.izohcore

data class IzohLogConfig(
  var logger: IzohLogger? = null,
  var isEnabled: Boolean = false
)
