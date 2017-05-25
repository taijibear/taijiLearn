//: innerclasses/Parcel5.java
// Nesting a class within a method.

package thinkInJava.innerclasses;

/**
 * 
 * 第一个例子展示了在方法的作用域内（而不是在其他类的作用域内）创建一个完整的类。
 * 这被称作局部内部类：
 *
 */
public class Parcel5 {
  
	// 目的地 这是一个方法
	public Destination destination(String s) {
	    // 方法内的类, 局部内部类
		class PDestination implements Destination {
	      private String label;
	      private PDestination(String whereTo) {
	        label = whereTo;
	      }
	      public String readLabel() { return label; }
	    }
		
		// 返回来一个 局部内部类?
		return new PDestination(s);
  }
	
	// 重载 了一个方法 
	public Destination destination() {
	    // 方法内的类, 局部内部类
		class PDestination implements Destination {
	      private String label;
	      private PDestination() {
	        label = "go to  the static destination ";
	      }
	      public String readLabel() { return label; }
	    }
		
		// 返回来一个 局部内部类?
		return new PDestination();
  }
	
	
	
  public static void main(String[] args) {
    Parcel5 p = new Parcel5();
    Destination d = p.destination("Tasmania");
    Destination d2 = p.destination();
    
    System.out.println( d.readLabel() );
    System.out.println( d2.readLabel() );
    
    
  }
} ///:~
