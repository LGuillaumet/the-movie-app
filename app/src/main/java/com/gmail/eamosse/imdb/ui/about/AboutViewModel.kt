package com.gmail.eamosse.imdb.ui.about

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class AboutViewModel : ViewModel() {

    private val _text = MutableLiveData<String>().apply {
        value = "Projet AnneFlix  Kotlin MBDS M2"
    }
    val text: LiveData<String> = _text
}