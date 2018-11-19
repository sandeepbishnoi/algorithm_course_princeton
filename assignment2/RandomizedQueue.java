import java.util.Iterator;

/**
 * Randomized queue. A randomized queue is similar to a stack or queue, except that the item removed
 * is chosen uniformly at random from items in the data structure. Create a generic data type
 * RandomizedQueue that implements the following API:
 *
 * public class RandomizedQueue<Item> implements Iterable<Item> {
 * public RandomizedQueue()                 // construct an empty randomized queue
 * public boolean isEmpty()                 // is the randomized queue empty?
 * public int size()                        // return the number of items on the randomized queue
 * public void enqueue(Item item)           // add the item
 * public Item dequeue()                    // remove and return a random item
 * public Item sample()                     // return a random item (but do not remove it)
 * public Iterator<Item> iterator()         // return an independent iterator over items in random order
 * public static void main(String[] args)   // unit testing (optional)
 * }
 *
 * Iterator.  Each iterator must return the items in uniformly random order. The order of two or more
 * iterators to the same randomized queue must be mutually independent; each iterator must maintain
 * its own random order.
 *
 * Corner cases.  Throw the specified exception for the following corner cases:
 * Throw a java.lang.IllegalArgumentException if the client calls enqueue() with a null argument.
 * Throw a java.util.NoSuchElementException if the client calls either sample() or dequeue() when the randomized queue is empty.
 * Throw a java.util.NoSuchElementException if the client calls the next() method in the iterator when there are no more items to return.
 * Throw a java.lang.UnsupportedOperationException if the client calls the remove() method in the iterator.
 *
 * Performance requirements.  Your randomized queue implementation must support each randomized queue
 * operation (besides creating an iterator) in constant amortized time. That is, any sequence of m
 * randomized queue operations (starting from an empty queue) must take at most cm steps in the worst case,
 * for some constant c. A randomized queue containing n items must use at most 48n + 192 bytes of memory.
 * Additionally, your iterator implementation must support operations next() and hasNext() in constant
 * worst-case time; and construction in linear time; you may (and will need to) use a linear amount
 * of extra memory per iterator.
 */
public class RandomizedQueue<Item> implements Iterable<Item> {
  private Object[] collection;
  private int first;
  private int last;
  private int size;

  public RandomizedQueue()                 // construct an empty randomized queue
  {
    collection = new Object[1];
    size  = 0;
    first = -1;
    last  = -1;
  }

  public boolean isEmpty()                 // is the randomized queue empty?
  {
    return size == 0;
  }

  public int size()                        // return the number of items on the randomized queue
  {
    return size;
  }

  private void resizeDouble()
  {
    int newSize = collection.length * 2;
    Object[] newCollection = new Object[newSize];
    for(int i=first, j=0; i <=last; i++,j++)
      newCollection[j] = collection[i];
    collection = newCollection;
    first=0;
    last=size-1;
  }

  private void resizeHalf()
  {
    int newSize = collection.length/2;
    Object[] newCollection = new Object[newSize];
    for(int i=first, j=0; i <=last; i++,j++)
      newCollection[j] = collection[i];
    collection = newCollection;
    first=0;
    last=size-1;
  }

  private void shiftleft()
  {
    int emptySlots = first;
    if(emptySlots > 0)
    {
      for(int i = first; i <= last; i++)
        collection[i-emptySlots] = collection[i];
    }
    first=0;
    last=size-1;
  }

  public void enqueue(Item item)           // add the item
  {
    if(item == null)
      throw new IllegalArgumentException();

    if(isEmpty())
    {
      first = 0;
      last  = 0;
      collection[first] = item;
    }
    else {
      // If collection is full, need to resize
      if (size == collection.length)
        resizeDouble();

      if (last == collection.length - 1 && first > 0)
        shiftleft();

      collection[++last] = item;
    }
    size++;
  }

