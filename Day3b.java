public class Day3b {

	static int gridSize = 101;
	static int[][] grid = new int[gridSize][gridSize];
	static int center = (gridSize - 1) / 2;
	
	static public int addSurrounding(int i,int j){
			return grid[i][j+1]+grid[i-1][j+1]+grid[i-1][j]
				+ grid[i-1][j-1]+grid[i][j-1]+grid[i+1][j-1]
				+ grid[i+1][j]+grid[i+1][j+1];
		}

	public static void main(String args[]){
		
		grid[center][center] = 1;
		int up = 1;
		int left = 2;
		int down = 2;
		int right = 3;
		boolean upB = true;
		boolean leftB = false;
		boolean downB = false;
		boolean rightB = false;
		int temp;
		int i = center;
		int j = center;
		
		int input = Integer.parseInt(args[0]);

		// init with one step right
		j++;
		grid[i][j] = addSurrounding(i,j);
		temp = up;

		while(input >= grid[i][j]){
			if(upB){
				if(temp > 0){
					i--;
					temp--;
					grid[i][j] = addSurrounding(i,j);
					System.out.println(grid[i][j]);
				}
				else{
					up = up + 2;
					upB = false;
					leftB = true;
					temp = left;
				}
			}
			else if(leftB){
				if(temp > 0){
					j--;
					temp--;
					grid[i][j] = addSurrounding(i,j);
					System.out.println(grid[i][j]);
				}
				else{
					left = left + 2;
					leftB = false;
					downB = true;
					temp = down;
				}

			}		
			else if(downB){
				if(temp > 0){
					i++;
					temp--;
					grid[i][j] = addSurrounding(i,j);
					System.out.println(grid[i][j]);			
				}
				else{
					down = down + 2;
					downB = false;
					rightB = true;
					temp = right; 
				}
			}
			else if(rightB){
				if(temp > 0){
					j++;
					temp--;
					grid[i][j] = addSurrounding(i,j);
					System.out.println(grid[i][j]);
				}
				else {
					right = right + 2;
					rightB = false;
					upB = true;
					temp = up;
				}
			}
		}
		//System.out.println(grid[i][j]);
	}
}
