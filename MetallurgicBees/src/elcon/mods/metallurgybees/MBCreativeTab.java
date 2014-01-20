package elcon.mods.metallurgybees;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.ItemStack;

public class MBCreativeTab extends CreativeTabs {

	public MBCreativeTab(String label) {
		super(label);
	}
	
	@Override
	public ItemStack getIconItemStack() {
		return new ItemStack(MetallurgyBees.honeyComb.itemID, 1, 0);
	}
}
