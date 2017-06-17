package otus;

import java.util.ArrayList;

//VM options -Xmx512m -Xms512m
public class MainClass {
    public static void main(String...args){
        Benchmark benchmark = new Benchmark();

        benchmark.measure(() -> new String(new char[0]),"String(new char[0]) ");
        benchmark.measure(() -> new String(""),"String ");
        benchmark.measure(() -> new Integer(0),"Integer(0) ");
        benchmark.measure(() -> new Integer[0],"Integer[0]");
        benchmark.measure(() -> new Integer[1],"Integer[1]");
        benchmark.measure(() -> new Integer[100],"Integer[100]");
        benchmark.measure(() -> new int[0],"int[0]");
        benchmark.measure(() -> new int[1],"int[1]");
        benchmark.measure(() -> new int[100],"int[100]");
        benchmark.measure(()-> new ArrayList<Integer>(1),"ArrayList<Integer>(1)");
        benchmark.measure(()-> new ArrayList<Integer>(100),"ArrayList<Integer>(100)");

    }

}
