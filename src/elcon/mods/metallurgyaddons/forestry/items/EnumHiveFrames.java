package elcon.mods.metallurgyaddons.forestry.items;

import elcon.mods.metallurgyaddons.forestry.MetallurgyBeeTypes;
import forestry.api.apiculture.IBeeGenome;

public enum EnumHiveFrames {
	HEATED(360, MetallurgyBeeTypes.IGNATIUS),
	REINFORCED(540, MetallurgyBeeTypes.SHADOW_IRON), 
	FORTIFIED(1080, MetallurgyBeeTypes.SHADOW_STEEL), 
	MIDAS(360, MetallurgyBeeTypes.MIDASIUM),
	MUTATING(360, MetallurgyBeeTypes.VYROXERES), 
	COOLING(360, MetallurgyBeeTypes.CERUCLASE), 
	FERTILE(360, MetallurgyBeeTypes.INOLASHITE), 
	ANCIENT(360, MetallurgyBeeTypes.KALENDRITE), 
	IMMORTAL(360, MetallurgyBeeTypes.AMORDRINE), 
	MOLTEN(360, MetallurgyBeeTypes.VULCANITE),
	SANGUINE(360, MetallurgyBeeTypes.SANGUINITE);
	
	int maxDamage;
	MetallurgyBeeTypes type;

	float productionModifer = 1;
	float floweringModifer = 1;
	float lifespanModifer = 1;
	float mutationModifier = 1;
	float territoryModifer = 1;

	private EnumHiveFrames(int maxDamage, MetallurgyBeeTypes type) {
		this.maxDamage = maxDamage;
		this.type = type;
	}

	public static void init() {
		HEATED.setProductionModifier(1.5f);
		//
		REINFORCED.setProductionModifier(1.5f);
		FORTIFIED.setProductionModifier(1.5f);
		// MIDAS.setProductionModifier(1.5f);
		MUTATING.setLifespanModifier(1.5f);
		MUTATING.setProductionModifier(0f);
		MUTATING.setMutationModifier(2f);
		COOLING.setProductionModifier(1.5f);
		//
		//
		ANCIENT.setProductionModifier(1.75f);
		ANCIENT.setLifespanModifier(1.5f);
		IMMORTAL.setProductionModifier(1.75f);
		IMMORTAL.setLifespanModifier(2f);
		MOLTEN.setProductionModifier(3f);
		MOLTEN.setLifespanModifier(0.25f);
		SANGUINE.setProductionModifier(1.25f);
		SANGUINE.setLifespanModifier(3f);
		SANGUINE.setMutationModifier(0f);
	}

	public void setFloweringModifier(float modifer) {
		this.floweringModifer = modifer;
	}

	public void setLifespanModifier(float modifer) {
		this.lifespanModifer = modifer;
	}

	public void setMutationModifier(float modifer) {
		this.mutationModifier = modifer;
	}

	public void setProductionModifier(float modifer) {
		this.productionModifer = modifer;
	}

	public void setTerritoryModifier(float modifer) {
		this.territoryModifer = modifer;
	}

}
