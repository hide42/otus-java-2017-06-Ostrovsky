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

        for(Field field : map.getFields()){
            String fieldName = field.getName();
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
        String fields = String.join(",",map.getStringFields());
        return String.format("SELECT %s FROM %s WHERE id = %s", fields, map.getTableName(), id);
    }

    public static MapORM getMappingORM(Class<?> clazz){
        MapORM map = new MapORM();

        if(clazz.isAnnotationPresent(Table.class)){
        Table tableAnnotations = clazz.getDeclaredAnnotation(Table.class);
            map.setTableName(tableAnnotations.name());
        }else {
            System.err.println("No table annotation");
            map.setTableName(clazz.getSimpleName().toLowerCase());
        }
        List<Field> listFields = new ArrayList<>();
        listFields.addAll(Arrays.asList(clazz.getSuperclass().getDeclaredFields()));
        listFields.addAll(Arrays.asList(clazz.getDeclaredFields()));
        for(Field field : listFields){
            if (isNotSupported(field)) {
                continue;
            }
            map.addField(field);
        }
        return map;
    }
    private static boolean isNotSupported(Field field){

        return (field.getDeclaredAnnotation(Column.class) == null)
                || ((!field.getType().isPrimitive() && (field.getType() != String.class)) && (!mapWrappers.values().contains(field.getDeclaringClass())));
    }

}
