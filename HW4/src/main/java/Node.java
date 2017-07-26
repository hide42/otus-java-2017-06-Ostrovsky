/**
 * Created by peter on 26.07.2017.
 */
public class Node<E>{
    E value;
    Node nextNode;

    public E getValue() {
        return value;
    }

    public void setValue(E value) {
        this.value = value;
    }

    public Node getNextNode() {
        return nextNode;
    }

    public void setNextNode(Node nextNode) {
        this.nextNode = nextNode;
    }
}
