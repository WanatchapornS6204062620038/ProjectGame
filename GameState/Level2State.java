/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GameState;

import Entity.Enemies.GelPop;
import Entity.Enemies.Slugger;
import Entity.Enemies.Tengu;
import Entity.Enemy;
import Entity.Explosion;
import Entity.HUD;
import Entity.Player;
import Entity.Teleport;
import Main.GamePanel;
import TileMap.*;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.util.ArrayList;

public class Level2State extends GameState {

    private GameStateManager gsm;
    private TileMap tileMap;
    private Background bg;
    private Player player;
    private HUD hud;
    private Teleport teleport;
    private int second = 120;

    private ArrayList<Enemy> enemies;
    private ArrayList<Explosion> explosions;

    public Level2State(GameStateManager gsm) {
        this.gsm = gsm;
        init();
        time.start();
    }

    public void init() {

        bg = new Background("/Backgrounds/bglevel1.jpg", 1);
        bg.setVector(-0.1, 0);

        tileMap = new TileMap(gsm, 30);
        tileMap.loadTiles("/Tilesets/tilemap1.gif");
        tileMap.loadMap("/Maps/level1-1.map");
        tileMap.setPosition(0, 0);
        tileMap.setTween(1);

        player = new Player(gsm, tileMap);
        player.setPosition(100, 100);

        populateEnemies();

        hud = new HUD(player);

        explosions = new ArrayList<Explosion>();

        // teleport
        teleport = new Teleport(tileMap);
        teleport.setPosition(3150, 190);

    }

    private void populateEnemies() {

        enemies = new ArrayList<Enemy>();

        Slugger s;
        Point[] points = new Point[]{
            new Point(400, 140),
            new Point(860, 200),
            new Point(1525, 200),
            new Point(1680, 200),
            new Point(1800, 200),
            new Point(2800, 200),
            new Point(2850, 200),
            new Point(2900, 200),
            new Point(2920, 200),
            new Point(2950, 200)
        };
        for (int i = 0; i < points.length; i++) {
            s = new Slugger(tileMap);
            s.setPosition(points[i].x, points[i].y);
            enemies.add(s);
        }

        GelPop p1 = new GelPop(tileMap, player);
        p1.setPosition(300, 140);
        enemies.add(p1);
        GelPop p2 = new GelPop(tileMap, player);
        p2.setPosition(1000, 200);
        enemies.add(p2);
        GelPop p3 = new GelPop(tileMap, player);
        p3.setPosition(1400, 100);
        enemies.add(p3);
        GelPop p4 = new GelPop(tileMap, player);
        p4.setPosition(1500, 100);
        enemies.add(p4);
        GelPop p5 = new GelPop(tileMap, player);
        p5.setPosition(2400, 200);
        enemies.add(p5);
        GelPop p6 = new GelPop(tileMap, player);
        p6.setPosition(2800, 200);
        enemies.add(p6);
        GelPop p7 = new GelPop(tileMap, player);
        p7.setPosition(2800, 250);
        enemies.add(p7);
        GelPop p8 = new GelPop(tileMap, player);
        p8.setPosition(2850, 200);
        enemies.add(p8);
        GelPop p9 = new GelPop(tileMap, player);
        p9.setPosition(2900, 200);
        enemies.add(p9);
        
        
       
        Tengu t = new Tengu(tileMap, player);
        t.setPosition(2900, 200);
        enemies.add(t);

    }
    
    Thread time = new Thread(new Runnable() {
        @Override
        public void run() {
            while(true){
                try{
                    Thread.sleep(1000);
                    if(second != 0){
                        second--;
                    }
                    if(second == 0){
                        time.stop();
                        toGameOver();
                    }
                } catch (InterruptedException ex) {
                   toGameOver();
                
                } 
            }
        }
    });
    
    private void toGameOver() {
        gsm.setState(GameStateManager.GAMEOVER);
    }

    @Override
    public void update() {
        // update player
        player.update();
        teleport.update();

        // update tilemap
        tileMap.setPosition(
                GamePanel.WIDTH / 2 - player.getx(),
                GamePanel.HEIGHT / 2 - player.gety()
        );

        // attack enemies
        player.checkAttack(enemies);

        // update explosions
        for (int i = 0; i < explosions.size(); i++) {
            explosions.get(i).update();
            if (explosions.get(i).shouldRemove()) {
                explosions.remove(i);
                i--;
            }
        }

        // update enemies
        for (int i = 0; i < enemies.size(); i++) {
            Enemy e = enemies.get(i);
            e.update();
            if (e.isDead()) {
                enemies.remove(i);
                i--;
                explosions.add(new Explosion(e.getx(), e.gety()));
            }
        }
        if (enemies.size() == 0) {
            if (teleport.intersects(player)) {
                WinGame();
            }

        }
    }

    public void WinGame() {
        gsm.setState(GameStateManager.WINGAME);
    }

    public void draw(Graphics2D g) {

        //draw background
        bg.draw(g);

        //draw portal
        teleport.draw(g);

        // draw tilemap
        tileMap.draw(g);

        // draw player
        player.draw(g);

        // draw enemies
        for (int i = 0; i < enemies.size(); i++) {
            enemies.get(i).draw(g);
        }

        // draw explosions
        for (int i = 0; i < explosions.size(); i++) {
            explosions.get(i).setMapPosition(
                    (int) tileMap.getx(), (int) tileMap.gety());
            explosions.get(i).draw(g);
        }

        //draw hud
        hud.draw(g);
        
         g.drawString("" + second, 290, 20);

    }

    public void keyPressed(int k) {
        if (k == KeyEvent.VK_A) {
            player.setLeft(true);
        }
        if (k == KeyEvent.VK_D) {
            player.setRight(true);
        }
        if (k == KeyEvent.VK_UP) {
            player.setUp(true);
        }
        if (k == KeyEvent.VK_DOWN) {
            player.setDown(true);
        }
        if (k == KeyEvent.VK_W) {
            player.setJumping(true);
        }
        if (k == KeyEvent.VK_RIGHT) {
            player.setGliding(true);
        }
        if (k == KeyEvent.VK_UP) {
            player.setScratching();
        }
        if (k == KeyEvent.VK_LEFT) {
            player.setFiring();
        }
    }

    public void keyReleased(int k) {
        if (k == KeyEvent.VK_A) {
            player.setLeft(false);
        }
        if (k == KeyEvent.VK_D) {
            player.setRight(false);
        }
        if (k == KeyEvent.VK_UP) {
            player.setUp(false);
        }
        if (k == KeyEvent.VK_DOWN) {
            player.setDown(false);
        }
        if (k == KeyEvent.VK_W) {
            player.setJumping(false);
        }
        if (k == KeyEvent.VK_RIGHT) {
            player.setGliding(false);
        }
    }

}
