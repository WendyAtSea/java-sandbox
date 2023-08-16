package com.dojo;

import com.dojo.dp.Solutions;

public class App {
  public static void main(String[] args) {
    String s1 = "abac";
    String s2 = "cab";
    int value = Solutions.findSCSLength(s1, s2);  // cabac
    System.out.println(value);

    //String s = Solutions.findSCS(s1, s2);
    //System.out.println(s); // cabac
  }
}
