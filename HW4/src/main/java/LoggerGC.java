import javax.management.*;
import java.lang.management.GarbageCollectorMXBean;
import java.lang.management.ManagementFactory;
import java.util.List;

/**
 * Created by peter on 06.07.2017.
 */
public class LoggerGC extends Thread {
    private final List<GarbageCollectorMXBean> beansGCMX;
    long[] countGarbage,timeGarbage;
    String[] namesGC;
    long[] totalCount,totalTime;

    MBeanServer mBeanServer;

    LoggerGC(){
        this.mBeanServer = ManagementFactory.getPlatformMBeanServer();
        this.beansGCMX = ManagementFactory.getGarbageCollectorMXBeans();
        timeGarbage = new long[beansGCMX.size()];
        namesGC = new String[beansGCMX.size()];
        countGarbage = new long[beansGCMX.size()];
        totalTime = new long[beansGCMX.size()];
        totalCount = new long[beansGCMX.size()];
        for (int i = 0; i < namesGC.length; i++) {
            namesGC[i]=beansGCMX.get(i).getName();
        }
        this.setDaemon(true);
        this.start();
    }

    @SuppressWarnings("InfiniteLoopStatement")
    @Override
    public void run() {
        long time = 0,count = 0;
        while (true){
            try {
                Thread.sleep(60_000);

            for (int i = 0; i < beansGCMX.size(); i++) {
                count= beansGCMX.get(i).getCollectionCount() - countGarbage[i];
                time = beansGCMX.get(i).getCollectionTime() - timeGarbage[i];
                totalTime[i]+=time;
                totalCount[i]+=count;

                countGarbage[i] = beansGCMX.get(i).getCollectionCount();
                timeGarbage[i] = beansGCMX.get(i).getCollectionTime();


                System.out.println(beansGCMX.get(i).getName()
                                    +" was executed "+count+" times"
                                    +" and spent " + time+" ms.        Total time and count "
                                    +totalTime[i]+" ms "+totalCount[i]+" times");
            }
            System.out.println("===========================");

            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
