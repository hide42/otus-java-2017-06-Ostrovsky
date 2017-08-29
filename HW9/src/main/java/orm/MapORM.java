package orm;


import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;


public class MapORM {

    private List<Field> fields;
    private String tableName;


    public MapORM() {
        this.fields = new ArrayList<>();
    }

    public String getTableName() {
        return tableName;
    }
    public void addField(Field field){
        if(!fields.contains(field))
            fields.add(field);
        else
            System.err.println("Conflict fields");
    }

    public void setTableName(String tableName) {
        this.tableName = tableName;
    }

    public List<String> getStringFields(){
        List<String> fieldsName = new LinkedList<>();
        for(Field field:fields){
            fieldsName.add(field.getName());
        }
        return fieldsName;
    }

    public List<Field> getFields() {
        return fields;
    }
}
