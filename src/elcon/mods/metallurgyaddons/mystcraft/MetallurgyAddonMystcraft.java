package elcon.mods.metallurgyaddons.mystcraft;

import net.minecraftforge.common.Configuration;

import com.xcompwiz.mystcraft.api.MystAPI;
import com.xcompwiz.mystcraft.api.internals.BlockCategory;
import com.xcompwiz.mystcraft.api.internals.BlockDescriptor;
import com.xcompwiz.mystcraft.api.internals.IInstabilityFormula;
import com.xcompwiz.mystcraft.api.symbol.IAgeSymbol;
import com.xcompwiz.mystcraft.symbols.ModifierBlock;

import elcon.mods.metallurgyaddons.MetallurgyAddon;
import elcon.mods.metallurgyaddons.Metals;
import elcon.mods.metallurgyaddons.Metals.Metal;

public class MetallurgyAddonMystcraft extends MetallurgyAddon {

	@Override
	public String getID() {
		return "MetallurgyAddonMystcraft";
	}

	@Override
	public String getName() {
		return "Metallurgy Addon Mystcraft";
	}

	@Override
	public void preInit() {

	}

	@Override
	public void init() {

	}

	@Override
	public void postInit() {
		// register Mystcraft symbols
		for(Metal metal : Metals.metals.values()) {
			if(metal.oreInfo.getOre() != null) {
				MystAPI.symbol.registerSymbol(createBlockSymbol("Terrain", 0.18F, 0.18F, metal.oreInfo.getOre().itemID, (byte) metal.oreInfo.getOre().getItemDamage(), new BlockCategory[]{BlockCategory.TERRAIN, BlockCategory.STRUCTURE, BlockCategory.SOLID}));
			}
		}
	}

	@Override
	public void loadConfig(Configuration config, String category) {

	}

	@Override
	public void saveConfig(Configuration config, String category) {

	}

	public IAgeSymbol createBlockSymbol(String word, float grammarweight, float itemrarity, int blockId, BlockCategory[] cats) {
		return createBlockSymbol(word, grammarweight, itemrarity, blockId, (byte) 0, null, cats);
	}

	public IAgeSymbol createBlockSymbol(String word, float grammarweight, float itemrarity, int blockId, byte metadata, BlockCategory[] cats) {
		return createBlockSymbol(word, grammarweight, itemrarity, blockId, metadata, null, cats);
	}

	public IAgeSymbol createBlockSymbol(String word, float grammarweight, float itemrarity, int blockId, IInstabilityFormula formulaLinear, BlockCategory[] cats) {
		return createBlockSymbol(word, grammarweight, itemrarity, blockId, (byte) 0, formulaLinear, cats);
	}

	public IAgeSymbol createBlockSymbol(String word, float grammarweight, float itemrarity, int blockId, byte metadata, IInstabilityFormula formulaLinear, BlockCategory[] cats) {
		BlockDescriptor block = new BlockDescriptor((short) blockId, metadata, formulaLinear);
		for(BlockCategory cat : cats) {
			block.setUsable(cat, true);
		}
		return new ModifierBlock(block, word, grammarweight).setRarity(itemrarity);
	}
}
