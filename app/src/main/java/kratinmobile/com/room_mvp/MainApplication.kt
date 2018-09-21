package kratinmobile.com.room_mvp

import android.app.Application
import android.content.res.Configuration
import com.androidnetworking.AndroidNetworking

class MainApplication :Application() {
    override fun onConfigurationChanged(newConfig: Configuration?) {
        super.onConfigurationChanged(newConfig)
        AndroidNetworking.initialize(applicationContext)
    }
}