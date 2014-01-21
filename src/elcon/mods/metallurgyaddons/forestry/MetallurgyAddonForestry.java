package elcon.mods.metallurgyaddons.forestry;

import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.common.Configuration;
import net.minecraftforge.common.MinecraftForge;
import cpw.mods.fml.common.registry.GameRegistry;
import elcon.mods.metallurgyaddons.MetallurgyAddon;
import elcon.mods.metallurgyaddons.core.items.ItemBlockExtendedMetadata;
import elcon.mods.metallurgyaddons.forestry.blocks.BlockBeehive;
import elcon.mods.metallurgyaddons.forestry.items.ItemHoneyComb;
import forestry.api.apiculture.EnumBeeChromosome;
import forestry.api.apiculture.IBeeRoot;
import forestry.api.genetics.AlleleManager;
import forestry.api.genetics.IAllele;
import forestry.api.genetics.IClassification;
import forestry.apiculture.genetics.AlleleBeeSpecies;
import forestry.apiculture.genetics.AlleleFlowers;
import forestry.apiculture.genetics.BeeMutation;
import forestry.apiculture.genetics.BranchBees;
import forestry.apiculture.genetics.HiveDrop;
import forestry.core.utils.Localization;

public class MetallurgyAddonForestry extends MetallurgyAddon {

	public static MACreativeTabForestry creativeTab = new MACreativeTabForestry("MetallurgyBees");

	public static int blockBeehiveID = 4000;
	public static int itemHoneyCombID = 6000;

	public static Block beehive;

	public static Item honeyComb;

	public static IBeeRoot beeRoot;
	public static IClassification branchMetal;
	public static AlleleFlowers alleleFlowerStone;

	@Override
	public String getID() {
		return "MetallurgyAddonForestry";
	}

	@Override
	public String getName() {
		return "Metallurgy Forestry Addon";
	}

	@Override
	public void preInit() {
		// init blocks
		beehive = new BlockBeehive(blockBeehiveID).setUnlocalizedName("metallurgyBeehive");

		// register blocks
		GameRegistry.registerBlock(beehive, ItemBlockExtendedMetadata.class, "metallurgyBeehive");

		// init items
		honeyComb = new ItemHoneyComb(itemHoneyCombID).setUnlocalizedName("metallurgyHoneyComb");

		// register items
		GameRegistry.registerItem(honeyComb, "metallurgyHoneyComb");

		// set block harvest levels
		MinecraftForge.setBlockHarvestLevel(beehive, "pickaxe", 0);

		// add localizations to Forestry's Localization
		Localization.instance.addLocalization("/assets/metallurgybees/lang/");
	}

	@Override
	public void init() {

	}

	@Override
	public void postInit() {
		beeRoot = (IBeeRoot) AlleleManager.alleleRegistry.getSpeciesRoot("rootBees");

		alleleFlowerStone = new AlleleFlowers("flowerStone", new FlowerProviderStone(), true);

		branchMetal = new BranchBees("metal", "Metallum");
		AlleleManager.alleleRegistry.getClassification("family.apidae").addMemberGroup(branchMetal);

		for(int i = 0; i < MetallurgyBeeTypes.values().length; i++) {
			MetallurgyBeeTypes beeType = MetallurgyBeeTypes.values()[i];

			// init bee species alleles
			beeType.speciesRough = new AlleleBeeSpecies(beeType.name + "Rough", true, "metallurgy.metals." + beeType.name, branchMetal, "metallum", beeType.colorBeeRoughPrimary, beeType.colorBeeRoughSecondary);
			beeType.speciesRefined = new AlleleBeeSpecies(beeType.name + "Refined", true, "metallurgy.metals." + beeType.name, branchMetal, "metallum", beeType.colorBeeRefinedPrimary, beeType.colorBeeRefinedSecondary);
			beeType.speciesReforged = (AlleleBeeSpecies) new AlleleBeeSpecies(beeType.name + "Reforged", true, "metallurgy.metals." + beeType.name, branchMetal, "metallum", beeType.colorBeeReforgedPrimary, beeType.colorBeeReforgedSecondary).setHasEffect();

			// register templates
			beeRoot.registerTemplate(getMetalBeeRoughTemplate(beeType));
			beeRoot.registerTemplate(getMetalBeeRefinedTemplate(beeType));
			beeRoot.registerTemplate(getMetalBeeReforgedTemplate(beeType));

			// init bee mutations
			if(beeType.hasHive) {
				new BeeMutation(beeType.speciesRough, beeType.speciesRough, getMetalBeeRefinedTemplate(beeType), 5);
				new BeeMutation(beeType.speciesRefined, beeType.speciesRefined, getMetalBeeReforgedTemplate(beeType), 2);
			}

			// add hive drops
			if(beeType.hasHive) {
				beeType.hiveDrops.add(new HiveDrop(getMetalBeeRoughTemplate(beeType), new ItemStack[]{new ItemStack(honeyComb.itemID, 1, i)}, 80));
			}
		}
		createAlloyBeeMutations();
	}

