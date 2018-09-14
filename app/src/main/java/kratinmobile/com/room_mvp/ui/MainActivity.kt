package kratinmobile.com.roomdbexample.ui

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import kratinmobile.com.room_mvp.R
import kratinmobile.com.room_mvp.network.NetworkApi
import javax.inject.Inject



class MainActivity : AppCompatActivity() {
    @Inject
    var networkApi: NetworkApi? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }
}
