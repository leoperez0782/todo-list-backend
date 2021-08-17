package com.example.todolistbackend.controllers;

import java.util.stream.Collectors;
import java.util.stream.StreamSupport;
import java.util.Optional;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.todolistbackend.dtos.TodoDTO;
import com.example.todolistbackend.models.Todo;
import com.example.todolistbackend.services.ITodoService;
@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping(path = "/todos")
public class TodoController {

	private Logger logger = LoggerFactory.getLogger(TodoController.class);

	@Autowired
	private ITodoService service;
	@Autowired
	private ModelMapper modelMapper;

	@GetMapping()
	public ResponseEntity<Iterable<TodoDTO>> findAll() {
		try {
			
			Iterable<Todo> todos = service.findAll();
			
			Iterable<TodoDTO> dtosIterable = StreamSupport.stream(todos.spliterator(),false)
					.map(this::convertToDTO)
					.collect(Collectors.toList());
			return new ResponseEntity<Iterable<TodoDTO>>(dtosIterable, HttpStatus.OK);
			
		} catch (Exception e) {
			
			logger.error(e.getMessage());
			return ResponseEntity.internalServerError().build();
		}
	}
	
	
	@GetMapping(value="/find/{id}")
	public ResponseEntity<TodoDTO> findById(@PathVariable(name="id") Long id){
		try {
			Optional<Todo> todo = service.findById(id);
			if(todo.isPresent()) {
				TodoDTO dto = this.convertToDTO(todo.get());
				
				return new ResponseEntity<TodoDTO>(dto, HttpStatus.OK);
			}
			return ResponseEntity.badRequest().build();
		}catch(Exception e) {
			logger.error(e.getMessage());
			return ResponseEntity.internalServerError().build();
		}
		
	}
	
	
	@PostMapping()
	@ResponseBody
	public ResponseEntity<Long> saveTodo(@RequestBody TodoDTO todo) {
		return saveOrUpdate(todo, HttpStatus.CREATED);
	}
	
	@PutMapping()
	@ResponseBody
	public ResponseEntity<Long> updateTodo(@RequestBody TodoDTO todo) {
		return saveOrUpdate(todo, HttpStatus.OK);
	}
	
	@DeleteMapping()
	public ResponseEntity<Object> delete(@RequestBody TodoDTO todo) {

		try {
			if (todo == null || service.findById(todo.getId()).isEmpty()) {
				return ResponseEntity.notFound().build();
			}
			service.deleteById(todo.getId());
		} catch (Exception e) {
			logger.error(e.getMessage());
			return ResponseEntity.badRequest().build();
		}

		return ResponseEntity.noContent().build();
	}
	private ResponseEntity<Long> saveOrUpdate(TodoDTO dto, HttpStatus status) {
		try {
			return new ResponseEntity<Long>(service.save(convertToTodo(dto)), status);
		}catch(Exception e) {
			logger.error(e.getMessage());
			return ResponseEntity.badRequest().build();
		}
		
	}

	private TodoDTO convertToDTO(Todo model) {
		return modelMapper.map(model, TodoDTO.class);
	}
	
	private Todo convertToTodo(TodoDTO dto) {
		return modelMapper.map(dto, Todo.class);
	}
}
