public class AI {
	public int minimax(Board currentBoard, int depth, int count) {
		int score = currentBoard.evaluateAI();
		if (score != 0) return score;
		if (!currentBoard.isNotFull()) return 0;
		if (count % 2 == 1) {
			int max = Integer.MIN_VALUE;
			for (int i = 0; i < currentBoard.board.length; i++) {
				for (int j = 0; j < currentBoard.board.length; j++) {
					if (currentBoard.board[i][j] == 1 || currentBoard.board[i][j] == 2) {
						continue;
					}
					currentBoard.board[i][j] = 1;
					max = Math.max(max, minimax(currentBoard, depth + 1, count + 1));
					currentBoard.board[i][j] = 0;
				}
			}
			return max;
		} else {
			int min = Integer.MAX_VALUE;
			for (int i = 0; i < currentBoard.board.length; i++) {
				for (int j = 0; j < currentBoard.board.length; j++) {
					if (currentBoard.board[i][j] == 1 || currentBoard.board[i][j] == 2) {
						continue;
					}
					currentBoard.board[i][j] = 2;
					min = Math.min(min, minimax(currentBoard, depth + 1, count + 1));
					currentBoard.board[i][j] = 0;
				}
			}
			return min;
		}
	}
	public int[] generateNextMove(Board currentBoard) {
		int minVal = Integer.MAX_VALUE;
		int[] res = new int[2];
		for (int i = 0; i < currentBoard.board.length; i++) {
			for (int j = 0; j < currentBoard.board.length; j++) {
				if (currentBoard.board[i][j] != 0) continue;
				currentBoard.board[i][j] = 2;
				int move = minimax(currentBoard, 0, 1);
				currentBoard.board[i][j] = 0;
				if (move < minVal) {
					minVal = move;
					res[0] = i;
					res[1] = j;
				}
			}
		}
		return res;
	}
}