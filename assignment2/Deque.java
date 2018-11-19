import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * Programming Assignment 2: Deques and Randomized Queues
 * Write a generic data type for a deque and a randomized queue. The goal of this assignment is to implement elementary data structures using arrays and linked lists, and to introduce you to generics and iterators.

 * Dequeue. A double-ended queue or deque (pronounced “deck”) is a generalization of a stack and a queue that supports adding and removing items from either the front or the back of the data structure. Create a generic data type Deque that implements the following API:

 * public class Deque<Item> implements Iterable<Item> {
 * public Deque()                           // construct an empty deque
 * public boolean isEmpty()                 // is the deque empty?
 * public int size()                        // return the number of items on the deque
 * public void addFirst(Item item)          // add the item to the front
 * public void addLast(Item item)           // add the item to the end
 * public Item removeFirst()                // remove and return the item from the front
 * public Item removeLast()                 // remove and return the item from the end
 * public Iterator<Item> iterator()         // return an iterator over items in order from front to end
 * public static void main(String[] args)   // unit testing (optional)
 * }

 * Corner cases.  Throw the specified exception for the following corner cases:
 * Throw a java.lang.IllegalArgumentException if the client calls either addFirst() or addLast() with a null argument.
 * Throw a java.util.NoSuchElementException if the client calls either removeFirst() or removeLast when the deque is empty.
 * Throw a java.util.NoSuchElementException if the client calls the next() method in the iterator when there are no more items to return.
 * Throw a java.lang.UnsupportedOperationException if the client calls the remove() method in the iterator.
 *
 *
 * Performance requirements.  Your deque implementation must support each deque operation (including construction) in constant worst-case time.
 * A deque containing n items must use at most 48n + 192 bytes of memory and use space proportional to the number of items currently in the deque.
 * Additionally, your iterator implementation must support each operation (including construction) in constant worst-case time.
 */
public class Deque<Item> implements Iterable<Item> {

  private Node<Item> first;
  private Node<Item> last;
  private int size;

  public Deque()                           // construct an empty deque
  {
    first = null;
    last  = null;
    size = 0;
  }

  public boolean isEmpty()                 // is the deque empty?
  {
    return size == 0;
  }

  public int size()                        // return the number of items on the deque
  {
    return size;
  }

  public void addFirst(Item item)          // add the item to the front
  {
    if(item == null)
      throw new IllegalArgumentException();

    Node<Item> newItem = new Node<Item>();
    newItem.setValue(item);
    newItem.setNext(null);
    newItem.setPrev(null);

    if(isEmpty())
    {
      first = newItem;
      last = first;
    }
    else
    {
      newItem.setNext(first);
      first.setPrev(newItem);
      first = newItem;
    }
    size++;
  }

  public void addLast(Item item)           // add the item to the end
  {
    if(item == null)
      throw new IllegalArgumentException();
    Node<Item> newItem = new Node<Item>();
    newItem.setValue(item);
    newItem.setNext(null);
    newItem.setPrev(null);

    if(isEmpty())
    {
      last = newItem;
      first = last;
    }
    else
    {
      newItem.setPrev(last);
      last.setNext(newItem);
      last = newItem;
    }
    size++;
  }

  public Item removeFirst()                // remove and return the item from the front
  {
    if(isEmpty())
      throw new NoSuchElementException();

    Item removed = first.getValue();

    // If it is last node of the collection
    if(first.getNext() == null) {
      first = null;
      last = null;
    }
    else {
      first = first.getNext();
      first.setPrev(null);
    }

    size--;
    return removed;
  }

  public Item removeLast()                 // remove and return the item from the end
  {
    if(isEmpty())
      throw new NoSuchElementException();

    Item removed = last.getValue();

    // If it is last node of the collection
    if(last.getPrev() == null) {
      first = null;
      last = null;
    }
    else {
      last = last.getPrev();
      last.setNext(null);
    }

    size--;
    return removed;
  }

  public Iterator<Item> iterator()         // return an iterator over items in order from front to end
  {
    return new DequeueIterator<>(first);
  }

  public static void main(String[] args)   // unit testing (optional)
  {}
}

class Node<Item> {
  private Item value;
  private Node<Item> next;
  private Node<Item> prev;

  public Node() {
  }

  public Node(Item value) {
    this.value = value;
  }

  public Item getValue() {
    return value;
  }

  public void setValue(Item value) {
    this.value = value;
  }

  public Node<Item> getNext() {
    return next;
  }

  public void setNext(Node<Item> next) {
    this.next = next;
  }

  public Node<Item> getPrev() {
    return prev;
  }

  public void setPrev(Node<Item> prev) {
    this.prev = prev;
  }
}

class DequeueIterator<Item>implements Iterator<Item>
{
  private Node<Item> current;

  public DequeueIterator(Node<Item> current)
  {
    this.current = current;
  }

  @Override
  public boolean hasNext() {
    return current != null;
  }

  @Override
  public Item next() {
    if(!hasNext())
      throw new NoSuchElementException();
    Item next = current.getValue();
    current = current.getNext();
    return next;
  }

  @Override
  public void remove() {
    throw new UnsupportedOperationException();
  }
}
