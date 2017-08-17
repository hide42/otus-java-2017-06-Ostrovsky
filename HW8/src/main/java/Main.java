import JSON.JsonWriter;
import TestClasses.AdditionalInformation;
import TestClasses.Breed;
import TestClasses.Cat;
import TestClasses.CatHouse;
import com.google.gson.Gson;


public class Main {
    public static void main(String[] args) throws IllegalAccessException {
        CatHouse catHouse=new CatHouse();
        AdditionalInformation additionalInformation=new AdditionalInformation("Masters","Oliver and Monika");
        Cat catFirst =new Cat("John",7.0, Breed.FelisCatus,additionalInformation);
        catHouse.addCat(catFirst);
        Cat catSecond = new Cat("Murzik",3,Breed.MaineCoon,additionalInformation);
        catHouse.addCat(catSecond);



        JsonWriter jsonWriter=new JsonWriter();
        String jsonWritterString = jsonWriter.toJson(catHouse).toString();
        System.out.println(jsonWriter.toJson(catHouse));

        Gson gson=new Gson();
        String gsonString = gson.toJson(catHouse);
        System.out.println(gson.toJson(catHouse));

        System.out.println(gsonString.length()==jsonWritterString.length());

    }
}
