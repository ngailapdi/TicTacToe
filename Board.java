public class Board {
	int[][] board;
	public Board() {
		board = new int[3][3];
	}
	public int[][] getBoard() {
		return board;
	}
	public int isTie() {
		if (checkColumn() == 0 && checkRow() == 0 && checkDiagonal() == 0) return 0;
		if (checkColumn() == 1 || checkRow() == 1 || checkDiagonal() == 1) return 1;
		return 2;
	}
	private int checkColumn() {
		for (int i = 0; i < 3; i++) {
			int count = 1;
			for (int j = 1; j < 3; j++) {
				if (board[j][i] == board[j-1][i]) count++;
			}
			if (count == 3) return board[0][i];
		}
		return 0;
	}
	private int checkRow() {
		for (int i = 0; i < 3; i++) {
			int count = 1;
			for (int j = 1; j < 3; j++) {
				if (board[i][j] == board[i][j-1]) count++;
			}
			if (count == 3) return board[i][0];
		}
		return 0;
	}
	private int checkDiagonal() {
		int count = 1;
		for (int i = 1; i < 3; i++) {
			if (board[i][i] == board[i-1][i-1]) count++;
		}
		if (count == 3) return board[0][0];
		int count1 = 1;
		if (board[0][2] == board[1][1] && board[1][1] == board[2][0]) return board[0][2];
		return 0;
	}
	public boolean go(int player, int x, int y) {
		if (x < 0 || x > 2 || y < 0 || y > 2) return false;
		else {
			if (board[x][y] != 0) return false;
			else { 
				board[x][y] = player;
				return true;
			}
		}
	}
	public boolean isNotFull() {
		for (int i = 0; i < 3; i++) {
			for (int j = 0; j < 3; j++) {
				if (board[i][j] == 0) return true;
			}
		}
		return false;
	}

	public String getState() {
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < 3; i++) {
			for (int j = 0; j < 3; j++) {
				if (board[i][j] == 0) sb.append('0');
				else if (board[i][j] == 1) sb.append('1');
				else sb.append('2');
			}
		}
		return sb.toString();
	}
	public int[] findEmptySpot() {
		for (int i = 0; i < 3; i++) {
			for (int j = 0; j < 3; j++) {
				int[] a = {i, j};
				if (board[i][j] == 0) return a;
			}
		}
		return new int[2];
	}
	// public void ai(int player, int oldX, int oldY) {
	// 	for (int i = 0; i < 4; i++) {
	// 		newX = oldX + dir[i][0];
	// 		newY = oldY + dir[i][1];
	// 		if (newX < 0 || newX > 3 || newY < 0 || newY > 3) continue;
	// 		if (board[newX][newY] != 0) {
	// 			if (board[newX][newY] == board[oldX][oldY]) board[newX + dir[i][0]]
	// 		}

	// 	}
	// }
	private int checkRowAI() {
		if (checkRow() == 1) return 10;
		if (checkRow() == 2) return -10;
		return 0;
	}

	private int checkColumnAI() {
		if (checkColumn() == 1) return 10;
		if (checkColumn() == 2) return -10;
		return 0;
	}

	private int checkDiagonalAI() {
		if (checkDiagonal() == 1) return 10;
		if (checkDiagonal() == 2) return -10;
		return 0;
	}

	public int evaluateAI() {
		if (isTie() == 1) return 10;
		if (isTie() == 2) return -10;
		return 0;
	}



}