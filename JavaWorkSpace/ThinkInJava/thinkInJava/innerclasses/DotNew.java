//: innerclasses/DotNew.java
// Creating an inner class directly using the .new syntax.

package thinkInJava.innerclasses;
// 使用类似  new 的方法 来创建内部类, 不能直接使用new 方法

public class DotNew {
  public class Inner {}
  public static void main(String[] args) {
    DotNew dn = new DotNew();
    
    // 内部类 只能通过 外部类的实例创建
    DotNew.Inner dni = dn.new Inner();
  }
} ///:~
