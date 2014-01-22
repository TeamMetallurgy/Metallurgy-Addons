package elcon.mods.metallurgyaddons.forestry;

import net.minecraft.block.Block;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.network.packet.Packet53BlockChange;
import net.minecraft.world.World;
import net.minecraftforge.common.Configuration;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.ForgeSubscribe;
import net.minecraftforge.event.world.BlockEvent.BreakEvent;
import rebelkeithy.mods.metallurgy.api.OreType;
import cpw.mods.fml.common.registry.GameRegistry;
import elcon.mods.metallurgyaddons.MetallurgyAddon;
import elcon.mods.metallurgyaddons.Metals;
import elcon.mods.metallurgyaddons.core.blocks.BlockExtendedMetadata;
import elcon.mods.metallurgyaddons.core.items.ItemBlockExtendedMetadata;
import elcon.mods.metallurgyaddons.forestry.blocks.BlockBeehive;
import elcon.mods.metallurgyaddons.forestry.items.ItemHoneyComb;
import elcon.mods.metallurgyaddons.forestry.worldgen.WorldGenBeehives;
import forestry.api.apiculture.EnumBeeChromosome;
import forestry.api.apiculture.IBeeRoot;
import forestry.api.genetics.AlleleManager;
import forestry.api.genetics.IAllele;
import forestry.api.genetics.IClassification;
import forestry.api.recipes.RecipeManagers;
import forestry.apiculture.genetics.AlleleBeeSpecies;
import forestry.apiculture.genetics.AlleleFlowers;
import forestry.apiculture.genetics.BeeMutation;
import forestry.apiculture.genetics.BranchBees;
import forestry.apiculture.genetics.HiveDrop;
import forestry.core.config.ForestryItem;
import forestry.core.utils.Localization;

public class MetallurgyAddonForestry extends MetallurgyAddon {

	public static MACreativeTabForestry creativeTab = new MACreativeTabForestry("MetallurgyBees");

	public static int blockBeehiveID = 1500;
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
		for(int i = 0; i < MetallurgyBeeTypes.values().length; i++) {
			// MinecraftForge.setBlockHarvestLevel(beehive, i, "pickaxe", Metals.getMetal(MetallurgyBeeTypes.values()[i].name).oreInfo.getBlockHarvestLevel());
			MinecraftForge.setBlockHarvestLevel(beehive, i, "pickaxe", 0);
		}

