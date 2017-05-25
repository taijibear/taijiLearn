package thinkInJava.innerclasses;
//: innerclasses/Factories.java

// 服务接口?
interface Service {
  void method1();
  void method2();
}

// 服务工程 接口?
interface ServiceFactory {
  Service getService();
}	

class Implementation1 implements Service {
  private Implementation1() {}
  public void method1() {System.out.print("Implementation1 method1");}
  public void method2() {System.out.print("Implementation1 method2");}
  
  // 作为类的一个属性, 应该在初始化的时候 就会运行. 
  // 尤其是作为一个 静态属性,大约编译阶段就会运行吧
  public static ServiceFactory factory =
    /* new 出来的是 一个 匿名内部类,对  ServiceFacotory   进行了实现 */
    new ServiceFactory() {
	  /* 返回该类的一个引用 */
	  public Service getService() {
        return new Implementation1();
      }
    };
}	

class Implementation2 implements Service {
  private Implementation2() {}
  public void method1() {System.out.print("Implementation2 method1");}
  public void method2() {System.out.print("Implementation2 method2");}
  public static ServiceFactory factory =
    new ServiceFactory() {
      /* 返回该类的一个引用 */
	  public Service getService() {
        return new Implementation2();
      }
    };
}	

public class Factories {
 /* 服务顾客? */ 
 public static void serviceConsumer(ServiceFactory fact) {
    Service s = fact.getService();
    s.method1();
    s.method2();
  }
  public static void main(String[] args) {
    
	  serviceConsumer(Implementation1.factory);
    // Implementations are completely interchangeable:
    serviceConsumer(Implementation2.factory);
  }
} /* Output:
Implementation1 method1
Implementation1 method2
Implementation2 method1
Implementation2 method2
*///:~
