public class fooCHM92Hash { 
  /*
   * To reduce space usage, remove the key list if it is not needed. The
   * list is not needed for hash table operation. If you hash a value
   * which is not in the table (which you shouldn't), the only way to
   * detect the miss is to compare against the corresponding table index.
   */
  public static final String[] KEY_LIST = { 
    "January",
    "February",
    "March",
    "April",
    "May",
    "June",
    "July",
    "August",
    "September",
    "October",
    "November",
    "December",
  };

  private static final int[][] TABLE_1 = { 
    {
      23, 1, 14, 6, 17, 23, 19, 6, 
      0, 9, 15, 10, 9, 13, 18, 0, 
      24, 21, 0, 5, 14, 5, 18, 3, 
      21, 21, 12, 5, 7, 2, 6, 12, 
      20, 1, 5, 4, 14, 0, 13, 4, 
      24, 5, 21, 5, 3, 7, 20, 24, 
      16, 0, 0, 9, 13, 21, 9, 8, 
      11, 1, 9, 14, 16, 19, 7, 13
    },
    {
      16, 
      14, 17, 3, 0, 12, 8, 5, 2, 
      14, 4, 22, 14, 7, 15, 11, 8, 
      6, 2, 11, 18, 2, 24, 5, 3, 
      13, 9, 3, 21, 13, 4, 13, 24, 
      5, 2, 18, 7, 4, 18, 10, 0, 
      1, 4, 19, 17, 19, 9, 14, 8, 
      6, 16, 13, 21, 2, 1, 14, 12, 
      14, 2, 7, 20, 10, 4, 18
    },
    {
      18, 12, 
      15, 19, 15, 10, 16, 4, 12, 18, 
      10, 1, 13, 0, 7, 22, 3, 14, 
      22, 11, 3, 10, 20, 17, 0, 6, 
      5, 9, 20, 23, 18, 5, 20, 11, 
      23, 1, 13, 4, 4, 7, 15, 13, 
      1, 16, 12, 6, 1, 15, 2, 24, 
      4, 17, 12, 19, 13, 4, 10, 20, 
      3, 9, 6, 15, 24, 21
    },
    {
      5, 6, 1, 
      5, 0, 8, 0, 17, 17, 14, 21, 
      9, 24, 16, 19, 22, 7, 4, 20, 
      2, 8, 20, 15, 6, 14, 12, 11, 
      7, 14, 12, 12, 9, 13, 15, 22, 
      17, 17, 2, 4, 22, 14, 10, 2, 
      5, 5, 5, 9, 15, 7, 9, 0, 
      1, 14, 6, 4, 24, 0, 11, 21, 
      19, 19, 0, 3, 14
    },
    {
      8, 1, 8, 10, 
      13, 13, 7, 8, 16, 2, 4, 9, 
      2, 14, 8, 16, 24, 20, 0, 7, 
      11, 15, 0, 7, 19, 21, 14, 13, 
      22, 18, 0, 15, 10, 14, 17, 6, 
      17, 3, 14, 19, 5, 21, 23, 22, 
      16, 3, 16, 12, 17, 7, 0, 6, 
      16, 20, 6, 8, 1, 3, 21, 13, 
      9, 22, 13, 21
    },
    {
      22, 6, 19, 16, 7, 
      23, 22, 0, 1, 23, 24, 11, 2, 
      21, 2, 5, 7, 15, 6, 4, 10, 
      16, 12, 21, 23, 12, 22, 7, 0, 
      20, 1, 10, 3, 7, 1, 15, 14, 
      2, 18, 22, 7, 0, 24, 5, 18, 
      1, 5, 16, 13, 18, 22, 10, 20, 
      5, 1, 22, 7, 9, 8, 15, 18, 
      2, 6, 6
    },
    {
      13, 4, 2, 19, 1, 22, 
      10, 5, 1, 21, 11, 9, 5, 15, 
      23, 20, 8, 4, 24, 17, 3, 18, 
      9, 13, 0, 12, 16, 15, 1, 22, 
      16, 15, 5, 10, 16, 14, 18, 9, 
      3, 23, 16, 1, 10, 6, 0, 15, 
      5, 3, 5, 24, 15, 2, 1, 19, 
      8, 12, 9, 10, 3, 21, 22, 14, 
      20, 9
    },
    {
      24, 14, 6, 10, 1, 1, 18, 
      13, 21, 5, 13, 14, 11, 12, 12, 
      6, 17, 6, 18, 15, 18, 0, 13, 
      4, 2, 1, 7, 3, 11, 20, 24, 
      10, 6, 7, 18, 19, 15, 21, 9, 
      17, 20, 1, 18, 21, 2, 15, 2, 
      8, 2, 2, 7, 11, 0, 18, 13, 
      20, 13, 12, 13, 20, 9, 17, 9, 
      18
    },
  };

