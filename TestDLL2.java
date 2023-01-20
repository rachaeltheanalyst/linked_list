public class TestDLL2 {
  public static final String[] abc = { "A", "B", "C" };
  public static final String[] ab = { "A", "B"};
  public static final String[] ac = { "A", "C"};
  public static final String[] a = { "A"};
  public static final String[] b = { "B"};
  public static final String[] empty = {};
  public static final String[] cba = { "C", "B", "A" };
  public static final String[] ba = { "B", "A" };
  public static final String[] bac = { "B", "A", "C" };
  public static final String[] bc = { "B", "C" };
  public static final String[] abdc = { "A", "B", "D", "C" };

  public static void main(String[] args) {
    // phase 2 tests
    System.out.println("\nDLL Phase 2 tests:");
    test_removeFirst();
    test_addLast();
    test_removeLast();
    test_size();
    test_addAfter();
    test_remove();
    System.out.println("Phase 2 DLL testing complete.");
}

  public static void test_removeFirst() {
    try {
      //System.out.println("[removeFirst]");
      DLL<String> list = TestUtils.makeDLL(cba);
      TestCode.runTest("CBA.removeFirst -> C",list.removeFirst().equals("C"));
      TestUtils.verifyDLL("CBA.removeFirst leaves BA",list,ba);
      TestCode.runTest("BA.removeFirst -> B",list.removeFirst().equals("B"));
      TestUtils.verifyDLL("BA.removeFirst leaves A",list,a);
      TestCode.runTest("A.removeFirst -> A",list.removeFirst().equals("A"));
      TestUtils.verifyDLL("A.removeFirst leaves empty",list,empty);       
    } catch (Exception e) {
      System.err.println("Test removeFIrst exception:  "+e);
      e.printStackTrace();
    } catch (NoSuchMethodError e) {
      System.err.println("Test missing method:  "+e);
    }
  }

  public static void test_addLast() {
    try {
      //System.out.println("[addLast]");
      DLL<String> list = new DLL<String>();
      list.addLast("A");
      TestUtils.verifyDLL("addLast -> A",list,a);
      list.addLast("B");
      TestUtils.verifyDLL("addLast -> AB",list,ab);
      list.addLast("C");
      TestUtils.verifyDLL("addLast -> ABC",list,abc);
    } catch (Exception e) {
      System.err.println("Test addLast exception:  "+e);
      e.printStackTrace();
    } catch (NoSuchMethodError e) {
      System.err.println("Test missing method:  "+e);
    }
  }

  public static void test_removeLast() {
    try {
      //System.out.println("[removeLast]");
      DLL<String> list = TestUtils.makeDLL(abc);
      TestCode.runTest("removeLast C",list.removeLast().equals("C"));
      TestUtils.verifyDLL("removeLast -> AB",list,ab);
      TestCode.runTest("removeLast B",list.removeLast().equals("B"));
      TestUtils.verifyDLL("removeLast -> A",list,a);
      TestCode.runTest("removeLast A",list.removeLast().equals("A"));
      TestUtils.verifyDLL("removeLast -> empty",list,empty);
    } catch (Exception e) {
      System.err.println("Test removeLast exception:  "+e);
      e.printStackTrace();
    } catch (NoSuchMethodError e) {
      System.err.println("Test missing method:  "+e);
    }
  }

  public static void test_size() {
    try {
      //System.out.println("[size]");
      DLL<String> list = new DLL<String>();
      TestCode.runTest("size of empty",0==list.size());
      list = TestUtils.makeDLL(a);
      TestCode.runTest("size of A",1==list.size());
      list = TestUtils.makeDLL(ba);
      TestCode.runTest("size of BA",2==list.size());
      list = TestUtils.makeDLL(cba);
      TestCode.runTest("size of CBA",3==list.size());
    } catch (Exception e) {
      System.err.println("Test size exception:  "+e);
      e.printStackTrace();
    } catch (NoSuchMethodError e) {
      System.err.println("Test missing method:  "+e);
    }
  }

  public static void test_addAfter() {
    try {
      //System.out.println("[addAfter]");
      DLL<String> list = TestUtils.makeDLL(a);
      list.addAfter(list.getHead(),"B");
      TestUtils.verifyDLL("A.addAfter(A,B)",list,ab);
      list.addAfter(list.getTail(),"C");
      TestUtils.verifyDLL("AB.addAfter(B,C)",list,abc);
      list.addAfter(list.getHead().getNext(),"D");
      TestUtils.verifyDLL("ABC.addAfter(B,D)",list,abdc);
    } catch (Exception e) {
      System.err.println("Test addAfter exception:  "+e);
      e.printStackTrace();
    } catch (NoSuchMethodError e) {
      System.err.println("Test missing method:  "+e);
    }
  }

  public static void test_remove() {
    try {
      //System.out.println("[remove]");
      DLL<String> list = TestUtils.makeDLL(abc);
      TestCode.runTest("ABC.remove(B) -> B",list.remove(list.getHead().getNext()).equals("B"));
      TestUtils.verifyDLL("ABC.remove(B) leaves AC", list, ac);
      TestCode.runTest("AC.remove(C) -> C",list.remove(list.getTail()).equals("C"));
      TestUtils.verifyDLL("AC.remove(C) leaves A", list, a);
      TestCode.runTest("A.remove(A) -> A",list.remove(list.getHead()).equals("A"));
      TestUtils.verifyDLL("A.remove(A) leaves empty",list, empty);
    } catch (Exception e) {
      System.err.println("Test subseqByCopy exception:  "+e);
      e.printStackTrace();
    } catch (NoSuchMethodError e) {
      System.err.println("Test missing method:  "+e);
    }
  }
}