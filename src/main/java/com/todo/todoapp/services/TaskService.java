package com.todo.todoapp.services;

import java.util.List;
import java.util.Optional;

import com.todo.todoapp.models.Task;

public interface TaskService {
	public List<Task> getTasks(long userId, Optional<Integer> pageNo, Optional<Integer> pageSize, Optional<Boolean> completed);
	public Task getTaskById(long userId, long taskId);
	public Task addTask(long userId, Task task);
	public Task updateTask(long taskId, Task updatedTask);
	public void deleteTask(long taskId);
}
