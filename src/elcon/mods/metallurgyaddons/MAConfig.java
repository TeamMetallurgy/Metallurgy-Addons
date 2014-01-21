package elcon.mods.metallurgyaddons;

import java.io.File;

import net.minecraftforge.common.Configuration;

public class MAConfig {
	
	public Configuration config;
	
	public MAConfig(Configuration config) {
		this.config = config;
	}
	
	public MAConfig(File file) {
		this(new Configuration(file));
	}
	
	public void load() {
		config.load();
		for(MetallurgyAddon addon : MetallurgyAddons.addons) {
			addon.loadConfig(config, addon.getID());
		}
	}
	
	public void save() {
		for(MetallurgyAddon addon : MetallurgyAddons.addons) {
			addon.saveConfig(config, addon.getID());
		}
		config.save();
	}
}
