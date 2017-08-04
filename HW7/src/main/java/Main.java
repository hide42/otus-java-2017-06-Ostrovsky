import atm.ATM;
import atm.Cell;


import java.util.ArrayList;
import java.util.List;

/**
 * Created by peter on 04.08.2017.
 */
public class Main {
    public static void main(String[] args) {
        ATMDepartment department = new ATMDepartment();
        final List<Cell> cells1 = new ArrayList<>();
        cells1.add(new Cell(1, 5));
        cells1.add(new Cell(5, 5));
        cells1.add(new Cell(10, 5));
        cells1.add(new Cell(50, 5));
        cells1.add(new Cell(100, 5));

        final ATM atm1 = new ATM(cells1);
        department.addATM(atm1);
        System.out.println("Initial balance of ATM1: "+department.getBalance(atm1));

        final List<Cell> cells2 = new ArrayList<>();
        cells2.add(new Cell(1, 10));
        cells2.add(new Cell(5, 10));
        cells2.add(new Cell(10, 10));
        cells2.add(new Cell(50, 10));
        cells2.add(new Cell(100, 10));
        ATM atm2 = new ATM(cells2);
        department.addATM(atm2);
        System.out.println("Initial balance of ATM2: "+department.getBalance(atm2));

        final List<Cell> cells3 = new ArrayList<>();
        cells3.add(new Cell(1, 15));
        cells3.add(new Cell(5, 15));
        cells3.add(new Cell(10, 15));
        cells3.add(new Cell(50, 15));
        cells3.add(new Cell(100, 15));
        final ATM atm3 = new ATM(cells3);
        department.addATM(atm3);
        System.out.println("Initial balance of ATM3: "+department.getBalance(atm3));

        System.out.println("Initial balance of department: "+department.getBalance());

        atm1.withdraw(100);
        atm2.withdraw(500);
        atm3.withdraw(1000);
        System.out.println("Balance after withdraw: "+department.getBalance());

        department.restoreAllATM();
        System.out.println("Balance after restore: "+department.getBalance());

    }
}
