package uz.behzoddev.izoh

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import uz.behzoddev.izohandroid.debug
import uz.behzoddev.izohandroid.failure
import uz.behzoddev.izohandroid.success

class MainActivity : AppCompatActivity() {
  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.activity_main)

    success { "There is a success log function" }

    failure { "There is a failure log function" }

    debug { "There is a debug log function" }
  }
}
