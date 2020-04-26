package fit.interview;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

public class Main {
  public static void main(String[] args){
    int result = Solution.lengthOfLongestSubstring("abrkaabcdefghijjxxx");
    System.out.println(result);
  }

  static class Solution {
    public static int lengthOfLongestSubstring(String s) {
      if (s.isEmpty())
        return 0;

      Map<Integer, Integer> counts = new HashMap<>();
      AtomicInteger maxLength = new AtomicInteger(-1);

      s.chars()
          .forEach(
              value -> {
                Integer prev = counts.get(value);
                if (prev != null) {
                  if (counts.size() > maxLength.get())
                    maxLength.set(counts.size());
                  counts.clear();
                }
                counts.put(value, 1);
              });

      return maxLength.get();
    }
  }
}
