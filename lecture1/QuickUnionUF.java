public class QuickUnionUF implements UF {
  private int arr[];

  public QuickUnionUF(int n)
  {
    // Initialize array element with their index values;
    arr = new int[n];
    for(int i=0; i< n; i++)
      arr[i] = i;
  }

  @Override
  public void union(int a, int b) {
    System.out.println("Union("+a+","+b+")");
    int rootA = find(a);
    int rootB = find(b);
    arr[rootA] = rootB;
  }

  @Override
  public boolean connected(int a, int b) {
    boolean result = find(a) == find(b);
    System.out.println("Connected("+a+","+b+") = " + result);
    return result;
  }

  // Return identifier of component
  @Override
  public int find(int p) {
    if(arr[p] == p)
      return p;
    else
      return find(arr[p]);
  }

  @Override
  public int count() {
    return 0;
  }
}
