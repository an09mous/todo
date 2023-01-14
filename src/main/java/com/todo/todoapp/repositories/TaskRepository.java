package com.todo.todoapp.repositories;

import java.util.List;

import org.springframework.data.mongodb.repository.Aggregation;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import com.todo.todoapp.models.Task;

public interface TaskRepository extends MongoRepository<Task, Integer>{
	@Aggregation(pipeline = {
			"{ '$match': { 'userId' : ?0 } }", 
			"{ '$sort' : {'createdAt' : -1 } }", 
			"{ '$skip' : ?1 }", 
			"{ '$limit' : ?2 }"
	})
	List<Task> findAll(long userId, int skip, int limit);
	
	@Aggregation(pipeline = {
			"{ '$match': { 'userId' : ?0, 'isCompleted' : ?3 } }", 
			"{ '$sort' : { 'createdAt' : -1 } }", 
			"{ '$skip' : ?1 }", 
			"{ '$limit' : ?2 }"
	})
	List<Task> findAll(long userId, int skip, int limit, boolean completed);
	
	@Query("{id : ?0}")
	Task findById(long id);
	
	
	

}
