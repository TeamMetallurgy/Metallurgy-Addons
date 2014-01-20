package elcon.mods.metallurgybees;

public class MBColors {
	int red;
	int blue;
	int green;
	int red2;
	int blue2;
	int green2;

	public MBColors(int red, int blue, int green, int red2, int blue2, int green2) {
		this.red = red;
		this.blue = blue;
		this.green = green;
		this.red2 = red2;
		this.blue2 = blue2;
		this.green2 = green2;
	}

	public int getRed() {
		return red;
	}

	public int getBlue() {
		return blue;
	}

	public int getGreen() {
		return green;
	}

	public int getRed2() {
		return red2;
	}

	public int getBlue2() {
		return blue2;
	}

	public int getGreen2() {
		return green2;
	}

	public int getHex() {
		String hex = String.format("%02x%02x%02x", this.getRed(), this.getBlue(), this.getGreen());
		return Integer.decode("0x" + hex);
	}

	public int getHex2() {
		String hex = String.format("%02x%02x%02x", this.getRed2(), this.getBlue2(), this.getGreen2());
		return Integer.decode("0x" + hex);
	}

}