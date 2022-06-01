package ca.jrvs.practice.dataStructure;

import ca.jrvs.practice.dataStructure.list.ArrayJLists;
import java.util.ArrayList;
import java.util.List;

class HelloWorld {

  // Your program begins with a call to main().
  // Prints "Hello, World" to the terminal window.
  public static void main(String args[]) {
    System.out.println("Hello, World");
    List<String> arr = new ArrayList<>();
    System.out.println(arr.size());
    arr.add("Mark");
    System.out.println(arr.size());
  }
}
