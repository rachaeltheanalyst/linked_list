public class TestSLL2 {
  public static final String[] abc = { "A", "B", "C" };
  public static final String[] ab = { "A", "B"};
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
    System.out.println("\nSLL Phase 2 tests:");
    test_removeFirst();
    test_addLast();
    test_removeLast();
    test_size();
    test_addAfter();
    test_removeAfter();
    System.out.println("Phase 2 SLL testing complete.");
}

  public static void test_removeFirst() {
    try {
      //System.out.println("[removeFirst]");
      SLL<String> list = TestUtils.makeSLL(cba);
      TestCode.runTest("remove C",list.removeFirst().equals("C"));
      TestUtils.verifySLL("removeFirst -> BA",list,ba);
      TestCode.runTest("remove B",list.removeFirst().equals("B"));
      TestUtils.verifySLL("removeFirst -> A",list,a);
      TestCode.runTest("remove A",list.removeFirst().equals("A"));
      TestUtils.verifySLL("removeFirst -> empty",list,empty);       
    } catch (Exception e) {
      System.err.println("Test removeFirst exception:  "+e);
      e.printStackTrace();
    } catch (NoSuchMethodError e) {
      System.err.println("Test missing method:  "+e);
    }
  }

  public static void test_addLast() {
    try {
      //System.out.println("[addLast]");
      SLL<String> list = new SLL<String>();
      list.addLast("A");
      TestUtils.verifySLL("addLast -> A",list,a);
      list.addLast("B");
      TestUtils.verifySLL("addLast -> AB",list,ab);
      list.addLast("C");
      TestUtils.verifySLL("addLast -> ABC",list,abc);
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
      SLL<String> list = TestUtils.makeSLL(abc);
      TestCode.runTest("removeLast C",list.removeLast().equals("C"));
      TestUtils.verifySLL("removeLast -> AB",list,ab);
      TestCode.runTest("removeLast B",list.removeLast().equals("B"));
      TestUtils.verifySLL("removeLast -> A",list,a);
      TestCode.runTest("removeLast A",list.removeLast().equals("A"));
      TestUtils.verifySLL("removeLast -> empty",list,empty);
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
      SLL<String> list = new SLL<String>();
      TestCode.runTest("size of empty",0==list.size());
      list = TestUtils.makeSLL(a);
      TestCode.runTest("size of A",1==list.size());
      list = TestUtils.makeSLL(ba);
      TestCode.runTest("size of BA",2==list.size());
      list = TestUtils.makeSLL(cba);
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
      SLL<String> list = TestUtils.makeSLL(a);
      list.addAfter(list.getHead(),"B");
      TestUtils.verifySLL("A.addAfter(A,B)",list,ab);
      list.addAfter(list.getTail(),"C");
      TestUtils.verifySLL("AB.addAfter(B,C)",list,abc);
      list.addAfter(list.getHead().getNext(),"D");
      TestUtils.verifySLL("ABC.addAfter(B,D)",list,abdc);
    } catch (Exception e) {
      System.err.println("Test addAfter exception:  "+e);
      e.printStackTrace();
    } catch (NoSuchMethodError e) {
      System.err.println("Test missing method:  "+e);
    }
  }

  public static void test_removeAfter() {
    try {
      //System.out.println("[removeAfter]");
      SLL<String> list = TestUtils.makeSLL(abc);
      TestCode.runTest("ABC after B",list.removeAfter(list.getHead().getNext()).equals("C"));
      TestUtils.verifySLL("ABC removeAfter B -> AB",list,ab);
      TestCode.runTest("AB after A",list.removeAfter(list.getHead()).equals("B"));
      TestUtils.verifySLL("AB removeAfter A -> A",list,a);
      TestCode.runTest("A after null",list.removeAfter(null).equals("A"));
      TestUtils.verifySLL("A removeAfter null -> empty",list,empty);
    } catch (Exception e) {
      System.err.println("Test removeAfter exception:  "+e);
      e.printStackTrace();
    } catch (NoSuchMethodError e) {
      System.err.println("Test missing method:  "+e);
    }
  }
}