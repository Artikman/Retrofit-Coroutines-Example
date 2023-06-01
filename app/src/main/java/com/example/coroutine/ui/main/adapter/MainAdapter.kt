package com.example.coroutine.ui.main.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.coroutine.data.model.User
import com.example.firebase.databinding.ItemLayoutBinding

class MainAdapter(val users: ArrayList<User>) :
    RecyclerView.Adapter<MainAdapter.DataViewHolder>() {

    class DataViewHolder(private val itemBinding: ItemLayoutBinding) :
        RecyclerView.ViewHolder(itemBinding.root) {
        fun bind(user: User) {
            itemBinding.apply {
                textViewUserName.text = user.name
                textViewUserEmail.text = user.email
                Glide.with(imageViewAvatar.context)
                    .load(user.avatar)
                    .into(imageViewAvatar)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DataViewHolder {
        val itemBinding =
            ItemLayoutBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return DataViewHolder(itemBinding)
    }

    override fun getItemCount(): Int = users.size

    override fun onBindViewHolder(holder: DataViewHolder, position: Int) {
        holder.bind(users[position])
    }

    fun addUsers(users: List<User>) {
        this.users.apply {
            clear()
            addAll(users)
        }
    }
}