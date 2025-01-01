import lombok.Data;

@Data
public class Player {

    private String name;
    private PlayerColor playerColor;
    private DiscColor discColor;

    public Player(String name, PlayerColor playerColor,DiscColor discColor) {
        this.name = name;
        this.playerColor = playerColor;
        this.discColor = discColor;
    }

    public String getName() {
        return name;
    }

    public PlayerColor getPlayerColor() {
        return playerColor;
    }

    public DiscColor getDiscColor() {
        return discColor;
    }
}
