package moze_intel.projecte.gameObjs.items.armor;

public class DMArmor extends AbstractPEArmor
{
	public DMArmor(EnumArmorType armorPiece)
	{
		super(armorPiece, "pe_dm_armor_" + armorPiece.ordinal(), "dm_armor", "darkmatter_", 350, 5, 100, 150);
	}
}
