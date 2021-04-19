package com.example.homework4

import kotlinx.coroutines.flow.Flow

class DreamRepository(private val dreamDAO: DreamDAO) {
    val allDreams: Flow<List<Dream>> = dreamDAO.getAllDreams();

    suspend fun insert(dream:Dream){
        dreamDAO.insert(dream)
    }
    suspend fun delete(id: Int){
        dreamDAO.deleteDream(id)
    }
     fun getDream(id: Int):Dream{
        return dreamDAO.getDream(id)
    }
    suspend fun update(dream:Dream){
        dreamDAO.update(dream.id.hashCode(),dream.title,dream.content,dream.reflection, dream.emotion)
    }
}