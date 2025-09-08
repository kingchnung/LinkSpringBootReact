package com.spring.mallapi.todo.domain;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;


@Getter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "tbl_todo")
@SequenceGenerator(name = "tbl_todo_generator",
sequenceName = "tbl_todo_seq",
initialValue = 1,
allocationSize = 1)
public class Todo {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "tbl_todo_generator")
    private Long tno;


    private String title;
    private String writer;
    private boolean complete;
    private LocalDate dueDate;

    public void changeTitle(String title) {
        this.title = title;
    }

    public void changeComplete(boolean complete) {
        this.complete = complete;
    }

    public void changeDueDate(LocalDate dueDate) {
        this.dueDate = dueDate;
    }
}
