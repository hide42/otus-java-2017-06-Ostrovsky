import java.util.ArrayList;

/**
 **  -Xms256m -Xmx256m -XX:+UseParallelGC -XX:+UseParallelOldGC
 */
public class LeakClass {
    private int ADD_ELEMENTS = 20_000;
    private ArrayList<String> array;

    LeakClass(){array=new ArrayList<>();}

    void start(){
        for (int i = 0; i < ADD_ELEMENTS; i++) {
            array.add(Integer.toString(i+array.size()*2));
        }

        for (int i = 0; i < ADD_ELEMENTS / 2; i++) {
            if (array.size()==0) {
                break;
            }
            array.remove(array.size()-1);
        }

    }


}
