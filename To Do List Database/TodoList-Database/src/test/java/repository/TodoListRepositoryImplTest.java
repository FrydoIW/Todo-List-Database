package repository;

import com.zaxxer.hikari.HikariDataSource;
import entity.Todolist;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import util.DatabaseUtil;

public class TodoListRepositoryImplTest {

    private HikariDataSource dataSource;
    private TodoListRepository todoListRepository;

    @BeforeEach
    public void setUp() {
        dataSource = DatabaseUtil.getDataSource();
        todoListRepository = new TodolListRepositoryImpl(dataSource);
    }

    @Test
    public void testAdd() {
        Todolist todolist = new Todolist();
        todolist.setTodo("Frydo");
        todoListRepository.add(todolist);
    }

    @AfterEach
    public void tearDown() {
        dataSource.close();
    }
}
