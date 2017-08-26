package orm;


import java.util.ArrayList;
import java.util.List;


public class MapORM {

    private List<String> fields;
    private String tableName;

    public MapORM() {
        this.fields = new ArrayList<>();
    }

    public String getTableName() {
        return tableName;
    }
    public void addField(String field){
        fields.add(field);
    }

    public void setTableName(String tableName) {
        this.tableName = tableName;
    }


    public List<String> getFields() {
        return fields;
    }
}
