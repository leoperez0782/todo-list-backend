package com.example.todolistbackend.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.todolistbackend.daos.TodoDAO;
import com.example.todolistbackend.models.Todo;

@Service
public class TodoServiceImp implements ITodoService {

	@Autowired
	private TodoDAO dao;
	@Override
	public Iterable<Todo> findAll() {
		return this.dao.findAll();
	}

	@Override
	public Long save(Todo todo) {
		Todo saved = dao.save(todo);
		return saved.getId();
	}

	@Override
	public void deleteById(Long id) {
		this.dao.deleteById(id);

	}

	@Override
	public Optional<Todo> findById(Long id) {
		return this.dao.findById(id);
	}

}
