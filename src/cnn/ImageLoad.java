package cnn;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import java.util.ArrayList;

import javax.imageio.ImageIO;



public class ImageLoad  {

	private ArrayList<Double> images = new ArrayList<Double>();

	

	ImageLoad() {
	}

	public void loadAllImages() {
		for(int i = 0; i < new File("C:/Users/wanga/eclipse-workspace1/CNN/images/").list().length; i++) {
			loadImage(i);
		}
	}
	
	public void loadImage(int index) {
		File f = new File("C:/Users/wanga/eclipse-workspace1/CNN/images/image" + index + ".png");
		BufferedImage img = null;

		try {
			img = ImageIO.read(f);
			BufferedImage outputImage = new BufferedImage(28, 28, img.getType());

			Graphics2D g2d = outputImage.createGraphics();
			g2d.drawImage(img, 0, 0, 28, 28, null);
			g2d.dispose();

			img = outputImage;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		for (int x = 0; x < 28; x++) {
			for (int h = 0; h < 28; h++) {

				images.add(x * h + h, (img.getRGB(h, x) & 0xFF) * 1.0);

				

			}
		}
	}

}
