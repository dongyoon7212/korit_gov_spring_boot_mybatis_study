package com.korit.mybatis_study.mapper;

import com.korit.mybatis_study.entity.Todo;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Optional;

@Mapper
public interface TodoMapper {
    Optional<Todo> findTodoByTitle(String title);
    int addTodo(Todo todo);
    List<Todo> getTodoAll();
    Optional<Todo> getTodoByTodoId(Integer todoId);
    int editTodo(Todo todo);
    int removeTodo(Integer todoId);
}
