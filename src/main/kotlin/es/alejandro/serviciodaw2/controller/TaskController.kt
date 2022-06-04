package es.alejandro.serviciodaw2.controller

import es.alejandro.serviciodaw2.model.NewTask
import es.alejandro.serviciodaw2.model.Task
import es.alejandro.serviciodaw2.repository.TaskRepository
import org.bson.types.ObjectId
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.http.ResponseEntity.BodyBuilder
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/tasks")
class TaskController(
    private val db: TaskRepository
) {

    @GetMapping
    fun getAllTasks(): ResponseEntity<List<Task>> {
        val tasks = db.findAll()
        return ResponseEntity.ok(tasks)
    }

    @GetMapping("/{id}")
    fun show(@PathVariable("id") id: String): ResponseEntity<Task> {
        val task = db.findOneById(ObjectId(id))
        return ResponseEntity.ok(task)
    }

    @PostMapping
    fun create(@RequestBody request: NewTask): ResponseEntity<Task> {
        print(request)
        val task = db.save(Task(
            name = request.name,
            description = request.description,
            completed = false
        ))
        return ResponseEntity(task, HttpStatus.CREATED)
    }

    @PutMapping("/{id}")
    fun update(@RequestBody request: NewTask, @PathVariable("id") id: String): ResponseEntity<Task> {
        val task = db.findOneById(ObjectId(id))
        val updatedTask = db.save(Task(
            id = task.id,
            name = task.name,
            description = task.description,
            completed = task.completed
        ))
        return ResponseEntity.ok(updatedTask)
    }

    @DeleteMapping("/{id}")
    fun delete(@PathVariable("id") id: String): BodyBuilder {
        db.deleteById(id)
        return ResponseEntity.ok()
    }
}