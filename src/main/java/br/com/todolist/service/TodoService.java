package br.com.todolist.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.server.ResponseStatusException;

import br.com.todolist.entity.TodoEntity;
import br.com.todolist.repository.TodoRepository;
import jakarta.validation.Valid;

@Service
public class TodoService {

    @Autowired
    private TodoRepository todoRepository; // Injeção de dependência do repositório TodoRepository

    // Método para criar uma nova tarefa
    public List<TodoEntity> create(@Valid TodoEntity todo) {

        // Valida se o título da tarefa está vazio ou nulo
        if (!StringUtils.hasText(todo.getTitle())) {
            // Lança uma exceção de status HTTP 400 (BAD_REQUEST) caso o título seja inválido
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "O campo 'title' é obrigatório");
        } else {
            // Salva a tarefa no repositório caso o título seja válido
            todoRepository.save(todo);
        }

        return list(); // Retorna a lista atualizada de todas as tarefas
    }

    // Método para listar todas as tarefas
    public List<TodoEntity> list() {
        return todoRepository.findAll(); // Retorna todas as tarefas do repositório
    }

    // Método para buscar uma tarefa pelo seu ID
    public TodoEntity getById(Long id) {
        // Busca a tarefa pelo ID no repositório, lançando uma exceção de status HTTP 404 (NOT_FOUND) se não encontrada
        return todoRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Tarefa não encontrada"));
    }

    // Método para atualizar uma tarefa existente pelo seu ID
    public List<TodoEntity> update(Long id, @Valid TodoEntity todo) {

        // Valida se o título da tarefa está vazio ou nulo
        if (!StringUtils.hasText(todo.getTitle())) {
            // Lança uma exceção de status HTTP 400 (BAD_REQUEST) caso o título seja inválido
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "O campo 'title' é obrigatório");
        } else {
            // Atualiza a tarefa no repositório caso o título seja válido
            todoRepository.findById(id).ifPresentOrElse((existingTodo) -> {
                todo.setId(id); // Define o ID da tarefa a ser atualizada
                todoRepository.save(todo); // Salva a tarefa atualizada no repositório
            }, () -> {
                // Lança uma exceção de status HTTP 404 (NOT_FOUND) caso a tarefa não seja encontrada
                throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Todo não encontrado");
            });
        }

        return list(); // Retorna a lista atualizada de todas as tarefas
    }

    // Método para deletar uma tarefa pelo seu ID
    public List<TodoEntity> delete(Long id) {

        // Busca a tarefa pelo ID no repositório e a deleta, lançando uma exceção de status HTTP 404 (NOT_FOUND) se não encontrada
        todoRepository.findById(id).ifPresentOrElse((existingTodo) -> {
            todoRepository.delete(existingTodo); // Deleta a tarefa do repositório
        }, () -> {
            // Lança uma exceção de status HTTP 404 (NOT_FOUND) caso a tarefa não seja encontrada
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Todo não encontrado");
        });

        return list(); // Retorna a lista atualizada de todas as tarefas
    }
}
