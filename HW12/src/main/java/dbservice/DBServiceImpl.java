package dbservice;


import cache.CacheEngine;
import cache.MyElement;
import dataSets.AddressDataSet;
import dataSets.PhoneDataSet;
import dataSets.User;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;


public class DBServiceImpl implements DBService {
    private final Executor executor;
    private CacheEngine<Long,User> cacheEngine;

    public DBServiceImpl(CacheEngine<Long,User> cacheEngine) {
        this.cacheEngine=cacheEngine;

        Configuration configuration = new Configuration();

        configuration.addAnnotatedClass(User.class);
        configuration.addAnnotatedClass(PhoneDataSet.class);
        configuration.addAnnotatedClass(AddressDataSet.class);
        configuration.setProperty("hibernate.dialect", "org.hibernate.dialect.MySQL5Dialect");
        configuration.setProperty("hibernate.connection.driver_class", "com.mysql.jdbc.Driver");
        configuration.setProperty("hibernate.connection.url", "jdbc:mysql://localhost:3306/test");
        configuration.setProperty("hibernate.connection.username", "root");
        configuration.setProperty("hibernate.connection.password", "root"); //default password, allowed value - tully
        configuration.setProperty("hibernate.show_sql", "true");
        configuration.setProperty("hibernate.format_sql", "true");
        configuration.setProperty("hibernate.hbm2ddl.auto", "create");
        configuration.setProperty("hibernate.connection.useSSL", "false");
        configuration.setProperty("hibernate.enable_lazy_load_no_trans", "true");

        SessionFactory sessionFactory = createSessionFactory(configuration);
        executor = new Executor(sessionFactory);

    }
    private static SessionFactory createSessionFactory(Configuration configuration) {
        StandardServiceRegistryBuilder builder = new StandardServiceRegistryBuilder();
        builder.applySettings(configuration.getProperties());
        ServiceRegistry serviceRegistry = builder.build();
        return configuration.buildSessionFactory(serviceRegistry);
    }


    public String getLocalStatus() {
        return executor.getLocalStatus();
    }
    public String getCacheStatus(){
        return "Miss_count: "+cacheEngine.getMissCount()+" Hit_count: "+cacheEngine.getHitCount()+" Element_Count: "+cacheEngine.getSize();
    }

    public void save(User dataSet) {
        cacheEngine.put(executor.save(dataSet),dataSet);
    }

    public User read(long id) {
        User user = cacheEngine.get(id);
        if(user==null){
            user = executor.read(id);
            cacheEngine.put(id,user);
        }
        return user;
    }

    public void shutdown() {
        cacheEngine.dispose();
        executor.shutdown();
    }

}
