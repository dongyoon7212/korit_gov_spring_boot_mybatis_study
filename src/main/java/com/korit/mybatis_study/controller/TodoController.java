package com.korit.mybatis_study.controller;

import com.korit.mybatis_study.dto.AddTodoReqDto;
import com.korit.mybatis_study.dto.EditTodoReqDto;
import com.korit.mybatis_study.service.TodoService;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/todo")
public class TodoController {

    @Autowired
    private TodoService todoService;

    @PostMapping("/add")
    ResponseEntity<?> addTodo(@RequestBody AddTodoReqDto addTodoReqDto) {
        return ResponseEntity.ok(todoService.addTodo(addTodoReqDto));
    }

    @GetMapping("/all")
    ResponseEntity<?> getTodoAll() {
        return ResponseEntity.ok(todoService.getTodoAll());
    }

    @GetMapping("/{todoId}")
    ResponseEntity<?> getTodoByTodoId(@PathVariable Integer todoId) {
        return ResponseEntity.ok(todoService.getTodoByTodoId(todoId));
    }

    @PostMapping("/edit")
    ResponseEntity<?> editTodo(@RequestBody EditTodoReqDto editTodoReqDto) {
        return ResponseEntity.ok(todoService.editTodo(editTodoReqDto));
    }

    @PostMapping("/remove")
    ResponseEntity<?> removeTodo(@RequestParam Integer todoId) {
        return ResponseEntity.ok(todoService.removeTodo(todoId));
    }
}
