package com.example.pgr202eksamen

import android.database.sqlite.SQLiteConstraintException
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.fragment_history.*

class HistoryFragment : Fragment() {

    private lateinit var userModel: UserViewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val viewOfLayout = inflater.inflate(R.layout.fragment_history, container, false)
        val addUserButton: Button = viewOfLayout.findViewById(R.id.addUserBtn)
        val newUserText: EditText = viewOfLayout.findViewById(R.id.newUserText)
        userModel = ViewModelProviders.of(this).get(UserViewModel::class.java)
        val recycleViewScore: RecyclerView = viewOfLayout.findViewById(R.id.scoreboardRV)

        val linearLayoutManager = LinearLayoutManager(
            (activity as MainActivity),
            RecyclerView.VERTICAL, false
        )
        recycleViewScore.layoutManager = linearLayoutManager

        userModel.allUsers.observe(this, Observer { users ->
            recycleViewScore.adapter = UserListAdapter(users)
        })


        fun createNewUser() {
            val newUser = User(newUserText.text.toString(), 0)
            try {
                userModel.insert(newUser)
            } catch (e: SQLiteConstraintException) {
                Toast.makeText(this.context, "Username is not unique", Toast.LENGTH_LONG).show()
            }


        }
        addUserButton.setOnClickListener {
            createNewUser()
            recycleViewScore.adapter?.notifyDataSetChanged()
            Log.d("onClick addUser", "clicked")
        }



        return viewOfLayout

    }


}
