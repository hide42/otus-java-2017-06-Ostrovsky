
public class LeakClass {
    private int ADD_ELEMENTS = 11_000;
    private LinkedList<String> linkedList;

    LeakClass(){linkedList=new LinkedList<>();}

    void start(){
        for (int i = 0; i < ADD_ELEMENTS; i++) {
            linkedList.add(Integer.toString(i+ADD_ELEMENTS*2));
        }

        for (int i = 0; i < ADD_ELEMENTS / 2; i++) {
            if (linkedList.firstNode==null) {
                break;
            }
            linkedList.poll();
        }

    }


}
