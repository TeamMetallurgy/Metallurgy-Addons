package elcon.mods.metallurgyaddons.forestry;

import java.util.ArrayList;

import net.minecraft.util.Icon;
import elcon.mods.metallurgyaddons.core.util.ColorPair;
import forestry.api.apiculture.IHiveDrop;
import forestry.apiculture.genetics.AlleleBeeSpecies;

public enum MetallurgyBeeTypes {

	COPPER("copper", 				true, new ColorPair(192, 81, 14, 137, 57, 10), 		new ColorPair(238, 104, 23, 192, 81, 14), 		new ColorPair(241, 136, 73, 238, 104, 23), 		new ColorPair(241, 136, 73, 192, 81, 14)),
	TIN("tin", 						true, new ColorPair(170, 170, 170, 126, 126, 126), 	new ColorPair(192, 192, 192, 170, 170, 170), 	new ColorPair(210, 210, 210, 192, 192, 192), 	new ColorPair(210, 210, 210, 170, 170, 170)),
	BRONZE("bronze",				false, new ColorPair(132, 80, 32, 94, 56, 23), 		new ColorPair(186, 112, 44, 132, 80, 32), 		new ColorPair(225, 175, 130, 186, 112, 44),		new ColorPair(225, 175, 130, 132, 80, 32)),
	IRON("iron", 					true, new ColorPair(175, 142, 119, 130, 98, 77), 	new ColorPair(216, 175, 147, 175, 142, 119),	new ColorPair(235, 210, 194, 216, 175, 147), 	new ColorPair(235, 210, 194, 188, 153, 128)),
	HEPATIZON("hepatizon", 			false, new ColorPair(97, 78, 97, 73, 58, 73),		new ColorPair(148, 122, 148, 97, 78, 97), 		new ColorPair(180, 160, 180, 148, 122, 148), 	new ColorPair(180, 160, 180, 97, 78, 97)),
	DAMASCUS_STEEL("damascusSteel", false, new ColorPair(88, 63, 44, 65, 47, 33), 		new ColorPair(153, 109, 77, 88, 63, 44), 		new ColorPair(223, 205, 191, 153, 109, 77), 	new ColorPair(223, 205, 191, 153, 109, 77)),
	ANGMALLEN("angmallen",			false, new ColorPair(207, 190, 71,156, 142, 41), 	new ColorPair(225, 215, 138, 207, 190, 71), 	new ColorPair(235, 227, 177, 225, 215, 138), 	new ColorPair(235, 227, 177, 207, 190, 71)),
	MANGANESE("manganese", 			true, new ColorPair(255, 136, 136, 255, 66, 66), 	new ColorPair(255, 187, 187, 255, 136, 136), 	new ColorPair(255, 221, 221, 255, 187, 187), 	new ColorPair(255, 221, 221, 255, 136, 136)),
	STEEL("steel", 					false, new ColorPair(136, 136, 136, 104, 104, 104), new ColorPair(173, 173, 173, 136, 136, 136), 	new ColorPair(216, 216, 216, 173, 173, 173), 	new ColorPair(216, 216, 216, 104, 104, 104)),
	ZINC("zinc", 					true, new ColorPair(147, 152, 54, 112, 115, 40), 	new ColorPair(191, 197, 92, 147, 152, 54), 		new ColorPair(220, 223, 164, 191, 197, 92), 	new ColorPair(220, 223, 164, 147, 152, 54)),
	BRASS("brass", 					false, new ColorPair(101, 69, 20, 62, 43, 13), 		new ColorPair(216, 150, 52, 101, 69, 20), 		new ColorPair(236, 205, 159, 216, 150, 52), 	new ColorPair(236, 205, 159, 101, 69, 20)),
	SILVER("silver", 				true, new ColorPair(173, 173, 173, 102, 102, 102), 	new ColorPair(212, 212, 212, 173, 173, 173), 	new ColorPair(229, 229, 229, 212, 212, 212), 	new ColorPair(229, 229, 229, 173, 173, 173)),
	GOLD("gold",					true, new ColorPair(248, 175, 43, 183, 97, 16), 	new ColorPair(252, 238, 75, 248, 175, 43), 		new ColorPair(255, 255, 181, 250, 190, 80), 	new ColorPair(255, 255, 181, 183, 97, 16)),
	ELECTRUM("electrum", 			false, new ColorPair(185, 153, 70, 100, 83, 38), 	new ColorPair(206, 184, 127, 185, 153, 70), 	new ColorPair(223, 208, 170, 206, 184, 127), 	new ColorPair(223, 208, 170, 185, 153, 70)),
	PLATINUM("platinum", 			true, new ColorPair(54, 103, 112, 38, 72, 79), 		new ColorPair(155, 201, 208, 54, 103, 112), 	new ColorPair(220, 236, 239, 155, 201, 208), 	new ColorPair(220, 236, 239, 54, 103, 112)),
	IGNATIUS("ignatius", 			true, new ColorPair(255, 255, 255, 255, 255, 255), 	new ColorPair(255, 255, 255, 255, 255, 255),	new ColorPair(255, 255, 255, 255, 255, 255), 	new ColorPair(255, 255, 255, 255, 255, 255)),
	SHADOW_IRON("shadowIron", 		true, new ColorPair(255, 255, 255, 255, 255, 255), 	new ColorPair(255, 255, 255, 255, 255, 255), 	new ColorPair(255, 255, 255, 255, 255, 255), 	new ColorPair(255, 255, 255, 255, 255, 255)),
	LEMURITE("lemurite", 			true, new ColorPair(255, 255, 255, 255, 255, 255), 	new ColorPair(255, 255, 255, 255, 255, 255), 	new ColorPair(255, 255, 255, 255, 255, 255), 	new ColorPair(255, 255, 255, 255, 255, 255)),
	SHADOW_STEEL("shadowSteel", 	false, new ColorPair(255, 255, 255, 255, 255, 255), new ColorPair(255, 255, 255, 255, 255, 255), 	new ColorPair(255, 255, 255, 255, 255, 255), 	new ColorPair(255, 255, 255, 255, 255, 255)),
	MIDASIUM("midasium", 			true, new ColorPair(255, 255, 255, 255, 255, 255), 	new ColorPair(255, 255, 255, 255, 255, 255), 	new ColorPair(255, 255, 255, 255, 255, 255), 	new ColorPair(255, 255, 255, 255, 255, 255)),
	VYROXERES("vyroxeres", 			true, new ColorPair(255, 255, 255, 255, 255, 255), 	new ColorPair(255, 255, 255, 255, 255, 255), 	new ColorPair(255, 255, 255, 255, 255, 255), 	new ColorPair(255, 255, 255, 255, 255, 255)),
	CERUCLASE("ceruclase", 			true, new ColorPair(255, 255, 255, 255, 255, 255), 	new ColorPair(255, 255, 255, 255, 255, 255), 	new ColorPair(255, 255, 255, 255, 255, 255), 	new ColorPair(255, 255, 255, 255, 255, 255)),
	ALDUORITE("alduorite", 			true, new ColorPair(255, 255, 255, 255, 255, 255), 	new ColorPair(255, 255, 255, 255, 255, 255), 	new ColorPair(255, 255, 255, 255, 255, 255), 	new ColorPair(255, 255, 255, 255, 255, 255)),
	INOLASHITE("inolashite",		false, new ColorPair(255, 255, 255, 255, 255, 255), new ColorPair(255, 255, 255, 255, 255, 255), 	new ColorPair(255, 255, 255, 255, 255, 255), 	new ColorPair(255, 255, 255, 255, 255, 255)),
	KALENDRITE("kalendrite",		true, new ColorPair(255, 255, 255, 255, 255, 255), 	new ColorPair(255, 255, 255, 255, 255, 255), 	new ColorPair(255, 255, 255, 255, 255, 255), 	new ColorPair(255, 255, 255, 255, 255, 255)),
	AMORDRINE("amordrine", 			false, new ColorPair(255, 255, 255, 255, 255, 255), new ColorPair(255, 255, 255, 255, 255, 255), 	new ColorPair(255, 255, 255, 255, 255, 255), 	new ColorPair(255, 255, 255, 255, 255, 255)),
	VULCANITE("vulcanite", 			true, new ColorPair(255, 255, 255, 255, 255, 255), 	new ColorPair(255, 255, 255, 255, 255, 255), 	new ColorPair(255, 255, 255, 255, 255, 255), 	new ColorPair(255, 255, 255, 255, 255, 255)),
	SANGUINITE("sanguinite", 		true, new ColorPair(255, 255, 255, 255, 255, 255), 	new ColorPair(255, 255, 255, 255, 255, 255), 	new ColorPair(255, 255, 255, 255, 255, 255), 	new ColorPair(255, 255, 255, 255, 255, 255)),
	PROMETHEUM("prometheum", 		true, new ColorPair(255, 255, 255, 255, 255, 255), 	new ColorPair(255, 255, 255, 255, 255, 255), 	new ColorPair(255, 255, 255, 255, 255, 255), 	new ColorPair(255, 255, 255, 255, 255, 255)),
	DEEP_IRON("deepIron", 			true, new ColorPair(255, 255, 255, 255, 255, 255), 	new ColorPair(255, 255, 255, 255, 255, 255), 	new ColorPair(255, 255, 255, 255, 255, 255), 	new ColorPair(255, 255, 255, 255, 255, 255)),
	INFUSCOLIUM("infuscolium", 		true, new ColorPair(255, 255, 255, 255, 255, 255), 	new ColorPair(255, 255, 255, 255, 255, 255), 	new ColorPair(255, 255, 255, 255, 255, 255), 	new ColorPair(255, 255, 255, 255, 255, 255)),
	BLACK_STEEL("blackSteel", 		true, new ColorPair(255, 255, 255, 255, 255, 255), 	new ColorPair(255, 255, 255, 255, 255, 255), 	new ColorPair(255, 255, 255, 255, 255, 255), 	new ColorPair(255, 255, 255, 255, 255, 255)),
	OURECLASE("oureclase", 			true, new ColorPair(255, 255, 255, 255, 255, 255), 	new ColorPair(255, 255, 255, 255, 255, 255), 	new ColorPair(255, 255, 255, 255, 255, 255), 	new ColorPair(255, 255, 255, 255, 255, 255)),
	ASTRAL_SILVER("astralSilver", 	true, new ColorPair(255, 255, 255, 255, 255, 255), 	new ColorPair(255, 255, 255, 255, 255, 255), 	new ColorPair(255, 255, 255, 255, 255, 255), 	new ColorPair(255, 255, 255, 255, 255, 255)),
	CARMOT("carmot", 				true, new ColorPair(255, 255, 255, 255, 255, 255), 	new ColorPair(255, 255, 255, 255, 255, 255), 	new ColorPair(255, 255, 255, 255, 255, 255), 	new ColorPair(255, 255, 255, 255, 255, 255)),
	MITHRIL("mithril", 				true, new ColorPair(255, 255, 255, 255, 255, 255), 	new ColorPair(255, 255, 255, 255, 255, 255), 	new ColorPair(255, 255, 255, 255, 255, 255), 	new ColorPair(255, 255, 255, 255, 255, 255)),
	RUBRACIUM("rubracium", 			true, new ColorPair(255, 255, 255, 255, 255, 255), 	new ColorPair(255, 255, 255, 255, 255, 255), 	new ColorPair(255, 255, 255, 255, 255, 255), 	new ColorPair(255, 255, 255, 255, 255, 255)),
	QUICKSILVER("quicksilver", 		true, new ColorPair(255, 255, 255, 255, 255, 255), 	new ColorPair(255, 255, 255, 255, 255, 255), 	new ColorPair(255, 255, 255, 255, 255, 255), 	new ColorPair(255, 255, 255, 255, 255, 255)),
	HADEROTH("haderoth", 			true, new ColorPair(255, 255, 255, 255, 255, 255), 	new ColorPair(255, 255, 255, 255, 255, 255), 	new ColorPair(255, 255, 255, 255, 255, 255),	new ColorPair(255, 255, 255, 255, 255, 255)),
	ORICHALCUM("orichalcum", 		true, new ColorPair(255, 255, 255, 255, 255, 255), 	new ColorPair(255, 255, 255, 255, 255, 255), 	new ColorPair(255, 255, 255, 255, 255, 255),	new ColorPair(255, 255, 255, 255, 255, 255)),
	CELENEGIL("celenegil", 			true, new ColorPair(255, 255, 255, 255, 255, 255), 	new ColorPair(255, 255, 255, 255, 255, 255), 	new ColorPair(255, 255, 255, 255, 255, 255), 	new ColorPair(255, 255, 255, 255, 255, 255)),
	ADAMANTINE("adamantine",		true, new ColorPair(255, 255, 255, 255, 255, 255), 	new ColorPair(255, 255, 255, 255, 255, 255), 	new ColorPair(255, 255, 255, 255, 255, 255), 	new ColorPair(255, 255, 255, 255, 255, 255)),
	ATLARUS("atlarus", 				true, new ColorPair(255, 255, 255, 255, 255, 255), 	new ColorPair(255, 255, 255, 255, 255, 255), 	new ColorPair(255, 255, 255, 255, 255, 255), 	new ColorPair(255, 255, 255, 255, 255, 255)),
	TARTARITE("tartarite", 			true, new ColorPair(255, 255, 255, 255, 255, 255), 	new ColorPair(255, 255, 255, 255, 255, 255), 	new ColorPair(255, 255, 255, 255, 255, 255), 	new ColorPair(255, 255, 255, 255, 255, 255)),
	PHOSPHORITE("phosphorite", 		true, new ColorPair(255, 255, 255, 255, 255, 255), 	new ColorPair(255, 255, 255, 255, 255, 255), 	new ColorPair(255, 255, 255, 255, 255, 255), 	new ColorPair(255, 255, 255, 255, 255, 255)),
	SULFUR("sulfur", 				true, new ColorPair(255, 255, 255, 255, 255, 255), 	new ColorPair(255, 255, 255, 255, 255, 255), 	new ColorPair(255, 255, 255, 255, 255, 255), 	new ColorPair(255, 255, 255, 255, 255, 255)),
	SALTPETER("saltpeter", 			true, new ColorPair(255, 255, 255, 255, 255, 255), 	new ColorPair(255, 255, 255, 255, 255, 255), 	new ColorPair(255, 255, 255, 255, 255, 255), 	new ColorPair(255, 255, 255, 255, 255, 255)),
	MAGNESIUM("magnesium", 			true, new ColorPair(255, 255, 255, 255, 255, 255), 	new ColorPair(255, 255, 255, 255, 255, 255), 	new ColorPair(255, 255, 255, 255, 255, 255), 	new ColorPair(255, 255, 255, 255, 255, 255)),
	BITUMEN("bitumen", 				true, new ColorPair(255, 255, 255, 255, 255, 255), 	new ColorPair(255, 255, 255, 255, 255, 255), 	new ColorPair(255, 255, 255, 255, 255, 255), 	new ColorPair(255, 255, 255, 255, 255, 255)),
	POTASH("potash", 				true, new ColorPair(255, 255, 255, 255, 255, 255), 	new ColorPair(255, 255, 255, 255, 255, 255), 	new ColorPair(255, 255, 255, 255, 255, 255), 	new ColorPair(255, 255, 255, 255, 255, 255)),
	EXIMITE("eximite", 				true, new ColorPair(255, 255, 255, 255, 255, 255), 	new ColorPair(255, 255, 255, 255, 255, 255), 	new ColorPair(255, 255, 255, 255, 255, 255), 	new ColorPair(255, 255, 255, 255, 255, 255)),
	MEUTOITE("meutoite", 			true, new ColorPair(255, 255, 255, 255, 255, 255), 	new ColorPair(255, 255, 255, 255, 255, 255), 	new ColorPair(255, 255, 255, 255, 255, 255), 	new ColorPair(255, 255, 255, 255, 255, 255)),
	DESICHALKOS("desichalkos", 		true, new ColorPair(255, 255, 255, 255, 255, 255), 	new ColorPair(255, 255, 255, 255, 255, 255), 	new ColorPair(255, 255, 255, 255, 255, 255), 	new ColorPair(255, 255, 255, 255, 255, 255));
	
