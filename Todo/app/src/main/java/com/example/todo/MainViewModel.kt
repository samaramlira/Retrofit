package com.example.todo

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.todo.model.Categoria
import com.example.todo.repository.Repository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import retrofit2.Response
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    val repository: Repository
) : ViewModel() {

    private val _responseListCategoria =
        MutableLiveData<Response<List<Categoria>>>()

    val responseListCategoria: LiveData<Response<List<Categoria>>> =
        _responseListCategoria

    init {
        listCategoria()
    }

    fun listCategoria(){

        viewModelScope.launch {
            try{
                val response = repository.listCategoria()
                _responseListCategoria.value = response
            }catch (e: Exception){
                Log.d("ErroRequisicao", e.message.toString())
            }
        }
    }
}