		// add localizations to Forestry's Localization
		Localization.instance.addLocalization("/assets/metallurgybees/lang/");
	}

	@Override
	public void init() {
		// register events
		MinecraftForge.EVENT_BUS.register(this);
	}

	@Override
	public void postInit() {
		beeRoot = (IBeeRoot) AlleleManager.alleleRegistry.getSpeciesRoot("rootBees");

		alleleFlowerStone = new AlleleFlowers("flowerStone", new FlowerProviderStone(), true);

		branchMetal = new BranchBees("metal", "Metallum");
		AlleleManager.alleleRegistry.getClassification("family.apidae").addMemberGroup(branchMetal);

		for(MetallurgyBeeTypes beeType : MetallurgyBeeTypes.values()) {
			beeType.metal = Metals.getMetal(beeType.name);
			beeType.hasHive = beeType.metal.oreInfo.getType() != OreType.ALLOY;

			// init bee species alleles
			beeType.speciesRough = new AlleleBeeSpecies(beeType.name + "Rough", true, "metallurgy.bees." + beeType.name + ".rough", branchMetal, "metallum", beeType.colorBeeRoughPrimary, beeType.colorBeeRoughSecondary).addProduct(new ItemStack(honeyComb.itemID, 1, beeType.ordinal()), 30);
			beeType.speciesRefined = new AlleleBeeSpecies(beeType.name + "Refined", true, "metallurgy.bees." + beeType.name + ".refined", branchMetal, "metallum", beeType.colorBeeRefinedPrimary, beeType.colorBeeRefinedSecondary).addProduct(new ItemStack(honeyComb.itemID, 1, beeType.ordinal()), 50);
			beeType.speciesReforged = (AlleleBeeSpecies) new AlleleBeeSpecies(beeType.name + "Reforged", true, "metallurgy.bees." + beeType.name + ".reforged", branchMetal, "metallum", beeType.colorBeeReforgedPrimary, beeType.colorBeeReforgedSecondary).addProduct(new ItemStack(honeyComb.itemID, 1, beeType.ordinal()), 70);

			// register templates
			beeRoot.registerTemplate(getMetalBeeRoughTemplate(beeType));
			beeRoot.registerTemplate(getMetalBeeRefinedTemplate(beeType));
			beeRoot.registerTemplate(getMetalBeeReforgedTemplate(beeType));

			// init bee mutations
			if(beeType.hasHive) {
				new BeeMutation(beeType.speciesRough, beeType.speciesRough, getMetalBeeRefinedTemplate(beeType), 5);
				new BeeMutation(beeType.speciesRefined, beeType.speciesRefined, getMetalBeeReforgedTemplate(beeType), 2);
			}

			// register centrifuge recipes
			RecipeManagers.centrifugeManager.addRecipe(20, new ItemStack(honeyComb.itemID, 1, beeType.ordinal()), new ItemStack[]{Metals.getMetal(beeType.name).oreInfo.getDust(), ForestryItem.beeswax.getItemStack(), ForestryItem.honeyDrop.getItemStack()}, new int[]{25, 50, 25});

			// add hives and their drops
			if(beeType.hasHive) {
				beeType.hiveDrops.add(new HiveDrop(getMetalBeeRoughTemplate(beeType), new ItemStack[]{new ItemStack(honeyComb.itemID, 1, beeType.ordinal())}, 80));

				GameRegistry.registerWorldGenerator(new WorldGenBeehives(beeType));
			}
		}
		createAlloyBeeMutations();
	}

	public void createAlloyBeeMutations() {
		new BeeMutation(MetallurgyBeeTypes.COPPER.speciesRough, MetallurgyBeeTypes.TIN.speciesRough, getMetalBeeRoughTemplate(MetallurgyBeeTypes.BRONZE), 10);
		new BeeMutation(MetallurgyBeeTypes.COPPER.speciesRefined, MetallurgyBeeTypes.TIN.speciesRefined, getMetalBeeRefinedTemplate(MetallurgyBeeTypes.BRONZE), 5);
		new BeeMutation(MetallurgyBeeTypes.COPPER.speciesReforged, MetallurgyBeeTypes.TIN.speciesReforged, getMetalBeeReforgedTemplate(MetallurgyBeeTypes.BRONZE), 2);

		createMutations(MetallurgyBeeTypes.BRONZE.speciesRough, MetallurgyBeeTypes.GOLD.speciesRough, MetallurgyBeeTypes.HEPATIZON);
		createMutations(MetallurgyBeeTypes.BRONZE.speciesRough, MetallurgyBeeTypes.IRON.speciesRough, MetallurgyBeeTypes.DAMASCUS_STEEL);
		createMutations(MetallurgyBeeTypes.IRON.speciesRough, MetallurgyBeeTypes.GOLD.speciesRough, MetallurgyBeeTypes.ANGMALLEN);
		createMutations(MetallurgyBeeTypes.IRON.speciesRough, MetallurgyBeeTypes.MANGANESE.speciesRough, MetallurgyBeeTypes.STEEL);
		createMutations(MetallurgyBeeTypes.ZINC.speciesRough, MetallurgyBeeTypes.COPPER.speciesRough, MetallurgyBeeTypes.BRASS);
		createMutations(MetallurgyBeeTypes.GOLD.speciesRough, MetallurgyBeeTypes.SILVER.speciesRough, MetallurgyBeeTypes.ELECTRUM);
		createMutations(MetallurgyBeeTypes.LEMURITE.speciesRough, MetallurgyBeeTypes.SHADOW_IRON.speciesRough, MetallurgyBeeTypes.SHADOW_STEEL);
	}

	public void createMutations(AlleleBeeSpecies parent1, AlleleBeeSpecies parent2, MetallurgyBeeTypes child) {
		new BeeMutation(parent1, parent2, getMetalBeeRoughTemplate(child), 10);
		new BeeMutation(child.speciesRough, child.speciesRough, getMetalBeeRefinedTemplate(child), 5);
		new BeeMutation(child.speciesRefined, child.speciesRefined, getMetalBeeReforgedTemplate(child), 2);
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

	@ForgeSubscribe
	public void onBlockBreak(BreakEvent event) {
		if(event.block.blockID == beehive.blockID) {
			event.setCanceled(true);
			ItemStack stack = event.getPlayer().getCurrentEquippedItem();
			if(stack != null && stack.getItem().onBlockStartBreak(stack, event.x, event.y, event.z, event.getPlayer())) {
				return;
			}
			int id = event.world.getBlockId(event.x, event.y, event.z);
			int meta = ((BlockExtendedMetadata) event.block).getMetadata(event.world, event.x, event.y, event.z);
			event.world.playAuxSFXAtEntity(event.getPlayer(), 2001, event.x, event.y, event.z, id);
			boolean flag = false;
			if(event.getPlayer().capabilities.isCreativeMode) {
				flag = removeBlock(event.world, event.x, event.y, event.z, meta, event.getPlayer());
				((EntityPlayerMP) event.getPlayer()).playerNetServerHandler.sendPacketToPlayer(new Packet53BlockChange(event.x, event.y, event.z, event.world));
			} else {
				ItemStack itemstack = event.getPlayer().getCurrentEquippedItem();
				boolean flag1 = false;
				Block block = Block.blocksList[id];
				if(block != null) {
					flag1 = block.canHarvestBlock(event.getPlayer(), meta);
				}
				if(itemstack != null) {
					itemstack.onBlockDestroyed(event.world, id, event.x, event.y, event.z, event.getPlayer());
					if(itemstack.stackSize == 0) {
						event.getPlayer().destroyCurrentEquippedItem();
					}
				}
				flag = removeBlock(event.world, event.x, event.y, event.z, meta, event.getPlayer());
				if(flag && flag1) {
					Block.blocksList[id].harvestBlock(event.world, event.getPlayer(), event.x, event.y, event.z, meta);
				}
			}
			if(!event.getPlayer().capabilities.isCreativeMode && flag && event != null) {
				Block.blocksList[id].dropXpOnBlockBreak(event.world, event.x, event.y, event.z, event.getExpToDrop());
			}
		}
	}

	private boolean removeBlock(World world, int x, int y, int z, int meta, EntityPlayer player) {
		Block block = Block.blocksList[world.getBlockId(x, y, z)];
		if(block != null) {
			block.onBlockHarvested(world, x, y, z, meta, player);
		}
		boolean flag = (block != null && block.removeBlockByPlayer(world, player, x, y, z));
		if(block != null && flag) {
			block.onBlockDestroyedByPlayer(world, x, y, z, meta);
		}
		return flag;
	}
}