	MetallurgyBeeTypes(String name, boolean hasHive, ColorPair rough, ColorPair refined, ColorPair reforged , ColorPair comb) {
		this.name = name;
		this.hasHive = hasHive;
		this.colorBeeRoughPrimary = rough.getRGB1();
		this.colorBeeRoughSecondary = rough.getRGB2();
		this.colorBeeRefinedPrimary = refined.getRGB1();
		this.colorBeeRefinedSecondary = refined.getRGB2();
		this.colorBeeReforgedPrimary = reforged.getRGB1();
		this.colorBeeReforgedSecondary = reforged.getRGB2();
		this.colorCombPrimary = comb.getRGB1();
		this.colorCombSecondary = comb.getRGB2();
	}	
	
	public String name;
	public String setName;
	public int colorBeeRoughPrimary;
	public int colorBeeRoughSecondary;
	public int colorBeeRefinedPrimary;
	public int colorBeeRefinedSecondary;
	public int colorBeeReforgedPrimary;
	public int colorBeeReforgedSecondary;
	public int colorCombPrimary;
	public int colorCombSecondary;
	
	public boolean hasHive;
	
	public ArrayList<IHiveDrop> hiveDrops = new ArrayList<IHiveDrop>();
	
	public Icon iconBeehiveTop;
	public Icon iconBeehiveSide;
	
	public AlleleBeeSpecies speciesRough;
	public AlleleBeeSpecies speciesRefined;
	public AlleleBeeSpecies speciesReforged;
}
