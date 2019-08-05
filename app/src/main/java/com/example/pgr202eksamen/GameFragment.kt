package com.example.pgr202eksamen

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment

class GameFragment(val mode: String) : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        val viewOfLayout = inflater.inflate(R.layout.fragment_sign_up, container, false)

        return viewOfLayout

    }

}
