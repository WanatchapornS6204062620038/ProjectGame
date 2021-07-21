/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GameState;

import TileMap.Background;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;

public class Howtoplay extends GameState {

    private Background bg;
    private Font font;
    private Color font2;
    private int currentChoice = 0;
    
    
    Howtoplay(GameStateManager gsm){
        this.gsm = gsm;
        
        try {
			
            bg = new Background("/Backgrounds/howtobg.gif", 1);
            bg.setVector(-0.1, 0);
			
            font = new Font("Arial", Font.PLAIN, 12);	
	}
	catch(Exception e) {
            e.printStackTrace();
	}
    }
    
    private void select() {
        if(currentChoice == 0) {
		gsm.setState(GameStateManager.MENUSTATE);
	}
    }
    
    
    
    @Override
    public void init() {}

    @Override
    public void update() {}

    @Override
    public void draw(Graphics2D g) {
        bg.draw(g);
        g.setFont(font);
        if(currentChoice == 0) g.setColor(Color.RED);
        g.drawString("BACK", 280, 20);
        g.drawString("HOW TO PLAY",20, 30);
        g.drawString("LEFT",50, 55);
        g.drawString("RIGHT",50,90);
        g.drawString("JUMP",50,125);
        g.drawString("FLY",100,155);
        g.drawString("HIT",50,190);
         g.drawString("SHOOT",50,225);

    }
    

    @Override
    public void keyPressed(int k) {
        if(k == KeyEvent.VK_ENTER){
			select();
		}
    }

    @Override
    public void keyReleased(int k) {}

}
