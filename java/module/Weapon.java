package module;

import android.util.ArrayMap;

import java.util.HashMap;
import java.util.Random;

/**
 * Created by j on 1/3/15.
 */
public class Weapon {

    protected String player;
    protected String type;


    public Weapon(String user, String type) {
        this.player = user;
        this.type = type;
    }

    // get list of available weapons
    public static String[] getList() {
        String[] weapons = {"rock", "paper", "scissors", "lizard", "spock"};

        return weapons;
    }

    // returns pseudo-random weapon from weapons list
    public static String random() {
        Random random = new Random();
        String weapon = "";
        String[] weapons = getList();

        int chosen = random.nextInt(weapons.length);

        weapon = weapons[chosen];

        return weapon;
    }
}
