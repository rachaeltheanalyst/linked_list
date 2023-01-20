public class TestSLL4 {
  public static void main(String[] args) {
    System.out.println("\nSLL Phase 4 tests:");
    test_MEE1();
    test_MEE2();
    test_MEE3();
    test_SIE1();
    test_SIE2();
    System.out.println("Phase 4 SLL testing complete.");
  }

  public static void test_MEE1() {
    try {
      //System.out.println("[Missing Element Exception 1]");
      SLL<String> list = new SLL<>();
      String[] empty = {};

      boolean thrown = false;
      try {
        list.removeFirst();
      } catch (MissingElementException e) {
        thrown = true;
      }
      TestCode.runTest("removeFirst from empty list",thrown);
    } catch (Exception e) {
      System.err.println("Test spliceByCopy exception:  "+e);
    } catch (NoSuchMethodError e) {
      System.err.println("Test missing method:  "+e);
    }
  }
  
  public static void test_MEE2() {
    try {
      //System.out.println("[Missing Element Exception 2]");
      SLL<String> list = new SLL<String>();
      boolean thrown = false;
      try {
        list.removeLast();
      } catch (MissingElementException e) {
        thrown = true;
      }
      TestCode.runTest("removeLast from empty list",thrown);
    } catch (Exception e) {
      System.err.println("Test spliceByCopy exception:  "+e);
    } catch (NoSuchMethodError e) {
      System.err.println("Test missing method:  "+e);
    }
  }
  
  public static void test_MEE3() {
    try {
      //System.out.println("[Missing Element Exception 3]");
      SLL<String> list = new SLL<String>();
      boolean thrown = false;
      try {
        list.removeAfter(null);
      } catch (MissingElementException e) {
        thrown = true;
      }
      TestCode.runTest("removeAfter from empty list",thrown);
    } catch (Exception e) {
      System.err.println("Test spliceByCopy exception:  "+e);
    } catch (NoSuchMethodError e) {
      System.err.println("Test missing method:  "+e);
    }
  }
  
  public static void test_SIE1() {
    try {
      //System.out.println("[Self Insert Exception 1]");
      String[] ab = {"A", "B"};
      SLL<String> list = TestUtils.makeSLL(ab);
      boolean thrown = false;
      try {
        list.spliceByTransfer(list,list.getHead());
      } catch (SelfInsertException e) {
        thrown = true;
      }
      TestCode.runTest("self spliceByTransfer",thrown);
    } catch (Exception e) {
      System.err.println("Test spliceByCopy exception:  "+e);
    } catch (NoSuchMethodError e) {
      System.err.println("Test missing method:  "+e);
    }
  }
    
  public static void test_SIE2() {
    try {
      //System.out.println("[Self Insert Exception 2]");
      String[] ab = {"A", "B"};
      SLL<String> list = TestUtils.makeSLL(ab);
      boolean thrown = false;
      try {
        list.spliceByCopy(list,list.getHead());
      } catch (SelfInsertException e) {
        thrown = true;
      }
      TestCode.runTest("self spliceByCopy",thrown);
    } catch (Exception e) {
      System.err.println("Test spliceByCopy exception:  "+e);
    } catch (NoSuchMethodError e) {
      System.err.println("Test missing method:  "+e);
    }
  }
}