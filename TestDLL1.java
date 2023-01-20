public class TestDLL1 {
    public static final String[] abc = { "A", "B", "C" };
    public static final String[] cba = { "C", "B", "A" };
    public static final String[] a = { "A"};
    public static final String[] empty = {};

    public static void main(String[] args) {
        // phase 1 tests
        System.out.println("\nDLL Phase 1 tests:");
        test_constructor();
        test_addFirst_1();
        test_addFirst_2();
        test_addFirst_3();
        test_toString();
        System.out.println("Phase 1 DLL testing complete.");
    }

    public static void test_constructor() {
        try {
            //System.out.println("[default constructor]");
            DLL<String> list = new DLL<>();
            TestCode.runTest("default list empty",list.isEmpty());
            TestCode.runTest("default list head null",null==list.getHead());
            TestCode.runTest("default list tail null",null==list.getTail());
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
            DLL<String> list = new DLL<>();
            list.addFirst("A");
            TestCode.runTest("singleton list not empty",!list.isEmpty());
            TestCode.runTest("singleton list head=tail",list.getHead()==list.getTail());
            TestCode.runTest("singleton list element = A",list.getHead().getData().equals("A"));
            TestCode.runTest("singleton list tail next null",null==list.getTail().getNext());
        } catch (Exception e) {
            System.err.println("Test addFirst 1 exception:  "+e);
            e.printStackTrace();
        } catch (NoSuchMethodError e) {
            System.err.println("Test missing method:  "+e);
        }
    }

    public static void test_addFirst_2() {
        try {
            //System.out.println("[addFirst 2]");
            DLL<String> list = new DLL<>();
            list.addFirst("A");
            list.addFirst("B");
            TestCode.runTest("BA list not empty",!list.isEmpty());
            TestCode.runTest("BA second = tail",list.getHead().getNext()==list.getTail());
            TestCode.runTest("BA first B",list.getHead().getData().equals("B"));
            TestCode.runTest("BA second A",list.getHead().getNext().getData().equals("A"));
            TestCode.runTest("BA tail next null",null==list.getTail().getNext());
        } catch (Exception e) {
            System.err.println("Test addFirst 2 exception:  "+e);
            e.printStackTrace();
        } catch (NoSuchMethodError e) {
            System.err.println("Test missing method:  "+e);
        }
    }

    public static void test_addFirst_3() {
        try {
            //System.out.println("[addFirst 3]");
            DLL<String> list = new DLL<>();
            list.addFirst("A");
            list.addFirst("B");
            list.addFirst("C");
            TestCode.runTest("CBA list not empty",!list.isEmpty());
            TestCode.runTest("CBA third = tail",list.getHead().getNext().getNext()==list.getTail());
            TestCode.runTest("CBA first C",list.getHead().getData().equals("C"));
            TestCode.runTest("CBA second B",list.getHead().getNext().getData().equals("B"));
            TestCode.runTest("CBA third A",list.getHead().getNext().getNext().getData().equals("A"));
            TestCode.runTest("CBA tail next null",null==list.getTail().getNext());
            String[] cba = { "C", "B", "A" };
            TestUtils.verifyDLL("Verify CBA",list,cba);
        } catch (Exception e) {
            System.err.println("Test addFirst exception:  "+e);
            e.printStackTrace();
        } catch (NoSuchMethodError e) {
            System.err.println("Test missing method:  "+e);
        }
    }

    public static void test_toString() {
        try {
            //System.out.println("[toString]");
            DLL<String> list = new DLL<>();
            TestCode.runTest("toString []",list.toString().equals("[]"));
            list.addFirst("A");
            TestCode.runTest("toString [A]", list.toString().equals("[A]"));
            list.addFirst("B");
            TestCode.runTest("toString [B, A]", list.toString().equals("[B, A]"));
            list.addFirst("C");
            TestCode.runTest("toString [C, B, A]", list.toString().equals("[C, B, A]"));
        } catch (Exception e) {
            System.err.println("Test toString exception:  "+e);
            e.printStackTrace();
        } catch (NoSuchMethodError e) {
            System.err.println("Test missing method:  "+e);
        }
    }
}