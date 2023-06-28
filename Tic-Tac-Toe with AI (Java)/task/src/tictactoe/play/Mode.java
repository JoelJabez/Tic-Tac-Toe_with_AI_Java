package tictactoe.play;

import java.util.Arrays;
import java.util.Random;
import java.util.Scanner;

import static tictactoe.play.GameStatus.isGameOver;
import static tictactoe.play.GameStatus.turn;

class Mode {

	static Scanner scanner = new Scanner(System.in);
	static Random random = new Random();
	static char[][] ticTacToeTable = new char[3][3];

	private String player1;
	private String player2;

	Mode(String player1, String player2) {
		this.player1 = player1;
		this.player2 = player2;
	}

	String getPlayer1() {
		return player1;
	}

	String getPlayer2() {
		return player2;
	}

	static void createEmptyBoard() {
		for (char[] chars: ticTacToeTable) {
			Arrays.fill(chars, ' ');
		}
	}

	void userInput() {
		int yCoordinate;
		int xCoordinate;
		while (true) {
			try {
				System.out.print("Enter the coordinates: ");
				String coordinates = scanner.nextLine();
				xCoordinate = Integer.parseInt(coordinates.split(" ")[0]);
				yCoordinate = Integer.parseInt(coordinates.split(" ")[1]);

				if (checkRange(xCoordinate, yCoordinate)) {
					xCoordinate -= 1;
					yCoordinate -= 1;

					if (isOccupied(ticTacToeTable, yCoordinate, xCoordinate)) {
						System.out.println("This cell is occupied! Choose another one!");
					} else {
						ticTacToeTable[xCoordinate][yCoordinate] = turn() ? 'X': 'O';
						printTable(ticTacToeTable);
						break;
					}

				} else {
					System.out.println("Coordinates should be from 1 to 3!");
				}
			} catch (NumberFormatException nfe) {
				System.out.println("You should enter numbers!");
			} catch (ArrayIndexOutOfBoundsException aioobe) {
				System.out.println("Please give two numbers!");
			}
		}
	}

	void computerInput() {}

	void battleAgainst() {
		createEmptyBoard();
		printTable(ticTacToeTable);
		do {
			userInput();
		} while(!isGameOver(ticTacToeTable));
	}

	static boolean isOccupied(char[][] ticTacToe, int yCoordinate, int xCoordinate) {
		return ticTacToe[xCoordinate][yCoordinate] != ' ';
	}

	static void printTable(char[][] ticTacToe) {
		System.out.println("---------");
		for (char[] firstArray: ticTacToe) {
			System.out.print("|");
			for (char secondArray: firstArray) {
				System.out.print(" " + secondArray);
			}
			System.out.println(" |");
		}
		System.out.println("---------");
	}

	static boolean checkRange(int x, int y) {
		return (1 <= x && x <= 3) && (1 <= y && y <= 3);
	}
}