	public void createAlloyBeeMutations() {
		new BeeMutation(MetallurgyBeeTypes.COPPPER.speciesRough, MetallurgyBeeTypes.TIN.speciesRough, getMetalBeeRoughTemplate(MetallurgyBeeTypes.BRONZE), 10);
		new BeeMutation(MetallurgyBeeTypes.COPPPER.speciesRefined, MetallurgyBeeTypes.TIN.speciesRefined, getMetalBeeRefinedTemplate(MetallurgyBeeTypes.BRONZE), 5);
		new BeeMutation(MetallurgyBeeTypes.COPPPER.speciesReforged, MetallurgyBeeTypes.TIN.speciesReforged, getMetalBeeReforgedTemplate(MetallurgyBeeTypes.BRONZE), 2);

		new BeeMutation(MetallurgyBeeTypes.BRONZE.speciesRough, MetallurgyBeeTypes.GOLD.speciesRough, getMetalBeeRoughTemplate(MetallurgyBeeTypes.HEPATIZON), 10);
		new BeeMutation(MetallurgyBeeTypes.HEPATIZON.speciesRough, MetallurgyBeeTypes.HEPATIZON.speciesRough, getMetalBeeRefinedTemplate(MetallurgyBeeTypes.HEPATIZON), 5);
		new BeeMutation(MetallurgyBeeTypes.HEPATIZON.speciesRefined, MetallurgyBeeTypes.HEPATIZON.speciesRefined, getMetalBeeReforgedTemplate(MetallurgyBeeTypes.HEPATIZON), 2);

		new BeeMutation(MetallurgyBeeTypes.BRONZE.speciesRough, MetallurgyBeeTypes.IRON.speciesRough, getMetalBeeRoughTemplate(MetallurgyBeeTypes.DAMASCUS_STEEL), 10);
		new BeeMutation(MetallurgyBeeTypes.DAMASCUS_STEEL.speciesRough, MetallurgyBeeTypes.DAMASCUS_STEEL.speciesRough, getMetalBeeRefinedTemplate(MetallurgyBeeTypes.DAMASCUS_STEEL), 5);
		new BeeMutation(MetallurgyBeeTypes.DAMASCUS_STEEL.speciesRefined, MetallurgyBeeTypes.DAMASCUS_STEEL.speciesRefined, getMetalBeeReforgedTemplate(MetallurgyBeeTypes.DAMASCUS_STEEL), 2);

		new BeeMutation(MetallurgyBeeTypes.IRON.speciesRough, MetallurgyBeeTypes.GOLD.speciesRough, getMetalBeeRoughTemplate(MetallurgyBeeTypes.ANGMALLEN), 10);
		new BeeMutation(MetallurgyBeeTypes.ANGMALLEN.speciesRough, MetallurgyBeeTypes.ANGMALLEN.speciesRough, getMetalBeeRefinedTemplate(MetallurgyBeeTypes.ANGMALLEN), 5);
		new BeeMutation(MetallurgyBeeTypes.ANGMALLEN.speciesRefined, MetallurgyBeeTypes.ANGMALLEN.speciesRefined, getMetalBeeReforgedTemplate(MetallurgyBeeTypes.ANGMALLEN), 2);

		new BeeMutation(MetallurgyBeeTypes.IRON.speciesRough, MetallurgyBeeTypes.MANGANESE.speciesRough, getMetalBeeRoughTemplate(MetallurgyBeeTypes.STEEL), 10);
		new BeeMutation(MetallurgyBeeTypes.STEEL.speciesRough, MetallurgyBeeTypes.STEEL.speciesRough, getMetalBeeRefinedTemplate(MetallurgyBeeTypes.STEEL), 5);
		new BeeMutation(MetallurgyBeeTypes.STEEL.speciesRefined, MetallurgyBeeTypes.STEEL.speciesRefined, getMetalBeeReforgedTemplate(MetallurgyBeeTypes.STEEL), 2);

		new BeeMutation(MetallurgyBeeTypes.ZINC.speciesRough, MetallurgyBeeTypes.COPPPER.speciesRough, getMetalBeeRoughTemplate(MetallurgyBeeTypes.BRASS), 10);
		new BeeMutation(MetallurgyBeeTypes.BRASS.speciesRough, MetallurgyBeeTypes.BRASS.speciesRough, getMetalBeeRefinedTemplate(MetallurgyBeeTypes.BRASS), 5);
		new BeeMutation(MetallurgyBeeTypes.BRASS.speciesRefined, MetallurgyBeeTypes.BRASS.speciesRefined, getMetalBeeReforgedTemplate(MetallurgyBeeTypes.BRASS), 2);

		new BeeMutation(MetallurgyBeeTypes.GOLD.speciesRough, MetallurgyBeeTypes.SILVER.speciesRough, getMetalBeeRoughTemplate(MetallurgyBeeTypes.ELECTRUM), 10);
		new BeeMutation(MetallurgyBeeTypes.ELECTRUM.speciesRough, MetallurgyBeeTypes.ELECTRUM.speciesRough, getMetalBeeRefinedTemplate(MetallurgyBeeTypes.ELECTRUM), 5);
		new BeeMutation(MetallurgyBeeTypes.ELECTRUM.speciesRefined, MetallurgyBeeTypes.ELECTRUM.speciesRefined, getMetalBeeReforgedTemplate(MetallurgyBeeTypes.ELECTRUM), 2);

		new BeeMutation(MetallurgyBeeTypes.LEMURITE.speciesRough, MetallurgyBeeTypes.SHADOW_IRON.speciesRough, getMetalBeeRoughTemplate(MetallurgyBeeTypes.SHADOW_STEEL), 10);
		new BeeMutation(MetallurgyBeeTypes.SHADOW_STEEL.speciesRough, MetallurgyBeeTypes.SHADOW_STEEL.speciesRough, getMetalBeeRefinedTemplate(MetallurgyBeeTypes.SHADOW_STEEL), 5);
		new BeeMutation(MetallurgyBeeTypes.SHADOW_STEEL.speciesRefined, MetallurgyBeeTypes.SHADOW_STEEL.speciesRefined, getMetalBeeReforgedTemplate(MetallurgyBeeTypes.SHADOW_STEEL), 2);
	}

