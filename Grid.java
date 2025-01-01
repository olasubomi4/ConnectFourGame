
public class Grid {

    private int rows;
    private int cols;
    private DiscColor[][] grid;
    public Grid(int rows, int cols) {
        this.rows = rows;
        this.cols = cols;
        resetGrid();
    }
    public void  resetGrid() {
        grid = new DiscColor[rows][cols];
        for (int row = getRows()-1; row >=0; row--) {
            for (int col = 0; col < getCols(); col++) {
                grid[row][col]=DiscColor.EMPTY;
            }
        }
    }

    public int getRows() {
        return rows;
    }
    public int getCols() {
        return cols;
    }
    public DiscColor[][] getGrid() {
        return grid;
    }

    public int addDiscToGrid(int col,DiscColor discColor) {
        if(discColor == null|| discColor==DiscColor.EMPTY) {
            return -1;
        }
        int row=getEmptyRowBasedOnCol(col);
        if(row==-1|| row>rows || col>cols ){
            return -1;
        }
        grid[row][col] = discColor;
        return row;
    }

    private int getEmptyRowBasedOnCol(int col) {
        for (int row = 0; row < rows; row++) {
            if (grid[row][col] == DiscColor.EMPTY ) {
                return row;
            }
        }
        return -1;
    }

    public Boolean isThereAWin(int lastColPlayed, int lastRowPlayed,DiscColor discColor,Integer numberOfMatchingDiscForWin) {
        if(isThereAWinHorizontally(lastRowPlayed,discColor,numberOfMatchingDiscForWin)){
            return true;
        }
        if(isThereAWinVertically(lastColPlayed,discColor,numberOfMatchingDiscForWin)){
            return true;
        }
        return isThereAWinDiagonally(lastColPlayed, lastRowPlayed, discColor, numberOfMatchingDiscForWin);
    }

    private Boolean isThereAWinHorizontally( int lastRowPlayed,DiscColor discColor,Integer numberOfMatchingDiscForWin) {
        int count=0;
        if(lastRowPlayed>getRows()){
            return false;
        }
        for(int col=0;col<cols;col++){
            if(grid[lastRowPlayed][col] == discColor){
                count++;
                if(count==numberOfMatchingDiscForWin) {
                    return true;
                }
            }
            else{
                count=0;
            }
        }
        return false;
    }

    private Boolean isThereAWinVertically( int lastColPlayed,DiscColor discColor,Integer numberOfMatchingDiscForWin) {
        int count=0;
        if(lastColPlayed>cols||lastColPlayed<0){
            return false;
        }
        for(int row=0;row<rows;row++){
            if(grid[row][lastColPlayed] == discColor){
                count++;
                if(count==numberOfMatchingDiscForWin) {
                    return true;
                }
            }
            else{
                count=0;
            }
        }
        return false;
    }

    private boolean isThereAWinDiagonally(int lastColPlayed, int lastRowPlayed, DiscColor discColor, int numberOfMatchingDiscForWin, int rowDirection, int colDirection) {
        int count = 0;

        for (int step = 0; step < Math.max(rows, cols); step++) {
            int row = lastRowPlayed + step * rowDirection;
            int col = lastColPlayed + step * colDirection;

            if (row < 0 || row >= rows || col < 0 || col >= cols) {
                break;
            }

            if (grid[row][col] == discColor) {
                count++;
                if (count == numberOfMatchingDiscForWin) {
                    return true;
                }
            } else {
                count = 0;
            }
        }
        return false;
    }

    public boolean isThereAWinDiagonally(int lastColPlayed, int lastRowPlayed, DiscColor discColor, int numberOfMatchingDiscForWin) {
        if (isThereAWinDiagonally(lastColPlayed, lastRowPlayed, discColor, numberOfMatchingDiscForWin, -1, -1) ||
                isThereAWinDiagonally(lastColPlayed, lastRowPlayed, discColor, numberOfMatchingDiscForWin, 1, 1)) {
            return true;
        }

        if (isThereAWinDiagonally(lastColPlayed, lastRowPlayed, discColor, numberOfMatchingDiscForWin, -1, 1) ||
                isThereAWinDiagonally(lastColPlayed, lastRowPlayed, discColor, numberOfMatchingDiscForWin, 1, -1)) {
            return true;
        }

        return false;
    }


}
