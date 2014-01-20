package elcon.mods.metallurgybees.blocks;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import net.minecraft.block.Block;
import net.minecraft.block.BlockContainer;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.Entity;
import net.minecraft.entity.boss.EntityDragon;
import net.minecraft.entity.boss.EntityWither;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.Icon;
import net.minecraft.util.StatCollector;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import elcon.mods.metallurgybees.MetallurgyBeeTypes;
import elcon.mods.metallurgybees.MetallurgyBees;
import elcon.mods.metallurgybees.tileentities.TileEntityBeehive;
import forestry.api.apiculture.IHiveDrop;
import forestry.apiculture.MaterialBeehive;

public class BlockBeehive extends BlockContainer {
	
	public BlockBeehive(int id) {
		super(id, new MaterialBeehive(true));
		setHardness(1.5F);
		setResistance(10.0F);
		setLightValue(0.8F);
		setStepSound(Block.soundStoneFootstep);
		setCreativeTab(MetallurgyBees.creativeTab);
	}
	
	public String getLocalizedName(ItemStack stack) {
		return StatCollector.translateToLocal("metallurgy.metals." + MetallurgyBeeTypes.values()[stack.getItemDamage()].name) + " " + StatCollector.translateToLocal(getUnlocalizedName(stack));
	}
	
	public String getUnlocalizedName(ItemStack stack) {
		return getUnlocalizedName();
	}
	
	@Override
	public String getUnlocalizedName() {
		return "tile.metallurgyBeehive.name";
	}

	@Override
	public TileEntity createNewTileEntity(World world) {
		return new TileEntityBeehive();
	}

	@Override
	public boolean canEntityDestroy(World world, int x, int y, int z, Entity entity) {
		if(entity instanceof EntityDragon || entity instanceof EntityWither) {
			return false;
		}
		return true;
	}

	@Override
	public ArrayList<ItemStack> getBlockDropped(World world, int x, int y, int z, int meta, int fortune) {
		ArrayList ret = new ArrayList();
		ArrayList<IHiveDrop> dropList = MetallurgyBeeTypes.values()[((TileEntityBeehive) world.getBlockTileEntity(x, y, z)).metalType].hiveDrops;
		System.out.println(((TileEntityBeehive) world.getBlockTileEntity(x, y, z)).metalType);
		Collections.shuffle(dropList);
		int tries = 0;
		boolean hasPrincess = false;
		while((tries <= 10) && (!hasPrincess)) {
			tries++;
			for(IHiveDrop drop : dropList) {
				if(world.rand.nextInt(100) < drop.getChance(world, x, y, z)) {
					ret.add(drop.getPrincess(world, x, y, z, fortune));
					hasPrincess = true;
					break;
				}
			}
		}
		for(IHiveDrop drop : dropList) {
			if(world.rand.nextInt(100) < drop.getChance(world, x, y, z)) {
				ret.addAll(drop.getDrones(world, x, y, z, fortune));
				break;
			}
		}
		for(IHiveDrop drop : dropList) {
			if(world.rand.nextInt(100) < drop.getChance(world, x, y, z)) {
				ret.addAll(drop.getAdditional(world, x, y, z, fortune));
				break;
			}
		}
		return ret;
	}

	@Override
	public boolean removeBlockByPlayer(World world, EntityPlayer player, int x, int y, int z) {
		ArrayList<ItemStack> drops = new ArrayList<ItemStack>();
		TileEntityBeehive tile = (TileEntityBeehive) world.getBlockTileEntity(x, y, z);
		if(tile != null && !tile.hasDropppedBlock) {
			drops = Block.blocksList[world.getBlockId(x, y, z)].getBlockDropped(world, x, y, z, world.getBlockMetadata(x, y, z), EnchantmentHelper.getFortuneModifier(player));
		}
		boolean hasBeenBroken = world.setBlockToAir(x, y, z);
		if(hasBeenBroken && !world.isRemote && drops.size() > 0 && (player == null || !player.capabilities.isCreativeMode)) {
			for(ItemStack drop : drops) {
				dropBlockAsItem_do(world, x, y, z, drop);
			}
			tile.hasDropppedBlock = true;
		}
		return hasBeenBroken;
	}

	@Override
	public void dropBlockAsItemWithChance(World world, int x, int y, int z, int meta, float chance, int fortune) {
		TileEntityBeehive tile = (TileEntityBeehive) world.getBlockTileEntity(x, y, z);
		if(tile != null && !tile.hasDropppedBlock) {
			super.dropBlockAsItemWithChance(world, x, y, z, meta, chance, fortune);
		}
	}
	
	@Override
	public int getDamageValue(World world, int x, int y, int z) {
		return ((TileEntityBeehive) world.getBlockTileEntity(x, y, z)).metalType;
	}

	@Override
	@SideOnly(Side.CLIENT)
	public Icon getIcon(int side, int meta) {
		if(side == 0 || side == 1) {
			return MetallurgyBeeTypes.values()[meta].iconBeehiveTop;
		}
		return MetallurgyBeeTypes.values()[meta].iconBeehiveSide;
	}

	@Override
	@SideOnly(Side.CLIENT)
	public Icon getBlockTexture(IBlockAccess blockAccess, int x, int y, int z, int side) {
		return getIcon(side, ((TileEntityBeehive) blockAccess.getBlockTileEntity(x, y, z)).metalType);
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void registerIcons(IconRegister iconRegister) {
		for(int i = 0; i < MetallurgyBeeTypes.values().length; i++) {
			MetallurgyBeeTypes beeType = MetallurgyBeeTypes.values()[i];
			beeType.iconBeehiveSide = iconRegister.registerIcon("metallurgybees:beehive" + MetallurgyBees.firstUpperCase(beeType.name) + "Side");
			beeType.iconBeehiveTop = iconRegister.registerIcon("metallurgybees:beehive" + MetallurgyBees.firstUpperCase(beeType.name) + "Top");
		}
	}
	
	@Override
	@SideOnly(Side.CLIENT)
	public void getSubBlocks(int id, CreativeTabs creativeTab, List list) {
		for(int i = 0; i < MetallurgyBeeTypes.values().length; i++) {
			list.add(new ItemStack(id, 1, i));
		}
	}
}
