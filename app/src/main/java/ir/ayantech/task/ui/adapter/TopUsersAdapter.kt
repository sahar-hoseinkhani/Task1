package ir.ayantech.task.ui.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.AppCompatImageView
import androidx.appcompat.widget.AppCompatTextView
import androidx.recyclerview.widget.RecyclerView
import ir.ayantech.task.R
import ir.ayantech.task.api.Data
import ir.ayantech.task.helper.loadUrl

class TopUsersAdapter(private val topUsersList: List<Data>) :
    RecyclerView.Adapter<TopUsersAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val v = LayoutInflater.from(parent.context).inflate(R.layout.row_top_user, parent, false)
        return ViewHolder(v)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bindItems(topUsersList[position])
    }

    override fun getItemCount(): Int {
        return topUsersList.size
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        fun bindItems(user: Data) {
            val nameTv = itemView.findViewById(R.id.nameTv) as AppCompatTextView
            val emailTv = itemView.findViewById(R.id.emailTv) as AppCompatTextView
            val avatarIv = itemView.findViewById(R.id.avatarIv) as AppCompatImageView

            nameTv.text = "${user.first_name} ${user.last_name}"
            emailTv.text = user.email
            avatarIv.loadUrl(user.avatar)
        }
    }
}