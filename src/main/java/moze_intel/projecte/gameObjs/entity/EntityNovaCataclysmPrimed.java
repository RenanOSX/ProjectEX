package moze_intel.projecte.gameObjs.entity;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.world.World;

public class EntityNovaCataclysmPrimed extends EntityNovaCatalystPrimed
{
	public EntityNovaCataclysmPrimed(World world)
	{
		super(world);
	}

	public EntityNovaCataclysmPrimed(World world, double x, double y, double z, EntityLivingBase placer)
	{
		super(world, x, y, z, placer);
	}

	@Override
	protected float getBlastRadius()
	{
		return 48.0F;
	}
}
