package elcon.mods.metallurgyaddons.core.blocks;

import java.util.ArrayList;

import net.minecraft.block.Block;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.client.particle.EffectRenderer;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.Icon;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.util.StatCollector;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import elcon.mods.metallurgyaddons.core.tileentities.TileEntityMetadata;
import elcon.mods.metallurgyaddons.core.util.MAUtilClient;

public class BlockExtendedMetadata extends BlockContainer {

	public BlockExtendedMetadata(int id, Material material) {
		super(id, material);
	}

	public String getLocalizedName(ItemStack stack) {
		return StatCollector.translateToLocal(getUnlocalizedName(stack));
	}

	public String getUnlocalizedName(ItemStack stack) {
		return getUnlocalizedName();
	}

	@Override
	public TileEntity createNewTileEntity(World world) {
		return new TileEntityMetadata();
	}

	public int getPlacedMetadata(EntityPlayer player, ItemStack stack, World world, int x, int y, int z, int side, float xx, float yy, float zz) {
		return stack.getItemDamage();
	}

	public int getDroppedMetadata(World world, int x, int y, int z, int meta, int fortune) {
		return meta;
	}
	
	@Override
	public float getPlayerRelativeBlockHardness(EntityPlayer player, World world, int x, int y, int z) {
		if(player.getCurrentEquippedItem() == null) {
			return super.getPlayerRelativeBlockHardness(player, world, x, y, z);
		}
		return player.getCurrentEquippedItem().getItem().getStrVsBlock(player.getCurrentEquippedItem(), this, getMetadata(world, x, y, z)) / getBlockHardness(world, x, y, z) / 30F;
	}

	@Override
	public boolean removeBlockByPlayer(World world, EntityPlayer player, int x, int y, int z) {
		return breakBlock(this, player, world, x, y, z);
	}

	@Override
	public void dropBlockAsItemWithChance(World world, int x, int y, int z, int metadata, float chance, int fortune) {
		TileEntityMetadata tile = (TileEntityMetadata) world.getBlockTileEntity(x, y, z);
		if(tile != null && !tile.droppedBlock) {
			super.dropBlockAsItemWithChance(world, x, y, z, tile.getTileMetadata(), chance, fortune);
		}
	}

	@Override
	public ArrayList<ItemStack> getBlockDropped(World world, int x, int y, int z, int metadata, int fortune) {
		ArrayList<ItemStack> ret = new ArrayList<ItemStack>();
		int count = quantityDropped(metadata, fortune, world.rand);
		for(int i = 0; i < count; i++) {
			int id = idDropped(metadata, world.rand, fortune);
			if(id > 0) {
				ret.add(new ItemStack(id, 1, getDroppedMetadata(world, x, y, z, metadata, fortune)));
			}
		}
		return ret;
	}

	public boolean breakBlock(BlockExtendedMetadata block, EntityPlayer player, World world, int x, int y, int z) {
		ArrayList<ItemStack> drops = new ArrayList<ItemStack>();
		Block block2 = (Block) block;
		TileEntityMetadata tile = (TileEntityMetadata) world.getBlockTileEntity(x, y, z);
		if(tile != null && !tile.droppedBlock) {
			drops = block2.getBlockDropped(world, x, y, z, getMetadata(world, x, y, z), EnchantmentHelper.getFortuneModifier(player));
		}
		boolean hasBeenBroken = world.setBlockToAir(x, y, z);
		if(hasBeenBroken && !world.isRemote && drops.size() > 0 && (player == null || !player.capabilities.isCreativeMode) && shouldDropItems(world, x, y, z, tile.getTileMetadata(), player, player.getCurrentEquippedItem())) {
			for(ItemStack drop : drops) {
				block.dropAsStack(world, x, y, z, drop);
			}
			tile.droppedBlock = true;
		}
		return hasBeenBroken;
	}

	public boolean shouldDropItems(World world, int x, int y, int z, int meta, EntityPlayer player, ItemStack stack) {
		return true;
	}

	@Override
	public int getDamageValue(World world, int x, int y, int z) {
		return getDroppedMetadata(world, x, y, z, getMetadata(world, x, y, z), 0);
	}

	public int getMetadata(IBlockAccess blockAccess, int x, int y, int z) {
		if(Block.blocksList[blockAccess.getBlockId(x, y, z)] instanceof BlockExtendedMetadata) {
			return ((TileEntityMetadata) blockAccess.getBlockTileEntity(x, y, z)).getTileMetadata();
		}
		return blockAccess.getBlockMetadata(x, y, z);
	}

	public void setMetadata(World world, int x, int y, int z, int meta) {
		if(Block.blocksList[world.getBlockId(x, y, z)] instanceof BlockExtendedMetadata) {
			((TileEntityMetadata) world.getBlockTileEntity(x, y, z)).setTileMetadata(meta);
		}
	}

	public void dropAsStack(World world, int x, int y, int z, ItemStack stack) {
		dropBlockAsItem_do(world, x, y, z, stack);
	}

	@Override
	@SideOnly(Side.CLIENT)
	public Icon getBlockTexture(IBlockAccess blockAccess, int x, int y, int z, int side) {
		return getIcon(side, getMetadata(blockAccess, x, y, z));
	}

	@Override
	@SideOnly(Side.CLIENT)
	public boolean addBlockDestroyEffects(World world, int x, int y, int z, int meta, EffectRenderer effectRenderer) {
		return MAUtilClient.addBlockDestroyEffects(world, x, y, z, meta, effectRenderer, this, getMetadata(world, x, y, z));
	}

	@Override
	@SideOnly(Side.CLIENT)
	public boolean addBlockHitEffects(World world, MovingObjectPosition target, EffectRenderer effectRenderer) {
		int x = target.blockX;
		int y = target.blockY;
		int z = target.blockZ;
		return MAUtilClient.addBlockHitEffects(world, target, effectRenderer, getMetadata(world, x, y, z));
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void registerIcons(IconRegister iconRegister) {
	}
}
