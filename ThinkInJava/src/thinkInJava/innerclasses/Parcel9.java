package thinkInJava.innerclasses;
//: innerclasses/Parcel9.java
// An anonymous inner class that performs
// initialization. A briefer version of Parcel5.java.

// 在匿名类中定义字段时，其执行初始化操作

public class Parcel9 {
  // Argument must be final to use inside
  // anonymous inner class:
	
	// 这是一个类的 方法
	
	/*
	如果定义一个匿名内部类，并且希望它使用一个在其外部定义的对象，那么编译器会要求
	其参数引用是final的
 	*/
  public Destination destination(final String dest) {
    
	  // 方法中的 匿名内部类 
	  return new Destination() {
	      private String label = dest;
	      public String readLabel() { return label; }
	  };
  }
  public static void main(String[] args) {
    Parcel9 p = new Parcel9();
    Destination d = p.destination("Tasmania");
    
    System.out.println ( d.readLabel()  );
    
  }
} ///:~
