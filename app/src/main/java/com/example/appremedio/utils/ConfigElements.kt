package com.example.appremedio.utils

import android.content.Context
import android.widget.Spinner
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.appremedio.R
import com.example.appremedio.manager.RecycleViewManager
import com.example.appremedio.manager.SpinnerManager

internal object ConfigElements {
    //Configura o dropdown do spinner
    fun setupSpinner(context: Context, elementSpinner: Spinner, items: List<Any>) {
        val adapter =
            SpinnerManager(context, R.layout.text_spinner_item, items)
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        elementSpinner.adapter = adapter
    }

    fun setupRecycler(context: Context, elementRecycler: RecyclerView ,items: List<String> ) {
        val layoutManager = LinearLayoutManager(context)
        elementRecycler.layoutManager = layoutManager
        val meuAdapter = RecycleViewManager(items)
        elementRecycler.adapter = meuAdapter
    }
}