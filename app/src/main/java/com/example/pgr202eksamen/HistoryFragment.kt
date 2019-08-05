package com.example.pgr202eksamen

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.fragment_history.view.*

class HistoryFragment : Fragment() {

    private lateinit var userViewModel: UserViewModel


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val viewOfLayout = inflater.inflate(R.layout.fragment_history, container, false)

        userViewModel = ViewModelProviders.of(this).get(UserViewModel::class.java)
        val scoreBoard: RecyclerView = viewOfLayout.scoreboardRV
        val adapter = UserListAdapter((activity as MainActivity))
        scoreBoard.adapter = adapter
        scoreBoard.layoutManager = LinearLayoutManager((activity as MainActivity))

        return viewOfLayout


    }

}
