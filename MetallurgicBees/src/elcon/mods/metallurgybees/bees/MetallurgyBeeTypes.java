package elcon.mods.metallurgybees.bees;

import java.util.ArrayList;

import net.minecraft.util.Icon;
import elcon.mods.metallurgybees.misc.MBColors;
import forestry.api.apiculture.IHiveDrop;
import forestry.apiculture.genetics.AlleleBeeSpecies;

public enum MetallurgyBeeTypes {

	COPPPER("copper", 				new MBColors(192, 81, 14, 137, 57, 10), new MBColors(238, 104, 23, 192, 81, 14), new MBColors(241, 136, 73, 238, 104, 23), new MBColors(241, 136, 73, 192, 81, 14)),
	TIN("tin", 						new MBColors(170, 170, 170, 126, 126, 126), new MBColors(192, 192, 192, 170, 170, 170), new MBColors(210, 210, 210, 192, 192, 192), new MBColors(210, 210, 210, 170, 170, 170)),
	BRONZE("bronze",				new MBColors(132, 80, 32, 94, 56, 23), new MBColors(186, 112, 44, 132, 80, 32), new MBColors(225, 175, 130, 186, 112, 44), new MBColors(225, 175, 130, 132, 80, 32)),
	IRON("iron", 					new MBColors(175, 142, 119, 130, 98, 77), new MBColors(216, 175, 147, 175, 142, 119), new MBColors(235, 210, 194, 216, 175, 147), new MBColors(235, 210, 194, 188, 153, 128)),
	HEPATIZON("hepatizon", 			new MBColors(97, 78, 97, 73, 58, 73), new MBColors(148, 122, 148, 97, 78, 97), new MBColors(180, 160, 180, 148, 122, 148), new MBColors(180, 160, 180, 97, 78, 97)),
	DAMASCUS_STEEL("damascusSteel", new MBColors(88, 63, 44, 65, 47, 33), new MBColors(153, 109, 77, 88, 63, 44), new MBColors(223, 205, 191, 153, 109, 77), new MBColors(223, 205, 191, 153, 109, 77)),
	ANGMALLEN("angmallen",			new MBColors(207, 190, 71,156, 142, 41), new MBColors(225, 215, 138, 207, 190, 71), new MBColors(235, 227, 177, 225, 215, 138), new MBColors(235, 227, 177, 207, 190, 71)),
	MANGANESE("manganese", 			new MBColors(255, 136, 136, 255, 66, 66), new MBColors(255, 187, 187, 255, 136, 136), new MBColors(255, 221, 221, 255, 187, 187), new MBColors(255, 221, 221, 255, 136, 136)),
	STEEL("steel", 					new MBColors(136, 136, 136, 104, 104, 104), new MBColors(173, 173, 173, 136, 136, 136), new MBColors(216, 216, 216, 173, 173, 173), new MBColors(216, 216, 216, 104, 104, 104)),
	ZINC("zinc", 					new MBColors(147, 152, 54, 112, 115, 40), new MBColors(191, 197, 92, 147, 152, 54), new MBColors(220, 223, 164, 191, 197, 92), new MBColors(220, 223, 164, 147, 152, 54)),
	BRASS("brass", 					new MBColors(101, 69, 20, 62, 43, 13), new MBColors(216, 150, 52, 101, 69, 20), new MBColors(236, 205, 159, 216, 150, 52), new MBColors(236, 205, 159, 101, 69, 20)),
	SILVER("silver", 				new MBColors(173, 173, 173, 102, 102, 102), new MBColors(212, 212, 212, 173, 173, 173), new MBColors(229, 229, 229, 212, 212, 212), new MBColors(229, 229, 229, 173, 173, 173)),
	GOLD("gold",					new MBColors(248, 175, 43, 183, 97, 16), new MBColors(252, 238, 75, 248, 175, 43), new MBColors(255, 255, 181, 250, 190, 80), new MBColors(255, 255, 181, 183, 97, 16)),
	ELECTRUM("electrum", 			new MBColors(185, 153, 70, 100, 83, 38), new MBColors(206, 184, 127, 185, 153, 70), new MBColors(223, 208, 170, 206, 184, 127), new MBColors(223, 208, 170, 185, 153, 70)),
	PLATINUM("platinum", 			new MBColors(54, 103, 112, 38, 72, 79), new MBColors(155, 201, 208, 54, 103, 112), new MBColors(220, 236, 239, 155, 201, 208), new MBColors(220, 236, 239, 54, 103, 112)),
	IGNATIUS("ignatius", 			new MBColors(255, 255, 255, 255, 255, 255), new MBColors(255, 255, 255, 255, 255, 255), new MBColors(255, 255, 255, 255, 255, 255), new MBColors(255, 255, 255, 255, 255, 255)),
	SHADOW_IRON("shadowIron", 		new MBColors(255, 255, 255, 255, 255, 255), new MBColors(255, 255, 255, 255, 255, 255), new MBColors(255, 255, 255, 255, 255, 255), new MBColors(255, 255, 255, 255, 255, 255)),
	LEMURITE("lemurite", 			new MBColors(255, 255, 255, 255, 255, 255), new MBColors(255, 255, 255, 255, 255, 255), new MBColors(255, 255, 255, 255, 255, 255), new MBColors(255, 255, 255, 255, 255, 255)),
	SHADOW_STEEL("shadowSteel", 	new MBColors(255, 255, 255, 255, 255, 255), new MBColors(255, 255, 255, 255, 255, 255), new MBColors(255, 255, 255, 255, 255, 255), new MBColors(255, 255, 255, 255, 255, 255)),
	MIDASIUM("midasium", 			new MBColors(255, 255, 255, 255, 255, 255), new MBColors(255, 255, 255, 255, 255, 255), new MBColors(255, 255, 255, 255, 255, 255), new MBColors(255, 255, 255, 255, 255, 255)),
	VYROXERES("vyroxeres", 			new MBColors(255, 255, 255, 255, 255, 255), new MBColors(255, 255, 255, 255, 255, 255), new MBColors(255, 255, 255, 255, 255, 255), new MBColors(255, 255, 255, 255, 255, 255)),
	CERUCLASE("ceruclase", 			new MBColors(255, 255, 255, 255, 255, 255), new MBColors(255, 255, 255, 255, 255, 255), new MBColors(255, 255, 255, 255, 255, 255), new MBColors(255, 255, 255, 255, 255, 255)),
	ADLUORITE("adluorite", 			new MBColors(255, 255, 255, 255, 255, 255), new MBColors(255, 255, 255, 255, 255, 255), new MBColors(255, 255, 255, 255, 255, 255), new MBColors(255, 255, 255, 255, 255, 255)),
	INOLASHITE("inolashite",		new MBColors(255, 255, 255, 255, 255, 255), new MBColors(255, 255, 255, 255, 255, 255), new MBColors(255, 255, 255, 255, 255, 255), new MBColors(255, 255, 255, 255, 255, 255)),
	KALENDRITE("kalendrite",		new MBColors(255, 255, 255, 255, 255, 255), new MBColors(255, 255, 255, 255, 255, 255), new MBColors(255, 255, 255, 255, 255, 255), new MBColors(255, 255, 255, 255, 255, 255)),
	AMORDRINE("amordrine", 			new MBColors(255, 255, 255, 255, 255, 255), new MBColors(255, 255, 255, 255, 255, 255), new MBColors(255, 255, 255, 255, 255, 255), new MBColors(255, 255, 255, 255, 255, 255)),
	VULCANITE("vulcanite", 			new MBColors(255, 255, 255, 255, 255, 255), new MBColors(255, 255, 255, 255, 255, 255), new MBColors(255, 255, 255, 255, 255, 255), new MBColors(255, 255, 255, 255, 255, 255)),
	SANGUINITE("sanguinite", 		new MBColors(255, 255, 255, 255, 255, 255), new MBColors(255, 255, 255, 255, 255, 255), new MBColors(255, 255, 255, 255, 255, 255), new MBColors(255, 255, 255, 255, 255, 255)),
	PROMETHEUM("prometheum", 		new MBColors(255, 255, 255, 255, 255, 255), new MBColors(255, 255, 255, 255, 255, 255), new MBColors(255, 255, 255, 255, 255, 255), new MBColors(255, 255, 255, 255, 255, 255)),
	DEEP_IRON("deepIron", 			new MBColors(255, 255, 255, 255, 255, 255), new MBColors(255, 255, 255, 255, 255, 255), new MBColors(255, 255, 255, 255, 255, 255), new MBColors(255, 255, 255, 255, 255, 255)),
	INFUSCOLIUM("infuscolium", 		new MBColors(255, 255, 255, 255, 255, 255), new MBColors(255, 255, 255, 255, 255, 255), new MBColors(255, 255, 255, 255, 255, 255), new MBColors(255, 255, 255, 255, 255, 255)),
	BLACK_STEEL("blackSteel", 		new MBColors(255, 255, 255, 255, 255, 255), new MBColors(255, 255, 255, 255, 255, 255), new MBColors(255, 255, 255, 255, 255, 255), new MBColors(255, 255, 255, 255, 255, 255)),
	OURECLASE("oureclase", 			new MBColors(255, 255, 255, 255, 255, 255), new MBColors(255, 255, 255, 255, 255, 255), new MBColors(255, 255, 255, 255, 255, 255), new MBColors(255, 255, 255, 255, 255, 255)),
	ASTRAL_SILVER("astralSilver", 	new MBColors(255, 255, 255, 255, 255, 255), new MBColors(255, 255, 255, 255, 255, 255), new MBColors(255, 255, 255, 255, 255, 255), new MBColors(255, 255, 255, 255, 255, 255)),
	CARMOT("carmot", 				new MBColors(255, 255, 255, 255, 255, 255), new MBColors(255, 255, 255, 255, 255, 255), new MBColors(255, 255, 255, 255, 255, 255), new MBColors(255, 255, 255, 255, 255, 255)),
	MITHRIL("mithril", 				new MBColors(255, 255, 255, 255, 255, 255), new MBColors(255, 255, 255, 255, 255, 255), new MBColors(255, 255, 255, 255, 255, 255), new MBColors(255, 255, 255, 255, 255, 255)),
	RUBRACIUM("rubracium", 			new MBColors(255, 255, 255, 255, 255, 255), new MBColors(255, 255, 255, 255, 255, 255), new MBColors(255, 255, 255, 255, 255, 255), new MBColors(255, 255, 255, 255, 255, 255)),
	QUICKSILVER("quicksilver", 		new MBColors(255, 255, 255, 255, 255, 255), new MBColors(255, 255, 255, 255, 255, 255), new MBColors(255, 255, 255, 255, 255, 255), new MBColors(255, 255, 255, 255, 255, 255)),
	HADEROTH("haderoth", 			new MBColors(255, 255, 255, 255, 255, 255), new MBColors(255, 255, 255, 255, 255, 255), new MBColors(255, 255, 255, 255, 255, 255), new MBColors(255, 255, 255, 255, 255, 255)),
	ORICHALCUM("orichalcum", 		new MBColors(255, 255, 255, 255, 255, 255), new MBColors(255, 255, 255, 255, 255, 255), new MBColors(255, 255, 255, 255, 255, 255), new MBColors(255, 255, 255, 255, 255, 255)),
	CELENEGIL("celenegil", 			new MBColors(255, 255, 255, 255, 255, 255), new MBColors(255, 255, 255, 255, 255, 255), new MBColors(255, 255, 255, 255, 255, 255), new MBColors(255, 255, 255, 255, 255, 255)),
	ADAMANTITE("adamantite",		new MBColors(255, 255, 255, 255, 255, 255), new MBColors(255, 255, 255, 255, 255, 255), new MBColors(255, 255, 255, 255, 255, 255), new MBColors(255, 255, 255, 255, 255, 255)),
	ATLARUS("atlarus", 				new MBColors(255, 255, 255, 255, 255, 255), new MBColors(255, 255, 255, 255, 255, 255), new MBColors(255, 255, 255, 255, 255, 255), new MBColors(255, 255, 255, 255, 255, 255)),
	TARTARITE("tartarite", 			new MBColors(255, 255, 255, 255, 255, 255), new MBColors(255, 255, 255, 255, 255, 255), new MBColors(255, 255, 255, 255, 255, 255), new MBColors(255, 255, 255, 255, 255, 255)),
	PHOSPHORITE("phosphorite", 		new MBColors(255, 255, 255, 255, 255, 255), new MBColors(255, 255, 255, 255, 255, 255), new MBColors(255, 255, 255, 255, 255, 255), new MBColors(255, 255, 255, 255, 255, 255)),
	SULFUR("sulfur", 				new MBColors(255, 255, 255, 255, 255, 255), new MBColors(255, 255, 255, 255, 255, 255), new MBColors(255, 255, 255, 255, 255, 255), new MBColors(255, 255, 255, 255, 255, 255)),
	SALTPETER("saltpeter", 			new MBColors(255, 255, 255, 255, 255, 255), new MBColors(255, 255, 255, 255, 255, 255), new MBColors(255, 255, 255, 255, 255, 255), new MBColors(255, 255, 255, 255, 255, 255)),
	MAGNESIUM("magnesium", 			new MBColors(255, 255, 255, 255, 255, 255), new MBColors(255, 255, 255, 255, 255, 255), new MBColors(255, 255, 255, 255, 255, 255), new MBColors(255, 255, 255, 255, 255, 255)),
	BITUMEN("bitumen", 				new MBColors(255, 255, 255, 255, 255, 255), new MBColors(255, 255, 255, 255, 255, 255), new MBColors(255, 255, 255, 255, 255, 255), new MBColors(255, 255, 255, 255, 255, 255)),
	POTASH("potash", 				new MBColors(255, 255, 255, 255, 255, 255), new MBColors(255, 255, 255, 255, 255, 255), new MBColors(255, 255, 255, 255, 255, 255), new MBColors(255, 255, 255, 255, 255, 255)),
	EXIMITE("eximite", 				new MBColors(255, 255, 255, 255, 255, 255), new MBColors(255, 255, 255, 255, 255, 255), new MBColors(255, 255, 255, 255, 255, 255), new MBColors(255, 255, 255, 255, 255, 255)),
	MEUTOITE("meutoite", 			new MBColors(255, 255, 255, 255, 255, 255), new MBColors(255, 255, 255, 255, 255, 255), new MBColors(255, 255, 255, 255, 255, 255), new MBColors(255, 255, 255, 255, 255, 255)),
	DESICHALKOS("desichalkos", 		new MBColors(255, 255, 255, 255, 255, 255), new MBColors(255, 255, 255, 255, 255, 255), new MBColors(255, 255, 255, 255, 255, 255), new MBColors(255, 255, 255, 255, 255, 255));
	
	MetallurgyBeeTypes(String name, MBColors rough, MBColors refined, MBColors reforged , MBColors comb) {
		this.name = name;
		this.colorBeeRoughPrimary = rough.getHex();
		this.colorBeeRoughSecondary = rough.getHex2();
		this.colorBeeRefinedPrimary = refined.getHex();
		this.colorBeeRefinedSecondary = refined.getHex2();
		this.colorBeeReforgedPrimary = reforged.getHex();
		this.colorBeeReforgedSecondary = reforged.getHex2();
		this.colorCombPrimary = comb.getHex();
		this.colorCombSecondary = comb.getHex2();
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
