package com.hfad.secretmessage

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment


class SecretMessageFragment : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_secret_message, container, false)
        val tv: TextView = view!!.findViewById(R.id.encrypted_message)
        val message: String? = this.arguments?.getString("message")
        Log.d("secretMessageFragment", message.toString())
        tv.text = message?.reversed() ?: "no message inserted"
        return view
    }
}
