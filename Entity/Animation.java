package Entity;

import java.awt.image.BufferedImage;

public class Animation {
	
	private BufferedImage[] frames;
	private int currentFrame;
	private int numFrames;
        private int count;
	
	private int delay;
        
        private long startTime;
        private boolean playedOnce;
	
	public Animation() {
		playedOnce = false;
	}
	
	public void setFrames(BufferedImage[] frames) {
		this.frames = frames;
		currentFrame = 0;
		startTime = System.nanoTime();
		playedOnce = false;
                numFrames = frames.length;
	}
	
	public void setDelay(int i) { delay = i; }
	public void setFrame(int i) { currentFrame = i; }
	public void setNumFrames(int i) { numFrames = i; }
	
	public void update() {
		
		if(delay == -1) return;
		
                long elapsed = (System.nanoTime() - startTime) / 1000000;
		if(elapsed  >= delay) {
			currentFrame++;
                        startTime = System.nanoTime();
		}
		if(currentFrame == numFrames) {
			currentFrame = 0;
                        playedOnce = true;
		}
		
	}
	
	public int getFrame() { return currentFrame; }
	public BufferedImage getImage() { return frames[currentFrame]; }
        public boolean hasPlayedOnce() { return playedOnce; }
        public int getCount() { return count; }
	
}