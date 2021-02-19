package com.lambdaschool.todos.services;

import com.lambdaschool.todos.models.Todos;
import com.lambdaschool.todos.repository.TodoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;

@Transactional
@Service(value = "todosService")
public class TodosServiceImpl implements TodosService {

    @Autowired
    TodoRepository todoRepository;


    @Override
    public void markComplete(long todoid) {
       Todos todoToChange = todoRepository.findById(todoid)
                .orElseThrow(() -> new EntityNotFoundException("Todo id " + todoid + " is not found! "));
        todoToChange.setCompleted();
    }
}
