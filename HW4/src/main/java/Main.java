
/*
-Xms256m -Xmx256m

-XX:+UseParallelGC -XX:+UseParallelOldGC
or
-XX:+UseParNewGC -XX:+UseConcMarkSweepGC
or
-XX:+UseSerialGC
or
-XX:+UseG1GC
 */

public class Main {
    public static void main(String[] args) {
        LoggerGC loggerGC = new LoggerGC();
        loggerGC.start();
        LeakClass leakClass=new LeakClass();

        try{
            while (true){
            leakClass.start();
            Thread.sleep(500);}
        }catch (Exception e){e.printStackTrace();}




    }
}
