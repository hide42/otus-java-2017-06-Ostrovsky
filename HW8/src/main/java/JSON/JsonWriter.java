package JSON;

import com.sun.org.apache.xpath.internal.operations.Bool;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import java.lang.reflect.Array;
import java.lang.reflect.Field;
import java.util.Collection;

/**
 *
 */
public class JsonWriter {

    public JSONObject toJson(Object object) throws IllegalAccessException{
        JSONObject jsonObject = new JSONObject();

        for(Field field:object.getClass().getDeclaredFields()){
            field.setAccessible(true);
            Class type = field.getType();

            if (type.isArray()) {
                jsonObject.put(field.getName(), arrayToJson(field.get(object)));
            } else if (type.isEnum()) {
                jsonObject.put( field.getName(), field.get(object).toString() ) ;
            } else if ( Collection.class.isAssignableFrom(type) ) {
                Collection collection = (Collection) field.get(object);
                jsonObject.put(field.getName() , arrayToJson(collection.toArray()));
            } else {
                if(!Number.class.isInstance(field.get(object))
                        &&!String.class.isInstance(field.get(object))
                        &&!Boolean.class.isInstance(field.get(object))){

                    jsonObject.put( field.getName(), toJson(field.get(object))) ;
                }else{
                jsonObject.put( field.getName(), field.get(object) ) ;}
            }
        }

        return jsonObject;
    }

    private JSONArray arrayToJson(Object array) throws IllegalAccessException{

        JSONArray jsonArray = new JSONArray();
        for(int i =0;i< Array.getLength(array);i++){
            Object elementOfArray = Array.get(array,i);

            if(elementOfArray.getClass().isArray()){
                jsonArray.add(arrayToJson(elementOfArray));
            }else {
                if(Number.class.isInstance(elementOfArray)
                        ||String.class.isInstance(elementOfArray)
                        ||Boolean.class.isInstance(elementOfArray))
                    jsonArray.add(elementOfArray);
                else
                    jsonArray.add(toJson(elementOfArray));
                }
        }
        return jsonArray;
    }
}
