package elcon.mods.metallurgybees;

import java.io.File;

import net.minecraftforge.common.Configuration;

public class MBConfig {
	
	public Configuration config;
	
	public static int beehiveID = 4000;
	
	public static int honeyCombID = 6000;
	
	public MBConfig(Configuration config) {
		this.config = config;
	}
	
	public MBConfig(File file) {
		this(new Configuration(file));
	}
	
	public void load() {
		config.load();
		beehiveID = config.getBlock("beehive", beehiveID).getInt();
		
		honeyCombID = config.getItem("honeyComb", honeyCombID).getInt();
	}
	
	public void save() {
		config.save();
	}
}
