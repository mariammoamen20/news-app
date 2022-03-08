package com.example.news.settings

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.AutoCompleteTextView
import android.widget.Spinner
import androidx.fragment.app.Fragment
import com.example.news.R
import java.util.*

class SettingsFragment : Fragment() {
    lateinit var languge_text : AutoCompleteTextView
    lateinit var local_language: Locale
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_settings, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        languge_text = view.findViewById(R.id.language_label)
        val language = resources.getStringArray(R.array.language_array)
        val array_adapter = ArrayAdapter(requireContext(),R.layout.dropdown_item,language)
        languge_text.setAdapter(array_adapter)

        languge_text.onItemSelectedListener= object : AdapterView.OnItemSelectedListener{
            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View?,
                position: Int,
                id: Long
            ) {
                when (position) {
                    0 -> {
                    }
                    1 -> setLocal("ar")
                    1 -> setLocal("en")
                }
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {
                TODO("Not yet implemented")
            }

        }

    }
    fun setLocal(language: String) {
        local_language = Locale(language)
        var res = resources
        var dm = res.displayMetrics
        var conf = res.configuration
        conf.locale = local_language
        res.updateConfiguration(conf, dm)

    }
}

/*
package com.example.news.settings

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Spinner
import androidx.fragment.app.Fragment
import com.example.news.R
import java.util.*

class SettingsFragment : Fragment() {
    var languge_lits = ArrayList<String>()
    lateinit var spinner_languge: Spinner
    lateinit var local_language: Locale
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_settings, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        spinner_languge = view.findViewById(R.id.spinner_languge)

        languge_lits.add("English")
        languge_lits.add("Arabic")


        var adapter = ArrayAdapter(
            requireContext(),
            androidx.appcompat.R.layout.support_simple_spinner_dropdown_item,
            languge_lits
        )
        spinner_languge.adapter = adapter

        spinner_languge.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View?,
                position: Int,
                id: Long
            ) {
                when (position) {
                    0 -> {
                    }
                    1 -> setLocal("ar")
                }

            }

            override fun onNothingSelected(parent: AdapterView<*>?) {
                TODO("Not yet implemented")
            }

        }


    }

    fun setLocal(language: String) {
        local_language = Locale(language)
        var res = resources
        var dm = res.displayMetrics
        var conf = res.configuration
        conf.locale = local_language
        res.updateConfiguration(conf, dm)

    }
}
* */