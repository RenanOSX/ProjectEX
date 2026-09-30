package moze_intel.projecte.utils;

import moze_intel.projecte.model.Coordinates;
import moze_intel.projecte.world.AoeHelper;
import moze_intel.projecte.world.EntityHelper;
import moze_intel.projecte.world.ExplosionHelper;
import moze_intel.projecte.world.HarvestHelper;
import net.minecraft.block.Block;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.projectile.EntityArrow;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.world.World;
import net.minecraftforge.common.util.ForgeDirection;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Helper class for anything that touches a World.
 * Notice: Please try to keep methods tidy and alphabetically ordered. Thanks!
 */
public final class WorldHelper
{
	public static boolean blacklistInterdiction(Class<? extends Entity> clazz)
	{
		return EntityHelper.blacklistInterdiction(clazz);
	}

	public static boolean blacklistSwrg(Class<? extends Entity> clazz)
	{
		return EntityHelper.blacklistSwrg(clazz);
	}

	public static void createLootDrop(List<ItemStack> drops, World world, double x, double y, double z)
	{
		ExplosionHelper.createLootDrop(drops, world, x, y, z);
	}

	public static void createNovaExplosion(World world, Entity exploder, double x, double y, double z, float power)
	{
		ExplosionHelper.createNovaExplosion(world, exploder, x, y, z, power);
	}

	public static void extinguishNearby(World world, EntityPlayer player)
	{
		AoeHelper.extinguishNearby(world, player);
	}

	public static void freezeInBoundingBox(World world, AxisAlignedBB box, EntityPlayer player, boolean random)
	{
		AoeHelper.freezeInBoundingBox(world, box, player, random);
	}

	public static List<TileEntity> getAdjacentTileEntities(World world, TileEntity tile)
	{
		return AoeHelper.getAdjacentTileEntities(world, tile);
	}

	public static Map<ForgeDirection, TileEntity> getAdjacentTileEntitiesMapped(final World world, final TileEntity tile)
	{
		return AoeHelper.getAdjacentTileEntitiesMapped(world, tile);
	}

	public static ArrayList<ItemStack> getBlockDrops(World world, EntityPlayer player, Block block, ItemStack stack, int x, int y, int z)
	{
		return HarvestHelper.getBlockDrops(world, player, block, stack, x, y, z);
	}

	public static AxisAlignedBB getBroadDeepBox(Coordinates coords, ForgeDirection direction, int offset)
	{
		return AoeHelper.getBroadDeepBox(coords, direction, offset);
	}

	public static AxisAlignedBB getDeepBox(Coordinates coords, ForgeDirection direction, int depth)
	{
		return AoeHelper.getDeepBox(coords, direction, depth);
	}

	public static AxisAlignedBB getFlatYBox(Coordinates coords, int offset)
	{
		return AoeHelper.getFlatYBox(coords, offset);
	}

	public static <T extends Entity> T getNewEntityInstance(Class<T> c, World world)
	{
		return EntityHelper.getNewEntityInstance(c, world);
	}

	public static EntityLiving getRandomEntity(World world, EntityLiving toRandomize)
	{
		return EntityHelper.getRandomEntity(world, toRandomize);
	}

	public static List<TileEntity> getTileEntitiesWithinAABB(World world, AxisAlignedBB bBox)
	{
		return AoeHelper.getTileEntitiesWithinAABB(world, bBox);
	}

	public static void gravitateEntityTowards(Entity ent, double x, double y, double z)
	{
		EntityHelper.gravitateEntityTowards(ent, x, y, z);
	}

	public static void growNearbyRandomly(boolean harvest, World world, double xCoord, double yCoord, double zCoord, EntityPlayer player)
	{
		HarvestHelper.growNearbyRandomly(harvest, world, xCoord, yCoord, zCoord, player);
	}

	public static void harvestVein(World world, EntityPlayer player, ItemStack stack, Coordinates coords, Block target, List<ItemStack> currentDrops, int numMined)
	{
		HarvestHelper.harvestVein(world, player, stack, coords, target, currentDrops, numMined);
	}

	public static void igniteNearby(World world, EntityPlayer player)
	{
		AoeHelper.igniteNearby(world, player);
	}

	public static boolean isArrowInGround(EntityArrow arrow)
	{
		return EntityHelper.isArrowInGround(arrow);
	}

	public static void repelEntitiesInAABBFromPoint(World world, AxisAlignedBB effectBounds, double x, double y, double z, boolean isSWRG)
	{
		EntityHelper.repelEntitiesInAABBFromPoint(world, effectBounds, x, y, z, isSWRG);
	}

	public static void spawnEntityItem(World world, ItemStack stack, double x, double y, double z)
	{
		ExplosionHelper.spawnEntityItem(world, stack, x, y, z);
	}
}
