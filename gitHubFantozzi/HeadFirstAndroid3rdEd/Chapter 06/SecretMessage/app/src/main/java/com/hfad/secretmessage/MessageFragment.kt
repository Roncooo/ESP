package com.hfad.secretmessage

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController

class MessageFragment : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_message, container, false)
        val nextButton = view.findViewById<Button>(R.id.next)
        nextButton.setOnClickListener {
            val message = view.findViewById<EditText>(R.id.message).text.toString()
            val action = MessageFragmentDirections.actionMessageFragmentToSecretMessageFragment(message)
            view.findNavController()
                .navigate(action)
        }
        return view
    }
}
