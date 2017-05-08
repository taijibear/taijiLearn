package thinkInJava.innerclasses;
//: innerclasses/AnonymousConstructor.java
// Creating a constructor for an anonymous inner class.
///import static net.mindview.util.Print.*;


/*
在匿名类中不可能有命名构造器（因为它根本没名字！)，但通过
实例初始化，就能够达到为匿名内部类创建一个构造器的效果
*/


/* 一个抽象类 ,作为 基类 */
abstract class Base {
  public Base(int i) {
    System.out.print("Base constructor, i = " + i);
  }
  public abstract void f();
}	

public class AnonymousConstructor {
   /*  一个类的方法 , 而且是个静态方法 */
	
	// 该基类的 参数 i 因为不需要在 内部类中 被使用,所以 无需使用 final 类型
	public static Base getBase(int i) {
    
	/* 一个匿名的 内部类 */	
	return new Base(i) {
      
		// 不知道这段话 该如何理解 
		// 这就是所谓的 实例初始化? 
		// 目前理解为, 类被初始化的时候, 会执行该 代码段. 具体的初始化机制,可以在翻之前章节的时候在行研究.
		{ 
			System.out.println("Inside instance initializer");
		}
      
		public void f() {
    	  System.out.println("In anonymous f()");
      }
    };
  
	
}
  
  
  public static void main(String[] args) {
    Base base = getBase(47);
    base.f();
  }
} /* Output:
Base constructor, i = 47
Inside instance initializer
In anonymous f()
*///:~
