package com.gmail.eamosse.imdb.ui.home

import androidx.lifecycle.*
import com.gmail.eamosse.idbdata.data.*
import com.gmail.eamosse.idbdata.repository.MovieRepository
import com.gmail.eamosse.idbdata.utils.Result
import com.gmail.eamosse.imdb.data.Resource
import io.reactivex.Single
import io.reactivex.disposables.Disposable
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch

class HomeViewModel(private val repository: MovieRepository) : ViewModel() {

    private val _token: MutableLiveData<Token> = MutableLiveData()
    val token: LiveData<Token>
        get() = _token


    private val _previews: MutableLiveData<List<Preview>> = MutableLiveData()
    val previews: MutableLiveData<List<Preview>>
        get() = _previews

    private val _populars: MutableLiveData<List<Popular>> = MutableLiveData()
    val populars: MutableLiveData<List<Popular>>
        get() = _populars

    private var currentPage = 1
    private var lastPage = 1

    private val _categories: MutableLiveData<List<Category>> = MutableLiveData()
    val categories: LiveData<List<Category>>
        get() = _categories

    private val _movie: MutableLiveData<Movie> = MutableLiveData()
    val movie: LiveData<Movie>
        get() = _movie

    private val _error: MutableLiveData<String> = MutableLiveData()
    val error: LiveData<String>
        get() = _error

    /**
     * Block d'initialisation du viewmodel
     * On en profite (pour l'instant) pour récupérer le token utilisateur
     */
    init {
        /**
         * getToken est une méthode suspendue, par conséquent elle doit être appelée dans une coroutine
         * De plus, le repository accède à une source de données (API ou BDD); il faut appeler la méthode
         * dans un thread secondaire
         *
         * On utilise l'attribut viewModelScope qui est une coroutine lié au cycle de vie du VM
         * Puis on exécute la méthode getToken dans un [Dispatchers.IO], soit dans un thread secondaire
         */
        viewModelScope.launch(Dispatchers.IO) {
            when (val result = repository.getToken()) {
                is Result.Succes -> {
                    _token.postValue(result.data)
                }
                is Result.Error -> {
                    _error.postValue(result.message)
                }
            }
        }
    }



        fun getMovieById(id: Int) {
            viewModelScope.launch(Dispatchers.IO) {
                when (val result = repository.getMovieById(id)) {
                    is Result.Succes -> {
                        _movie.postValue(result.data)
                    }
                    is Result.Error -> {
                        _error.postValue(result.message)
                    }
                }
            }
        }
    private fun <T, X : List<T>> MutableLiveData<MutableList<T>>.appendList(list: X) {
        val newList = this.value ?: mutableListOf()
        newList.addAll(list)
        this.value = newList
    }

        fun addNewPreviews(id: Int, page: Int) {
            viewModelScope.launch(Dispatchers.IO) {
                when (val result = repository.getPreviews(id, page)) {
                    is Result.Succes -> {
                        val dis = _previews.value;
                        dis.let {
                            val movies =
                                listOf(*it!!.toTypedArray(), *result.data.toTypedArray());
                            _previews.postValue(movies)
                        }

                    }
                    is Result.Error -> {
                        _error.postValue(result.message)
                    }
                }

            }
        }

    fun addNewPopulars(page: Int) {
        viewModelScope.launch(Dispatchers.IO) {
            when (val result = repository.getPopulars(page)) {
                is Result.Succes -> {
                    val dis = _populars.value;
                    dis.let {
                        val movies =
                            listOf(*it!!.toTypedArray(), *result.data.toTypedArray());
                        _populars.postValue(movies)
                    }

                }
                is Result.Error -> {
                    _error.postValue(result.message)
                }
            }

        }
    }


    fun getCategories() {
            viewModelScope.launch(Dispatchers.IO) {
                when (val result = repository.getCategories()) {
                    is Result.Succes -> {
                        _categories.postValue(result.data)
                    }
                    is Result.Error -> {
                        _error.postValue(result.message)
                    }
                }
            }
        }

        fun getPreviews(categoryId: Int, page: Int) {
            viewModelScope.launch(Dispatchers.IO) {
                when (val result = repository.getPreviews(categoryId, page)) {
                    is Result.Succes -> {
                        _previews.postValue(result.data)
                    }
                    is Result.Error -> {
                        _error.postValue(result.message)
                    }
                }
            }
        }

    fun getPopulars(page: Int) {
        viewModelScope.launch(Dispatchers.IO) {
            when (val result = repository.getPopulars(page)) {
                is Result.Succes -> {
                    _populars.postValue(result.data)
                }
                is Result.Error -> {
                    _error.postValue(result.message)
                }
            }
        }
    }


    }