package com.example.todolistbackend.daos;

import org.springframework.data.repository.CrudRepository;

import com.example.todolistbackend.models.Todo;

public interface TodoDAO extends CrudRepository<Todo, Long> {

}
