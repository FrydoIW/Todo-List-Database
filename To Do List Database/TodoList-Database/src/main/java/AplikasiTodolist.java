import repository.TodoListRepository;
import repository.TodolListRepositoryImpl;
import service.TodoListService;
import service.TodoListServiceImpl;
import view.TodoListView;

public class AplikasiTodolist {

    public static void main(String[] args) {

        TodoListRepository todoListRepository = new TodolListRepositoryImpl();
        TodoListService todoListService = new TodoListServiceImpl(todoListRepository);
        TodoListView todoListView = new TodoListView(todoListService);

        todoListView.showTodoList();


    }

}