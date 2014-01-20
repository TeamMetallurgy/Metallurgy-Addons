package elcon.mods.metallurgybees;

import java.util.ArrayList;

import net.minecraft.util.Icon;
import forestry.api.apiculture.IHiveDrop;
import forestry.apiculture.genetics.AlleleBeeSpecies;

public enum MetallurgyBeeTypes {

	COPPPER("copper", 				new MBColors(192, 81, 14, 137, 57, 10), new MBColors(238, 104, 23, 192, 81, 14), new MBColors(241, 136, 73, 238, 104, 23), 0xFFFFFF, 0xFFFFFF),
	TIN("tin", 						new MBColors(170, 170, 170, 126, 126, 126), new MBColors(192, 192, 192, 170, 170, 170), new MBColors(210, 210, 210, 192, 192, 192), 0xFFFFFF, 0xFFFFFF),
	BRONZE("bronze",				new MBColors(132, 80, 32, 94, 56, 23), new MBColors(186, 112, 44, 132, 80, 32), new MBColors(225, 175, 130, 186, 112, 44), 0xFFFFFF, 0xFFFFFF),
	IRON("iron", 					new MBColors(175, 142, 119, 130, 98, 77), new MBColors(216, 175, 147, 175, 142, 119), new MBColors(235, 210, 194, 216, 175, 147), 0xFFFFFF, 0xFFFFFF),
	HEPATIZON("hepatizon", 			new MBColors(97, 78, 97, 73, 58, 73), new MBColors(148, 122, 148, 97, 78, 97), new MBColors(180, 160, 180, 148, 122, 148), 0xFFFFFF, 0xFFFFFF),
	DAMASCUS_STEEL("damascusSteel", new MBColors(88, 63, 44, 65, 47, 33), new MBColors(153, 109, 77, 88, 63, 44), new MBColors(223, 205, 191, 153, 109, 77), 0xFFFFFF, 0xFFFFFF),
	ANGMALLEN("angmallen",			new MBColors(207, 190, 71,156, 142, 41), new MBColors(225, 215, 138, 207, 190, 71), new MBColors(235, 227, 177, 225, 215, 138), 0xFFFFFF, 0xFFFFFF),
	MANGANESE("manganese", 			new MBColors(255, 136, 136, 255, 66, 66), new MBColors(255, 187, 187, 255, 136, 136), new MBColors(255, 221, 221, 255, 187, 187), 0xFFFFFF, 0xFFFFFF),
	STEEL("steel", 					new MBColors(136, 136, 136, 104, 104, 104), new MBColors(173, 173, 173, 136, 136, 136), new MBColors(216, 216, 216, 173, 173, 173), 0xFFFFFF, 0xFFFFFF),
	ZINC("zinc", 					new MBColors(147, 152, 54, 112, 115, 40), new MBColors(191, 197, 92, 147, 152, 54), new MBColors(220, 223, 164, 191, 197, 92), 0xFFFFFF, 0xFFFFFF),
	BRASS("brass", 					new MBColors(101, 69, 20, 62, 43, 13), new MBColors(216, 150, 52, 101, 69, 20), new MBColors(236, 205, 159, 216, 150, 52), 0xFFFFFF, 0xFFFFFF),
	SILVER("silver", 				new MBColors(173, 173, 173, 102, 102, 102), new MBColors(212, 212, 212, 173, 173, 173), new MBColors(229, 229, 229, 212, 212, 212), 0xFFFFFF, 0xFFFFFF),
	GOLD("gold",					new MBColors(248, 175, 43, 183, 97, 16), new MBColors(252, 238, 75, 248, 175, 43), new MBColors(255, 255, 181, 250, 190, 80), 0xFFFFFF, 0xFFFFFF),
	ELECTRUM("electrum", 			new MBColors(185, 153, 70, 100, 83, 38), new MBColors(206, 184, 127, 185, 153, 70), new MBColors(223, 208, 170, 206, 184, 127), 0xFFFFFF, 0xFFFFFF),
	PLATINUM("platinum", 			new MBColors(54, 103, 112, 38, 72, 79), new MBColors(155, 201, 208, 54, 103, 112), new MBColors(220, 236, 239, 155, 201, 208), 0xFFFFFF, 0xFFFFFF),
	IGNATIUS("ignatius", 			new MBColors(0, 0, 0, 0, 0, 0), new MBColors(0, 0, 0, 0, 0, 0), new MBColors(0, 0, 0, 0, 0, 0), 0xFFFFFF, 0xFFFFFF),
	SHADOW_IRON("shadowIron", 		new MBColors(0, 0, 0, 0, 0, 0), new MBColors(0, 0, 0, 0, 0, 0), new MBColors(0, 0, 0, 0, 0, 0), 0xFFFFFF, 0xFFFFFF),
	LEMURITE("lemurite", 			new MBColors(0, 0, 0, 0, 0, 0), new MBColors(0, 0, 0, 0, 0, 0), new MBColors(0, 0, 0, 0, 0, 0), 0xFFFFFF, 0xFFFFFF),
	SHADOW_STEEL("shadowSteel", 	new MBColors(0, 0, 0, 0, 0, 0), new MBColors(0, 0, 0, 0, 0, 0), new MBColors(0, 0, 0, 0, 0, 0), 0xFFFFFF, 0xFFFFFF),
	MIDASIUM("midasium", 			new MBColors(0, 0, 0, 0, 0, 0), new MBColors(0, 0, 0, 0, 0, 0), new MBColors(0, 0, 0, 0, 0, 0), 0xFFFFFF, 0xFFFFFF),
	VYROXERES("vyroxeres", 			new MBColors(0, 0, 0, 0, 0, 0), new MBColors(0, 0, 0, 0, 0, 0), new MBColors(0, 0, 0, 0, 0, 0), 0xFFFFFF, 0xFFFFFF),
	CERUCLASE("ceruclase", 			new MBColors(0, 0, 0, 0, 0, 0), new MBColors(0, 0, 0, 0, 0, 0), new MBColors(0, 0, 0, 0, 0, 0), 0xFFFFFF, 0xFFFFFF),
	ADLUORITE("adluorite", 			new MBColors(0, 0, 0, 0, 0, 0), new MBColors(0, 0, 0, 0, 0, 0), new MBColors(0, 0, 0, 0, 0, 0), 0xFFFFFF, 0xFFFFFF),
	INOLASHITE("inolashite",		new MBColors(0, 0, 0, 0, 0, 0), new MBColors(0, 0, 0, 0, 0, 0), new MBColors(0, 0, 0, 0, 0, 0), 0xFFFFFF, 0xFFFFFF),
	KALENDRITE("kalendrite",		new MBColors(0, 0, 0, 0, 0, 0), new MBColors(0, 0, 0, 0, 0, 0), new MBColors(0, 0, 0, 0, 0, 0), 0xFFFFFF, 0xFFFFFF),
	AMORDRINE("amordrine", 			new MBColors(0, 0, 0, 0, 0, 0), new MBColors(0, 0, 0, 0, 0, 0), new MBColors(0, 0, 0, 0, 0, 0), 0xFFFFFF, 0xFFFFFF),
	VULCANITE("vulcanite", 			new MBColors(0, 0, 0, 0, 0, 0), new MBColors(0, 0, 0, 0, 0, 0), new MBColors(0, 0, 0, 0, 0, 0), 0xFFFFFF, 0xFFFFFF),
	SANGUINITE("sanguinite", 		new MBColors(0, 0, 0, 0, 0, 0), new MBColors(0, 0, 0, 0, 0, 0), new MBColors(0, 0, 0, 0, 0, 0), 0xFFFFFF, 0xFFFFFF),
	PROMETHEUM("prometheum", 		new MBColors(0, 0, 0, 0, 0, 0), new MBColors(0, 0, 0, 0, 0, 0), new MBColors(0, 0, 0, 0, 0, 0), 0xFFFFFF, 0xFFFFFF),
	DEEP_IRON("deepIron", 			new MBColors(0, 0, 0, 0, 0, 0), new MBColors(0, 0, 0, 0, 0, 0), new MBColors(0, 0, 0, 0, 0, 0), 0xFFFFFF, 0xFFFFFF),
	INFUSCOLIUM("infuscolium", 		new MBColors(0, 0, 0, 0, 0, 0), new MBColors(0, 0, 0, 0, 0, 0), new MBColors(0, 0, 0, 0, 0, 0), 0xFFFFFF, 0xFFFFFF),
	BLACK_STEEL("blackSteel", 		new MBColors(0, 0, 0, 0, 0, 0), new MBColors(0, 0, 0, 0, 0, 0), new MBColors(0, 0, 0, 0, 0, 0), 0xFFFFFF, 0xFFFFFF),
	OURECLASE("oureclase", 			new MBColors(0, 0, 0, 0, 0, 0), new MBColors(0, 0, 0, 0, 0, 0), new MBColors(0, 0, 0, 0, 0, 0), 0xFFFFFF, 0xFFFFFF),
	ASTRAL_SILVER("astralSilver", 	new MBColors(0, 0, 0, 0, 0, 0), new MBColors(0, 0, 0, 0, 0, 0), new MBColors(0, 0, 0, 0, 0, 0), 0xFFFFFF, 0xFFFFFF),
	CARMOT("carmot", 				new MBColors(0, 0, 0, 0, 0, 0), new MBColors(0, 0, 0, 0, 0, 0), new MBColors(0, 0, 0, 0, 0, 0), 0xFFFFFF, 0xFFFFFF),
	MITHRIL("mithril", 				new MBColors(0, 0, 0, 0, 0, 0), new MBColors(0, 0, 0, 0, 0, 0), new MBColors(0, 0, 0, 0, 0, 0), 0xFFFFFF, 0xFFFFFF),
	RUBRACIUM("rubracium", 			new MBColors(0, 0, 0, 0, 0, 0), new MBColors(0, 0, 0, 0, 0, 0), new MBColors(0, 0, 0, 0, 0, 0), 0xFFFFFF, 0xFFFFFF),
	QUICKSILVER("quicksilver", 		new MBColors(0, 0, 0, 0, 0, 0), new MBColors(0, 0, 0, 0, 0, 0), new MBColors(0, 0, 0, 0, 0, 0), 0xFFFFFF, 0xFFFFFF),
	HADEROTH("haderoth", 			new MBColors(0, 0, 0, 0, 0, 0), new MBColors(0, 0, 0, 0, 0, 0), new MBColors(0, 0, 0, 0, 0, 0), 0xFFFFFF, 0xFFFFFF),
	ORICHALCUM("orichalcum", 		new MBColors(0, 0, 0, 0, 0, 0), new MBColors(0, 0, 0, 0, 0, 0), new MBColors(0, 0, 0, 0, 0, 0), 0xFFFFFF, 0xFFFFFF),
	CELENEGIL("celenegil", 			new MBColors(0, 0, 0, 0, 0, 0), new MBColors(0, 0, 0, 0, 0, 0), new MBColors(0, 0, 0, 0, 0, 0), 0xFFFFFF, 0xFFFFFF),
	ADAMANTITE("adamantite",		new MBColors(0, 0, 0, 0, 0, 0), new MBColors(0, 0, 0, 0, 0, 0), new MBColors(0, 0, 0, 0, 0, 0), 0xFFFFFF, 0xFFFFFF),
	ATLARUS("atlarus", 				new MBColors(0, 0, 0, 0, 0, 0), new MBColors(0, 0, 0, 0, 0, 0), new MBColors(0, 0, 0, 0, 0, 0), 0xFFFFFF, 0xFFFFFF),
	TARTARITE("tartarite", 			new MBColors(0, 0, 0, 0, 0, 0), new MBColors(0, 0, 0, 0, 0, 0), new MBColors(0, 0, 0, 0, 0, 0), 0xFFFFFF, 0xFFFFFF),
	PHOSPHORITE("phosphorite", 		new MBColors(0, 0, 0, 0, 0, 0), new MBColors(0, 0, 0, 0, 0, 0), new MBColors(0, 0, 0, 0, 0, 0), 0xFFFFFF, 0xFFFFFF),
	SULFUR("sulfur", 				new MBColors(0, 0, 0, 0, 0, 0), new MBColors(0, 0, 0, 0, 0, 0), new MBColors(0, 0, 0, 0, 0, 0), 0xFFFFFF, 0xFFFFFF),
	SALTPETER("saltpeter", 			new MBColors(0, 0, 0, 0, 0, 0), new MBColors(0, 0, 0, 0, 0, 0), new MBColors(0, 0, 0, 0, 0, 0), 0xFFFFFF, 0xFFFFFF),
	MAGNESIUM("magnesium", 			new MBColors(0, 0, 0, 0, 0, 0), new MBColors(0, 0, 0, 0, 0, 0), new MBColors(0, 0, 0, 0, 0, 0), 0xFFFFFF, 0xFFFFFF),
	BITUMEN("bitumen", 				new MBColors(0, 0, 0, 0, 0, 0), new MBColors(0, 0, 0, 0, 0, 0), new MBColors(0, 0, 0, 0, 0, 0), 0xFFFFFF, 0xFFFFFF),
	POTASH("potash", 				new MBColors(0, 0, 0, 0, 0, 0), new MBColors(0, 0, 0, 0, 0, 0), new MBColors(0, 0, 0, 0, 0, 0), 0xFFFFFF, 0xFFFFFF),
	EXIMITE("eximite", 				new MBColors(0, 0, 0, 0, 0, 0), new MBColors(0, 0, 0, 0, 0, 0), new MBColors(0, 0, 0, 0, 0, 0), 0xFFFFFF, 0xFFFFFF),
	MEUTOITE("meutoite", 			new MBColors(0, 0, 0, 0, 0, 0), new MBColors(0, 0, 0, 0, 0, 0), new MBColors(0, 0, 0, 0, 0, 0), 0xFFFFFF, 0xFFFFFF),
	DESICHALKOS("desichalkos", 		new MBColors(0, 0, 0, 0, 0, 0), new MBColors(0, 0, 0, 0, 0, 0), new MBColors(0, 0, 0, 0, 0, 0), 0xFFFFFF, 0xFFFFFF);
	
