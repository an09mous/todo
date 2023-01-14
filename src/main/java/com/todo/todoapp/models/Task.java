package com.todo.todoapp.models;

import java.math.BigInteger;
import java.text.SimpleDateFormat;

import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document("tasks")
public class Task {
	@Id
	private ObjectId objectId;
	private long id;
	private long userId;
	private String message;
	private String remindAt;
	private boolean isCompleted;
	private String createdAt;
	
	public Task() 
	{
		super();
	}

	public Task(long id, long userId, String message, String remindAt, boolean isCompleted, String createdAt) 
	{
		super();

		this.id = id;
		this.userId = userId;
		this.message = message;
		this.remindAt = remindAt;
		this.isCompleted = isCompleted;
		this.createdAt = createdAt;
	}

	public String getMessage() 
	{
		return message;
	}

	public void setMessage(String message) 
	{
		this.message = message;
	}

	public String getRemindAt() 
	{
		return remindAt;
	}

	public void setRemindAt(String remindAt) 
	{
		this.remindAt = remindAt;
	}

	public boolean isCompleted() 
	{
		return isCompleted;
	}

	public void setCompleted(boolean isCompleted) 
	{
		this.isCompleted = isCompleted;
	}

	public long getId() 
	{
		return id;
	}
	
	public void setId(long id) 
	{
		this.id = id;
	}

	public long getUserId() 
	{
		return userId;
	}

	public String getCreatedAt() 
	{
		return createdAt;
	}
	
	public void setCreatedAt(String createdAt) 
	{
		this.createdAt = createdAt;
	}
	
	@Override
	public String toString() 
	{
		return "Task [id=" + id + ", userId=" + userId + ", message=" + message + ", remindAt=" + remindAt
				+ ", isCompleted=" + isCompleted + ", createdAt=" + createdAt + "]";
	}
}
