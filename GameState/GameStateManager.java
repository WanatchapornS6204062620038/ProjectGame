package GameState;

import java.util.ArrayList;

public class GameStateManager {

    private ArrayList<GameState> gameStates;
    private int currentState;

    public static int MENUSTATE = 0;
    public static int SELECTLEVEL = 1;
    public static int HOWTOPLAY = 2;
    public static int LEVEL1STATE = 3;
    public static int LEVEL2STATE = 4;
    public static int LEVEL3STATE = 5;
    public static int GAMEOVER = 6;
    public static int WINGAME = 7;

    public GameStateManager() {

        gameStates = new ArrayList<GameState>();

        currentState = MENUSTATE;
        gameStates.add(new MenuState(this));
        gameStates.add(new SelectLevel(this));
        gameStates.add(new Howtoplay(this));
        gameStates.add(new Level1State(this));
        gameStates.add(new Level2State(this));
        gameStates.add(new Level3State(this));
        gameStates.add(new GameOver(this));
        gameStates.add(new WinGame(this));

    }
    public void setState(int state) {
        currentState = state;
        gameStates.get(currentState).init();
    }

    public void update() {
        gameStates.get(currentState).update();
    }

    public void draw(java.awt.Graphics2D g) {
        gameStates.get(currentState).draw(g);
    }

    public void keyPressed(int k) {
        gameStates.get(currentState).keyPressed(k);
    }

    public void keyReleased(int k) {
        gameStates.get(currentState).keyReleased(k);
    }

}