  private static final int[][] TABLE_2 = { 
    {
      6, 8, 4, 10, 20, 2, 20, 11, 
      24, 0, 19, 10, 9, 7, 11, 5, 
      1, 5, 15, 10, 17, 21, 18, 11, 
      24, 12, 15, 21, 24, 16, 4, 2, 
      13, 8, 23, 5, 13, 8, 0, 22, 
      12, 4, 16, 14, 10, 17, 9, 20, 
      15, 24, 12, 18, 3, 6, 11, 19, 
      18, 1, 5, 22, 10, 4, 3, 11
    },
    {
      6, 
      13, 5, 3, 6, 9, 15, 3, 8, 
      10, 11, 13, 5, 20, 4, 10, 23, 
      11, 12, 1, 1, 1, 10, 14, 24, 
      17, 0, 2, 9, 7, 6, 8, 10, 
      4, 11, 11, 1, 10, 15, 10, 24, 
      11, 9, 16, 23, 6, 16, 0, 15, 
      7, 1, 2, 12, 15, 12, 6, 8, 
      7, 11, 3, 12, 9, 4, 9
    },
    {
      16, 14, 
      11, 6, 2, 1, 4, 13, 7, 12, 
      1, 24, 5, 10, 15, 8, 20, 9, 
      6, 9, 20, 20, 7, 16, 18, 18, 
      2, 16, 5, 16, 14, 18, 23, 7, 
      5, 0, 7, 0, 16, 8, 2, 23, 
      10, 17, 11, 15, 23, 11, 21, 22, 
      8, 16, 12, 0, 16, 12, 3, 18, 
      20, 8, 4, 7, 24, 0
    },
    {
      16, 13, 2, 
      3, 14, 19, 0, 21, 15, 5, 5, 
      7, 12, 24, 11, 24, 18, 23, 0, 
      12, 21, 21, 4, 20, 22, 1, 2, 
      23, 9, 11, 24, 19, 5, 20, 22, 
      22, 5, 1, 2, 15, 21, 11, 17, 
      5, 0, 24, 9, 23, 1, 17, 19, 
      22, 14, 14, 13, 19, 3, 8, 5, 
      16, 18, 22, 9, 23
    },
    {
      0, 22, 6, 3, 
      7, 5, 3, 20, 1, 14, 18, 14, 
      6, 19, 14, 8, 24, 8, 13, 21, 
      14, 21, 18, 15, 18, 8, 16, 13, 
      24, 1, 0, 17, 1, 23, 24, 19, 
      6, 1, 21, 17, 7, 14, 6, 7, 
      3, 12, 24, 11, 10, 19, 14, 4, 
      3, 8, 20, 21, 13, 23, 20, 11, 
      4, 6, 21, 2
    },
    {
      7, 12, 9, 17, 17, 
      11, 23, 16, 22, 22, 24, 15, 7, 
      13, 16, 4, 7, 16, 1, 15, 3, 
      9, 6, 5, 20, 1, 8, 8, 17, 
      7, 3, 15, 21, 10, 23, 5, 18, 
      8, 12, 14, 19, 19, 5, 0, 24, 
      4, 7, 14, 8, 6, 16, 9, 11, 
      12, 6, 9, 24, 15, 14, 14, 4, 
      6, 8, 18
    },
    {
      4, 18, 23, 20, 12, 21, 
      3, 5, 21, 0, 15, 24, 14, 7, 
      9, 1, 5, 12, 14, 13, 12, 9, 
      2, 11, 12, 24, 6, 2, 18, 24, 
      9, 14, 18, 4, 2, 3, 23, 22, 
      9, 6, 19, 2, 23, 23, 14, 18, 
      18, 1, 14, 14, 1, 14, 22, 24, 
      22, 13, 6, 9, 20, 16, 10, 17, 
      10, 17
    },
    {
      3, 5, 0, 6, 7, 0, 10, 
      6, 3, 19, 20, 21, 21, 13, 10, 
      20, 15, 7, 15, 14, 12, 12, 16, 
      23, 15, 14, 21, 8, 5, 8, 2, 
      13, 13, 1, 2, 16, 5, 19, 18, 
      23, 4, 18, 4, 9, 18, 7, 9, 
      11, 7, 11, 18, 24, 3, 8, 24, 
      6, 22, 12, 17, 2, 13, 21, 11, 
      2
    },
  };

  private static final int[] G = { 
    0, 0, 0, 4, 0, 0, 0, 0, 
    9, 9, 1, 0, 0, 4, 7, 2, 
    0, 6, 8, 11, 8, 0, 0, 3, 
    0,   
  };

  /**
   * Determines the hash code of the given string, which is equal to its
   * index in the key list.
   * Precondition: the given string is within the key list.
   * 
   * @param key
   *   the string for which to calculate a hash
   * @return
   *   the hash code of the given string
   */
  public static int hash(String key) {
    int i, u, v;

    for (u = v = i = 0; i < key.length(); ++i) {
      u = (u + TABLE_1[i % 8][key.charAt(i) % 64])
            % 25;
      v = ((v + TABLE_2[i % 8][key.charAt(i) % 64])
            % 25);
    }

    return (G[u] + G[v]) % 12;
  }

  /*
   * Example main method to test the hash function. Every key should hash
   * to its own index. An error message will be printed if this doesn't
   * hold.
   * 
   * Uncomment to use.
   */
  /*
  public static void main(String[] args) {
    for (int i = 0; i < KEY_LIST.length; ++i) {
      int h = hash(KEY_LIST[i]);
      if (h != i) {
        System.err.printf("Error while hashing \"%s\": expected %d but was %d%n", KEY_LIST[i], i, h);
      }
    }
  }
  */
}