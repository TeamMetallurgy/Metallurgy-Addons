package elcon.mods.metallurgyaddons.forestry.worldgen;

import java.util.Random;

import rebelkeithy.mods.metallurgy.api.IOreInfo;
import rebelkeithy.mods.metallurgy.api.MetallurgyAPI;
import rebelkeithy.mods.metallurgy.core.metalsets.OreInfo;
import net.minecraft.block.Block;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraft.world.chunk.IChunkProvider;
import net.minecraft.world.gen.feature.WorldGenerator;
import net.minecraftforge.oredict.OreDictionary;
import cpw.mods.fml.common.IWorldGenerator;
import elcon.mods.metallurgyaddons.Metals;
import elcon.mods.metallurgyaddons.core.tileentities.TileEntityMetadata;
import elcon.mods.metallurgyaddons.core.util.MAUtil;
import elcon.mods.metallurgyaddons.forestry.MetallurgyAddonForestry;
import elcon.mods.metallurgyaddons.forestry.MetallurgyBeeTypes;

public class BeeGeneration extends WorldGenerator implements IWorldGenerator {

	MetallurgyBeeTypes hiveType;

	public BeeGeneration(MetallurgyBeeTypes hiveType) {
		this.hiveType = hiveType;
	}

	@Override
	public void generate(Random rand, int chunkX, int chunkZ, World world, IChunkProvider chunkGenerator, IChunkProvider chunkProvider) {
		for(int i = 0; i < 20; i++) {
			int randPosX = chunkX + rand.nextInt(16);
			int randPosY = rand.nextInt(50) + 20;
			int randPosZ = chunkZ + rand.nextInt(16);
			this.generate(world, rand, randPosX, randPosY, randPosZ);
		}
	}

	@Override
	public boolean generate(World world, Random random, int i, int j, int k) {
		Block block = Block.blocksList[world.getBlockId(i, j, k)];
		if(block != null) {
			IOreInfo info = Metals.getMetal(hiveType.name).oreInfo;
			if(info != null) {
				ItemStack ore = info.getOre();
				if(ore != null) {
					int id = ore.itemID;
					if(block.isGenMineableReplaceable(world, i, j, k, id)) {
						if(world.getBlockMetadata(i, j, k) == Metals.getMetal(hiveType.name).oreInfo.getOre().getItemDamage()) {
							world.setBlock(i, j, k, MetallurgyAddonForestry.beehive.blockID, 1, 0);
							((TileEntityMetadata) world.getBlockTileEntity(i, j, k)).setTileMetadata(hiveType.ordinal());
							return true;
						}
					}
				}
			}
		}
		return false;

	}
}
