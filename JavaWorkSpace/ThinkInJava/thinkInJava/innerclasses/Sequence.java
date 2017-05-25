//: innerclasses/Sequence.java
// Holds a sequence of Objects.
package thinkInJava.innerclasses;


/*
 * 
 * 一个.java文件中可以有很多类。不过注意以下几点：

1、public 权限的类只能有一个（也可以一个都没有，但最多只有1个）
2、这个.java文件的文件名必须是public类的类名（一般的情况下，这里放置main方法是程序的入口。）
3、若这个文件中没有public的类，则文件名随便是一个类的名字即可
4、用javac 编译这个.java文件的时候，它会给每一个类生成一个.class文件
 * 
 */

/*
当生成一个内部类的对象时，此对象与制造它的外围对
象（enclosing object）之间就有了一种联系，所以它能访问其外围对象的所有成员，
而不需要任何特殊条件。此外，内部类还拥有其外围类的所有元紊的访问权
*/


/*
 * 
 * 当某个外围类的对象创建了一个内部类对象时，
 * 此内部类对象必定会秘密地捕获一个指向那个外围类对象的引用。
 * 然后，在你访问此外围类的成员时，就是用那个引用来选择外围类的成员。
 * 幸运的是，编译器会帮你处理所有的细节，但你现在可以看到：
 * 内部类的对象只能在与其外围类的对象相关联的情况下才能被创建
 * (就像你应该看到的，在内部类是非statlc类时)。
*  构建内部类对象时，需要一个指向其外围类对象的引用，如果编译器访问不到这个引用就会报错。
 */

interface Selector {
  boolean end();
  Object current();
  void next();
}	

// 序列
 public class Sequence {
  private Object[] items;
  private int next = 0;

  // 构造方法
  public Sequence(int size) { items = new Object[size]; }
  
  public void add(Object x) {
    if(next < items.length)
      items[next++] = x;
  }
  
  // 内部类 (队列选择器? 继承自一个 外部的接口 )
  private class SequenceSelector implements Selector {
    private int i = 0;
    // 内部类可以直接使用 外部类的 对象  items
    public boolean end() { return i == items.length; }
    public Object current() { return items[i]; }
    public void next() { if(i < items.length) i++; }
  }
  
  // 返回 Selector 的引用
  public Selector selector() {
    return new SequenceSelector();
  }	

  public static void main(String[] args) {
    Sequence sequence = new Sequence(10);
    for(int i = 0; i < 10; i++)
      sequence.add(Integer.toString(i));
    // 读取到 内部的  selector
    Selector selector = sequence.selector();
    
    while(!selector.end()) {
      System.out.print(selector.current() + " ");
      selector.next();
    }
    
  }
} /* Output:
0 1 2 3 4 5 6 7 8 9
*///:~
