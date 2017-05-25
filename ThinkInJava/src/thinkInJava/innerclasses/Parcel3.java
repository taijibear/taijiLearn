//: innerclasses/Parcel3.java
// Using .new to create instances of inner classes.

package thinkInJava.innerclasses;

public class Parcel3 {
  class Contents {
    private int i = 11;
    public int value() { return i; }
  }
  class Destination {
    private String label;
    Destination(String whereTo) { label = whereTo; }
    String readLabel() { return label; }
  }
  public static void main(String[] args) {
    Parcel3 p = new Parcel3();
    // Must use instance of outer class
    // to create an instance of the inner class:
    
    // 还是可以通过 new 方法创建的 ,但必须通过外部类的实例进行创建
    Parcel3.Contents c = p.new Contents();
    Parcel3.Destination d = p.new Destination("Tasmania");
 
  }
} ///:~
