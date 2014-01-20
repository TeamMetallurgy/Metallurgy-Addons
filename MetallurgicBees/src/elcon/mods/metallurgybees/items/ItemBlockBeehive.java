package elcon.mods.metallurgybees.items;

import net.minecraft.block.Block;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.Icon;
import net.minecraft.world.World;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import elcon.mods.metallurgybees.blocks.BlockBeehive;
import elcon.mods.metallurgybees.tileentities.TileEntityBeehive;

public class ItemBlockBeehive extends ItemBlock {

	public ItemBlockBeehive(int id) {
		super(id);
		setMaxDamage(0);
		setHasSubtypes(true);
	}
	
	@Override
	public String getItemDisplayName(ItemStack stack) {
		return getItemStackDisplayName(stack);
	}
	
	@Override
	public String getItemStackDisplayName(ItemStack stack) {
		return ((BlockBeehive) Block.blocksList[getBlockID()]).getLocalizedName(stack);
	}

	@Override
	public String getUnlocalizedName(ItemStack stack) {
		return ((BlockBeehive) Block.blocksList[getBlockID()]).getUnlocalizedName(stack);
	}
	
	@Override
	public String getUnlocalizedName() {
		return Block.blocksList[getBlockID()].getUnlocalizedName();
	}
	
	@Override
	@SideOnly(Side.CLIENT)
	public int getColorFromItemStack(ItemStack stack, int renderPass) {
		return Block.blocksList[getBlockID()].getRenderColor(stack.getItemDamage());
	}
	
	@Override
	public int getMetadata(int meta) {
		return meta;
	}
	
	@Override
	@SideOnly(Side.CLIENT)
	public Icon getIconFromDamage(int meta) {
		return Block.blocksList[getBlockID()].getIcon(2, meta);
	}
	
	@Override
	public boolean placeBlockAt(ItemStack stack, EntityPlayer player, World world, int x, int y, int z, int side, float hitX, float hitY, float hitZ, int meta) {
		Block block = Block.blocksList[getBlockID()];
		if(!(block instanceof BlockBeehive)) {
			return false;
		}
		if(!world.setBlock(x, y, z, getBlockID(), meta, 2)) {
			return false;
		}
		if(world.getBlockId(x, y, z) == getBlockID()) {
			TileEntity tile = world.getBlockTileEntity(x, y, z);
			if(tile != null) {
				if(!(tile instanceof TileEntityBeehive)) {
					tile = new TileEntityBeehive();
					world.setBlockTileEntity(x, y, z, tile);
				}
				System.out.println(meta);
				((TileEntityBeehive) tile).metalType = (byte) meta;
			}
			block.onBlockPlacedBy(world, x, y, z, player, stack);
			block.onPostBlockPlaced(world, x, y, z, meta);
		}
		return true;
	}
}
