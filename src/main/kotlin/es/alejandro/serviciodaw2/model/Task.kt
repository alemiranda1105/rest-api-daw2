package es.alejandro.serviciodaw2.model

import com.fasterxml.jackson.databind.annotation.JsonSerialize
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer
import org.bson.types.ObjectId
import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.mapping.Document

@Document
data class Task (
    @JsonSerialize(using = ToStringSerializer::class)
    @Id val id: ObjectId = ObjectId.get(),
    val name: String,
    val description: String,
    val completed: Boolean
)

// Class for the body
class NewTask (
    val name: String,
    val description: String
)