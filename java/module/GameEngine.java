package module;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by j on 1/3/15.
 */
public class GameEngine {

    // check which player wins in clash
    // 0 = draw
    // 1 = player one wins
    // 2 = player two wins
    public int fight(String playerOne, String playerTwo) {
        int winner = 0;

        // retrieve entry for player one weapon in lookup table
        Map<String, Map<String, Integer>> table = new HashMap<String, Map<String, Integer>>();
        table = createTable();
//        HashMap<String, Integer> weapon = (HashMap)table.get(playerOne);

        // retrieve int value for player two weapon
        // 0 = draw
        // 1 = player one wins
        // 2 = player two wins
        winner = table.get(playerOne).get(playerTwo);

        return winner;
    }

    // construct look up table for weapon comparison
    // values:
    // 0 = draw
    // 1 = player one wins
    // 2 = player two wins
    private HashMap createTable() {
        HashMap map = new HashMap();

        // rock look up
        HashMap<String, Integer> rock = new HashMap();
        rock.put("scissors", 1);
        rock.put("lizard", 1);
        rock.put("paper", 2);
        rock.put("spock", 2);
        rock.put("rock", 0);

        HashMap<String, Integer> scissors = new HashMap();
        scissors.put("paper", 1);
        scissors.put("lizard", 1);
        scissors.put("rock", 2);
        scissors.put("spock", 2);
        scissors.put("scissors", 0);

        HashMap<String, Integer> paper = new HashMap();
        paper.put("rock", 1);
        paper.put("spock", 1);
        paper.put("scissors", 2);
        paper.put("lizard", 2);
        paper.put("paper", 0);

        HashMap<String, Integer> lizard = new HashMap();
        lizard.put("paper", 1);
        lizard.put("spock", 1);
        lizard.put("rock", 2);
        lizard.put("scissors", 2);
        lizard.put("lizard", 0);

        HashMap<String, Integer> spock = new HashMap();
        spock.put("rock", 1);
        spock.put("scissors", 1);
        spock.put("paper", 2);
        spock.put("lizard", 2);
        spock.put("spock", 0);

        // Add hash map to map
        map.put("rock", rock);
        map.put("scissors", scissors);
        map.put("paper", paper);
        map.put("lizard", lizard);
        map.put("spock", spock);

        return map;
    }
}
