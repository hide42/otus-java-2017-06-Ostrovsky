package dbservice.DAO;

import dataSets.User;
import org.hibernate.Session;

public class UserDataSetDAO {
    private Session session;

    public UserDataSetDAO(Session session) {
        this.session = session;
    }

    public void save(User dataSet) {
        session.save(dataSet);
    }

    public User read(long id) {
        return session.load(User.class, id);
    }


}
