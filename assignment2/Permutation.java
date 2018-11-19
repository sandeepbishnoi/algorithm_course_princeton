import edu.princeton.cs.algs4.StdIn;

public class Permutation {
  public static void main(String[] args)
  {
    if(args.length == 0)
      throw new IllegalArgumentException();

    int k = Integer.parseInt(args[0]);
    Deque<String> collection = new Deque<String>();
    java.util.Random randomizer = new java.util.Random();
    for(int i=0; i < k; i++)
    {
      String next = StdIn.readString();
      boolean addFirst = randomizer.nextBoolean();
      if(addFirst)
        collection.addFirst(next);
      else
        collection.addLast(next);
    }

    java.util.Iterator<String> iter = collection.iterator();
    while(iter.hasNext())
      System.out.println(iter.next());
  }
}

