public class PercolationStats {
  private final int numTrials;
  private final double[] fractions;
  private final double sumFractions;

  public PercolationStats(int n, int trials)    // perform trials independent experiments on an n-by-n grid
  {
    numTrials = trials;
    fractions = new double[trials];
    double totalSites = n*n;
    double tempSumFractions=0.0d;
    for(int i =0; i < trials; i++)
    {
      Percolation percolation = new Percolation(n);
      boolean done = false;
      int numOpenedSites = 0;
      while(!done) {
        java.util.Random random = new java.util.Random();
        int randomRow = random.nextInt(n) + 1;
        int randomCol = random.nextInt(n) + 1;
        if (!percolation.isOpen(randomRow, randomCol)) {
          percolation.open(randomRow, randomCol);
          numOpenedSites++;
        }
        // Minimum number of open sites required to percolate is n (i.e. size of grid)
        done = numOpenedSites >= n && percolation.percolates();
      }
      fractions[i] = numOpenedSites/totalSites;
      tempSumFractions += fractions[i];
    }
    sumFractions = tempSumFractions;
  }

  public double mean()                          // sample mean of percolation threshold
  {
    return sumFractions/numTrials;
  }

  public double stddev()                        // sample standard deviation of percolation threshold
  {
    if(numTrials <= 1)
      return 0.0d;

    double sumSquareFractions = 0.0d;
    for(int i=0; i < fractions.length; i++)
      sumSquareFractions+= Math.pow(fractions[i]-mean(), 2);

    return Math.sqrt(sumSquareFractions/(numTrials-1));
  }

  public double confidenceLo()                  // low  endpoint of 95% confidence interval
  {
    return mean()-((1.96*stddev())/Math.sqrt(numTrials));
  }

  public double confidenceHi()                  // high endpoint of 95% confidence interval
  {
    return mean()+((1.96*stddev())/Math.sqrt(numTrials));
  }

  public static void main(String[] args)        // test client (described below)
  {
    PercolationStats trial1 = new PercolationStats(200,100);
    System.out.println("Mean:"+trial1.mean() + "\nstddev:" + trial1.stddev() + "\n95% confidence interval:(" + trial1.confidenceLo() + "," + trial1.confidenceHi() +")");
    trial1 = new PercolationStats(200,100);
    System.out.println("Mean:"+trial1.mean() + "\nstddev:" + trial1.stddev() + "\n95% confidence interval:(" + trial1.confidenceLo() + "," + trial1.confidenceHi() +")");
    trial1 = new PercolationStats(2,10000);
    System.out.println("Mean:"+trial1.mean() + "\nstddev:" + trial1.stddev() + "\n95% confidence interval:(" + trial1.confidenceLo() + "," + trial1.confidenceHi() +")");
    trial1 = new PercolationStats(2,100000);
    System.out.println("Mean:"+trial1.mean() + "\nstddev:" + trial1.stddev() + "\n95% confidence interval:(" + trial1.confidenceLo() + "," + trial1.confidenceHi() +")");
  }
}
