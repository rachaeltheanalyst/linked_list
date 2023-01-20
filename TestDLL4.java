public class TestDLL4 {
  public static void main(String[] args) {
    System.out.println("\nDLL Phase 4 tests:");
    test_MEE1();
    test_MEE2();
    //test_MEE3(); not really a fair test
    test_SIE1();
    test_SIE2();
    System.out.println("Phase 4 DLL testing complete.");
  }

  public static void test_MEE1() {
    try {
      //System.out.println("[Missing Element Exception 1]");
      DLL<String> list = new DLL<>();
      String[] empty = {};

      boolean thrown = false;
      try {
        list.removeFirst();
      } catch (MissingElementException e) {
        thrown = true;
      }
      TestCode.runTest("removeFirst from empty list",thrown);
    } catch (Exception e) {
      System.err.println("Test MissingElementException 1:  "+e);
    } catch (NoSuchMethodError e) {
      System.err.println("Test missing method:  "+e);
    }
  }
  
  public static void test_MEE2() {
    try {
      //System.out.println("[Missing Element Exception 2]");
      DLL<String> list = new DLL<String>();
      boolean thrown = false;
      try {
        list.removeLast();
      } catch (MissingElementException e) {
        thrown = true;
      }
      TestCode.runTest("removeLast from empty list",thrown);
    } catch (Exception e) {
      System.err.println("Test MissingElementException 2:  "+e);
    } catch (NoSuchMethodError e) {
      System.err.println("Test missing method:  "+e);
    }
  }
  
  public static void test_MEE3() {
    try {
      //System.out.println("[Missing Element Exception 3]");
      DLL<String> list = new DLL<String>();
      boolean thrown = false;
      try {
        list.remove(null);
      } catch (MissingElementException e) {
        thrown = true;
      }
      TestCode.runTest("remove from empty list",thrown);
    } catch (ArrayIndexOutOfBoundsException e) {
      System.err.println("Test MissingElementException 3:  "+e);
    } catch (NoSuchMethodError e) {
      System.err.println("Test missing method:  "+e);
    }
  }
  
  public static void test_SIE1() {
    try {
      //System.out.println("[Self Insert Exception 1]");
      String[] ab = {"A", "B"};
      DLL<String> list = TestUtils.makeDLL(ab);
      boolean thrown = false;
      try {
        list.spliceByTransfer(list,list.getHead());
      } catch (SelfInsertException e) {
        thrown = true;
      }
      TestCode.runTest("self spliceByTransfer",thrown);
    } catch (Exception e) {
      System.err.println("Test SelfInsertException 1:  "+e);
    } catch (NoSuchMethodError e) {
      System.err.println("Test missing method:  "+e);
    }
  }
    
  public static void test_SIE2() {
    try {
      //System.out.println("[Self Insert Exception 2]");
      String[] ab = {"A", "B"};
      DLL<String> list = TestUtils.makeDLL(ab);
      boolean thrown = false;
      try {
        list.spliceByCopy(list,list.getHead());
      } catch (SelfInsertException e) {
        thrown = true;
      }
      TestCode.runTest("self spliceByCopy",thrown);
    } catch (Exception e) {
      System.err.println("Test SelfInsertException 2:  "+e);
    } catch (NoSuchMethodError e) {
      System.err.println("Test missing method:  "+e);
    }
  }
}