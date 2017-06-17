package otus;
import java.util.function.Supplier;

/**
 * Created by peter on 17.06.2017.
 */
public class Benchmark {
    private Object[] array;
    private Runtime runtime = Runtime.getRuntime();
    private final int size = 1_000_000;
    int n = 3;

    void measure(Supplier<Object> supplier, String ObjectName) {
        double res = 0.0;

        for (int i = 0; i < n; i++) {
            array = new Object[size];
            System.gc();
            long memBefore = runtime.totalMemory() - runtime.freeMemory();
            for (int j = 0; j < size; j++) array[j] = supplier.get();
            System.gc();
            long memAfter = runtime.totalMemory() - runtime.freeMemory();
            res += (memAfter - memBefore) / size;
        }
        System.out.println("new "+ObjectName+ ": "+Math.round(res/n)+" bytes.");
    }
}
