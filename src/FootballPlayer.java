import java.io.Serializable;

/**
 * The FootballPlayer class represents a generic football player with a name and shirt number.
 * It implements the Serializable interface to support serialization.
 */
public class FootballPlayer implements Serializable {

    /**
     * The name of the football player.
     */
    protected String playerName;

    /**
     * The shirt number of the football player.
     */
    protected String shirtNumber;

    /**
     * Default constructor for the FootballPlayer class.
     * Initializes the player with default values.
     */
    public FootballPlayer() {
        super();
    }
}
