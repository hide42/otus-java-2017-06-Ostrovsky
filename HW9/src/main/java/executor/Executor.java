package executor;

import helper.QueryHelper;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.function.Function;

public class Executor {
    private final Connection connection;

    public Executor(Connection connection) {
        this.connection = connection;
    }


    public <T> T execQuery(String query, Function<ResultSet, T> function) throws SQLException {
        try (Statement statement = connection.createStatement()){
            statement.execute(query);
            return function.apply(statement.getResultSet());
        } catch (SQLException e) {
            return null;
        }
    }

    public boolean update(Object object){
        try (Statement statement = connection.createStatement()) {
            int rowsUpdate = statement.executeUpdate(QueryHelper.getUpdate(object),Statement.RETURN_GENERATED_KEYS);
            if (rowsUpdate == 1) {
                ResultSet rs = statement.getGeneratedKeys();
                if (rs.next()) {
                    return true;
                }
            }
            return true;
        } catch (SQLException e) {
            return false;
        }
    }
}
