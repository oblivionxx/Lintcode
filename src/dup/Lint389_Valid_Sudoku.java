package dup;
import java.util.HashSet;

/*
Determine whether a Sudoku is valid.
The Sudoku board could be partially filled, where empty cells are filled with the character .
Matrix, Uber
 */
public class Lint389_Valid_Sudoku {
	/**
     * @param board: the board
       @return: wether the Sudoku is valid. 
       O(n^4)
     */
   public boolean isValidSudoku(char[][] board) {
       if(board==null || board.length==0) return false;
       int m = board.length, n = board[0].length;
       
       //check row
       for(int i=0;i<m;i++){
           HashSet<Character> set = new HashSet<>();
           for(int j=0;j<n;j++){
               if(board[i][j]!='.'&& !set.add(board[i][j])) return false;
           }
       }
       
       //check col
       for(int j=0;j<n;j++){
           HashSet<Character> set = new HashSet<>();
           for(int i=0;i<m;i++){
               if(board[i][j]!='.'&& !set.add(board[i][j])) return false;
           }
       }
       
       //check sub-matrix
       for(int i=0;i<3;i++){
           for(int j=0;j<3;j++){
               HashSet<Character> set = new HashSet<>();
               for(int k=i*3;k<i*3+3;k++){
                   for(int l=j*3;l<j*3+3;l++){
                        if(board[k][l]!='.'&& !set.add(board[k][l])) return false;
                   }
               }
           }
       }
       
       return true;
   }
}
