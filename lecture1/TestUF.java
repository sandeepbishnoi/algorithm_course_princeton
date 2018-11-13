public class TestUF {
  public static void main(String[] args)
  {
    System.out.println("Quick Find");
    UF uf = new QuickFindUF(10);
    uf.connected(0,1);
    uf.connected(2,1);
    uf.union(1,5);
    uf.connected(4,5);
    uf.connected(5,1);

    System.out.println("Weighted Quick Union:");
    UF wquf = new WeightedQuickUnionUF(10);
    wquf.connected(0,1);
    wquf.connected(2,1);
    wquf.union(1,5);
    wquf.connected(4,5);
    wquf.connected(5,1);
  }
}
