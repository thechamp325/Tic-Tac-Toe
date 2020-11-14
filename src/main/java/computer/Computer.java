package computer;
import java.util.ArrayList;

import model.*;
public class Computer {
	private Model m=null;
	private char[][]board = null;
	private int playerId=2;
	private char player = 'O', opponent = 'X'; 
 


	
	public void setModel(Model m) {
		this.m= m;
	}
	
	public ArrayList<Integer> getMove() {
		ArrayList<Integer> a=null;
		
		if(m.getPlayerId()==this.playerId) {
			board=m.getBoard();
			a= new ArrayList<Integer>();
			
			Move bestMove = findBestMove(); 

//			System.out.println("Best Move is ROW:"+ bestMove.row+ " COL:" + bestMove.col ); 
			a.add(bestMove.row);	a.add(bestMove.col);

			return a;
		}
		return a;
	}
	
	
	// This will return the best possible move for the computer 
	public Move findBestMove() { 
		int bestVal = -1000; 
		Move bestMove = new Move();
		bestMove.row = -1;	bestMove.col = -1; 

		// Traverse all cells, evaluate minimax function for all empty cells and return cell with optimal value. 
		for (int i = 0; i < 3; i++) { 
			for (int j = 0; j < 3; j++) { 
				// Check if cell is empty 
				if (board[i][j] == '\0') { 
					// Make the move 
					board[i][j] = player; 

					// compute evaluation function for this move. 
					int moveVal = minimax(0, false); 

					// Undo the move 
					board[i][j] = '\0'; 

					// If the value of the current move is more than the best value, then update
					if (moveVal > bestVal) { 
						bestMove.row = i; 
						bestMove.col = j; 
						bestVal = moveVal; 
					} 
				} 
			} 
		} 

//		System.out.printf("The value of the best Move is : %d\n\n", bestVal); 

		return bestMove; 
	}
	
	// This is the minimax function. 
	//It considers all possible ways and returns the best value 
	public int minimax(int depth, boolean isMax) { 
		int score = evaluate(); 

		// If Maximizer has won the game return it's evaluated score 
		if (score == 10) 
			return score; 

		// If Minimizer has won the game return it's evaluated score 
		if (score == -10) 
			return score; 

		// If there are no more moves and no winner then it's a tie 
		if (isMovesLeft() == false) 
			return 0; 

		// Maximizer's move 
		if (isMax) { 
			int best = -1000; 

			// Traverse all cells 
			for (int i = 0; i < 3; i++) { 
				for (int j = 0; j < 3; j++) { 
					// Check if cell is empty 
					if (board[i][j]=='\0') { 
						// Make the move 
						board[i][j] = player; 

						// Call minimax recursively and choose the max value 
						best = Math.max(best, minimax(depth + 1, !isMax)); 

						// Undo the move 
						board[i][j] = '\0'; 
					} 
				} 
			} 
			return best; 
		} 

		// Minimizer's move 
		else{ 
			int best = 1000; 

			// Traverse all cells 
			for (int i = 0; i < 3; i++) { 
				for (int j = 0; j < 3; j++) { 
					// Check if cell is empty 
					if (board[i][j] == '\0') { 
						// Make the move 
						board[i][j] = opponent; 

						// Call minimax recursively and choose the min value 
						best = Math.min(best, minimax(depth + 1, !isMax)); 

						// Undo the move 
						board[i][j] = '\0'; 
					} 
				} 
			} 
			return best; 
		} 
	} 

	// contains the x and y coordinates of the move
	static class Move { 
		int row, col; 
	}; 
	

	// This function returns true if there are moves remaining and false if no moves left to play. 
	public boolean isMovesLeft() { 
		for (int i = 0; i < 3; i++) 
			for (int j = 0; j < 3; j++) 
				if (board[i][j] == '\0') 
					return true; 
		return false; 
	} 

	// This is the evaluation function
	public int evaluate() { 
		// Checking for Rows for X or O victory. 
		for (int row = 0; row < 3; row++) { 
			if (board[row][0] == board[row][1] && board[row][1] == board[row][2]) { 
				if (board[row][0] == player) 
					return +10; 
				else if (board[row][0] == opponent) 
					return -10; 
			} 
		} 
	
		// Checking for Columns for X or O victory. 
		for (int col = 0; col < 3; col++) { 
			if (board[0][col] == board[1][col] && board[1][col] == board[2][col]) { 
				if (board[0][col] == player) 
					return +10; 
	
				else if (board[0][col] == opponent) 
					return -10; 
			} 
		} 
	
		// Checking for Diagonals for X or O victory. 
		if (board[0][0] == board[1][1] && board[1][1] == board[2][2]) { 
			if (board[0][0] == player) 
				return +10; 
			else if (board[0][0] == opponent) 
				return -10; 
		} 
	
		if (board[0][2] == board[1][1] && board[1][1] == board[2][0]) { 
			if (board[0][2] == player) 
				return +10; 
			else if (board[0][2] == opponent) 
				return -10; 
		} 
	
		// Else if none of them have won then return 0 
		return 0; 
	} 



} 

