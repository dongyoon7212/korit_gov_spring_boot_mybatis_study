package com.korit.mybatis_study.service;

import com.korit.mybatis_study.dto.AddTodoReqDto;
import com.korit.mybatis_study.dto.ApiRespDto;
import com.korit.mybatis_study.dto.EditTodoReqDto;
import com.korit.mybatis_study.entity.Todo;
import com.korit.mybatis_study.repository.TodoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class TodoService {

    @Autowired
    private TodoRepository todoRepository;

    public ApiRespDto<?> addTodo(AddTodoReqDto addTodoReqDto) {
        //title 중복검사
        Optional<Todo> foundTodo = todoRepository.findTodoByTitle(addTodoReqDto.getTitle());
        if (foundTodo.isPresent()) {
            return new ApiRespDto<>("failed", "이미 존재하는 제목입니다.", null);
        }
        //추가
        Optional<Todo> todo = todoRepository.addTodo(addTodoReqDto.toEntity());
        if (todo.isEmpty()) {
            return new ApiRespDto<>("failed", "문제가 발생했습니다.", null);
        }
        return new ApiRespDto<>("success", "Todo가 정상적으로 추가되었습니다.", todo.get());
    }

    public ApiRespDto<?> getTodoAll() {
        return new ApiRespDto<>("success", "전체 조회가 완료되었습니다.", todoRepository.getTodoAll());
    }

    public ApiRespDto<?> getTodoByTodoId(Integer todoId) {
        Optional<Todo> foundTodo = todoRepository.getTodoByTodoId(todoId);
        if (foundTodo.isEmpty()) {
            return new ApiRespDto<>("failed", "해당 Todo는 존재하지 않습니다.", null);
        }
        return new ApiRespDto<>("success", "단건 조회가 완료되었습니다.", foundTodo.get());
    }

    public ApiRespDto<?> editTodo(EditTodoReqDto editTodoReqDto) {
        Optional<Todo> foundTodo = todoRepository.getTodoByTodoId(editTodoReqDto.getTodoId());
        if (foundTodo.isEmpty()) {
            return new ApiRespDto<>("failed", "해당 Todo는 존재하지 않습니다.", null);
        }
        int result = todoRepository.editTodo(editTodoReqDto.toEntity());
        if (result != 1) {
            return new ApiRespDto<>("failed", "문제가 발생했습니다.", null);
        }
        return new ApiRespDto<>("success", "수정이 완료되었습니다.", null);
    }

    public ApiRespDto<?> removeTodo(Integer todoId) {
        Optional<Todo> foundTodo = todoRepository.getTodoByTodoId(todoId);
        if (foundTodo.isEmpty()) {
            return new ApiRespDto<>("failed", "해당 Todo는 존재하지 않습니다.", null);
        }
        int result = todoRepository.removeTodo(todoId);
        if (result != 1) {
            return new ApiRespDto<>("failed", "문제가 발생했습니다.", null);
        }
        return new ApiRespDto<>("success", "삭제가 완료되었습니다.", null);
    }
}
