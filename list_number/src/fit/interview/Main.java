package fit.interview;

public class Main {
  public static void main(String[] args) {
    NonFunctionalPythonList l1 = new NonFunctionalPythonList(7);
    l1.next = new NonFunctionalPythonList(7);
    l1.next.next = new NonFunctionalPythonList(7);

    NonFunctionalPythonList l2 = new NonFunctionalPythonList(7);
    l2.next = new NonFunctionalPythonList(7);
    l2.next.next = new NonFunctionalPythonList(7);

    NonFunctionalPythonList result = new Solution().addTwoNumbers(l1, l2);
    while (result != null) {
      System.out.print(result.val);
      result = result.next;
    }
  }

  static class NonFunctionalPythonList {
    int val;
    NonFunctionalPythonList next = null;

    NonFunctionalPythonList(int val) {
      this.val = val;
    }
  }

  //This solution relies on reversing list links and has asymptotic complexity of O(n)
  static class Solution {

    public NonFunctionalPythonList addTwoNumbers(NonFunctionalPythonList l1, NonFunctionalPythonList l2) {
      NonFunctionalPythonList readyL1 = reverseLinks(l1);
      NonFunctionalPythonList readyL2 = reverseLinks(l2);

      NonFunctionalPythonList resultHead = null;
      NonFunctionalPythonList resultCursor = null;
      int carry = 0;

      while (readyL1 != null && readyL2 != null) {
        int nextVal = readyL1.val + readyL2.val + carry;
        carry = nextVal / 10;
        nextVal %= 10;
        if (resultCursor == null) {
          resultCursor = new NonFunctionalPythonList(nextVal);
          resultHead = resultCursor;
        } else {
          resultCursor.next = new NonFunctionalPythonList(nextVal);
          resultCursor = resultCursor.next;
        }
        readyL1 = readyL1.next;
        readyL2 = readyL2.next;
      }

      NonFunctionalPythonList rest = null;
      if (readyL1 != null)
        rest = readyL1;
      if (readyL2 != null)
        rest = readyL2;

      while (rest != null) {
        int nextVal = rest.val + carry;
        carry = nextVal / 10;
        nextVal %= 10;
        if (resultCursor == null) {
          resultCursor = new NonFunctionalPythonList(nextVal);
          resultHead = resultCursor;
        } else {
          resultCursor.next = new NonFunctionalPythonList(nextVal);
          resultCursor = resultCursor.next;
        }
        rest = rest.next;
      }

      if (carry != 0)
        resultCursor.next = new NonFunctionalPythonList(carry);

      return resultHead;
    }

    private NonFunctionalPythonList reverseLinks(NonFunctionalPythonList l) {
      if (l.next == null)
        return l;

      NonFunctionalPythonList p = l.next;
      NonFunctionalPythonList next;
      NonFunctionalPythonList prev = l;
      prev.next = null;
      while (p != null) {
        next = p.next;
        p.next = prev;

        prev = p;
        p = next;
      }
      return prev;
    }

  }
}
