package elcon.mods.metallurgybees.items;

import java.util.List;

import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Icon;
import net.minecraft.util.StatCollector;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import elcon.mods.metallurgybees.MetallurgyBeeTypes;
import elcon.mods.metallurgybees.MetallurgyBees;
import forestry.core.config.ForestryItem;

public class ItemHoneyComb extends Item {
	
	public ItemHoneyComb(int id) {
		super(id - 256);
		setMaxDamage(0);
		setHasSubtypes(true);
		setCreativeTab(MetallurgyBees.creativeTab);
	}
	
	@Override
	public String getItemDisplayName(ItemStack stack) {
		return StatCollector.translateToLocal("metallurgy.metals." + MetallurgyBeeTypes.values()[stack.getItemDamage()].name) + " " + StatCollector.translateToLocal(getUnlocalizedName());
	}
	
	@Override
	public String getUnlocalizedName() {
		return "item.metallurgyHoneyComb.name";
	}
	
	@Override
	@SideOnly(Side.CLIENT)
	public Icon getIconFromDamageForRenderPass(int meta, int renderPass) {
		return ForestryItem.beeComb.item().getIconFromDamageForRenderPass(0, renderPass);
	}
	
	@Override
	@SideOnly(Side.CLIENT)
	public boolean requiresMultipleRenderPasses() {
		return true;
	}
	
	@Override
	public int getRenderPasses(int meta) {
		return 2;
	}
	
	@Override
	@SideOnly(Side.CLIENT)
	public int getColorFromItemStack(ItemStack stack, int renderPass) {
		if(renderPass > 0) {
			return MetallurgyBeeTypes.values()[stack.getItemDamage()].colorCombSecondary;
		}
		return MetallurgyBeeTypes.values()[stack.getItemDamage()].colorCombPrimary;
	}
	
	@Override
	@SideOnly(Side.CLIENT)
	public void registerIcons(IconRegister iconRegister) {
	}
	
	@Override
	@SideOnly(Side.CLIENT)
	public void getSubItems(int id, CreativeTabs creativeTab, List list) {
		for(int i = 0; i < MetallurgyBeeTypes.values().length; i++) {
			list.add(new ItemStack(id, 1, i));
		}
	}
}
