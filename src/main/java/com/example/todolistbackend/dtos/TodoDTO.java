package com.example.todolistbackend.dtos;

import java.time.LocalDate;

import com.example.todolistbackend.models.Todo.Status;

public class TodoDTO {
	private Long id;
	private String description;
	private Status status;
	private LocalDate expireDate;
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public Status getStatus() {
		return status;
	}
	public void setStatus(Status status) {
		this.status = status;
	}
	public LocalDate getExpireDate() {
		return expireDate;
	}
	public void setExpireDate(LocalDate expireDate) {
		this.expireDate = expireDate;
	}
	
	
}
