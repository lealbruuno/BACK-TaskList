package br.com.todo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.todo.entity.TodoEntity;
import br.com.todo.service.TodoService;

@RestController
@RequestMapping("/todo")
@CrossOrigin
public class TodoController {

  @Autowired
  private TodoService todoService;

  @PostMapping
  List<TodoEntity> create(@RequestBody TodoEntity todo) {
    return todoService.create(todo);
  }

  @GetMapping
  List<TodoEntity> list() {
    return todoService.list();
  }

  @GetMapping("/{id}")
  TodoEntity getTodoById(@PathVariable Long id) {
    return todoService.getById(id);
  }

  @PutMapping("{id}")
  List<TodoEntity> update(@PathVariable Long id, @RequestBody TodoEntity todo) {
    return todoService.update(id, todo);
  }

  @DeleteMapping("{id}")
  List<TodoEntity> delete(@PathVariable Long id) {
    return todoService.delete(id);
  }

}
