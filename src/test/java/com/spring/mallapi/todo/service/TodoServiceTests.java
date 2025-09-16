package com.spring.mallapi.todo.service;

import com.spring.common.dto.PageRequestDTO;
import com.spring.common.dto.PageResponseDTO;
import com.spring.mallapi.todo.dto.TodoDTO;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;

@SpringBootTest
@Slf4j
public class TodoServiceTests {

    @Autowired
    private TodoService todoService;
    
    @Test
    public void testRegister() {
        TodoDTO todoDTO = TodoDTO.builder()
                .title("서비스 테스트")
                .writer("tester")
                .dueDate(LocalDate.of(2025, 9, 5))
                .build();

        Long tno = todoService.register(todoDTO);
        log.info("TNO: {}", tno);
    }

    @Test
    public void testGet() {
        Long tno = 63L;
        TodoDTO todoDTO = todoService.get(tno);
        log.info("데이터 : {}", todoDTO);
    }

    @Test
    public void testList() {
        PageRequestDTO pageRequestDTO = PageRequestDTO.builder()
                .page(2).size(10).build();

        PageResponseDTO<TodoDTO> response = todoService.list(pageRequestDTO);
        log.info("PageResponseDTO : {}", response);
    }
}
