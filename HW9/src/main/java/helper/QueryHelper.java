package helper;

import orm.MapORM;

import javax.persistence.Column;
import javax.persistence.Table;
import java.lang.reflect.Field;
import java.util.*;

public class QueryHelper {

    public final static Map<Class<?>, Class<?>> mapWrappers = new HashMap<>();

    static {
        mapWrappers.put(boolean.class, Boolean.class);
        mapWrappers.put(byte.class, Byte.class);
        mapWrappers.put(short.class, Short.class);
        mapWrappers.put(char.class, Character.class);
        mapWrappers.put(int.class, Integer.class);
        mapWrappers.put(long.class, Long.class);
        mapWrappers.put(float.class, Float.class);
        mapWrappers.put(double.class, Double.class);
    }


    public static <T> String getUpdate(T object) {
        MapORM map = getMappingORM(object.getClass());
        List<String> columns = new LinkedList<>();
        List<String> values = new LinkedList<>();
        List<String> results = new LinkedList<>();

        for(String fieldName : map.getFields()){
            Object value=getFieldValue(object,fieldName);
            columns.add(fieldName);
            if(value instanceof Character||value instanceof String){
                values.add("'"+value.toString()+"'");
                results.add(fieldName+"='"+value.toString()+"'");
            }else{
                values.add(value.toString());
                results.add(fieldName+"="+value.toString());
            }
        }
        String column = String.join(",",columns);
        String value = String.join(",",values);
        String result = String.join(",",results);

        return String.format("INSERT INTO %s (%s) VALUES (%s)  ON DUPLICATE KEY UPDATE %s", map.getTableName(), column, value, result);
    }

    public static Object getFieldValue(Object object,String fieldName){
        Field field;
        Object value=null;
        try {
            try{
            field = object.getClass().getDeclaredField(fieldName);
            } catch (Exception e) {
                field = object.getClass().getSuperclass().getDeclaredField(fieldName);
            }
            if(field.isAccessible()){
                 value = field.get(object);
            }else {
                field.setAccessible(true);
                value = field.get(object);
                field.setAccessible(false);
            }
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        return value;
    }

    public static <T> String getSelect(Class<T> clazz, int id) {
        MapORM map = getMappingORM(clazz);
        String fields = String.join(",",map.getFields());
        return String.format("SELECT %s FROM %s WHERE id = %s", fields, map.getTableName(), id);
    }

    public static MapORM getMappingORM(Class<?> clazz){
        Table tableAnnotations = clazz.getDeclaredAnnotation(Table.class);
        MapORM map = new MapORM();
        map.setTableName(tableAnnotations.name());
        List<Field> listFields = new ArrayList<>();
        listFields.addAll(Arrays.asList(clazz.getSuperclass().getDeclaredFields()));
        listFields.addAll(Arrays.asList(clazz.getDeclaredFields()));
        for(Field field : listFields){
            if (isSupported(field)) {
                continue;
            }
            map.addField(field.getName());
        }
        return map;
    }
    private static boolean isSupported(Field field){
        return (field.getDeclaredAnnotation(Column.class) == null)
                || ((!field.getType().isPrimitive() && (field.getType() != String.class)) && (!mapWrappers.values().contains(field.getDeclaringClass())));
    }

}
