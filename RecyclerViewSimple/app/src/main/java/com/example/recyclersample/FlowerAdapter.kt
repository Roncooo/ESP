/*
 * Copyright (C) 2020 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.example.recyclersample

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class FlowerAdapter(private val flowerList: Array<String>, private val flowerNumber: Array<String>) :
    RecyclerView.Adapter<FlowerAdapter.FlowerViewHolder>() {

    // companion object{ private val TAG = "MyActiviy" }
    private val mTAG = this::class.simpleName

    // Describes an item view and its place within the RecyclerView
    class FlowerViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val flowerTextView: TextView = itemView.findViewById(R.id.flower_text)
        private val flowerNumberView: TextView = itemView.findViewById(R.id.flower_number)

        fun bind(word: String, number: String) {
            flowerTextView.text = word
            flowerNumberView.text = number
        }
    }

    // Returns a new ViewHolder
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FlowerViewHolder {
        Log.v(mTAG, "Creato view holder")
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.flower_item, parent, false)

        return FlowerViewHolder(view)
    }

    // Returns size of data list
    override fun getItemCount(): Int {
        return flowerList.size
    }

    // Displays data at a certain position
    override fun onBindViewHolder(holder: FlowerViewHolder, position: Int) {
        Log.v(mTAG, "Riempimi di dati")
        holder.bind(flowerList[position], flowerNumber[position])
    }
}
