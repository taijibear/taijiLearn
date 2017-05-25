//: innerclasses/Parcel6.java
// Nesting a class within a scope.

package thinkInJava.innerclasses;

//下面的例子展示了如何在任意的作用域内嵌入一个内部类

public class Parcel6 {
  
	// 内部跟踪方法
	private void internalTracking(boolean b) {
    if(b) {
    	
        // 追踪滑块?  这是一个 内部类,该类 出现在了一个 if 语句的内部
    	// 实际上该类 并不会收到  if 语句的影响 . 
    	// Tackingslip  类被嵌入在if语句的作用域内，
    	// 这并不是说该类的创建是有条件的，它其实与别的类一起编译过了
    	
    	//然而，在定义  TrackingSlip 的作用域之外 (if 以内) ，它是不可用的，除此之外，它与普通的类一样。
    	
      class TrackingSlip {
        
    	
    	private String id;
    	
    	TrackingSlip(String s) {
    		id = s;
        }
    	
        String getSlip() { return id; }
      
      }
      
      TrackingSlip ts = new TrackingSlip("slip");
      String s = ts.getSlip();
    }
    
    // 不能再此处使用  TrackingSlip
    // Can't use it here! Out of scope:
    // !TrackingSlip ts = new TrackingSlip("x");
  }	

  //  track 轨道  这是一个内部类
  public void track() { internalTracking(true); }
  
  public static void main(String[] args) {
    Parcel6 p = new Parcel6();
    p.track();
  }

} ///:~
