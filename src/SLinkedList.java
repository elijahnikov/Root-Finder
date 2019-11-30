package function.root;

public class SLinkedList {
    
    public static String chosenFunction;
    public StringNode head;
        
    public SLinkedList() {
        head = new StringNode();
        
    }
    
    void addFirst(double xvalue, double fxvalue){
        head = new StringNode(xvalue, fxvalue, head);
    }
    
    void addLast(double xvalue, double fxvalue){
        StringNode tail;
        tail = head;
        while(tail.getNext() != null){
            tail = tail.getNext();
        }
        
        tail.setNext( new StringNode(xvalue, fxvalue, null));
    }
    
    public boolean isEmpty() {
        return head == null;
    }
    
    public static void printList(SLinkedList thelist){
        StringNode temp;
        String newLine = "\n";
        int count = 0;
        if (thelist.isEmpty()){
            System.out.println("List is empty");
        } else {
            temp = thelist.head;
            while (temp != null) {
                System.out.println(count + "- x: " + temp.getXValue()+ " fx: " + temp.getFXValue()+", " + newLine);
                temp = temp.getNext();
                count++;
            }
            System.out.println();
        }
    }
    
}