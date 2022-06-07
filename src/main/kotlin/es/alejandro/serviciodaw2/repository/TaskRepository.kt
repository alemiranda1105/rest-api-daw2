package es.alejandro.serviciodaw2.repository

import es.alejandro.serviciodaw2.model.Task
import org.bson.types.ObjectId
import org.springframework.data.mongodb.repository.MongoRepository


interface TaskRepository: MongoRepository<Task, String> {
    fun findOneById(id: ObjectId): Task
    fun save(task: Task): Task
    override fun deleteAll()
}