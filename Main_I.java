
//The algorithm assumes that x is player 1 always. The algorithm starts with O's turn to play
import java.util.*;

class Main_I {
  public static void main(String[] args) {
    String[][] board = new String[3][3];
    // int count = 0;

    // Random random = new Random();
    List<String> unfilled = new ArrayList<>();
    for (int i = 0; i < 3; i++) {
      for (int j = 0; j < 3; j++) {
        unfilled.add(i + "" + j);
      }
    }
    String winner = play(board, 0, unfilled);
    if (winner == "X" || winner == "O") {
      System.out.println("The winner is " + winner + "!");
    } else
      System.out.println("There is no winner this time. Stalemate!");

    printBoard(board);

    // run completion algorithm
    // if not complete
    // use recursion, base case is complete game. if game does not end, keep playing
    // check for each of the 8 possible winning methods after each play. if the game
    // nends return true
    // for (int i = 0; i < board.length; i++) {
    // for (int j = 0; j < 3; j++) {
    // if(board[i][j] == null)System.out.print(" _ ");
    // else System.out.print(" " + board[i][j] + " ");
    // }
    // System.out.println();
    // }
  }
  /*
   * Make algorithm better
   * 1. I only need to accept the row and column of the current play. You can only
   * win with the current play obviously, so I dont have to check the whole grid
   * for a win after every play.
   * 2. At the beginning, I can have an arraylist that stores every possible
   * position we can play in, once a position has been played in, we remove it
   * from the arraylist. This way, the random class does not cycle through
   * positions that already contain data.
   * 
   */

  public static String play(String[][] board, int turn, List<String> unfilled) {
    printBoard(board);
    if (turn > 4) {
      if (hCheck(board) != null)
        return hCheck(board);
      if (vCheck(board) != null)
        return vCheck(board);
      if (fdCheck(board) != null)
        return fdCheck(board);
      if (bdCheck(board) != null)
        return bdCheck(board);
      if (isFull(board))
        return null;
    }
    Random random = new Random();
    int m = 9 - turn;
    int index = random.nextInt(m);
    String position = unfilled.get(index);

    int x = position.charAt(0) - '0';
    int y = position.charAt(1) - '0';

    if (turn % 2 == 0) {
      board[x][y] = "X";
    } else if (turn % 2 == 1) {
      board[x][y] = "O";
    }
    unfilled.remove(x + "" + y);
    return play(board, turn + 1, unfilled);
  }

  public static String hCheck(String[][] board) {
    for (int i = 0; i < board.length; i++) {
      if ((board[i][0] == board[i][1]) && (board[i][1] == board[i][2])) {
        return board[i][0];
      }
    }
    return null;
  }

  public static String vCheck(String[][] board) {
    for (int i = 0; i < board.length; i++) {
      if ((board[0][i] == board[1][i]) && (board[1][i] == board[2][i])) {
        return board[0][i];
      }
    }
    return null;
  }

  public static String fdCheck(String[][] board) {
    if (board[0][0] == board[1][1] && board[1][1] == board[2][2])
      return board[0][0];
    return null;
  }

  public static String bdCheck(String[][] board) {
    if ((board[0][2] == board[1][1]) && (board[1][1] == board[2][0])) {
      return board[2][0];
    }
    return null;
  }

  public static Boolean isFull(String[][] board) {
    for (int i = 0; i < board.length; i++) {
      for (int j = 0; j < board[i].length; j++) {
        if (board[i][j] == null)
          return false;
      }
    }
    return true;
  }

  public static void printBoard(String[][] board) {
    System.out.println("BOARD");
    for (int i = 0; i < board.length; i++) {
      for (int j = 0; j < 3; j++) {
        if (board[i][j] == null)
          System.out.print("  _  ");
        else
          System.out.print("  " + board[i][j] + "  ");
      }
      System.out.println();
    }
    System.out.println();
  }
}