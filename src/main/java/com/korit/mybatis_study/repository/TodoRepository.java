package com.korit.mybatis_study.repository;

import com.korit.mybatis_study.entity.Todo;
import com.korit.mybatis_study.mapper.TodoMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class TodoRepository {

    @Autowired
    private TodoMapper todoMapper;

    public Optional<Todo> findTodoByTitle(String title) {
        return todoMapper.findTodoByTitle(title);
    }

    public Optional<Todo> addTodo(Todo todo) {
        try {
            todoMapper.addTodo(todo);
        } catch (DuplicateKeyException e) {
            return Optional.empty();
        }
        return Optional.of(todo);
    }

    public List<Todo> getTodoAll() {
        return todoMapper.getTodoAll();
    }

    public Optional<Todo> getTodoByTodoId(Integer todoId) {
        return todoMapper.getTodoByTodoId(todoId);
    }

    public int editTodo(Todo todo) {
        return todoMapper.editTodo(todo);
    }

    public int removeTodo(Integer todoId) {
        return todoMapper.removeTodo(todoId);
    }
}
