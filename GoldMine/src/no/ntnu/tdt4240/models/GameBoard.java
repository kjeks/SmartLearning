package no.ntnu.tdt4240.models;
import android.content.Context;

public class GameBoard{

	private Cell[][] gameBoard;
	private int numberOfRows, numberOfCols;
	public Context context;

	public GameBoard(Context context){
		gameBoard = new Cell[15][28];
		numberOfRows = 15; 
		numberOfCols = 28;
		this.context = context;
		createBoard(30,30);
		//ta hensyn til at det ikke kan v�re flere miner/gull enn ruter
	}

	public void addGold(int gold){
		while(gold > 0){
			int xValue = (int)(Math.random()*numberOfRows);
			int yValue = (int)(Math.random()*numberOfCols);
			if(gameBoard[xValue][yValue] == null){
				gameBoard[xValue][yValue] = new Gold(null);
				gold--;
			}
		}
	}

	public void addMines(int mines){
		while(mines > 0){
			int xValue=(int) (Math.random()*numberOfRows);
			int yValue=(int) (Math.random()*numberOfCols);
			if(gameBoard[xValue][yValue]==null){
				gameBoard[xValue][yValue]= new Mine(null);
				mines--;
			}
		}	
	}

	private boolean isInsideBounds(int rowCheck, int colCheck) {
		return rowCheck >= 0 && colCheck >= 0 && rowCheck <= numberOfRows && colCheck <= numberOfCols;
	}

	public void addBlanks(){
		for(int row = 0; row < numberOfRows; row++)
			for(int col = 0; col < numberOfCols; col++)
				if(gameBoard[row][col] == null)
					countAdjacentAndCreateBlank(row, col);
	}
	
	public void countAdjacentAndCreateBlank(int x, int y){
		int adjacentGold = 0, adjacentMines = 0;
		for(int currentRow = x-1; currentRow < x+1; currentRow++) //start one up from the cell...
			for(int currentCol = y-1; currentCol < y+1; currentCol++) //...and one left from the cell
				if(isInsideBounds(currentRow, currentCol)){
					if(gameBoard[currentRow][currentCol] != null){
						if(gameBoard[currentRow][currentCol] instanceof Gold)
							adjacentGold++;
						if(gameBoard[currentRow][currentCol] instanceof Mine)
							adjacentMines++;
					}
					gameBoard[x][y] = new Blank(context, adjacentGold, adjacentMines);
				}
	}

	public void createBoard(int gold, int mines){
		addGold(gold);
		addMines(mines);
		addBlanks();
	}

	public Cell[][] getGameBoard() {
		return gameBoard;
	}

	public int getNumberOfRows(){
		return numberOfRows;
	}
	public int getNumberOfCols(){
		return numberOfCols;
	}
}
