package moze_intel.projecte.gameObjs.blocks;

import moze_intel.projecte.gameObjs.ObjHandler;
import moze_intel.projecte.gameObjs.entity.EntityNovaCataclysmPrimed;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.item.EntityTNTPrimed;
import net.minecraft.world.World;

public class NovaCataclysm extends NovaCatalyst
{
	public NovaCataclysm()
	{
		this.setBlockName("pe_nova_cataclysm");
		this.setCreativeTab(ObjHandler.cTab);
	}

	@Override
	protected EntityTNTPrimed createPrimedEntity(World world, double x, double y, double z, EntityLivingBase placer)
	{
		return new EntityNovaCataclysmPrimed(world, x, y, z, placer);
	}

	@Override
	protected String getSideTexture()
	{
		return "projecte:explosives/nova1_side";
	}
}
