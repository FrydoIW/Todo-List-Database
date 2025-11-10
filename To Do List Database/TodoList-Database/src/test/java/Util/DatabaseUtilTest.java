package Util;

import com.zaxxer.hikari.HikariDataSource;
import org.junit.Test;
import util.DatabaseUtil;

import java.sql.Connection;
import java.sql.SQLException;

public class DatabaseUtilTest {

    @Test
    public void testConnection() throws SQLException {

        HikariDataSource dataSource = DatabaseUtil.getDataSource();

        Connection connection = dataSource.getConnection();

        connection.close();

        dataSource.close();

    }

}
