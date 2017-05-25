package thinkInJava.innerclasses;
//: innerclasses/Parcel8.java
// Calling the base-class constructor.

public class Parcel8 {
  
	// 这是一个方法 
	public Wrapping wrapping(int x) {
		// Base constructor call:
		
		// 实现了一个 内部 匿名类, 内部类重新定义了(覆盖)  Wrapping
		return new Wrapping(x) { // Pass constructor argument.
			public int value() {
				
					// 父类的  value * 47
					return super.value() * 47;
			}
		}; // Semicolon required
  }
  public static void main(String[] args) {
    Parcel8 p = new Parcel8();
    Wrapping w = p.wrapping(10);
    
    System.out.println(  w.value()  );
  }
} ///:~
