package orm;

import executor.Executor;
import helper.QueryHelper;

import java.lang.reflect.Field;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.concurrent.atomic.AtomicReference;

public class ORM {
    private Executor executor;

    public ORM(Connection connection) {
        this.executor = new Executor(connection);
    }


    public <T> boolean save(T object){
        return executor.update(object);
    }

    public <T> T select(Class<T> clazz,int id) throws SQLException {
        return executor.execQuery(QueryHelper.getSelect(clazz,id),resultSet -> {

            AtomicReference<T> instance = null;
            try {
                instance = new AtomicReference<>(clazz.newInstance());
            } catch (InstantiationException | IllegalAccessException e) {
                e.printStackTrace();
            }
            if (instance.get() == null) {
                return null;
            }
            try {
                if (resultSet.next()) {
                    MapORM entityMapping = QueryHelper.getMappingORM(clazz);
                    for (String field : entityMapping.getFields()) {
                        setFieldValue(instance.get(), field, resultSet.getObject(field));
                    }
                }
                return instance.get();
            } catch (SQLException e) {
                return null;
            }

        });
    }

    private static void setFieldValue(Object object, String fieldName, Object value) {
        boolean isAccessible = true;
        Field field = null;
        try {
            try{
                field = object.getClass().getDeclaredField(fieldName);}
            catch (Exception e){
                field = object.getClass().getSuperclass().getDeclaredField(fieldName);
            }
            if (!field.isAccessible()) {
                field.setAccessible(true);
                isAccessible = false;
            }

            field.set(object, value);
        } catch (IllegalAccessException | NoSuchFieldException e) {
            e.printStackTrace();
        }

        if (field != null && !isAccessible) {
            field.setAccessible(false);
        }
    }

}
