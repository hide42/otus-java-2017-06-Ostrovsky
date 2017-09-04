import dataSets.AddressDataSet;
import dataSets.PhoneDataSet;
import dataSets.User;
import dbservice.DBService;
import dbservice.DBServiceImpl;

public class Main {
    public static void main(String[] args) {
        DBService serviceDB = new DBServiceImpl();

        User user1=new User("Alex",
                            new AddressDataSet("Moskovskaya ,123"),
                            new PhoneDataSet("4000620246"));
        User user2=new User("John",
                            new AddressDataSet("Moskovskaya ,126"),
                            new PhoneDataSet("8500600700"));




        serviceDB.save(user1);
        serviceDB.save(user2);

        serviceDB.readAll().forEach(System.out::println);

        System.out.println(serviceDB.read(2));
        System.out.println(serviceDB.readByName("Alex"));


        serviceDB.shutdown();
    }
}
