package com.example.coroutine.utils

import androidx.recyclerview.widget.DiffUtil
import com.example.coroutine.data.model.User

class DiffUtils(oldList: List<User>, newList: List<User>) :
    DiffUtil.Callback() {

    private val oldList: List<User>
    private val newList: List<User>

    init {
        this.oldList = oldList
        this.newList = newList
    }

    override fun getOldListSize(): Int {
        return oldList.size
    }

    override fun getNewListSize(): Int {
        return newList.size
    }

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        val oldUser: User = oldList[oldItemPosition]
        val newUser: User = newList[newItemPosition]
        return oldUser.id === newUser.id
    }

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        val oldUser: User = oldList[oldItemPosition]
        val newUser: User = newList[newItemPosition]
        return (oldUser.name == newUser.name
                && oldUser.avatar === newUser.avatar && oldUser.email == newUser.email)
    }
}