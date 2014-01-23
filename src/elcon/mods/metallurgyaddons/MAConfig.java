package elcon.mods.metallurgyaddons;

import java.io.File;
import java.util.Iterator;

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
		Iterator<MetallurgyAddon> iterator = MetallurgyAddons.addons.iterator();
		while(iterator.hasNext()) {
			MetallurgyAddon addon = iterator.next();
			if(config.get(addon.getID(), "enabled", true).getBoolean(true)) {
				addon.loadConfig(config, addon.getID());
			} else {
				MetallurgyAddons.addons.remove(addon);
			}
		}
	}

	public void save() {
		for(MetallurgyAddon addon : MetallurgyAddons.addons) {
			addon.saveConfig(config, addon.getID());
		}
		config.save();
	}
}
