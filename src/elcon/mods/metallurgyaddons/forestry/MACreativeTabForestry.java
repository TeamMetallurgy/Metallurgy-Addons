package elcon.mods.metallurgyaddons.forestry;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.ItemStack;

public class MACreativeTabForestry extends CreativeTabs {

	public MACreativeTabForestry(String label) {
		super(label);
	}
	
	@Override
	public ItemStack getIconItemStack() {
		return new ItemStack(MetallurgyAddonForestry.honeyComb.itemID, 1, 0);
	}
}
