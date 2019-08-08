package com.example.pgr202eksamen

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.fragment_history.*

class HistoryFragment : Fragment() {

    private lateinit var userModel: UserViewModel
    private lateinit var recycleViewScore: RecyclerView


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_history, container, false)

    }

    /*
        #TODO
        Find out if the livedata observer is properly destroyed or not
     */

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        recycleViewScore = scoreboardRV
        userModel = ViewModelProviders.of(this).get(UserViewModel::class.java)

        val linearLayoutManager = LinearLayoutManager(
            (activity as MainActivity),
            RecyclerView.HORIZONTAL, false
        )
        recycleViewScore.layoutManager = linearLayoutManager

        userModel.allUsers.observe(this, Observer { users ->
            // Data bind the recycler view
            recycleViewScore.adapter = UserListAdapter(users)
        })

    }


}
