package moze_intel.projecte.gameObjs.items.armor;

import cpw.mods.fml.common.Optional;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.ItemStack;
import thaumcraft.api.IGoggles;
import thaumcraft.api.nodes.IRevealer;

@Optional.InterfaceList(value = {@Optional.Interface(iface = "thaumcraft.api.nodes.IRevealer", modid = "Thaumcraft"), @Optional.Interface(iface = "thaumcraft.api.IGoggles", modid = "Thaumcraft")})
public class RMArmor extends AbstractPEArmor implements IRevealer, IGoggles
{
	public RMArmor(EnumArmorType armorType)
	{
		super(armorType, "pe_rm_armor_" + armorType.ordinal(), "rm_armor", "redmatter_", 500, 10, 250, 350);
	}

	@Override
	@Optional.Method(modid = "Thaumcraft")
	public boolean showIngamePopups(ItemStack itemstack, EntityLivingBase player) 
	{
		return ((RMArmor) itemstack.getItem()).armorPiece == EnumArmorType.HEAD;
	}

	@Override
	@Optional.Method(modid = "Thaumcraft")
	public boolean showNodes(ItemStack itemstack, EntityLivingBase player) 
	{
		return ((RMArmor) itemstack.getItem()).armorPiece == EnumArmorType.HEAD;
	}
}
