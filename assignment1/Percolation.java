import edu.princeton.cs.algs4.WeightedQuickUnionUF;

public class Percolation
{
  private int numOpenSites;
  private final WeightedQuickUnionUF wquf;
  private final int DUMMY_TOP;
  private final int DUMMY_BOTTOM;
  private final int n;
  private final boolean[] isOpen;

  public Percolation(int n)                // create n-by-n grid, with all sites blocked
  {
    this.n = n;
    numOpenSites = 0;
    DUMMY_TOP =0;
    DUMMY_BOTTOM=n*n+1;
    isOpen = new boolean[n*n+2];
    isOpen[DUMMY_TOP] = true;
    isOpen[DUMMY_BOTTOM]=false;
    // n*n sites + two dummy sites
    wquf = new WeightedQuickUnionUF(n*n+2);
  }

  public void open(int row, int col)    // open site (row, col) if it is not open already
  {
    if(row < 1 || col < 1)
      throw new IllegalArgumentException("Invalid parameters");

    int idx =  (row-1) * n + col;

    // Return if it is already open
    if(isOpen[idx])
      return;

    isOpen[idx] = true;
    numOpenSites++;

    // Special case: Connect with DUMMY TOP
    if(row == 1)
      wquf.union(DUMMY_TOP, col);

    // Special case: Connect with DUMMY BOTTOM
    if(row == n)
      wquf.union(idx, DUMMY_BOTTOM);

    // Connect with left site if left is open
    if (col > 1 && isOpen[idx-1])
      wquf.union(idx-1, idx);

    // Connect with right site if right is open
    if (col < n && isOpen[idx+1])
      wquf.union(idx, idx+1);

    // Connect with upper site if upper is open
    if (row > 1 && isOpen[(row-2)*n + col])
      wquf.union((row-2)*n + col, idx);

    // Connect with lower site if lower is open
    if (row < n && isOpen[row*n + col])
      wquf.union(row*n + col, idx);
  }

  private int flattenedIndex(int row, int col)
  {
    return  (row-1) * n + col;
  }

  public boolean isOpen(int row, int col)  // is site (row, col) open?
  {
    if(row < 1 || col < 1)
      throw new IllegalArgumentException("Invalid parameters");
    return isOpen[flattenedIndex(row,col)];
  }

  public boolean isFull(int row, int col)  // is site (row, col) full?
  {
    if(row < 1 || col < 1)
      throw new IllegalArgumentException("Invalid parameters");
    return wquf.connected(DUMMY_TOP,flattenedIndex(row, col));
  }

  public int numberOfOpenSites()       // number of open sites
  {
    return numOpenSites;
  }

  public boolean percolates()              // does the system percolate?
  {
    return wquf.connected(DUMMY_TOP,DUMMY_BOTTOM);
  }

  public static void main(String[] args)   // test client (optional)
  {
    Percolation test = new Percolation(4);
    test.open(2,4);
    test.open(3,2);
    test.open(4,1);
    test.open(3,3);
    test.open(3,4);
    test.open(2,2);
    test.open(1,4);
    test.open(3,1);
    System.out.println(test.percolates());
  }
}