	MetallurgyBeeTypes(String name, MBColors rough, MBColors refined, MBColors reforged , int colorCombPrimary, int colorCombSecondary) {
		this.name = name;
		this.colorBeeRoughPrimary = rough.getHex();
		this.colorBeeRoughSecondary = rough.getHex2();
		this.colorBeeRefinedPrimary = refined.getHex();
		this.colorBeeRefinedSecondary = refined.getHex2();
		this.colorBeeReforgedPrimary = reforged.getHex();
		this.colorBeeReforgedSecondary = reforged.getHex2();
		this.colorCombPrimary = colorCombPrimary;
		this.colorCombSecondary = colorCombSecondary;
	}	
	
	public String name;
	public int colorBeeRoughPrimary;
	public int colorBeeRoughSecondary;
	public int colorBeeRefinedPrimary;
	public int colorBeeRefinedSecondary;
	public int colorBeeReforgedPrimary;
	public int colorBeeReforgedSecondary;
	public int colorCombPrimary;
	public int colorCombSecondary;
	
	public ArrayList<IHiveDrop> hiveDrops = new ArrayList<IHiveDrop>();
	
	public Icon iconBeehiveTop;
	public Icon iconBeehiveSide;
	
	public AlleleBeeSpecies speciesRough;
	public AlleleBeeSpecies speciesRefined;
	public AlleleBeeSpecies speciesReforged;
}
