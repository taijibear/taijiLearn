//: innerclasses/TestParcel.java

package thinkInJava.innerclasses;

class Parcel4 {
 
 // private 内部类 继承自 	 Contents  实现  value方法 返回 11
  class PContents implements Contents {
    private int i = 11;
    public int value() { return i; }
  }
 
 // protected  内部类 继承自  Destination 实现 readLabel 方法 返回 where to 
   class PDestination implements Destination {
    private String label;
    
    // 构造方法 成了 private 就没法从外面被调用了吧?
    private PDestination(String whereTo) {
      label = whereTo;
    }
    public String readLabel() { return label; }
  }
  
  // 返回内部类的引用
  public Destination destination(String s) {
    return new PDestination(s);
  }
  
  // 返回内部类的引用
  public Contents contents() {
    return new PContents();
  }
}

public class TestParcel {
  public static void main(String[] args) {
    // 实例化  Parcel4
	Parcel4 p = new Parcel4();
   // 获取 Parcel4 的  Contents
	Contents c = p.contents();
    // 调用 并 获取  Parcel4 的  Destination
	Destination d = p.destination("Tasmania");
	
	/*
		private PDestination 是一个私有的 内部类 因此在 类型外部 无法访问
		
	
	*/
	
    // Illegal -- can't access private class:
    //! Parcel4.PContents pc = p.new PContents();
	
	 //Parcel4.PDestination pd = p.new PDestination( "my label");
  }
} ///:~
