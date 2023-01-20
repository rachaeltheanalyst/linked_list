public class TestSLL3 {
  public static final String[] abc = { "A", "B", "C" };
  public static final String[] ab = { "A", "B"};
  public static final String[] a = { "A"};
  public static final String[] b = { "B"};
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
    System.out.println("\nSLL Phase 3 tests:");
    test_copy_constructor();    
    test_subseqByCopy();
    test_spliceByCopy();
    test_subseqByTransfer();
    test_spliceByTransfer();
    System.out.println("Phase 3 SLL testing complete.");
  }

  public static void test_copy_constructor() {
    try {
      //System.out.println("[subseqByCopy]");
      SLL<String> list = TestUtils.makeSLL(bac);
      TestUtils.verifySLL("copy BAC",new SLL<String>(list),bac);
      TestUtils.verifySLL("copy empty",new SLL<String>(new SLL<String>()),empty);
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
      SLL<String> list = TestUtils.makeSLL(bac);
      SLL<String> list2 = list.subseqByCopy(list.getHead(),2);
      TestUtils.verifySLL("BAC.subseqByCopy(B,2) -> BA",list2,ba);
      TestUtils.verifySLL("BAC.subseqByCopy(B,2): BAC same",list,bac);
      TestCode.runTest("Not shallow BA",list.getHead().getNext()!=list2.getTail());
      System.out.println(list);
      list2 = list.subseqByCopy(list.getHead().getNext(),2);
      System.out.println(list2);
      TestUtils.verifySLL("BAC.subseqByCopy(A,2) -> AC",list2,ac);
      TestUtils.verifySLL("BAC.subseqByCopy(A,2): BAC same",list,bac);
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
      SLL<String> list = TestUtils.makeSLL(dac);
      SLL<String> list2 = TestUtils.makeSLL(eb);
      list.spliceByCopy(list2,list.getHead());
      TestUtils.verifySLL("DAC.spliceByCopy(EB,D) -> DEBAC",list,debac);
      TestUtils.verifySLL("DAC.spliceByCopy(EB,D): EB same",list2,eb);
      TestCode.runTest("Not shallow EB",list.getHead().getNext().getNext()!=list2.getTail());
      list = TestUtils.makeSLL(debac);
      list2 = TestUtils.makeSLL(fg);
      list.spliceByCopy(list2,list.getTail());
      TestUtils.verifySLL("DEBAC.spliceByCopy(FG,C) -> DEBACFG",list,debacfg);
      TestUtils.verifySLL("DEBAC.spliceByCopy(FG,C): FG same",list2,fg);
      TestCode.runTest("Not shallow FG",list.getTail()!=list2.getTail());
      list = TestUtils.makeSLL(debacfg);
      list2 = TestUtils.makeSLL(hi);
      list.spliceByCopy(list2,null);
      TestUtils.verifySLL("DEBACFG.spliceByCopy(HI,null) -> HIDEBACFG",list,hidebacfg);
      TestUtils.verifySLL("DEBACFG.spliceByCopy(HI,null): HI same",list2,hi);
      TestCode.runTest("Not shallow HI",list.getHead().getNext()!=list2.getTail());
      list = TestUtils.makeSLL(abc);
      list2 = TestUtils.makeSLL(empty);
      list.spliceByCopy(list2,list.getHead().getNext());
      TestUtils.verifySLL("ABC.spliceByCopy(empty,B) -> ABC",list,abc);
    } catch (Exception e) {
      System.err.println("Test spliceByCopy exception:  "+e);
      e.printStackTrace();
    } catch (NoSuchMethodError e) {
      System.err.println("Test missing method:  "+e);
    }
  }

  public static void test_subseqByTransfer() {
    try {
      //System.out.println("[subseqByTransfer]");
      SLL<String> list = TestUtils.makeSLL(debac);
      SLL<String> list2 = list.subseqByTransfer(list.getHead(),list.getHead().getNext().getNext());
      TestUtils.verifySLL("DEBAC.subseqByTransfer(D,B) -> EB",list2,eb);    
      TestUtils.verifySLL("DEBAC.subseqByTransfer(D,B) becomes DAC",list,dac);
      list = TestUtils.makeSLL(abc);
      list2 = list.subseqByTransfer(list.getHead(),list.getHead().getNext());
      TestUtils.verifySLL("ABC.subseqByTransfer(A,B) -> B",list2,b);    
      TestUtils.verifySLL("ABC.subseqByTransfer(A,C) becomes AC",list,ac);      list = TestUtils.makeSLL(debacfg);
      list2 = list.subseqByTransfer(list.getHead().getNext().getNext().getNext().getNext(),list.getTail());
      TestUtils.verifySLL("DEBACFG.subseqByTransfer(C,G) -> FG",list2,fg);    
      TestUtils.verifySLL("DEBACFG.subseqByTransfer(C,G) becomes DEBAC",list,debac);  
      list = TestUtils.makeSLL(hidebacfg);
      list2 = list.subseqByTransfer(null,list.getHead().getNext()); 
      TestUtils.verifySLL("HIDEBACFG.subseqByTransfer(null,I) -> HI",list2,hi);  
      TestUtils.verifySLL("HIDEBACFG.subseqByTransfer(null,I) becomes DEBACFG",list,debacfg);  
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
      SLL<String> list = TestUtils.makeSLL(dbac);
      SLL<String> list2 = TestUtils.makeSLL(e);
      list.spliceByTransfer(list2,list.getHead());
      TestUtils.verifySLL("DBAC.spliceByTransfer(E,D) -> DEBAC",list,debac);  
      TestUtils.verifySLL("DBAC.spliceByTransfer(E,D) empties E",list2,empty);  
      list = TestUtils.makeSLL(debac);
      list2 = TestUtils.makeSLL(fg);
      list.spliceByTransfer(list2,list.getHead().getNext().getNext().getNext().getNext());
      TestUtils.verifySLL("DEBAC.spliceByTransfer(E,D) -> DEBACFG",list,debacfg);  
      TestUtils.verifySLL("DEBAC.spliceByTransfer(E,D) empties FG",list2,empty); 
      list = TestUtils.makeSLL(debacfg);
      list2 = TestUtils.makeSLL(hi);
      list.spliceByTransfer(list2,null);
      TestUtils.verifySLL("DEBACFG.spliceByTransfer(HI,null) -> HIDEBACFG",list,hidebacfg);  
      TestUtils.verifySLL("DEBACFG.spliceByTransfer(HI,null) empties HI",list2,empty);
    } catch (Exception e) {
      System.err.println("Test spliceByTransfer exception:  "+e);
      e.printStackTrace();
    } catch (NoSuchMethodError e) {
      System.err.println("Test missing method:  "+e);
    }
  }
}