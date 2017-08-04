package atm;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;


public class ATM {
    private Cell first;

    public ATM(List<Cell> cells) {
        Collections.sort(cells);
        first = cells.get(0);
        linkCells(cells);

    }

    public Memento saveToMemento(){
        List<Cell> cells = new ArrayList<>();
        Iterator<Cell> iterator = first.iterator();
        while (iterator.hasNext()) {
            Cell i = iterator.next();
            cells.add(new Cell(i.getNominal(),i.getCount()));
        }
        return new Memento(cells);
    }
    public void restoreFromMemento(Memento memento){
        first = memento.getCells().get(0);
        linkCells(memento.getCells());
    }

    public boolean withdraw(int requested) {
        return first.withdraw(requested);
    }

    public int getBalance() {
        Iterator<Cell> iterator = first.iterator();
        int balance = 0;
        while (iterator.hasNext()) {
            balance += iterator.next().getBalance();
        }
        return balance;
    }

    private void linkCells(List<Cell> cells) {
        Iterator<Cell> iterator = cells.iterator();
        Cell cellA = iterator.next();
        while (iterator.hasNext()) {
            Cell cellB = iterator.next();
            cellA.setNext(cellB);
            cellA = cellB;
        }
    }
}