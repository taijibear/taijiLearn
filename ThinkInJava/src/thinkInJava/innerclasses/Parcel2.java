package thinkInJava.innerclasses;
//: innerclasses/Parcel2.java
// Returning a reference to an inner class.

public class Parcel2 {
	
	//内部类 内容
	class Contents {
	    private int i = 11;
	    public int value() { return i; }
	}
	
	//内部类 目的地
	 class Destination {
	    private String label;
	    
	    // 构造方法
	    Destination(String whereTo) {
	      label = whereTo;
	    }
	    String readLabel() { return label; }
	 }

	//外部类将有一个方法，该方法返回一个指向内部类的引用 
	// 创建一个 内部类?
  public Destination to(String s) {
    return new Destination(s);
  }
//创建一个 内部类?
  public Contents contents() {
    return new Contents();
  }
  
 // 外部类的方法
  public void ship(String dest) {
    Contents c = contents();
    Destination d = to(dest);
    System.out.println(d.readLabel());
  }
  
  
  public static void main(String[] args) {
    Parcel2 p = new Parcel2();
    p.ship("Tasmania");
    Parcel2 q = new Parcel2();
    // Defining references to inner classes:
    // 可以 以这种方式 引用 和 创建 内部类 对象 
    Parcel2.Contents c = q.contents();
    Parcel2.Destination d = q.to("Borneo");
    
    // 测试 是否可以直接创建内部类呢?
    
    // 结论,内部类无法由外界 直接 实例化 得到
    //Parcel2.Contents tc =  new Parcel2.Contents();
    
    
  
  
  }
} /* Output:
Tasmania
*///:~
