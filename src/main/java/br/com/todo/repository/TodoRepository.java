package br.com.todo.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.todo.entity.TodoEntity;


public interface TodoRepository extends JpaRepository<TodoEntity, Long> {

}
