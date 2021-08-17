package com.example.todolistbackend.services;

import java.util.Optional;

import com.example.todolistbackend.models.Todo;

public interface ITodoService {

	Iterable<Todo> findAll();
	Long save(Todo todo);
	void deleteById(Long id);
	Optional<Todo> findById(Long id);
}
