package com.todo.todoapp.controllers;

import java.sql.Timestamp;
import java.time.Instant;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.todo.todoapp.models.Task;
import com.todo.todoapp.services.TaskService;
import com.todo.todoapp.services.ValidationService;

@RestController
public class TaskController {

	@Autowired
	private TaskService taskService;
	
	@Autowired
	private ValidationService validationService;
	
	@GetMapping("/tasks/{userId}")
	public ResponseEntity<List<Task>> getTasks(
			@PathVariable("userId") long userId, 
			@RequestParam("pageNo") Optional<Integer> pageNo, 
			@RequestParam("pageSize") Optional<Integer> pageSize,
			@RequestParam("completed") Optional<Boolean> completed)
	{
		return ResponseEntity.ok(taskService.getTasks(userId, pageNo, pageSize, completed));
	}
	
	@PostMapping("/tasks/{userId}")
	public ResponseEntity<Task> addTask(@PathVariable("userId") long userId, @RequestBody Task task) 
	{
		if(!validationService.validateUserId(userId, task.getUserId())) 
		{
			return new ResponseEntity(HttpStatus.BAD_REQUEST);
		}
		
		return ResponseEntity.ok(taskService.addTask(userId, task));
	}
	
	@PatchMapping("/tasks/{userId}/{taskId}")
	public ResponseEntity<Task> updateTask(@PathVariable("userId") long userId, @PathVariable("taskId") long taskId, @RequestBody Task task)
	{
		return ResponseEntity.ok(taskService.updateTask(taskId, task));
	}
	
	@DeleteMapping("/tasks/{userId}/{taskId}")
	public ResponseEntity<Boolean> deleteTask(@PathVariable("userId") long userId, @PathVariable("taskId") long taskId)
	{		
		taskService.deleteTask(taskId);
		return ResponseEntity.ok(true);
	}
	
	
}
