import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class Main {
	
	public static void main(String[] args) {
		
		List<Integer> draws = new ArrayList<>();
		List<BingoCard> bingoCards = new ArrayList<>();
		
		/*
		 * File scanner
		 * 1. Populate draws -list that contains the draws
		 * 2. Populate bingoCards -list that contains the individual bingo cards
		 */
		try (Scanner fileReader = new Scanner(Paths.get("input2.txt"))) {
			
			while (fileReader.hasNextLine()) {
				
				String nextLine = fileReader.nextLine().trim();
				
				/*
				 * Populate draws with the line that contains periods.
				 */
				if (nextLine.contains(",")) {
					String[] lineSplit = nextLine.split(",");
					for (String s: lineSplit) {
						draws.add(Integer.valueOf(s));
					}
					
				}
				
				/*
				 * If next line is blank (separator for next bingo cards)
				 * 1. Create a new 5x5 integer array
				 * 2. Populate integer array with number separated with space(s)
				 * 3. Add bingo card to the array of bingo cards
				 */
				if (nextLine.equals("")) {
					int[][] bingoCard = new int[5][5];
					for (int i = 0; i < 5; i++) {
						String nextLine2 = fileReader.nextLine().trim();
						String[] nextLine2Split = nextLine2.split("\s+");
						for (int j = 0; j < 5; j++) {
							bingoCard[i][j] = Integer.valueOf(nextLine2Split[j]);
						}
					}
					bingoCards.add(new BingoCard(bingoCard));
					
				}
				
			}
			
		} catch (Exception e) {
			System.out.println(e);
		}
		
		int winnerNumber = 0; // Tracking integer for the placement
		
		List<Integer> newDraws = new ArrayList<>(); // New array to add draws in one-by-one
		
		/*
		 * Announce the wins in draw order
		 * 1. Break loop if no bingo cards left
		 * 2. Add draws into the new list one-by-one
		 * 3. Loop trough all the bingo cards
		 * 		-Announce winners
		 * 		-Remove winners from list
		 */
		for (int i: draws) {
			
			if (bingoCards.isEmpty()) {
				break;
			}
			
			int removeInt = 0; // Integer to keep track of removable indexes
			List<Integer> removeIntList = new ArrayList<>(); // List removable indexes (in case of multiple winners per draw)
			
			newDraws.add(i);
			
			for (BingoCard bingoCard: bingoCards) {
				int checkWinInt = bingoCard.checkWin(newDraws);
				if (!(checkWinInt == -1)) {
					winnerNumber++; 
					System.out.println("Winner number #" + winnerNumber + " on draw " + newDraws.get(newDraws.size() - 1) + "! Winning score: " + checkWinInt);
					removeIntList.add(removeInt);
				} 
				removeInt++;
			}
			
			int shift = 0; // Compensation for the elements moving in the list during removal
			for (int s: removeIntList) {
				s = s - shift;
				bingoCards.remove(s);
				shift++;
			}
		}
		
	}
	
	
}
