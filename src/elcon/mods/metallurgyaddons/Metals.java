package elcon.mods.metallurgyaddons;

import java.util.HashMap;

import rebelkeithy.mods.metallurgy.api.IMetalSet;
import rebelkeithy.mods.metallurgy.api.IOreInfo;
import rebelkeithy.mods.metallurgy.api.MetallurgyAPI;
import rebelkeithy.mods.metallurgy.vanilla.MetallurgyVanilla;

public class Metals {

	public static class Metal {
		
		public String name;
		public String setName;
		public IOreInfo oreInfo;
		
		public Metal(String name, String setName, IOreInfo oreInfo) {
			this.name = name;
			this.setName = setName;
			this.oreInfo = oreInfo;
		}
	}
	
	public static HashMap<String, Metal> metals = new HashMap<String, Metal>();
	
	public static Metal getMetal(String metal) {
		return metals.get(metal.toLowerCase());
	}
	
	public static void registerMetal(Metal metal) {
		metals.put(metal.name.toLowerCase(), metal);
	}
	
	public static void unregisterMetal(String metal) {
		metals.remove(metal.toLowerCase());
	}
	
	public static void init() {
		for(int i = 0; i < MetallurgyAPI.getMetalSetNames().length; i++) {
			IMetalSet metalSet = MetallurgyAPI.getMetalSet(MetallurgyAPI.getMetalSetNames()[i]);
			for(IOreInfo oreInfo : metalSet.getOreList().values()) {
				registerMetal(new Metal(oreInfo.getName().replaceAll(" ", ""), MetallurgyAPI.getMetalSetNames()[i], oreInfo));
			}
		}
		IMetalSet metalSet = MetallurgyVanilla.vanillaSet;
		for(IOreInfo oreInfo : metalSet.getOreList().values()) {
			registerMetal(new Metal(oreInfo.getName().replaceAll(" ", ""), "Vanilla", oreInfo));
		}
	}
}
