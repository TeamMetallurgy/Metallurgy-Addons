package elcon.mods.metallurgyaddons.forestry;

public enum MetallurgyFrameTypes {

	HEATED(MetallurgyBeeTypes.IGNATIUS, 360), 
	REINFORCED(MetallurgyBeeTypes.SHADOW_IRON, 540), 
	FORTIFIED(MetallurgyBeeTypes.SHADOW_STEEL, 1080), 
	MIDAS(MetallurgyBeeTypes.MIDASIUM, 360), 
	MUTATING(MetallurgyBeeTypes.VYROXERES, 360), 
	COOLING(MetallurgyBeeTypes.CERUCLASE, 360), 
	FERTILE(MetallurgyBeeTypes.INOLASHITE, 360), 
	ANCIENT(MetallurgyBeeTypes.KALENDRITE, 360), 
	IMMORTAL(MetallurgyBeeTypes.AMORDRINE, 360), 
	MOLTEN(MetallurgyBeeTypes.VULCANITE, 360), 
	SANGUINE(MetallurgyBeeTypes.SANGUINITE, 360);

	public int maxDamage;
	public MetallurgyBeeTypes type;

	public float productionModifer = 1;
	public float floweringModifer = 1;
	public float lifespanModifer = 1;
	public float mutationModifier = 1;
	public float territoryModifer = 1;

	MetallurgyFrameTypes(MetallurgyBeeTypes type, int maxDamage) {
		this.type = type;
		this.maxDamage = maxDamage;
	}

	public static void init() {
		HEATED.setProductionModifier(1.5F);
		//
		REINFORCED.setProductionModifier(1.5F);
		FORTIFIED.setProductionModifier(1.5F);
		//MIDAS.setProductionModifier(1.5F);
		MUTATING.setProductionModifier(0.0F).setLifespanModifier(1.5F).setMutationModifier(2.0F);
		COOLING.setProductionModifier(1.5F);
		//
		//
		ANCIENT.setProductionModifier(1.75F).setLifespanModifier(1.5F);
		IMMORTAL.setProductionModifier(1.75F).setLifespanModifier(2.0F);
		MOLTEN.setProductionModifier(3.0F).setLifespanModifier(0.25F);
		SANGUINE.setProductionModifier(1.25F).setLifespanModifier(3.0F).setMutationModifier(0.0F);
	}

	public MetallurgyFrameTypes setFloweringModifier(float modifer) {
		this.floweringModifer = modifer;
		return this;
	}

	public MetallurgyFrameTypes setLifespanModifier(float modifer) {
		this.lifespanModifer = modifer;
		return this;
	}

	public MetallurgyFrameTypes setMutationModifier(float modifer) {
		this.mutationModifier = modifer;
		return this;
	}

	public MetallurgyFrameTypes setProductionModifier(float modifer) {
		this.productionModifer = modifer;
		return this;
	}

	public MetallurgyFrameTypes setTerritoryModifier(float modifer) {
		this.territoryModifer = modifer;
		return this;
	}
}
