package com.spring.mallapi.todo.service;

import com.spring.common.dto.PageRequestDTO;
import com.spring.common.dto.PageResponseDTO;
import com.spring.mallapi.todo.dto.TodoDTO;

public interface TodoService {
    public Long register(TodoDTO todoDTO);
    public TodoDTO get(Long tno);
    public void modify(TodoDTO todoDTO);
    public void remove(Long tno);
    public PageResponseDTO<TodoDTO> list(PageRequestDTO pageRequestDTO);
}
