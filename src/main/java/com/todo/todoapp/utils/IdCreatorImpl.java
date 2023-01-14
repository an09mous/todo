package com.todo.todoapp.utils;

import java.util.Random;

import org.springframework.stereotype.Component;

@Component
public class IdCreatorImpl implements IdCreator {

	@Override
	public long createId() {
		Random random = new Random();
		return random.nextLong();
	}

}
