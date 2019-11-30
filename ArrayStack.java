package function.root;

public class ArrayStack implements Stack {
  
    protected int capacity; 	
    public static final int CAPACITY = 2;	
    public Object S[];		
    protected int top = -1;	
    
    public ArrayStack() {
        this(CAPACITY);
    }
  
    public ArrayStack(int cap) {
        capacity = cap;
        S = new Object[capacity]; 
    }
  
    public int size() { 
        return (top + 1);
    }
  
    public boolean isEmpty() {
        return (top == -1);
    }
  
    public void push(Object element) throws FullStackException {
        if (size() == capacity) {
            doubleArray();
        }
        S[++top] = element;
    }
  
    public Object top() throws EmptyStackException {
        if (isEmpty())
            throw new EmptyStackException("Stack is empty.");
            return S[top];
        }
  
    public Object pop() throws EmptyStackException {
        Object element;
        if (isEmpty())
            throw new EmptyStackException("Stack is empty.");
        element = S[top];
        S[top--] = null;
        return element;
    }
  
    private void doubleArray( ) {
        Object [ ] newArray;

        System.out.println("Stack is full (max size was "+capacity+"). Increasing to "+(2*capacity));
  
        capacity = 2*capacity;
        newArray = new Object[ capacity ];
        for( int i = 0; i < S.length; i++ )
            newArray[ i ] = S[ i ];
        S = newArray;
    }
    
    public Object peek() throws EmptyStackException {
        Object element;
        if(isEmpty()){
            throw new EmptyStackException("Stack is empty.");
        }
        element = S[top];
        return element;
    }
    
}
