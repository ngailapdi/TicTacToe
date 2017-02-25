import java.util.*;
public class CaroDriver {
	Board b = new Board(3);
	Player player1;
	Player player2;
	static Scanner scan = new Scanner(System.in);
	public void setUp2Player() {
		System.out.println("Player 1, what is your name?");
		String name1 = scan.nextLine();
		player1 = new Player(name1);
		System.out.println("Player 2, whats is your name?");
		String name2 = scan.nextLine();
		player2 = new Player(name2);
		System.out.println("Please enter your move as follow:");
		System.out.println("numberOfRow numberOfColumn");
		System.out.println("Top row starts with 0 and most left column starts with 0");
	}
	public void setUp1Player() {
		System.out.println("Player 1, what is your name?");
		String name1 = scan.nextLine();
		player1 = new Player(name1);
		System.out.println("Please enter your move as follow:");
		System.out.println("numberOfRow numberOfColumn");
		System.out.println("Top row starts with 0 and most left column starts with 0");
	}
	public void printBoard() {
		int[][] board = b.getBoard();
		for (int i = 0; i < board.length; i++) {
			for (int j = 0; j < board.length; j++) {
				System.out.print("|");
				if (board[i][j] == 2) System.out.print("O");
				else if (board[i][j] == 1) System.out.print("X");
				else System.out.print(" ");
			}
			System.out.println("|");
		}
	}
	public static void main(String[] args) {
		CaroDriver obj = new CaroDriver();
		System.out.println("Press S to start");
		System.out.println("Press Q to quit");
		String in = scan.nextLine();
		if (in.equals("S")) {
			System.out.println("Press 0: 1 Player");
			System.out.println("Press 1: 2 Player");
			int choose = scan.nextInt();
			scan.nextLine();
			if (choose == 1) {
				obj.setUp2Player();
				String name1 = obj.player1.getName();
				String name2 = obj.player2.getName();
				boolean count = true;
				while(obj.b.isTie() == 0 && obj.b.isNotFull()) {
					obj.printBoard();
					if (count) {
						System.out.printf("%s, it's your turn\n", name1);
						int x = scan.nextInt();
						int y = scan.nextInt();
						boolean ok = obj.b.go(1, x, y);
						while (!ok) {
							System.out.println("Please go again");
							x = scan.nextInt();
							y = scan.nextInt();
							ok = obj.b.go(1, x, y);
						}
					} else {
						System.out.printf("%s, it's your turn\n", name2);
						int x = scan.nextInt();
						int y = scan.nextInt();
						boolean ok = obj.b.go(2, x, y);
						while (!ok) {
							System.out.println("Please go again");
							x = scan.nextInt();
							y = scan.nextInt();
							ok = obj.b.go(2, x, y);
						}

					}
					count = !count;
				}
				if (obj.b.isTie() == 1) System.out.printf("%s wins!\n", name1);
				else if (obj.b.isTie() == 2) System.out.printf("%s wins!\n", name2);
				else System.out.println("It's a tie");
			} else {
				obj.setUp1Player();
				String name1 = obj.player1.getName();
				AI agent = new AI();
				while(obj.b.isTie() == 0 && obj.b.isNotFull()) {
					obj.printBoard();
					System.out.printf("%s, it's your turn\n", name1);
					int x = scan.nextInt();
					int y = scan.nextInt();
					boolean ok = obj.b.go(1, x, y);
					while (!ok) {
						System.out.println("Please go again");
						x = scan.nextInt();
						y = scan.nextInt();
						ok = obj.b.go(1, x, y);
					}
					int[] nextMove = agent.generateNextMove(obj.b);
					obj.b.go(2, nextMove[0], nextMove[1]);
				}
				if (obj.b.isTie() == 1) System.out.printf("%s 1 wins!\n", name1);
				else if (obj.b.isTie() == 2) System.out.printf("Computer wins!\n");
				else System.out.println("It's a tie");

			}
		} 
		System.out.println("Thank you!");
	}
}