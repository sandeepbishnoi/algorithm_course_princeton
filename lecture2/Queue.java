package lecture2;

import java.util.LinkedList;

public class Queue {

  public static void main(String[] args) {

    // Testing queue implemented using linked list
    QueueUsingLinkedList test= new QueueUsingLinkedList();
    System.out.println("Testing LinkedList based Queue");
    System.out.println(test.isEmpty());
    test.enqueue("first");
    test.enqueue("second");
    test.enqueue("third");
    System.out.println(test.isEmpty());
    System.out.println(test.dequeue());
    System.out.println(test.dequeue());
    System.out.println(test.dequeue());
    System.out.println(test.isEmpty());

    System.out.println("Testing Generic Based Queue");
    // Testing queue implemented using linked list
    GenericQueue<String> q= new GenericQueue<String> ();
    System.out.println(test.isEmpty());
    q.enqueue("first");
    q.enqueue("second");
    q.enqueue("third");
    System.out.println(q.isEmpty());
    System.out.println(q.dequeue());
    System.out.println(q.dequeue());
    System.out.println(q.dequeue());
    System.out.println(q.isEmpty());

    System.out.println("Testing Generic Based Iterator");
    // Testing queue implemented using linked list
    GenericQueue<String> q1= new GenericQueue<String> ();
    q1.enqueue("first");
    q1.enqueue("second");
    q1.enqueue("third");
    Iterator<String> iter = q1.iterator();
    while(iter.hasNext())
      System.out.println(iter.next());
  }
}

class QueueUsingLinkedList
{
  Node first;
  Node last;

  public QueueUsingLinkedList()
  {
    first = null;
    last = null;
  }

  public void enqueue(String item)
  {
    Node newItem = new Node(item);
    if(isEmpty())
    {
      first = newItem;
      last  = newItem;
    }
    else
    {
      last.next = newItem;
      last = newItem;
    }
  }

  public String dequeue()
  {
    String next = first.value;
    first = first.next;
    return next;
  }

  public boolean isEmpty()
  {
    return first == null;
  }
}

class GenericQueue<T> implements Iterable<T>
{
  GenericNode<T> first;
  GenericNode<T> last;

  public GenericQueue()
  {
    first = null;
    last = null;
  }

  public void enqueue(T item)
  {
    GenericNode<T> newItem = new GenericNode<T>(item);
    if(isEmpty())
    {
      first = newItem;
      last  = newItem;
    }
    else
    {
      last.next = newItem;
      last = newItem;
    }
  }

  public T dequeue()
  {
    T next = first.value;
    first = first.next;
    return next;
  }

  public boolean isEmpty()
  {
    return first == null;
  }


  @Override
  public Iterator<T> iterator() {
    return new GenericQueueIterator(first);
  }
}

class GenericQueueIterator<T> implements Iterator<T>
{
  GenericNode<T> current;

  public GenericQueueIterator(GenericNode<T> current)
  {
    this.current = current;
  }

  public boolean hasNext()
  {
    return current != null;
  }

  public T next()
  {
    T shipped = current.value;
    current = current.next;
    return shipped;
  }
}
