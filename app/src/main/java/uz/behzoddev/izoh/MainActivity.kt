package uz.behzoddev.izoh

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import uz.behzoddev.izohcore.izoh

class MainActivity : AppCompatActivity() {
  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.activity_main)

    izoh { "Testing the izoh" }
  }
}
