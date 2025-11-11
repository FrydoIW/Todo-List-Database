package view;

import service.TodoListService;
import util.InputUtil;

public class TodoListView {

    TodoListService todoListService;

    public TodoListView(TodoListService todoListService){
        this.todoListService = todoListService;
    }

    public void showTodoList(){
        while (true) {
            todoListService.showTodoList();

            System.out.println("MENU : ");
            System.out.println("1. TAMBAH");
            System.out.println("2. HAPUS");
            System.out.println("3. KELUAR");

            var input = InputUtil.input("Pilih");

            if (input.equals("1")) {
                addTodoList();
            } else if (input.equals("2")) {
                removeTodoList();
            } else if (input.equals("x")) {
                break;
            }else {
                System.out.println("Didn't Understand Input");
            }
        }
    }

    public void addTodoList(){
        System.out.println("ADD TO DO LIST");

        var todo = InputUtil.input("Todo (x jika batal)");

        if (todo.equals("x")) {
            //batal
        } else {
            todoListService.addTodoList(todo);
        }

    }

    public void removeTodoList(){
        System.out.println("DELETE TO DO LIST");

        var number = InputUtil.input("Nomor yang dihapus (x Juka Batal)");

        if (number.equals("x")){
            //batal
        }else {
            todoListService.removeTodoList(Integer.valueOf(number));
        }

    }

}
