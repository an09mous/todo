package com.todo.todoapp.services;

import org.springframework.stereotype.Service;

@Service
public class ValidationServiceImpl implements ValidationService {

	@Override
	public boolean validateUserId(long userId1, long userId2) {
		return userId1 == userId2;
	}

}
