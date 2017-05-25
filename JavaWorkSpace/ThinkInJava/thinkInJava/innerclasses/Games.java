package thinkInJava.innerclasses;
//: innerclasses/Games.java
// Using anonymous inner classes with the Game framework.

// 游戏 接口
interface Game { boolean move(); }
// 游戏 工厂 接口
interface GameFactory { Game getGame(); }


// 跳棋游戏
class Checkers implements Game {
  private Checkers() {}
  private int moves = 0;
  private static final int MOVES = 3;
  public boolean move() {
	  System.out.println("Checkers move " + moves);
    return ++moves != MOVES;
  }
  // 静态 属性
  // new GameFactor (){} 是一个 内部匿名类的写法
  public static GameFactory factory = new GameFactory() {
    public Game getGame() { return new Checkers(); }
  };
}	
/* Chess 国际象棋? */
class Chess implements Game {
  private Chess() {}
  private int moves = 0;
  private static final int MOVES = 4;
  public boolean move() {
	  System.out.println("Chess move " + moves);
    return ++moves != MOVES;
  }
  public static GameFactory factory = new GameFactory() {
    public Game getGame() { return new Chess(); }
  };
}	

public class Games {
  public static void playGame(GameFactory factory) {
    Game s = factory.getGame();
    while(s.move())
      ;
  }
  public static void main(String[] args) {
    playGame(Checkers.factory);
    playGame(Chess.factory);
  }
} /* Output:
Checkers move 0
Checkers move 1
Checkers move 2
Chess move 0
Chess move 1
Chess move 2
Chess move 3
*///:~
