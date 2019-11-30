package function.root;

public interface Stack {
 
public int size();

public boolean isEmpty();

public Object top() 
        
    throws EmptyStackException;  

public Object peek()
        
    throws EmptyStackException;

public void push (Object element); 
 
public Object pop()
        
    throws EmptyStackException; 
}
