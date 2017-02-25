public class Board {
	int[][] board;
	int n;
	public Board(int n) {
		this.n = n;
		board = new int[n][n];
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
		for (int i = 0; i < n; i++) {
			int count = 0;
			int token = 0;
			for (int j = 0; j < n; j++) {
				if (board[j][i] == 0) {
					count = 0;
					token = 0;
					continue;
				}
				if (board[j][i] != token) {
					count = 1;
					token = board[j][i];
					continue;
				} else {
					count++;
				}
				if (count == 3) return token;
			}
		}
		return 0;
	}
	private int checkRow() {
		for (int i = 0; i < n; i++) {
			int count = 0;
			int token = 0;
			for (int j = 0; j < n; j++) {
				if (board[i][j] == 0) {
					count = 0;
					token = 0;
					continue;
				}
				if (board[i][j] != token) {
					count = 1;
					token = board[i][j];
					continue;
				} else {
					count++;
				}
				if (count == 3) return token;
			}
		}
		return 0;
	}
	private int checkDiagonal() {
		int diagonalFor = checkDiagonalFor();
		int diagonalAgainst = checkDiagonalAgainst();
		if (diagonalFor != 0) return diagonalFor;
		if (diagonalAgainst != 0) return diagonalAgainst;
		return 0;
	}

	private int checkDiagonalFor() {
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {
				if (board[i][j] == 0) {
					continue;
				}
				int count = 0;
				int token = 0;
				count = 1;
				token = board[i][j];
				int r = i + 1;
				int c = j + 1;
				while (r < n && c < n && board[r][c] == token) {
					count++;
					r++;
					c++;
				}
				if (count == 3) return token;
			}
		}
		return 0;
	}
	private int checkDiagonalAgainst() {
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {
				if (board[i][j] == 0) {
					continue;
				}
				int count = 0;
				int token = 0;
				count = 1;
				token = board[i][j];
				int r = i + 1;
				int c = j - 1;
				while (r < n && c >= 0 && board[r][c] == token) {
					count++;
					r++;
					c--;
				}
				if (count == 3) return token;
			}
		}
		return 0;
	}


	public boolean go(int player, int x, int y) {
		if (x < 0 || x > n || y < 0 || y > n) return false;
		else {
			if (board[x][y] != 0) return false;
			else { 
				board[x][y] = player;
				return true;
			}
		}
	}
	public boolean isNotFull() {
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {
				if (board[i][j] == 0) return true;
			}
		}
		return false;
	}

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