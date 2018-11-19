package lecture2;

/** Implementation of Generic Stack */
class GenericStack<T>
{
  GenericNode<T> head;

  public GenericStack()
  {
    head = null;
  }

  public T pop()
  {
    T popped = head.value;
    head = head.next;
    return popped;
  }

  public void push(T item)
  {
    // Add an element in stack
    GenericNode newHead = new GenericNode(item);
    newHead.next = head;
    head = newHead;
  }

  public boolean isEmpty()
  {
    return head == null;
  }

}
