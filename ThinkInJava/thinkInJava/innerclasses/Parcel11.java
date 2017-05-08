package thinkInJava.innerclasses;
//: innerclasses/Parcel11.java
// Nested classes (static inner classes).

/*
 * 如果不需要内部类对象与其外围类对象之间有联系，
 * 那么可以将内部类声明为static。这通常称为嵌套类
 *
 * 想要理解static应用于内部类时的含义，就必须记住，
 * 普通的内部类对象隐式地保存了一个引用，指向创建它的外围类对象。
 * 然而，当内部类是static的时，就不是这样了
 * 嵌套类意味着：
	1.要创建嵌套类的对象，并不需要其外围类的对象。
	2.不能从嵌套类的对象中访问  非静态的外围类对象。
	
嵌套类与普通的内部类还有一个区别。普通内部类的字段与方法，只能放在类的外部层次
上，所以普通的内部类不能有static数据和static字段，也不能包含嵌套类。
但是嵌套类可以包含以上所有.
 * 
 */


public class Parcel11 {
   // 一个静态的 内部类
  private static class ParcelContents implements Contents {
    private int i = 11;
    public int value() { return i; }
   }
  
  // 静态内部类
  protected static class ParcelDestination
  implements Destination {
    private String label;
    private ParcelDestination(String whereTo) {
      label = whereTo;
    }
    public String readLabel() { return label; }	
    // Nested classes can contain other static elements:
    // 内部类的静态方法
    public static void f() {}
    
    static int x = 10;
    // 内部类 里面的 内部类? 静态的
    static class AnotherLevel {
    public static void f() {}
      static int x = 10;
    }
  }
  //外部类的 普通 静态 方法, 返回 内部类的引用
  public static Destination destination(String s) {
    return new ParcelDestination(s);
  }
  
  // 外部类的普通 静态 方法 返回内部类的 引用
  public static Contents contents() {
    return new ParcelContents();
  }
  public static void main(String[] args) {
   // 不再需要通过外部类 来创建
	Contents c = contents();
    Destination d = destination("Tasmania");
  }
} ///:~
