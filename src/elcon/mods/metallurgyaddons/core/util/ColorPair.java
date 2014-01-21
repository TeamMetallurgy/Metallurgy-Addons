package elcon.mods.metallurgyaddons.core.util;

public class ColorPair {

	public Color color1;
	public Color color2;
	
	public ColorPair(Color color1, Color color2) {
		this.color1 = color1;
		this.color2 = color2;
	}
	
	public ColorPair(int r1, int g1, int b1, int r2, int g2, int b2) {
		this(new Color(r1, g1, b1), new Color(r2, g2, b2));
	}
	
	public int getRGB1() {
		return color1.getRGB();
	}
	
	public int getRGB2() {
		return color2.getRGB();
	}
}
