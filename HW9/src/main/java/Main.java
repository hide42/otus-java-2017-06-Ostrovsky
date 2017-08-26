import Entity.DataSet;
import Entity.UserDataSet;
import dao.UserDAO;
import helper.ConnectionHelper;
import orm.ORM;

import java.sql.SQLException;

public class Main {
    public static void main(String[] args) throws SQLException {

        ORM orm = new ORM(ConnectionHelper.getConnection());
        UserDAO userDAO = new UserDAO(orm);

        userDAO.insert(new UserDataSet(1,"John Milkovich",31));

        DataSet user = userDAO.find(1);
        System.out.println(user);
    }
}
