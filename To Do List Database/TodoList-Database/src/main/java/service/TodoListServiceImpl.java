package service;

import entity.Todolist;
import repository.TodoListRepository;

public class TodoListServiceImpl implements TodoListService{

    private TodoListRepository todolistRepository;

    public TodoListServiceImpl(TodoListRepository todolistRepository) {
        this.todolistRepository = todolistRepository;
    }

    @Override
    public void showTodoList() {

        Todolist[] model = todolistRepository.getAll();

        System.out.println("TODOLIST");

        for (var i = 0; i < model.length; i++){
            var todo = model[i];
            var no = i + 1;

            if (todo != null) {
                System.out.println(no + " " + todo.getTodo() + "With ID : " + todo.getId());
            }
        }

    }

    @Override
    public void addTodoList(String todo) {
        Todolist todolist = new Todolist(todo);
        todolistRepository.add(todolist);
        System.out.println("SUCCESS INSERT TODO : " + todo);
    }

    @Override
    public void removeTodoList(Integer number) {
        boolean success = todolistRepository.remove(number);
        if (success) {
            System.out.println("SUCCESS REMOVE");
        }else {
            System.out.println("FAILED REMOVE");
        }
    }
}
