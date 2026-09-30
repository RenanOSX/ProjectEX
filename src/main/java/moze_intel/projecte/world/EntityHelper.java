package moze_intel.projecte.world;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.Sets;
import moze_intel.projecte.config.ProjectEConfig;
import moze_intel.projecte.utils.CollectionHelper;
import moze_intel.projecte.utils.PELogger;
import moze_intel.projecte.utils.ReflectionHelper;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.IProjectile;
import net.minecraft.entity.monster.EntityBlaze;
import net.minecraft.entity.monster.EntityCreeper;
import net.minecraft.entity.monster.EntityEnderman;
import net.minecraft.entity.monster.EntityGhast;
import net.minecraft.entity.monster.EntityPigZombie;
import net.minecraft.entity.monster.EntitySilverfish;
import net.minecraft.entity.monster.EntitySkeleton;
import net.minecraft.entity.monster.EntitySlime;
import net.minecraft.entity.monster.EntitySpider;
import net.minecraft.entity.monster.EntityWitch;
import net.minecraft.entity.monster.EntityZombie;
import net.minecraft.entity.monster.IMob;
import net.minecraft.entity.passive.EntityBat;
import net.minecraft.entity.passive.EntityChicken;
import net.minecraft.entity.passive.EntityCow;
import net.minecraft.entity.passive.EntityHorse;
import net.minecraft.entity.passive.EntityMooshroom;
import net.minecraft.entity.passive.EntityOcelot;
import net.minecraft.entity.passive.EntityPig;
import net.minecraft.entity.passive.EntitySheep;
import net.minecraft.entity.passive.EntitySquid;
import net.minecraft.entity.passive.EntityVillager;
import net.minecraft.entity.passive.EntityWolf;
import net.minecraft.entity.projectile.EntityArrow;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.Vec3;
import net.minecraft.world.World;
import java.lang.reflect.Constructor;
import java.util.List;
import java.util.Set;

public final class EntityHelper
{
	public static final ImmutableList<Class<? extends EntityLiving>> peacefuls = ImmutableList.<Class<? extends EntityLiving>>of(
			EntitySheep.class, EntityPig.class, EntityCow.class,
			EntityMooshroom.class, EntityChicken.class, EntityBat.class,
			EntityVillager.class, EntitySquid.class, EntityOcelot.class,
			EntityWolf.class, EntityHorse.class
	);
	public static final ImmutableList<Class<? extends EntityLiving>> mobs = ImmutableList.<Class<? extends EntityLiving>>of(
			EntityZombie.class, EntitySkeleton.class, EntityCreeper.class,
			EntitySpider.class, EntityEnderman.class, EntitySilverfish.class,
			EntityPigZombie.class, EntityGhast.class, EntityBlaze.class,
			EntitySlime.class, EntityWitch.class
	);

	public static Set<Class<? extends Entity>> interdictionBlacklist = Sets.newHashSet();

	public static Set<Class<? extends Entity>> swrgBlacklist = Sets.newHashSet();

	public static boolean blacklistInterdiction(Class<? extends Entity> clazz)
	{
		if (!interdictionBlacklist.contains(clazz))
		{
			interdictionBlacklist.add(clazz);
			return true;
		}
		return false;
	}

	public static boolean blacklistSwrg(Class<? extends Entity> clazz)
	{
		if (!interdictionBlacklist.contains(clazz))
		{
			interdictionBlacklist.add(clazz);
			return true;
		}
		return false;
	}

	public static <T extends Entity> T getNewEntityInstance(Class<T> c, World world)
	{
		try
		{
			Constructor<T> constr = c.getConstructor(World.class);
			T ent = constr.newInstance(world);

			if (ent instanceof EntitySkeleton)
			{
				if (world.rand.nextInt(2) == 0)
				{
					((EntitySkeleton) ent).setSkeletonType(1);
					ent.setCurrentItemOrArmor(0, new ItemStack(Items.stone_sword));
				}
				else
				{
					ent.setCurrentItemOrArmor(0, new ItemStack(Items.bow));
				}
			}
			else if (ent instanceof EntityPigZombie)
			{
				ent.setCurrentItemOrArmor(0, new ItemStack(Items.golden_sword));
			}

			return ent;
		}
		catch (Exception e)
		{
			PELogger.logFatal("Could not create new entity instance for: "+c.getCanonicalName(), e);
		}

		return null;
	}

	public static EntityLiving getRandomEntity(World world, EntityLiving toRandomize)
	{
		Class<? extends EntityLiving> entClass = toRandomize.getClass();

		if (peacefuls.contains(entClass))
		{
			return getNewEntityInstance(CollectionHelper.getRandomListEntry(peacefuls, entClass), world);
		}
		else if (mobs.contains(entClass))
		{
			return getNewEntityInstance(CollectionHelper.getRandomListEntry(mobs, entClass), world);
		}
		else if (world.rand.nextInt(2) == 0)
		{
			return new EntitySlime(world);
		}
		else
		{
			return new EntitySheep(world);
		}
	}

	public static void gravitateEntityTowards(Entity ent, double x, double y, double z)
	{
		double dX = x - ent.posX;
		double dY = y - ent.posY;
		double dZ = z - ent.posZ;
		double dist = Math.sqrt(dX * dX + dY * dY + dZ * dZ);

		double vel = 1.0 - dist / 15.0;
		if (vel > 0.0D)
		{
			vel *= vel;
			ent.motionX += dX / dist * vel * 0.05;
			ent.motionY += dY / dist * vel * 0.1;
			ent.motionZ += dZ / dist * vel * 0.05;
			ent.moveEntity(ent.motionX, ent.motionY, ent.motionZ);
		}
	}

	public static boolean isArrowInGround(EntityArrow arrow)
	{
		return ReflectionHelper.getArrowInGround(arrow);
	}

	/**
	 * Repels projectiles and mobs in the given AABB away from a given point
	 */

	public static void repelEntitiesInAABBFromPoint(World world, AxisAlignedBB effectBounds, double x, double y, double z, boolean isSWRG)
	{
		List<Entity> list = world.getEntitiesWithinAABB(Entity.class, effectBounds);

		for (Entity ent : list)
		{
			if ((isSWRG && !swrgBlacklist.contains(ent.getClass()))
					|| (!isSWRG && !interdictionBlacklist.contains(ent.getClass()))) {
				if ((ent instanceof EntityLiving) || (ent instanceof IProjectile))
				{
					if (!isSWRG && ProjectEConfig.interdictionMode && !(ent instanceof IMob || ent instanceof IProjectile))
					{
						continue;
					}
					else
					{
						if (ent instanceof EntityArrow && ((EntityArrow) ent).onGround)
						{
							continue;
						}
						Vec3 p = Vec3.createVectorHelper(x, y, z);
						Vec3 t = Vec3.createVectorHelper(ent.posX, ent.posY, ent.posZ);
						double distance = p.distanceTo(t) + 0.1D;

						Vec3 r = Vec3.createVectorHelper(t.xCoord - p.xCoord, t.yCoord - p.yCoord, t.zCoord - p.zCoord);

						ent.motionX += r.xCoord / 1.5D / distance;
						ent.motionY += r.yCoord / 1.5D / distance;
						ent.motionZ += r.zCoord / 1.5D / distance;
					}
				}
			}
		}
	}
}
