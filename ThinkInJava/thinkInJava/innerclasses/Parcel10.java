package thinkInJava.innerclasses;
//: innerclasses/Parcel10.java
// Using "instance initialization" to perform
// construction on an anonymous inner class.
/* 
 * 进一步展示了 实例初始化 在 内部 匿名类中的使用方法 
 * */

/***
 * 
 * 所以对于匿名类而言，实例初始化的实际效果就是构造器。当然它受到
了限制一一你不能重载实例初始化方法，所以你仅有一个这样的构造器。
匿名内部类与正规的继承相比有些受限，因为匿名内部类既可以扩展类，
也可以实现接口，但是不能两者兼备。
而且如果是实现接口，也只能实现一个接口。
 * 
 * @author taijibear
 *
 */


public class Parcel10 {
	
  /* 这是一个类的方法, 返回内部类的引用  */	
	
 /* 变量 dest 和 price 因为要在 匿名内部类中使用,所以必须定义为 final */
  public Destination destination(final String dest, final float price) {
    
	 /* 内部类再次使用了 方法内的 内部匿名类,重写外界的类 */ 
	 return new Destination() {
      private int cost;
      // Instance initialization for each object:
      
      /* 初始化方法  实例初始化 */
      
	      {
	        cost = Math.round(price);
	        if(cost > 100)
	          System.out.println("Over budget!");
	      }
	      //  内部类的 属性
	      private String label = dest;
	      // 内部类的 方法
	      public String readLabel() { return label; }
	    
	 };
  }	
  public static void main(String[] args) {
    Parcel10 p = new Parcel10();
    Destination d = p.destination("Tasmania", 101.395F);
  }
} /* Output:
Over budget!
*///:~
