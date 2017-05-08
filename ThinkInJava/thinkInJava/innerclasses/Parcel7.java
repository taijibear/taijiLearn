package thinkInJava.innerclasses;
//: innerclasses/Parcel7.java
// Returning an instance of an anonymous inner class.


public class Parcel7 {
 
	public Contents contents() {
	      
		  // Contents 是在 new 的时候 直接定义的 匿名内部类
		
		// 上面的描述是不准确的,
		// 该方法并不是定义类一个 内部类,而是扩展了一个 已经存在的类.
		  return new Contents() {
	    	// Insert a class definition
		      private int i = 11;
		      public int value() { return i; }
	    
		 }; // Semicolon required in this case
	}
	
	
	public Object contents2() {
	      
		  // Contents 是在 new 的时候 直接定义的 匿名内部类
		
		// 试验了一下,其实是没办法  凭空返回一个 匿名类的. 
		// 只是说可以 实现一个 匿名的类,该类 是对 已经存在 类的 一种继承
		  return new Object() {
	    	// Insert a class definition
		      private int i = 11;
		      public int value() { return i; }
	    
		 }; // Semicolon required in this case
	}
	
	
	
  
	public static void main(String[] args) {
	    Parcel7 p = new Parcel7();
	    Contents c = p.contents();
	}
} ///:~
