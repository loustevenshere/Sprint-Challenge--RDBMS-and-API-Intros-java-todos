package com.lambdaschool.todos.services;

import com.lambdaschool.todos.models.Todos;
import com.lambdaschool.todos.repository.TodoRepository;
import com.lambdaschool.todos.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;
import java.util.ArrayList;
import java.util.List;

@Transactional
@Service(value = "todosService")
public class TodosServiceImpl implements TodosService {

    @Autowired
    TodoRepository todoRepository;

    @Autowired
    UserRepository userRepository;


    @Override
    public void markComplete(long todoid) {
       Todos todoToChange = todoRepository.findById(todoid)
                .orElseThrow(() -> new EntityNotFoundException("Todo id " + todoid + " is not found! "));
        todoToChange.setCompleted();
    }

    @Override
    public Todos save(Todos todo) {

        Todos newTodo = new Todos();
        if (todo.getTodoid() != 0)
        {
            todoRepository.findById(todo.getTodoid())
                    .orElseThrow(() -> new EntityNotFoundException("Todo " + todo.getTodoid() + " not found!"));
            newTodo.setTodoid(todo.getTodoid());
        }

        // Primitive Data Types
        newTodo.setDescription(todo.getDescription());

        newTodo.setUser(userRepository.findById(todo.getUser().getUserid())
        .orElseThrow(() -> new EntityNotFoundException("User " + todo.getUser().getUserid() + " Not Found!")));

        return todoRepository.save(newTodo);
    }

    // Just to check if I'm getting any todos
    @Override
    public List<Todos> findAllTodos() {
        List<Todos> list = new ArrayList<>();

        todoRepository.findAll().iterator().forEachRemaining(list::add);
        return list;
    }


}
