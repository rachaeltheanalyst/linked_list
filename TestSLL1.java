public class TestSLL1 {
  public static final String[] abc = { "A", "B", "C" };
  public static final String[] a = { "A"};
  public static final String[] empty = {};

  public static void main(String[] args) {
    // phase 1 tests
    System.out.println("\nSLL Phase 1 tests:");
    test_constructor();
    test_addFirst_1();
    test_addFirst_2();
    test_addFirst_3();
    test_toString();
    System.out.println("Phase 1 SLL testing complete.");
  }

  public static void test_constructor() {
    try {
      //System.out.println("[default constructor]");
      SLL<String> list = new SLL<>();
      TestCode.runTest("empty head is null",null==list.getHead());
      TestCode.runTest("empty tail is null",null==list.getTail());
      TestCode.runTest("empty list isEmpty",list.isEmpty());
    } catch (Exception e) {
      System.err.println("Test constructor exception:  "+e);
      e.printStackTrace();
    } catch (NoSuchMethodError e) {
      System.err.println("Test missing method:  "+e);
    }
  }

  public static void test_addFirst_1() {
    try {
      //System.out.println("[addFirst 1]");
      SLL<String> list = new SLL<>();
      list.addFirst("A");
      TestCode.runTest("list of 1 isn't empty",!list.isEmpty());
      TestCode.runTest("same head and tail of singleton",list.getHead()==list.getTail());
      TestCode.runTest("data correct",list.getHead().getData().equals("A"));
      TestCode.runTest("tail's next is null",null==list.getTail().getNext());
    } catch (Exception e) {
      System.err.println("addFirst 1 test exception:  "+e);
      e.printStackTrace();
    } catch (NoSuchMethodError e) {
      System.err.println("Test missing method:  "+e);
    }
  }

  public static void test_addFirst_2() {
    try {
      //System.out.println("[addFirst 2]");
      SLL<String> list = new SLL<>();
      list.addFirst("A");
      list.addFirst("B");
      TestCode.runTest("list of 2 isn't empty",!list.isEmpty());
      TestCode.runTest("second node is tail",list.getHead().getNext()==list.getTail());
      TestCode.runTest("first element is B",list.getHead().getData().equals("B"));
      TestCode.runTest("second element is A",list.getHead().getNext().getData().equals("A"));
      TestCode.runTest("tail's next is null",null==list.getTail().getNext());
    } catch (Exception e) {
      System.err.println("addFirst 2 test exception:  "+e);
      e.printStackTrace();
    } catch (NoSuchMethodError e) {
      System.err.println("Test missing method:  "+e);
    }
  }

  public static void test_addFirst_3() {
    try {
      //System.out.println("[addFirst 3]");
      SLL<String> list = new SLL<>();
      list.addFirst("A");
      list.addFirst("B");
      list.addFirst("C");
      TestCode.runTest("list of 3 isn't empty",!list.isEmpty());
      TestCode.runTest("third node is tail",list.getHead().getNext().getNext()==list.getTail());
      TestCode.runTest("first element is C",list.getHead().getData().equals("C"));
      TestCode.runTest("second element is B",list.getHead().getNext().getData().equals("B"));
      TestCode.runTest("third element is A",list.getHead().getNext().getNext().getData().equals("A"));
      TestCode.runTest("tail's next is null",null==list.getTail().getNext());
      String[] cba = {"C", "B", "A"};
      TestUtils.verifySLL("3-list contents",list,cba);
    } catch (Exception e) {
      System.err.println("addFirst 3 test exception:  "+e);
      e.printStackTrace();
    } catch (NoSuchMethodError e) {
      System.err.println("Test missing method:  "+e);
    }
  }

  public static void test_toString() {
    try {
      //System.out.println("[toString]");
      SLL<String> list = new SLL<>();
      TestCode.runTest("empty list is []",list.toString().equals("[]"));
      list.addFirst("A");
      TestCode.runTest("list [A]",list.toString().equals("[A]"));
      list.addFirst("B");
      TestCode.runTest("list [B, A]",list.toString().equals("[B, A]"));
      list.addFirst("C");    
      TestCode.runTest("list [C, B, A]",list.toString().equals("[C, B, A]"));
    } catch (Exception e) {
      System.err.println("toString test exception:  "+e);
      e.printStackTrace();
    } catch (NoSuchMethodError e) {
      System.err.println("Test missing method:  "+e);
    }
  }
}