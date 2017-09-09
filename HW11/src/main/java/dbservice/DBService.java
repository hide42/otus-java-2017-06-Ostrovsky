package dbservice;

import dataSets.User;


public interface DBService {

    String getCacheStatus();

    String getLocalStatus();

    void save(User dataSet);

    User read(long id);

    void shutdown();
}
