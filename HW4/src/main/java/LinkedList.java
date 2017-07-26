/**
 * Created by peter on 26.07.2017.
 */
public class LinkedList<E> {
    Node currentNode;
    Node firstNode;
    public LinkedList() {
    }
    public void add(E e){
        Node node=new Node();
        node.setValue(e);
        if(firstNode==null){
            firstNode=node;
            currentNode=node;
        }else{
            currentNode.setNextNode(node);
            currentNode=node;
        }
    }
    public E poll(){
        if(firstNode!=null){
            E returned = (E) firstNode.getValue();
            if(firstNode.getNextNode()!=null)
                firstNode=firstNode.getNextNode();
            else{firstNode=null;currentNode=null;}
            return returned;
        }else return null;
    }
}
