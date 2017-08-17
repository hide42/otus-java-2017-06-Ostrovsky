package TestClasses;

import java.util.LinkedList;
import java.util.List;

public class CatHouse {
    private List<Cat> cats = new LinkedList<>();
    public int[] arrayNumbers = {1,2,3};
    public String[][] doubleArray = { {"a","b","c"}, {"s","d","f"} };

    public CatHouse() {
    }
    public void addCat(Cat cat){
        cats.add(cat);
    }

}