	public IAllele[] getDefaultMetalBeeTemplate() {
		IAllele[] alleles = beeRoot.getDefaultTemplate();
		alleles[EnumBeeChromosome.FLOWER_PROVIDER.ordinal()] = alleleFlowerStone;
		return alleles;
	}

	public IAllele[] getMetalBeeRoughTemplate(MetallurgyBeeTypes beeType) {
		IAllele[] alleles = getDefaultMetalBeeTemplate();
		alleles[EnumBeeChromosome.SPECIES.ordinal()] = beeType.speciesRough;
		return alleles;
	}

	public IAllele[] getMetalBeeRefinedTemplate(MetallurgyBeeTypes beeType) {
		IAllele[] alleles = getDefaultMetalBeeTemplate();
		alleles[EnumBeeChromosome.SPECIES.ordinal()] = beeType.speciesRefined;
		return alleles;
	}

	public IAllele[] getMetalBeeReforgedTemplate(MetallurgyBeeTypes beeType) {
		IAllele[] alleles = getDefaultMetalBeeTemplate();
		alleles[EnumBeeChromosome.SPECIES.ordinal()] = beeType.speciesReforged;
		return alleles;
	}

	@Override
	public void loadConfig(Configuration config, String category) {
		blockBeehiveID = config.getBlock("beehive", blockBeehiveID).getInt();
		itemHoneyCombID = config.getItem("honeyComb", itemHoneyCombID).getInt();
	}

	@Override
	public void saveConfig(Configuration config, String category) {

	}
}
