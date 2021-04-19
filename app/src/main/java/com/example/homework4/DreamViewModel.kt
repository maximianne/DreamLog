package com.example.homework4

import androidx.lifecycle.*
import kotlinx.coroutines.launch
import java.lang.IllegalArgumentException

class DreamViewModel (private val repository: DreamRepository):ViewModel(){

    val allDreams:LiveData<List<Dream>> = repository.allDreams.asLiveData()

    fun insert(dream:Dream) = viewModelScope.launch {
        repository.insert(dream)
    }
    fun delete(id:Int) = viewModelScope.launch {
        repository.delete(id)
    }

//    fun getDream(id:Int) = viewModelScope.launch {
//        repository.getDream(id)
//    }
//    fun getDream(id: Int?) = DreamDAO.getDream(id)
     fun getDream(id: Int):Dream{
        return repository.getDream(id)
    }
}

class DreamViewModelFactory(private val repository: DreamRepository): ViewModelProvider.Factory{
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if(modelClass.isAssignableFrom(DreamViewModel::class.java)){
            return DreamViewModel(repository) as T
        }
        throw IllegalArgumentException("Unknown view model class")
    }
}