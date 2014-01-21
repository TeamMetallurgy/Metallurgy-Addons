package elcon.mods.metallurgyaddons.core.util;

import org.lwjgl.opengl.GL11;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class Color {

	public static final String TEXT_COLOR_PREFIX_BLACK = "\u00a70";
	public static final String TEXT_COLOR_PREFIX_DARK_BLUE = "\u00a71";
	public static final String TEXT_COLOR_PREFIX_DARK_GREEN = "\u00a72";
	public static final String TEXT_COLOR_PREFIX_DARK_AQUA = "\u00a73";
	public static final String TEXT_COLOR_PREFIX_DARK_RED = "\u00a74";
	public static final String TEXT_COLOR_PREFIX_PURPLE = "\u00a75";
	public static final String TEXT_COLOR_PREFIX_GOLD = "\u00a76";
	public static final String TEXT_COLOR_PREFIX_GRAY = "\u00a77";
	public static final String TEXT_COLOR_PREFIX_DARK_GRAY = "\u00a78";
	public static final String TEXT_COLOR_PREFIX_BLUE = "\u00a79";
	public static final String TEXT_COLOR_PREFIX_GREEN = "\u00a7a";
	public static final String TEXT_COLOR_PREFIX_AQUA = "\u00a7b";
	public static final String TEXT_COLOR_PREFIX_RED = "\u00a7c";
	public static final String TEXT_COLOR_PREFIX_LIGHT_PURPLE = "\u00a7d";
	public static final String TEXT_COLOR_PREFIX_YELLOW = "\u00a7e";
	public static final String TEXT_COLOR_PREFIX_WHITE = "\u00a7f";

	public static final int TEXT_COLOR_BLACK = 0x000000;
	public static final int TEXT_COLOR_DARK_BLUE = 0x0000AA;
	public static final int TEXT_COLOR_DARK_GREEN = 0x00AA00;
	public static final int TEXT_COLOR_DARK_AQUA = 0x00AAAA;
	public static final int TEXT_COLOR_DARK_RED = 0xAA0000;
	public static final int TEXT_COLOR_PURPLE = 0xAA00AA;
	public static final int TEXT_COLOR_GOLD = 0xFFAA00;
	public static final int TEXT_COLOR_GRAY = 0xAAAAAA;
	public static final int TEXT_COLOR_DARK_GRAY = 0x555555;
	public static final int TEXT_COLOR_BLUE = 0x5555FF;
	public static final int TEXT_COLOR_GREEN = 0x55FF55;
	public static final int TEXT_COLOR_AQUA = 0x55FFFF;
	public static final int TEXT_COLOR_RED = 0xFF5555;
	public static final int TEXT_COLOR_LIGHT_PURPLE = 0xFF55FF;
	public static final int TEXT_COLOR_YELLOW = 0xFFFF55;
	public static final int TEXT_COLOR_WHITE = 0xFFFFFF;

	public byte r;
	public byte g;
	public byte b;

	public Color(int r, int g, int b) {
		this.r = (byte) r;
		this.g = (byte) g;
		this.b = (byte) b;
	}

	public Color(Color color) {
		r = color.r;
		g = color.g;
		b = color.b;
	}

	@SideOnly(Side.CLIENT)
	public void glColor() {
		GL11.glColor3ub(r, g, b);
	}

	@SideOnly(Side.CLIENT)
	public void glColor(int a) {
		GL11.glColor4ub(r, g, b, (byte) a);
	}

	public Color add(Color color2) {
		r += color2.r;
		g += color2.g;
		b += color2.b;
		return this;
	}
	
	public Color set(Color color) {
		r = color.r;
		g = color.g;
		b = color.b;
		return this;
	}

	public Color sub(Color color2) {
		int ir = (r & 0xFF) - (color2.r & 0xFF);
		int ig = (g & 0xFF) - (color2.g & 0xFF);
		int ib = (b & 0xFF) - (color2.b & 0xFF);
		r = (byte) (ir < 0 ? 0 : ir);
		g = (byte) (ig < 0 ? 0 : ig);
		b = (byte) (ib < 0 ? 0 : ib);
		return this;
	}

	public Color invert() {
		r = (byte) (0xFF - (r & 0xFF));
		g = (byte) (0xFF - (g & 0xFF));
		b = (byte) (0xFF - (b & 0xFF));
		return this;
	}

	public Color multiply(Color color2) {
		r = (byte) ((r & 0xFF) * ((color2.r & 0xFF) / 255D));
		g = (byte) ((g & 0xFF) * ((color2.g & 0xFF) / 255D));
		b = (byte) ((b & 0xFF) * ((color2.b & 0xFF) / 255D));
		return this;
	}

	public Color scale(double d) {
		r = (byte) ((r & 0xFF) * d);
		g = (byte) ((g & 0xFF) * d);
		b = (byte) ((b & 0xFF) * d);
		return this;
	}

	public Color interpolate(Color color2, double d) {
		return this.add(color2.copy().sub(this).scale(d));
	}

	public Color multiplyC(double d) {
		r = (byte) Math.min(Math.max((r & 0xFF) * d, 0), 255);
		g = (byte) Math.min(Math.max((g & 0xFF) * d, 0), 255);
		b = (byte) Math.min(Math.max((b & 0xFF) * d, 0), 255);
		return this;
	}

	public Color copy() {
		return new Color(this);
	}

	public int getRGB() {
		return (r & 0xFF) << 16 | (g & 0xFF) << 8 | (b & 0xFF);
	}
	
	public String getHexString() {
		return "0x" + Integer.toHexString(getRGB()).toUpperCase();
	}
	
	@Override
	public String toString() {
		return getClass().getSimpleName() + "[" + getHexString() + "]";
	}
}
