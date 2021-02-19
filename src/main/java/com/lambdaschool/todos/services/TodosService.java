package com.lambdaschool.todos.services;

import com.lambdaschool.todos.models.Todos;

import java.util.List;

public interface TodosService
{
    void markComplete(long todoid);

    Todos save(Todos todo);

    List<Todos> findAllTodos();
}
