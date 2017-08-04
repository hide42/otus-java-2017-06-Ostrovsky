import atm.ATM;
import atm.Memento;
import java.util.HashMap;

/**
 * Created by peter on 04.08.2017.
 */
public class ATMDepartment {
    private final HashMap<ATM, Memento> hashMapDepartment;

    public ATMDepartment() {
        hashMapDepartment = new HashMap<>();
    }

    public void addATM(ATM atm){
        Memento memento = atm.saveToMemento();
        hashMapDepartment.put(atm,memento);
    }

    public void restoreAllATM(){
        for(ATM atm:hashMapDepartment.keySet()){
            restoreATM(atm);
        }
    }
    public void restoreATM(ATM atm){
        Memento memento=hashMapDepartment.get(atm);
        atm.restoreFromMemento(memento);
    }
    public int getBalance(ATM atm){
        return atm.getBalance();
    }
    public int getBalance(){
        int balance=0;

        for(ATM atm:hashMapDepartment.keySet()){
            balance+=getBalance(atm);
        }
        return balance;
    }

}
