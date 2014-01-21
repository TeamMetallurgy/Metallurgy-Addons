package elcon.mods.metallurgyaddons;

import net.minecraftforge.common.Configuration;

public abstract class MetallurgyAddon {

	public abstract String getID();
	
	public abstract String getName();
	
	public abstract void preInit();
	
	public abstract void init();
	
	public abstract void postInit();

	public abstract void loadConfig(Configuration config, String category);
	
	public abstract void saveConfig(Configuration config, String category);
}
