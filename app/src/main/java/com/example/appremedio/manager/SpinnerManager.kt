package com.example.appremedio.manager

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.TextView
import com.example.appremedio.R

class SpinnerManager(context: Context, resource: Int, items: List<Any>) :
    ArrayAdapter<Any>(context, resource, items) {
    private var contextClasse: Context
    private var itemClasse: List<Any>
    private var resourceClasse: Int = 0

    init {
        contextClasse = context
        itemClasse = items
        resourceClasse = resource
    }

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        return createItemView(position, convertView, parent)
    }

    override fun getDropDownView(position: Int, convertView: View?, parent: ViewGroup): View {
        return createItemView(position, convertView, parent)
    }

    private fun createItemView(position: Int, convertView: View?, parent: ViewGroup): View {
        val inflater =
            contextClasse.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as (LayoutInflater)
        val view = inflater.inflate(resourceClasse, parent, false)

        val textView = view.findViewById(R.id.idItemSpinner) as TextView
        textView.text = itemClasse[position].toString()

        return view
    }

}