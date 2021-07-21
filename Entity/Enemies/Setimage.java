/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entity.Enemies;

import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;
public class Setimage {
    
    public static BufferedImage[][] Tengu = load("/Sprites/Enemies/Tengu.gif", 30, 30);
    public static BufferedImage[][] DarkEnergy = load("/Sprites/Enemies/DarkEnergy.gif", 20, 20);
    public static BufferedImage[][] Gazer = load("/Sprites/Enemies/Gazer.gif", 39, 20);
    public static BufferedImage[][] GelPop = load("/Sprites/Enemies/GelPop.gif", 25, 25);
    
    public static BufferedImage[][] load(String s, int w, int h) {
		BufferedImage[][] ret;
		try {
			BufferedImage spritesheet = ImageIO.read(Setimage.class.getResourceAsStream(s));
			int width = spritesheet.getWidth() / w;
			int height = spritesheet.getHeight() / h;
			ret = new BufferedImage[height][width];
			for(int i = 0; i < height; i++) {
				for(int j = 0; j < width; j++) {
					ret[i][j] = spritesheet.getSubimage(j * w, i * h, w, h);
				}
			}
			return ret;
		}
		catch(Exception e) {
			e.printStackTrace();
			System.out.println("Error loading graphics.");
			System.exit(0);
		}
		return null;
	}
}
