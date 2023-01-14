package com.todo.todoapp.services;

import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.todo.todoapp.models.Task;
import com.todo.todoapp.repositories.TaskRepository;
import com.todo.todoapp.utils.IdCreator;
import static com.todo.todoapp.constants.PageConstants.*;

@Service
public class TaskServiceImpl implements TaskService {
	@Autowired
	private TaskRepository taskRepository;
	@Autowired
	private IdCreator idCreator;

	@Override
	public List<Task> getTasks(long userId, Optional<Integer> pageNo, Optional<Integer> pageSize, Optional<Boolean> completed) 
	{
		int limit = pageSize.isEmpty() ? DEFAULT_PAGE_SIZE : Math.min(pageSize.get(), MAX_PAGE_SIZE);
		int offset = ((pageNo.isEmpty() ?  DEFAULT_PAGE_NO: pageNo.get()) - 1) * limit;
		
		if(!completed.isEmpty()) 
		{
			return taskRepository.findAll(
					userId,
					offset,
					limit,
					completed.get());
		}
		
		return taskRepository.findAll(
				userId, 
				offset, 
				limit);
	}

	@Override
	public Task getTaskById(long userId, long taskId) 
	{
		return taskRepository.findById(taskId);
	}

	@Override
	public Task addTask(long userId, Task task) 
	{
		task.setId(idCreator.createId());
		task.setCreatedAt(new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new java.util.Date()));
		
		taskRepository.save(task);
		return task;
	}

	@Override
	public Task updateTask(long taskId, Task updatedTask) 
	{
		Task originalTask = taskRepository.findById(taskId);
		originalTask.setCompleted(updatedTask.isCompleted());
		taskRepository.save(originalTask);
		
		return originalTask;
	}

	@Override
	public void deleteTask(long taskId) 
	{
		taskRepository.delete(taskRepository.findById(taskId));
	}

}
