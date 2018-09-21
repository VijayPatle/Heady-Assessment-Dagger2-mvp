package kratinmobile.com.roomdbexample.ui

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.AppCompatButton
import android.support.v7.widget.AppCompatEditText
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.widget.LinearLayout
import kratinmobile.com.room_mvp.R
import kratinmobile.com.room_mvp.adapters.UserAdapter
import kratinmobile.com.room_mvp.entity.User
import kratinmobile.com.room_mvp.network.NetworkApi
import kratinmobile.com.room_mvp.util.DatabaseClient
import javax.inject.Inject



class MainActivity : AppCompatActivity() {
    val TAG = MainActivity::class.java!!.getSimpleName()
    private lateinit  var firstName:AppCompatEditText
    private lateinit  var lastName:AppCompatEditText
    private lateinit  var age:AppCompatEditText
    private lateinit  var submit:AppCompatButton
    private lateinit  var recyclerView:RecyclerView
    var userlist: ArrayList<User> = ArrayList()
    @Inject
    var networkApi: NetworkApi? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        findViewById()
        getAllUsers()
        submit?.setOnClickListener{
              val user= User()
            user.firstName=firstName?.text.toString()
            user.lastName=lastName?.text.toString()
            user.age=age?.text.toString().toInt()
            DatabaseClient.getInstance(this).insertPositionAsync(user, DatabaseClient.DatabaseHandler { success, result ->
                if (success){
                    clearTextField()
                    Log.d(TAG,"gdaes")
                    getAllUsers()
                }
            })
        }


    }

    private lateinit var adapter: UserAdapter

    private fun setupUserListAdapter() {

        //adding a layoutmanager
        recyclerView.layoutManager = LinearLayoutManager(this, LinearLayout.VERTICAL, false)
        //crating an arraylist to store users using the data class user
         adapter = UserAdapter(userlist)
        //now adding the adapter to recyclerview
        recyclerView.adapter = adapter
    }

    private fun clearTextField() {
        firstName.setText("")
        lastName.setText("")
        age.setText("")
    }

    private fun getAllUsers() {
        DatabaseClient.getInstance(this).getAllUsers(DatabaseClient.DatabaseHandler { success, result ->
            if (success){
                Log.d(TAG,"gdaes"+result)
                userlist= result as ArrayList<User>
                setupUserListAdapter()
            }
        })
    }

    private fun findViewById() {
        firstName = findViewById(R.id.first_name) as AppCompatEditText
        lastName= findViewById(R.id.last_name) as AppCompatEditText
        age = findViewById(R.id.age) as AppCompatEditText
        submit = findViewById(R.id.submit) as AppCompatButton
         recyclerView = findViewById(R.id.user_rcv) as RecyclerView
    }


}
