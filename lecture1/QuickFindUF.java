public class QuickFindUF implements UF{
  private int[] arr;

  /** Initialize UNION-FIND data structure with n objects */
  public QuickFindUF(int n)
  {
    // Initialize array element with their index values;
    arr = new int[n];
    for(int i=0; i< n; i++)
      arr[i] = i;
  }

  /** Add connection between a and b */
  public void union(int a, int b)
  {
    int toVal = arr[a];
    int fromVal = arr[b];
    for(int i=0; i < arr.length; i++)
    {
      if(arr[i] == fromVal)
        arr[i] = toVal;
    }
    System.out.println("Union("+a+","+b+")");
  }

  /** Are a and b in same component ? */
  public boolean connected(int a, int b)
  {
    boolean result = find(a) == find(b);
    System.out.println("Connected("+a+","+b+") = " + result);
    return result;
  }

  // Return identifier of component
  public int find(int p)
  {
    return arr[p];
  }

  // Number of components
  public int count()
  {
    return arr.length;
  }

}
