package platformer.graphics;

import java.awt.image.BufferedImage;

public class SpriteSheet {

	BufferedImage spriteSheet;
	
	public SpriteSheet(BufferedImage ss){
		spriteSheet = ss;
	}
	
	public BufferedImage getSprite(int x, int y, int width, int height){
		
		BufferedImage img = spriteSheet.getSubimage(x, y, width, height);
		return img;
		
	}
	
}
