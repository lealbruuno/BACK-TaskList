package br.com.todolist.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.todolist.entity.TodoEntity;


public interface TodoRepository extends JpaRepository<TodoEntity, Long> {

}
