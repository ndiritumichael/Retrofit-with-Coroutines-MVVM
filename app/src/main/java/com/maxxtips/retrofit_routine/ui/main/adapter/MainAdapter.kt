package com.maxxtips.retrofit_routine.ui.main.adapter

import android.content.Intent
import android.util.Log
import android.view.LayoutInflater
import android.view.View

import android.view.ViewGroup
import androidx.core.content.ContextCompat.startActivity
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.bumptech.glide.Glide

import com.maxxtips.retrofit_routine.data.model.User
import com.maxxtips.retrofit_routine.databinding.RecyclerlayoutBinding
import com.maxxtips.retrofit_routine.ui.main.view.FullDetails


class MainAdapter(private val userslist : ArrayList<User>,
private val listener : OnItemClickListener
                  ) :RecyclerView.Adapter<MainAdapter.MainViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MainViewHolder {
return MainViewHolder(RecyclerlayoutBinding.inflate(LayoutInflater.from(parent.context),parent,false))
    }

    override fun onBindViewHolder(holder: MainViewHolder, position: Int) {
        holder.bind(userslist[position])
    }

    override fun getItemCount() = userslist.size
    fun addUsers(users: List<User>?) {
        this.userslist.apply {
            clear()
            if (users != null) {
                addAll(users)
            }
        }

    }

   inner class MainViewHolder(val binding: RecyclerlayoutBinding) : RecyclerView.ViewHolder(binding.root),View.OnClickListener {
        fun bind(user: User) {
            binding.textViewUserEmail.text = user.email
            binding.textViewUserName.text = user.name
           binding.imageViewAvatar.load(user.avatar)

            Log.d("MainAdapter","the link is ${user.avatar}")


        }
        init {
            binding.root.setOnClickListener(this)
        }

        override fun onClick(p0: View?) {
            val  position = adapterPosition
            if (position!= RecyclerView.NO_POSITION) {
                listener.onClickedItem(userslist[position])
            }
         // startactivity()
        }

        private fun startactivity() {
            val intent = Intent(binding.root.context,FullDetails::class.java)
           // intent.putExtra("name")
            startActivity(binding.root.context,intent,null)
        }


    }
    interface OnItemClickListener{
        fun onClickedItem(user: User)
    }
}
