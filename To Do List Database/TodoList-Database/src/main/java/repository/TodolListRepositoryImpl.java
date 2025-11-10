package repository;
import entity.Todolist;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class TodolListRepositoryImpl implements TodoListRepository{

    public Todolist[] data = new Todolist[10];

    private DataSource dataSource;

    public TodolListRepositoryImpl(DataSource dataSource) {

        this.dataSource = dataSource;

    }

    public boolean isFull(){
        var isFull = true;
        for (int i = 0; i < data.length ; i++){
            if (data[i] == null){
                //there's still empty data
                return false;
            }
        }
        return isFull;
    }

    public void resizeIfFull(){
        if (isFull()){
            var temp = data;
            data = new Todolist[data.length * 2];

            for (int i = 0 ; i < temp.length; i++){
                data[i] = temp[i];
            }
        }
    }

    @Override
    public Todolist[] getAll() {
        return data;
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

    @Override
    public boolean remove(Integer number) {

        if ((number - 1) >= data.length){
            return false;
        } else if (data[number - 1] == null) {
            return  false;
        } else {
            for (int i = (number - 1) ; i < data.length ; i++){
                if (i == (data.length - 1)){
                    data[i] = null;
                }else{
                    data[i] = data[i + 1];
                }
            }
            return true;
        }
    }
}
