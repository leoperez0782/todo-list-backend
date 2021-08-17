package com.example.todolistbackend.models;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.Data;



@Data
@Entity
public class Todo {
	
	public enum Status{ 
		COMPLETE("Complete"),
		PENDING("Pending");
		
		private String value;
		
		Status(String value){
			this.value = value;
		}
		public String getValue() {
			return this.value;
		}
	}

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	@Column(name="todo_description")
	private String description;
	@Column(name="todo_status")
	@Enumerated
	private Status status;
	@JsonFormat(pattern = "yyyy-MM-dd", shape = JsonFormat.Shape.STRING)
	private LocalDate expireDate;
}
