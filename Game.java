import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

public class Game {
    static Scanner input = new Scanner(System.in);
    private Player player1;
    private Player player2;
    private ArrayList<Player> players= new ArrayList<>();
    private Grid grid;
    private HashMap<Player,Integer> score =new HashMap<>();
    private Integer targetScore;

    public Game(Player player1, Player player2, Grid grid,Integer targetScore) {
        this.player1 = player1;
        this.player2 = player2;
        this.grid = grid;
        this.targetScore = targetScore;
        players.add(player1);
        players.add(player2);
        score.put(player1,0);
        score.put(player2,0);
    }

    public void play() {
        int maxScore = 0;
        Player winner = null;
        while (maxScore < this.targetScore) {
            winner = playRound();
            System.out.println(winner.getName() + " won the round");
            maxScore = Math.max(this.score.get(winner), maxScore);

            this.grid.resetGrid();
        }
        System.out.println(winner.getName()+ " won the game");
    }

    private int[] playMove(Player player) {
        showBoard();
        System.out.println(player.getName() + "'s turn");
        int colCnt = this.grid.getCols();
        int moveRow;
        int moveColumn;
        while(true) {
            System.out.print("Enter column between 0 and " + (colCnt - 1) + " to add piece: ");
            moveColumn = input.nextInt();
            moveRow = this.grid.addDiscToGrid(moveColumn, player.getDiscColor());
            if (moveRow!=-1) {
                break;
            }
            else{
                showBoard();
                System.out.println("Invalid move");
            }
        }

        return new int[] { moveRow, moveColumn };
    }


    private Player playRound() {
        while (true) {
            for (Player player : this.players) {
                int[] pos = playMove(player);
                int row = pos[0];
                int col = pos[1];
                DiscColor pieceColor = player.getDiscColor();
                if (this.grid.isThereAWin(col,row, pieceColor,4)) {
                    this.score.put(player, this.score.get(player) + 1);
                    return player;
                }

            }
        }
    }

    public void showBoard() {
        for (int row = grid.getRows()-1; row >=0; row--) {
            System.out.println("");
            for (int col = 0; col < grid.getCols(); col++) {
                System.out.print("|"+grid.getGrid()[row][col].getSymbol()+"|");
            }
        }
    }



}
