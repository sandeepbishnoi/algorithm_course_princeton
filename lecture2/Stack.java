package lecture2;

/** Various Implementations of stack */
public class Stack {

  public static void main(String[] args) {
    // Test Stack implementation based on LinkedList
    System.out.println("## Stack based on linked list ##");
    StackUsingLinkedList st = new StackUsingLinkedList();
    System.out.println(st.isEmpty());
    st.push("hello");
    st.push("world");
    System.out.println(st.pop());

    // Test Stack implementation based on Array
    System.out.println("## Stack based on fixed-size array ##");
    StackUsingFixedArray st1 = new StackUsingFixedArray(5);
    System.out.println(st1.isEmpty());
    st1.push("hello");
    st1.push("world");
    st1.push("this");
    st1.push("is");
    st1.push("array-based");
    System.out.println(st1.pop());
    System.out.println(st1.pop());
    System.out.println(st1.pop());

    System.out.println("## Stack based on resizable-size array ##");
    StackUsingResizableArray st2 = new StackUsingResizableArray();
    System.out.println(st2.isEmpty());
    st2.push("hello");
    st2.push("world");
    st2.push("this");
    st2.push("is");
    st2.push("array-based");
    System.out.println(st2.pop());
    System.out.println(st2.pop());
    System.out.println(st2.pop());
  }

}

/** Implementation of stack using linked list */
class StackUsingLinkedList
{
  Node head;

  public StackUsingLinkedList()
  {
    head = null;
  }

  public String pop()
  {
    String popped = head.value;
    head = head.next;
    return popped;
  }

  public void push(String item)
  {
    // Add an element in stack
    Node newHead = new Node(item);
    newHead.next = head;
    head = newHead;
  }

  public boolean isEmpty()
  {
    return head == null;
  }

}

/** Implementation of Stack using Fixed Array */
class StackUsingFixedArray
{
  String[] arr;
  int top=-1;

  public StackUsingFixedArray(int capacity)
  {
    arr = new String[capacity];
  }

  public String pop()
  {
    String popped = arr[top];
    arr[top--] = null;
    return popped;
  }

  public void push(String item)
  {
    arr[top+1]= item;
    top++;
  }

  public boolean isEmpty()
  {
    return top == -1;
  }
}

class StackUsingResizableArray
{
  String[] arr;
  int top = -1;

  public StackUsingResizableArray()
  {
    arr = new String[1];
  }

  public String pop()
  {
    String popped = arr[top];
    arr[top--] = null;
    if(top == arr.length/4)
      resize_half();
    return popped;
  }

  public void push(String item)
  {
    // Check if resize is required
    if(top == arr.length-1)
      resize_double();
    arr[top+1]= item;
    top++;
  }

  public boolean isEmpty()
  {
    return top == -1;
  }

  public void resize_double()
  {
    System.out.println("Doubling stack size from "+ arr.length + " to " + (arr.length*2));
    String[] copy = new String[arr.length*2];
    for(int i=0; i<arr.length; i++)
      copy[i] = arr[i];
    arr = copy;
  }

  public void resize_half()
  {
    System.out.println("Halving stack size from "+ arr.length + " to " + (arr.length/2));
    String[] copy = new String[arr.length/2];
    for(int i=0; i<arr.length/2; i++)
      copy[i] = arr[i];
    arr = copy;
  }
}