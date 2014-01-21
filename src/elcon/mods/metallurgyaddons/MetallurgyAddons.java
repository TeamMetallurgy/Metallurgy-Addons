package elcon.mods.metallurgyaddons;

import java.util.ArrayList;

import cpw.mods.fml.common.Loader;
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
import elcon.mods.metallurgyaddons.core.tileentities.TileEntityExtended;
import elcon.mods.metallurgyaddons.core.tileentities.TileEntityMetadata;
import elcon.mods.metallurgyaddons.forestry.MetallurgyAddonForestry;
import elcon.mods.metallurgyaddons.mystcraft.MetallurgyAddonMystcraft;

@Mod(modid = MAReference.MOD_ID, name = MAReference.NAME, version = MAReference.VERSION, acceptedMinecraftVersions = MAReference.MC_VERSION, dependencies = MAReference.DEPENDENCIES)
@NetworkMod(clientSideRequired = true, serverSideRequired = false, clientPacketHandlerSpec = @SidedPacketHandler(channels = {"MetallurgyAddons"}, packetHandler = MAPacketHandlerClient.class))
public class MetallurgyAddons {

	@Instance(MAReference.MOD_ID)
	public static MetallurgyAddons instance;

	@SidedProxy(clientSide = MAReference.CLIENT_PROXY_CLASS, serverSide = MAReference.SERVER_PROXY_CLASS)
	public static MACommonProxy proxy;

	public static MAConfig config;

	public static ArrayList<MetallurgyAddon> addons = new ArrayList<MetallurgyAddon>();

	@EventHandler
	public void preInit(FMLPreInitializationEvent event) {
		// init config
		config = new MAConfig(event.getSuggestedConfigurationFile());
		config.load();
		config.save();

		// register tileentities
		GameRegistry.registerTileEntity(TileEntityExtended.class, "MetallurgyTileExtended");
		GameRegistry.registerTileEntity(TileEntityMetadata.class, "MetallurgyTileMetadata");
		
		//register addons
		addAddons();
		
		for(MetallurgyAddon addon : addons) {
			addon.preInit();
		}
	}

	@EventHandler
	public void init(FMLInitializationEvent event) {
		proxy.registerRenderingInformation();

		for(MetallurgyAddon addon : addons) {
			addon.init();
		}
	}

	@EventHandler
	public void postInit(FMLPostInitializationEvent event) {
		//init metals
		Metals.init();
		
		for(MetallurgyAddon addon : addons) {
			addon.postInit();
		}
	}
	
	public void addAddons() {
		if(Loader.isModLoaded("Forestry")) {
			registerAddon(new MetallurgyAddonForestry());
		}
		if(Loader.isModLoaded("Mystcraft")) {
			registerAddon(new MetallurgyAddonMystcraft());
		}
	}

	public static void registerAddon(MetallurgyAddon addon) {
		addons.add(addon);
	}

	public static void unregisterAddon(MetallurgyAddon addon) {
		addons.remove(addon);
	}
}
