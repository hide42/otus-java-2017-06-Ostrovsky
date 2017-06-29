import java.util.*;
import java.util.function.UnaryOperator;

/**
 * Created by peter on 21.06.2017.
 */
public class ListArray<E> implements List<E>{
    int size;
    Object[] elements= new Object[2];;

    public ListArray(){
        super();
    }
    private boolean resize(){
            elements=Arrays.copyOf(elements,elements.length*2);
        return true;
    }

    @Override
    public int size() {
        return size;
    }



    @Override
    public boolean isEmpty() {
        if(size==0)
            return true;
        else
            return false;
    }

    @Override
    public boolean contains(Object o) {
        return true;
    }

    @Override
    public Iterator<E> iterator() {
        return new Iterator<E>() {
            int index;
            @Override
            public boolean hasNext() {
                return index<size;
            }
            @Override
            public E next() {
                return (E) elements[index++];
            }
        };
    }

    @Override
    public Object[] toArray() {
        return new Object[0];
    }

    @Override
    public <T> T[] toArray(T[] a) {
        return null;
    }

    @Override
    public boolean add(E e) {
        if(size==elements.length)
            resize();
        elements[size]=e;
        size++;
        return true;
    }

    @Override
    public boolean remove(Object o) {
        return false;
    }

    @Override
    public boolean containsAll(Collection<?> c) {
        return false;
    }

    @Override
    public boolean addAll(Collection<? extends E> c) {
        for (E e: c) {
            this.add(e);
        }
        return false;
    }

    @Override
    public boolean addAll(int index, Collection<? extends E> c) {
        return false;
    }

    @Override
    public boolean removeAll(Collection<?> c) {
        return false;
    }

    @Override
    public boolean retainAll(Collection<?> c) {
        return false;
    }

    @Override
    public void replaceAll(UnaryOperator<E> operator) {

    }

    @Override
    public void sort(Comparator<? super E> c) {
        Arrays.sort((E[]) elements, 0, size, c);
    }

    @Override
    public void clear() {

    }

    @Override
    public E get(int index) {
        if(index >= 0 && index < this.size) {
            return (E)elements[index];
        }else{
            throw new IndexOutOfBoundsException();
            }
    }

    @Override
    public E set(int index, E element) {
        elements[index]=element;
        return element;
    }

    @Override
    public void add(int index, E element) {
        resize();
        System.arraycopy(elements,index,elements, index + 1,this.size - index);
        elements[index]=element;
    }

    @Override
    public E remove(int index) {
        return null;
    }

    @Override
    public int indexOf(Object o) {
        return -1;
    }

    @Override
    public int lastIndexOf(Object o) {
        return 0;
    }

    @Override
    public ListIterator<E> listIterator() {
        return null;
    }

    @Override
    public ListIterator<E> listIterator(int index) {
        return null;
    }

    @Override
    public List<E> subList(int fromIndex, int toIndex) {
        return null;
    }
    
}
