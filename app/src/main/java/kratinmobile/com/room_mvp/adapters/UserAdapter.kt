package kratinmobile.com.room_mvp.adapters

import android.support.v7.widget.AppCompatTextView
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kratinmobile.com.room_mvp.R
import kratinmobile.com.room_mvp.entity.User

  class UserAdapter(val userList: List<User>) : RecyclerView.Adapter<UserAdapter.UserViewHolder>() {
    override fun onBindViewHolder(holder: UserViewHolder, position: Int) {
        holder.bindItems(userList[position])    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserAdapter.UserViewHolder{
        val v = LayoutInflater.from(parent.context).inflate(R.layout.user_list_items, parent, false)
        return UserViewHolder(v)
    }

    override fun getItemCount(): Int {
        return  userList.size
    }


    class UserViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){
        fun bindItems(user: User){
            val firstname = itemView.findViewById(R.id.first_name_txt) as AppCompatTextView
            val lasttname  = itemView.findViewById(R.id.last_name_txt) as AppCompatTextView
            val age = itemView.findViewById(R.id.age_txt) as AppCompatTextView

            firstname.text = user.firstName
            lasttname.text = user.lastName
            age.text=user.age.toString()
        }
    }
}