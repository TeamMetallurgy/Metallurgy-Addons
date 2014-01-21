package elcon.mods.metallurgyaddons.core.util;

public class MAUtil {

	public static String firstUpperCase(String s) {
		return Character.toString(s.charAt(0)).toUpperCase() + s.substring(1, s.length());
	}
}
