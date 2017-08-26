package dao;

import Entity.DataSet;
import Entity.UserDataSet;
import orm.ORM;

import java.sql.SQLException;

public class UserDAO {
    private final ORM orm;

    public UserDAO(ORM orm) {
        this.orm = orm;
    }

    public boolean insert(DataSet user) {
        return orm.save(user);
    }

    public DataSet find(int id) throws SQLException {
        return orm.select(UserDataSet.class,id);
    }
}
