public interface UF {
  /** Add connection between a and b */
  void union(int a, int b);

  /** Are a and b in same component ? */
  boolean connected(int a, int b);

  // Return identifier of component
  int find(int p);

  // Number of components
  int count();
}
