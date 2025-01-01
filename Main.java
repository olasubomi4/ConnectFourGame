public class Main {

    public static void main(String[] args) {
        Player player1= new Player("Subomi",PlayerColor.RED,DiscColor.RED);
        Player player2= new Player("AI",PlayerColor.YELLOW,DiscColor.YELLOW);
        Grid grid = new Grid(6, 7);
        Game game = new Game(player1, player2, grid,4);
        game.play();
    }
}
