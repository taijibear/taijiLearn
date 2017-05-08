//: innerclasses/DotThis.java
// Qualifying access to the outer-class object.

package thinkInJava.innerclasses;


// 使用this

public class DotThis {
  void f() { System.out.println("DotThis.f()"); }
  public class Inner {
    public DotThis outer() {
      return DotThis.this;
      // A plain "this" would be Inner's "this"
    }
  }
  public Inner inner() { return new Inner(); }
  
  public static void main(String[] args) {
    DotThis dt = new DotThis();
    
    // 返回一个内部类的引用
    DotThis.Inner dti = dt.inner();
    
    // 内部类 的一个方法, 返回了 DoThis.this
    // 得到了外部类的引用之后, 调用  f()
    dti.outer().f();
  }
} /* Output:
DotThis.f()
*///:~
