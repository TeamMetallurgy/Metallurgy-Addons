package elcon.mods.metallurgyaddons.forestry.items;

import java.util.List;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Icon;
import net.minecraft.util.StatCollector;
import elcon.mods.metallurgyaddons.core.util.MAUtil;
import elcon.mods.metallurgyaddons.forestry.MetallurgyAddonForestry;
import elcon.mods.metallurgyaddons.forestry.MetallurgyBeeTypes;
import forestry.api.apiculture.IBee;
import forestry.api.apiculture.IBeeGenome;
import forestry.api.apiculture.IBeeHousing;
import forestry.api.apiculture.IHiveFrame;
import forestry.core.config.ForestryItem;

public class ItemFrames extends Item implements IHiveFrame {

	String name;

	public ItemFrames(int par1) {
		super(par1);
		this.setMaxStackSize(1);
		this.setHasSubtypes(true);
		this.setCreativeTab(MetallurgyAddonForestry.creativeTab);
	}

	@Override
	public String getUnlocalizedName() {
		return "metallurgybees.frame.name";
	}

	@Override
	public String getItemDisplayName(ItemStack stack) {
		this.name = EnumHiveFrames.values()[stack.getItemDamage()].name();
		return StatCollector.translateToLocal("metallurgy.frames." + this.name.toLowerCase()) + " " + StatCollector.translateToLocal(getUnlocalizedName());
	}

	@Override
	public float getFloweringModifier(IBeeGenome arg0, float arg1) {
		EnumHiveFrames frame = EnumHiveFrames.valueOf(this.name);
		return frame.floweringModifer;
	}

	@Override
	public float getGeneticDecay(IBeeGenome arg0, float arg1) {
		return 1.0f;
	}

	@Override
	public float getLifespanModifier(IBeeGenome arg0, IBeeGenome arg1, float arg2) {
		EnumHiveFrames frame = EnumHiveFrames.valueOf(this.name);
		return frame.lifespanModifer;
	}

	@Override
	public float getMutationModifier(IBeeGenome arg0, IBeeGenome arg1, float arg2) {
		EnumHiveFrames frame = EnumHiveFrames.valueOf(this.name);
		return frame.mutationModifier;
	}

	@Override
	public float getProductionModifier(IBeeGenome arg0, float arg1) {
		EnumHiveFrames frame = EnumHiveFrames.valueOf(this.name);
		return frame.productionModifer;
	}

	@Override
	public float getTerritoryModifier(IBeeGenome arg0, float arg1) {
		EnumHiveFrames frame = EnumHiveFrames.valueOf(this.name);
		return frame.territoryModifer;
	}

	@Override
	public boolean isHellish() {
		return false;
	}

	@Override
	public boolean isSealed() {
		return false;
	}

	@Override
	public boolean isSelfLighted() {
		return false;
	}

	@Override
	public boolean isSunlightSimulated() {
		return false;
	}

	@Override
	public ItemStack frameUsed(IBeeHousing housing, ItemStack frame, IBee queen, int wear) {
		if(getMaxDamage() == 0) {
			setMaxDamage(EnumHiveFrames.values()[frame.getItemDamage()].maxDamage);
		}
		frame.setItemDamage(frame.getItemDamage() + wear);
		if(frame.getItemDamage() >= frame.getMaxDamage()) {
			return null;
		}
		return frame;
	}
	
	@Override
	@SideOnly(Side.CLIENT)
	public void registerIcons(IconRegister iconRegister) {
		for(int i = 0; i < EnumHiveFrames.values().length; i++) {
			EnumHiveFrames hiveFrames = EnumHiveFrames.values()[i];
			this.itemIcon = iconRegister.registerIcon("forestry:frameImpregnated");
		}
	}
	
	@Override
	@SideOnly(Side.CLIENT)
	public void getSubItems(int id, CreativeTabs creativeTab, List list) {
		for(int i = 0; i < EnumHiveFrames.values().length; i++) {
			list.add(new ItemStack(id, 1, i));
		}
	}

}
