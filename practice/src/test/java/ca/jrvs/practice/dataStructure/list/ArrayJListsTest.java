package ca.jrvs.practice.dataStructure.list;

import static org.junit.Assert.assertArrayEquals;

import junit.framework.TestCase;

public class ArrayJListsTest extends TestCase {

  public void setUp() throws Exception {
    super.setUp();
  }

  public void tearDown() throws Exception {
  }

  public void testAdd() {
    JList<String> lst = new ArrayJLists<>();
    lst.add("Mark");
    assertEquals("Mark", lst.get(0));
  }

  public void testToArray() {
    JList<String> lst = new ArrayJLists<>();
    lst.add("Mark");
    Object[] arr = {"Mark"};
    assertArrayEquals(arr, lst.toArray());
  }

  public void testSize() {
    JList<String> lst = new ArrayJLists<>();
    lst.add("Mark");
    assertEquals(1, lst.size());
  }

  public void testIsEmpty() {
    JList<String> lst = new ArrayJLists<>();
    assertEquals(true, lst.isEmpty());
    lst.add("Mark");
    assertEquals(false, lst.isEmpty());
  }

  public void testIndexOf() {
    JList<String> lst = new ArrayJLists<>();
    lst.add("Mark");
    assertEquals(0, lst.indexOf("Mark"));
  }

  public void testContains() {
    JList<String> lst = new ArrayJLists<>();
    lst.add("Mark");
    assertEquals(true, lst.contains("Mark"));
  }

  public void testGet() {
    JList<String> lst = new ArrayJLists<>();
    lst.add("Mark");
    assertEquals("Mark", lst.get(0));
  }

  public void testRemove() {
    JList<String> lst = new ArrayJLists<>();
    lst.add("Mark");
    lst.remove(0);
    assertEquals(true, !lst.contains("Mark"));
  }

  public void testClear() {
    JList<String> lst = new ArrayJLists<>();
    lst.add("Mark");
    lst.add("John");
    assertEquals(true, !lst.isEmpty());
    assertEquals(false, !lst.contains("Mark"));
    assertEquals(false, !lst.contains("John"));
    lst.clear();
    assertEquals(true, lst.isEmpty());
    assertEquals(false, lst.contains("Mark"));
    assertEquals(false, lst.contains("John"));
  }
}