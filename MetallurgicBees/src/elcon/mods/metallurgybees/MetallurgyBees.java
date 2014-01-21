package elcon.mods.metallurgybees;

import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.common.MinecraftForge;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.EventHandler;
import cpw.mods.fml.common.Mod.Instance;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.network.NetworkMod;
import cpw.mods.fml.common.network.NetworkMod.SidedPacketHandler;
import cpw.mods.fml.common.registry.GameRegistry;
import elcon.mods.metallurgybees.bees.FlowerProviderStone;
import elcon.mods.metallurgybees.bees.MetallurgyBeeTypes;
import elcon.mods.metallurgybees.blocks.BlockBeehive;
import elcon.mods.metallurgybees.items.ItemBlockBeehive;
import elcon.mods.metallurgybees.items.ItemHoneyComb;
import elcon.mods.metallurgybees.misc.MBConfig;
import elcon.mods.metallurgybees.misc.MBReference;
import elcon.mods.metallurgybees.tileentities.TileEntityBeehive;
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

@Mod(modid = MBReference.MOD_ID, name = MBReference.NAME, version = MBReference.VERSION, acceptedMinecraftVersions = MBReference.MC_VERSION, dependencies = MBReference.DEPENDENCIES)
@NetworkMod(clientSideRequired = true, serverSideRequired = false, clientPacketHandlerSpec = @SidedPacketHandler(channels = { "Metallurgy" }, packetHandler = MBPacketHandlerClient.class))
public class MetallurgyBees {

	@Instance(MBReference.MOD_ID)
	public static MetallurgyBees instance;

	@SidedProxy(clientSide = MBReference.CLIENT_PROXY_CLASS, serverSide = MBReference.SERVER_PROXY_CLASS)
	public static MBCommonProxy proxy;

	public static MBConfig config;
	public static MBCreativeTab creativeTab = new MBCreativeTab("MetallurgyBees");

	public static Block beehive;

	public static Item honeyComb;

	public static IBeeRoot beeRoot;
	public static IClassification branchMetal;
	public static AlleleFlowers alleleFlowerStone;

	@EventHandler
	public void preInit(FMLPreInitializationEvent event) {
		// init config
		config = new MBConfig(event.getSuggestedConfigurationFile());
		config.load();
		config.save();

		// init blocks
		beehive = new BlockBeehive(MBConfig.beehiveID).setUnlocalizedName("metallurgyBeehive");

		// register blocks
		GameRegistry.registerBlock(beehive, ItemBlockBeehive.class, "metallurgyBeehive");

		// init items
		honeyComb = new ItemHoneyComb(MBConfig.honeyCombID).setUnlocalizedName("metallurgyHoneyComb");

		// register items
		GameRegistry.registerItem(honeyComb, "metallurgyHoneyComb");

		// register tileentities
		GameRegistry.registerTileEntity(TileEntityBeehive.class, "MetallurgyBeehive");

		// set block harvest levels
		MinecraftForge.setBlockHarvestLevel(beehive, "pickaxe", 0);

		// add localizations to Forestry's Localization
		Localization.instance.addLocalization("/assets/metallurgybees/lang/");
	}

	@EventHandler
	public void init(FMLInitializationEvent event) {
		proxy.registerRenderingInformation();
	}

	@EventHandler
	public void postInit(FMLPostInitializationEvent event) {
		beeRoot = (IBeeRoot) AlleleManager.alleleRegistry.getSpeciesRoot("rootBees");

		alleleFlowerStone = new AlleleFlowers("flowerStone", new FlowerProviderStone(), true);

		branchMetal = new BranchBees("metal", "Metallum");
		AlleleManager.alleleRegistry.getClassification("family.apidae").addMemberGroup(branchMetal);

		for (MetallurgyBeeTypes beeType : MetallurgyBeeTypes.values()) {

			// init bee species alleles
			beeType.speciesRough = new AlleleBeeSpecies(beeType.name + "Rough", true, "metallurgy.metals." + beeType.name, branchMetal, "metallum", beeType.colorBeeRoughPrimary, beeType.colorBeeRoughSecondary);
			beeType.speciesRefined = new AlleleBeeSpecies(beeType.name + "Refined", true, "metallurgy.metals." + beeType.name, branchMetal, "metallum", beeType.colorBeeRefinedPrimary, beeType.colorBeeRefinedSecondary);
			beeType.speciesReforged = (AlleleBeeSpecies) new AlleleBeeSpecies(beeType.name + "Reforged", true, "metallurgy.metals." + beeType.name, branchMetal, "metallum", beeType.colorBeeReforgedPrimary, beeType.colorBeeReforgedSecondary).setHasEffect();

			// register templates
			beeRoot.registerTemplate(getMetalBeeRoughTemplate(beeType));
			beeRoot.registerTemplate(getMetalBeeRefinedTemplate(beeType));
			beeRoot.registerTemplate(getMetalBeeReforgedTemplate(beeType));

			// add hive drops
			if (!beeType.alloy) {
				beeType.hiveDrops.add(new HiveDrop(getMetalBeeRoughTemplate(beeType), new ItemStack[] { new ItemStack(honeyComb.itemID, 1, beeType.ordinal()) }, 80));
				new BeeMutation(beeType.speciesRough, beeType.speciesRough, getMetalBeeRefinedTemplate(beeType), 5);
				new BeeMutation(beeType.speciesRefined, beeType.speciesRefined, getMetalBeeReforgedTemplate(beeType), 2);
			}

		}

		createAlloyMutations();
	}

	private void createAlloyMutations() {
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

	public static String firstUpperCase(String s) {
		return Character.toString(s.charAt(0)).toUpperCase() + s.substring(1, s.length());
	}
}
