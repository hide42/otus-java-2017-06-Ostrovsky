import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created by peter on 21.06.2017.
 */
public class Main {
    public static void main(String[] args) {
        List<String> arrayList = new ArrayList<String>();
        List<String> myListArray = new ListArray<String>();
        arrayList.add("aaa");
        myListArray.add("bbb");


        System.out.println("Collections addAll  ");
        Collections.addAll(arrayList,"baa","aba","aab");
        Collections.addAll(myListArray,"abb","bab","bba");
        print(myListArray);


        System.out.println("Collections copy   ");
        Collections.copy(myListArray,arrayList);
        print(myListArray);


        System.out.println("Collections sort   ");
        Collections.sort(myListArray);
        print(myListArray);


    }
    public static void print(List myListArray){
        for (int i = 0; i < myListArray.size(); i++) {
            System.out.print(myListArray.get(i)+" ");
        }
        System.out.println();
        System.out.println();
    }
}
