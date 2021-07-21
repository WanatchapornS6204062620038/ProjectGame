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
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;

/**
 *
 * @author poypr
 */
public class WinGame extends GameState {

    private Background bg;
    private BufferedImage icon;
    private Font font;
    private int currentChoice = 0;
    private Color titleColor;
    private Font titleFont;

    private String[] options = {
        "Select New Level",
        "Back To Menu"
    };

    public WinGame(GameStateManager gsm) {
        this.gsm = gsm;

        try {
            // load floating icon
            icon = ImageIO.read(getClass().getResourceAsStream("/HUD/iconhud.gif")).getSubimage(0, 12, 12, 11);

            bg = new Background("/Backgrounds/Winbg.jpg", 1);
            bg.setVector(-0.1, 0);

            titleColor = new Color(0, 0, 0);
            titleFont = new Font(
                    "Century Gothic",
                    Font.BOLD,
                    28);

            font = new Font("Arial", Font.PLAIN, 12);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void init() {
    }

    @Override
    public void update() {
    }

    @Override
    public void draw(Graphics2D g) {
        // draw bg
        bg.draw(g);

        g.setColor(titleColor);
        g.setFont(titleFont);
        g.drawString("YOU WIN!", 150, 70);

        g.setFont(font);
        for (int i = 0; i < options.length; i++) {
            if (i == currentChoice) {
                g.setColor(Color.RED);
            } else {
                g.setColor(Color.WHITE);
            }
            g.drawString(options[i], 175 + i * 8, 100 + i * 25);
        }

        if (currentChoice == 0) {
            g.drawImage(icon, 160, 90, null);
        } else if (currentChoice == 1) {
            g.drawImage(icon, 160, 115, null);
        }
    }

    private void select() {
        if (currentChoice == 0) {
            //RestartGame
            gsm.setState(GameStateManager.SELECTLEVEL);
        }
        if (currentChoice == 1) {
            //BackToMenu
            gsm.setState(GameStateManager.MENUSTATE);
        }
    }

    @Override
    public void keyPressed(int k) {
        if (k == KeyEvent.VK_ENTER) {
            select();
        }
        if (k == KeyEvent.VK_UP) {
            currentChoice--;
            if (currentChoice == -1) {
                currentChoice = options.length - 1;
            }
        }
        if (k == KeyEvent.VK_DOWN) {
            currentChoice++;
            if (currentChoice == options.length) {
                currentChoice = 0;
            }
        }
    }

    @Override
    public void keyReleased(int k) {
    }

}