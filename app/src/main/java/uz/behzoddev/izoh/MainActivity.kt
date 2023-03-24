package uz.behzoddev.izoh

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import uz.behzoddev.izohcore.IzohLog
import uz.behzoddev.izohcore.Platform
import uz.behzoddev.izohcore.extension.debug
import uz.behzoddev.izohcore.extension.izoh

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        IzohLog.install {
            logger(platform = Platform.ANDROID)
            enabled()
        }

        izoh { "" }
        debug { "Testing" }
    }
}