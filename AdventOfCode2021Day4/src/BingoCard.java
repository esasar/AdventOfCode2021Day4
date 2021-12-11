import java.util.Arrays;
import java.util.List;

public class BingoCard {
	
	public int[][] bingoCard;
	
	public BingoCard(int[][] bingoCard) {
		this.bingoCard = bingoCard;
	}
	
	public int[][] get() {
		return this.bingoCard;
	}
	
	public void set(int[][] bingoCard) {
		this.bingoCard = bingoCard;
	}
	
	/*
	 * Check win and return score in 3 steps:
	 * 1. Replace current draws in the bingo card with -1.
	 * 2. Check if any row or column is populated only with -1s
	 * 3. Return -1 if step 2. wasn't satisfied, otherwise return the score
	 */
	public int checkWin(List<Integer> draws) {
		
		/*
		 * Replaces all draws on the bingo card with -1
		 */
		
		for (Integer i: draws) {
			
			for (int j = 0; j < this.bingoCard.length; j++) {
				for (int k = 0; k < this.bingoCard.length; k++) {
					
					if (this.bingoCard[j][k] == i) {
						this.bingoCard[j][k] = -1;
					}
					
				}
			}
		}
		
		for (int i = 0; i < this.bingoCard.length; i++) {
			
			int rowSum = 0;
			int columnSum = 0;
			
			for (int j = 0; j < this.bingoCard.length; j++) {
				rowSum += this.bingoCard[i][j];
				columnSum += this.bingoCard[j][i];
			}
			
			if (rowSum == -5 || columnSum == -5) {
				/*
				 * Calculate score (sum of elements not drawn)
				 */
				int score = 0;
				for (int[] eachRow: this.bingoCard) {
					for (int k: eachRow) {
						if (!(k == -1)) {
							score += k;
						}
					}
				}
				/*
				 * Return score (positive integer) if win
				 */
				return score;
			}
			
		}
		
		/*
		 * Return -1 if no win.
		 */
		return -1;
	}
}
