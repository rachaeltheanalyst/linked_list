public class TestDLL3 {
  public static final String[] abc = { "A", "B", "C" };
  public static final String[] ab = { "A", "B"};
  public static final String[] a = { "A"};
  public static final String[] b = { "B"};
  public static final String[] c = { "C"};
  public static final String[] empty = {};
  public static final String[] cba = { "C", "B", "A" };
  public static final String[] ba = { "B", "A" };
  public static final String[] dac = { "D", "A", "C" };
  public static final String[] eb = { "E", "B" };
  public static final String[] bac = { "B", "A", "C" };
  public static final String[] dbac = {"D", "B", "A", "C" };
  public static final String[] bc = { "B", "C" };
  public static final String[] ac = {"A", "C"};
  public static final String[] e = {"E"};
  public static final String[] de = {"D", "E"};
  public static final String[] debac = {"D", "E", "B", "A", "C"};
  public static final String[] fg = {"F", "G"};
  public static final String[] debacfg = {"D", "E", "B", "A", "C", "F", "G"};
  public static final String[] dbacfg = {"D", "B", "A", "C", "F", "G"};
  public static final String[] hi = {"H", "I"};
  public static final String[] hidebacfg = {"H", "I", "D", "E", "B", "A", "C", "F", "G"};
  public static final String[] bacfg = {"B", "A", "C", "F", "G"};    

  public static void main(String[] args) {
    // phase 3 tests
    System.out.println("\nDLL Phase 3 tests:");
    test_copy_constructor();    
    test_subseqByCopy();
    test_spliceByCopy();
    test_subseqByTransfer();
    test_spliceByTransfer();
    System.out.println("Phase 3 DLL testing complete.");
  }

  public static void test_copy_constructor() {
    try {
      //System.out.println("[subseqByCopy]");
      DLL<String> list = TestUtils.makeDLL(bac);
      TestUtils.verifyDLL("copy BAC",new DLL<String>(list),bac);
      TestUtils.verifyDLL("copy empty",new DLL<String>(new DLL<String>()),empty);
    } catch (Exception e) {
      System.err.println("Test copy constructor exception:  "+e);
      e.printStackTrace();
    } catch (NoSuchMethodError e) {
      System.err.println("Test missing method:  "+e);
    }
  }

  public static void test_subseqByCopy() {
    try {
      //System.out.println("[subseqByCopy]");
      DLL<String> list = TestUtils.makeDLL(bac);
      DLL<String> list2 = list.subseqByCopy(list.getHead(),2);
      TestUtils.verifyDLL("BAC.subseqByCopy(B,2) -> BA",list2,ba);
      TestUtils.verifyDLL("BAC.subseqByCopy(B,2): BAC same",list,bac);
      TestCode.runTest("Not shallow BA",list.getHead().getNext()!=list2.getTail());
      list2 = list.subseqByCopy(list.getHead().getNext(),2);
      TestUtils.verifyDLL("BAC.subseqByCopy(A,2) -> AC",list2,ac);
      TestUtils.verifyDLL("BAC.subseqByCopy(A,2): BAC same",list,bac);
      TestCode.runTest("Not shallow AC",list.getTail()!=list2.getTail());
    } catch (Exception e) {
      System.err.println("Test subseqByCopy exception:  "+e);
      e.printStackTrace();
    } catch (NoSuchMethodError e) {
      System.err.println("Test missing method:  "+e);
    }
  }

  public static void test_spliceByCopy() {
    try {
      //System.out.println("[spliceByCopy]");
      DLL<String> list = TestUtils.makeDLL(dac);
      DLL<String> list2 = TestUtils.makeDLL(eb);
      list.spliceByCopy(list2,list.getHead());
      TestUtils.verifyDLL("DAC.spliceByCopy(EB,D) -> DEBAC",list,debac);
      TestUtils.verifyDLL("DAC.spliceByCopy(EB,D): EB same",list2,eb);
      TestCode.runTest("Not shallow EB",list.getHead().getNext().getNext()!=list2.getTail());
      list = TestUtils.makeDLL(debac);
      list2 = TestUtils.makeDLL(fg);
      list.spliceByCopy(list2,list.getTail());
      TestUtils.verifyDLL("DEBAC.spliceByCopy(FG,C) -> DEBACFG",list,debacfg);
      TestUtils.verifyDLL("DEBAC.spliceByCopy(FG,C): FG same",list2,fg);
      TestCode.runTest("Not shallow FG",list.getTail()!=list2.getTail());
      list = TestUtils.makeDLL(debacfg);
      list2 = TestUtils.makeDLL(hi);
      list.spliceByCopy(list2,null);
      TestUtils.verifyDLL("DEBACFG.spliceByCopy(HI,null) -> HIDEBACFG",list,hidebacfg);
      TestUtils.verifyDLL("DEBACFG.spliceByCopy(HI,null): HI same",list2,hi);
      TestCode.runTest("Not shallow HI",list.getHead().getNext()!=list2.getTail());
      list = TestUtils.makeDLL(abc);
      list2 = TestUtils.makeDLL(empty);
      list.spliceByCopy(list2,list.getHead().getNext());
      TestUtils.verifyDLL("ABC.spliceByCopy(empty,B) -> ABC",list,abc);
    } catch (ArrayIndexOutOfBoundsException e) {
      System.err.println("Test spliceByCopy exception:  "+e);
      e.printStackTrace();
    } catch (NoSuchMethodError e) {
      System.err.println("Test missing method:  "+e);
    }
  }

  public static void test_subseqByTransfer() {
    try {
      //System.out.println("[subseqByTransfer]");
      DLL<String> list = TestUtils.makeDLL(debac);
      DLL<String> list2 = list.subseqByTransfer(list.getHead().getNext(),list.getHead().getNext().getNext());
      TestUtils.verifyDLL("DEBAC.subseqByTransfer(E,B) -> EB",list2,eb);    
      TestUtils.verifyDLL("DEBAC.subseqByTransfer(E,B) becomes DAC",list,dac);
      list = TestUtils.makeDLL(abc);
      list2 = list.subseqByTransfer(list.getHead().getNext(),list.getTail());
      TestUtils.verifyDLL("ABC.subseqByTransfer(B,C) -> BC",list2,bc);    
      TestUtils.verifyDLL("ABC.subseqByTransfer(B,C) becomes A",list,a);      
      list = TestUtils.makeDLL(abc);
      list2 = list.subseqByTransfer(list.getHead(),list.getTail().getPrevious());
      TestUtils.verifyDLL("ABC.subseqByTransfer(A,B) -> AB",list2,ab);    
      TestUtils.verifyDLL("ABC.subseqByTransfer(A,B) becomes C",list,c);  
      list = TestUtils.makeDLL(abc);
      list2 = list.subseqByTransfer(list.getHead(),list.getTail()); 
      TestUtils.verifyDLL("ABC.subseqByTransfer(A,C) -> ABC",list2,abc);  
      TestUtils.verifyDLL("ABC.subseqByTransfer(A,C) becomes empty",list,empty);  
    } catch (Exception e) {
      System.err.println("Test subseqByTransfer exception:  "+e);
      e.printStackTrace();
    } catch (NoSuchMethodError e) {
      System.err.println("Test missing method:  "+e);
    }
  }

  public static void test_spliceByTransfer() {
    try {
      //System.out.println("[spliceByTransfer]");
      DLL<String> list = TestUtils.makeDLL(dbac);
      DLL<String> list2 = TestUtils.makeDLL(e);
      list.spliceByTransfer(list2,list.getHead());
      TestUtils.verifyDLL("DBAC.spliceByTransfer(E,D) -> DEBAC",list,debac);  
      TestUtils.verifyDLL("DBAC.spliceByTransfer(E,D) empties E",list2,empty);  
      list = TestUtils.makeDLL(debac);
      list2 = TestUtils.makeDLL(fg);
      list.spliceByTransfer(list2,list.getHead().getNext().getNext().getNext().getNext());
      TestUtils.verifyDLL("DEBAC.spliceByTransfer(E,D) -> DEBACFG",list,debacfg);  
      TestUtils.verifyDLL("DEBAC.spliceByTransfer(E,D) empties FG",list2,empty); 
      list = TestUtils.makeDLL(debacfg);
      list2 = TestUtils.makeDLL(hi);
      list.spliceByTransfer(list2,null);
      TestUtils.verifyDLL("DEBACFG.spliceByTransfer(HI,null) -> HIDEBACFG",list,hidebacfg);  
      TestUtils.verifyDLL("DEBACFG.spliceByTransfer(HI,null) empties HI",list2,empty);
    } catch (Exception e) {
      System.err.println("Test spliceByTransfer exception:  "+e);
      e.printStackTrace();
    } catch (NoSuchMethodError e) {
      System.err.println("Test missing method:  "+e);
    }
  }
}