  public Item dequeue()                    // remove and return a random item
  {
    if(size ==0)
      throw new java.util.NoSuchElementException();

    Item removed = (Item)collection[first++];
    size--;

    //TODO: Handle if resize is required
    if(size ==0)
    {
      collection = new Object[1];
      first = -1;
      last  = -1;
    }
    else if(size == collection.length/4)
    {
      resizeHalf();
    }
    return removed;
  }

  public Item sample()                     // return a random item (but do not remove it)
  {
    if(size ==0)
      throw new java.util.NoSuchElementException();

    if(size == 1)
      return (Item)collection[first];

    int randomIdx = new java.util.Random().nextInt(last-first) + first;
    return (Item)collection[randomIdx];
  }

  public Iterator<Item> iterator()         // return an independent iterator over items in random order
  {
    return new RandomizedQueueIterator<Item>(size,first,last,collection);
  }

  public static void main(String[] args)   // unit testing (optional)
  {
    RandomizedQueue<Integer> queue = new RandomizedQueue<Integer>();
    queue.enqueue(1);
    queue.enqueue(2);
    queue.enqueue(3);
    queue.enqueue(4);
    queue.enqueue(5);
    queue.enqueue(6);
    queue.enqueue(7);
    queue.enqueue(8);
    queue.enqueue(9);
    queue.enqueue(10);
    queue.enqueue(11);
    queue.enqueue(12);
    queue.enqueue(13);
    queue.enqueue(14);
    queue.enqueue(15);
    queue.enqueue(16);
    queue.enqueue(17);
    queue.enqueue(18);
    queue.enqueue(19);
    queue.enqueue(20);
    queue.enqueue(21);

    System.out.println("Iterating queue now..");
    Iterator<Integer> iter = queue.iterator();
    while(iter.hasNext())
      System.out.print(iter.next() +", ");

    System.out.println("\nRemoved:"+queue.dequeue());
    System.out.println("Removed:"+queue.dequeue());
    System.out.println("Removed:"+queue.dequeue());

    System.out.println("Removed:"+queue.dequeue());
    System.out.println("Removed:"+queue.dequeue());
    System.out.println("Removed:"+queue.dequeue());

    System.out.println("Removed:"+queue.dequeue());
    System.out.println("Removed:"+queue.dequeue());
    System.out.println("Removed:"+queue.dequeue());

    System.out.println("Removed:"+queue.dequeue());
    System.out.println("Removed:"+queue.dequeue());
    System.out.println("Removed:"+queue.dequeue());

    System.out.println("Removed:"+queue.dequeue());
    System.out.println("Removed:"+queue.dequeue());
    System.out.println("Removed:"+queue.dequeue());

    System.out.println("Removed:"+queue.dequeue());
    System.out.println("Removed:"+queue.dequeue());
    System.out.println("Removed:"+queue.dequeue());

    System.out.println("Removed:"+queue.dequeue());
    System.out.println("Removed:"+queue.dequeue());
    System.out.println("Removed:"+queue.dequeue());


    System.out.println("\nIterating queue now..");
    iter = queue.iterator();
    while(iter.hasNext())
      System.out.print(iter.next() +", ");
  }
}

class RandomizedQueueIterator<Item> implements Iterator<Item>
{
  private final int first;
  private final int last;
  private int current;
  private final int stepSize;
  private final Object[] collection;
  private int startPos;

  public RandomizedQueueIterator(int size, int first, int last, Object[] collection)
  {
    this.first = first;
    this.last  = last;
    this.collection = collection;
    if(size/2 == 0)
      stepSize = 1;
    else
      stepSize = new java.util.Random().nextInt((size/2)-1)+1;

    startPos = 0;
    current = first + startPos;
  }

  @Override
  public boolean hasNext() {
    return current != -1;
  }

  @Override
  public Item next() {
    if(!hasNext())
      throw new java.util.NoSuchElementException();

    Item next = (Item)collection[current];

    // Update current pointer

    if(current + stepSize > last)
    {
      // End the iteration
      if(startPos == stepSize-1)
        current = -1;
      else {
        startPos++;
        current = first+startPos;
      }
    }
    else
      current += stepSize;

    return next;
  }
}
