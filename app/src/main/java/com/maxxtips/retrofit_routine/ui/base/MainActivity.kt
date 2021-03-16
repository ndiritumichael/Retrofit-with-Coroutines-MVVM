package com.maxxtips.retrofit_routine.ui.base

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.maxxtips.retrofit_routine.data.api.ApiHelper
import com.maxxtips.retrofit_routine.data.api.RetrofitBuilder
import com.maxxtips.retrofit_routine.data.model.User
import com.maxxtips.retrofit_routine.databinding.ActivityMainBinding
import com.maxxtips.retrofit_routine.ui.main.adapter.MainAdapter
import com.maxxtips.retrofit_routine.ui.main.view.FullDetails
import com.maxxtips.retrofit_routine.ui.main.viewmodel.MainViewModel
import com.maxxtips.retrofit_routine.utils.Status

class MainActivity : AppCompatActivity() ,MainAdapter.OnItemClickListener{
    private lateinit var viewModel: MainViewModel
    private lateinit var adapter: MainAdapter
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setUpViewModel()
        setUpUI()
        setUpObservers()

    }

    private fun setUpObservers() {
       viewModel.getUsers().observe(this){resource ->
           when(resource.status){
               Status.SUCCESS->{
                   binding.retrofitRecyclerview.isVisible = true
                   binding.mainprogressbar.isVisible = false
                   resource.data.let {users ->
                       retrieveUsers(users)

                   }
               }

               Status.ERROR->{
                   binding.mainprogressbar.isVisible = false
                   binding.retrofitRecyclerview.isVisible = false
                   Toast.makeText(this,"Error in Fetching Data",Toast.LENGTH_SHORT).show()
               }
               Status.LOADING->{
                   binding.retrofitRecyclerview.visibility = View.GONE
                   binding.mainprogressbar.visibility = View.VISIBLE
               }
           }

       }
    }

    private fun retrieveUsers(users: List<User>?) {
        adapter.apply {
            addUsers(users)
            notifyDataSetChanged()
        }

    }



    private fun setUpUI() {
        binding.retrofitRecyclerview.layoutManager = LinearLayoutManager(this)
        adapter = MainAdapter(arrayListOf(),this)
        binding.retrofitRecyclerview.addItemDecoration(
            DividerItemDecoration(this,
                (binding.retrofitRecyclerview.layoutManager as LinearLayoutManager).orientation )


        )
        binding.retrofitRecyclerview.adapter = adapter

    }

    private fun setUpViewModel() {
        viewModel = ViewModelProvider(this,ViewModelFactory(ApiHelper(RetrofitBuilder.responseService)))
            .get(MainViewModel::class.java)

    }

    override fun onClickedItem(user: User) {
     //  Toast.makeText(this,"Item of Position $user",Toast.LENGTH_SHORT).show()
        val intent = Intent(this,FullDetails::class.java)
        intent.putExtra("user",user.name)
        intent.putExtra("avatar",user.avatar)
        intent.putExtra("email",user.email)
        startActivity(intent)


    }
}