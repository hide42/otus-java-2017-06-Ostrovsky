package dbservice.DAO;

import dataSets.User;
import org.hibernate.Session;

public class UserDataSetDAO {
    private Session session;

    public UserDataSetDAO(Session session) {
        this.session = session;
    }

    public long save(User dataSet) {
        return (long)session.save(dataSet);
    }

    public User read(long id) {
        return session.load(User.class, id);
    }


}
