package repository;
import entity.Todolist;

import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class TodolListRepositoryImpl implements TodoListRepository{

    private DataSource dataSource;

    public TodolListRepositoryImpl(DataSource dataSource) {

        this.dataSource = dataSource;

    }

    @Override
    public Todolist[] getAll() {

        String sql = "SELECT ID, TODO FROM TODOLIST";

        try(Connection connection = dataSource.getConnection();
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(sql)){

            List<Todolist> list = new ArrayList<>();

            while(resultSet.next()){

                Todolist todolist = new Todolist();
                todolist.setId(resultSet.getInt("id"));
                todolist.setTodo(resultSet.getString("todo"));

                list.add(todolist);
            }

            return list.toArray(new Todolist[]{});

        }catch (SQLException sqlException) {
            throw new RuntimeException(sqlException);
        }

    }

    @Override
    public void add(Todolist todolist) {

        String sql = "INSERT INTO TODOLIST(todo) VALUES (?)";

        try (Connection connection = dataSource.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setString(1,todolist.getTodo());
            statement.executeUpdate();

        }catch (SQLException e){
            throw new RuntimeException(e);
        }

    }

    private boolean isExist(Integer number) {

        String sql = "SELECT ID FROM TODOLIST WHERE ID = ?";

        try (Connection connection = dataSource.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setInt(1, number);

            try (ResultSet resultSet = statement.executeQuery()){
                return resultSet.next();
            }

        }catch (SQLException e){
            throw new RuntimeException(e);
        }

    }

    @Override
    public boolean remove(Integer number) {

        String sql = "DELETE FROM TODOLIST WHERE ID = ?";

        if (isExist(number)){

            try (Connection connection = dataSource.getConnection();
                 PreparedStatement statement = connection.prepareStatement(sql)) {

                statement.setInt(1,number);
                statement.executeUpdate();

                return true;

            }catch (SQLException e){
                throw new RuntimeException(e);
            }

        }else{
            return false;
        }

    }
}
