package moze_intel.projecte.world;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.Lists;
import moze_intel.projecte.model.Coordinates;
import moze_intel.projecte.utils.PlayerHelper;
import net.minecraft.block.Block;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.init.Blocks;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.world.World;
import net.minecraftforge.common.util.ForgeDirection;
import java.util.EnumMap;
import java.util.List;
import java.util.Map;

public final class AoeHelper
{
	public static void extinguishNearby(World world, EntityPlayer player)
	{
		for (int x = (int) (player.posX - 1); x <= player.posX + 1; x++)
			for (int y = (int) (player.posY - 1); y <= player.posY + 1; y++)
				for (int z = (int) (player.posZ - 1); z <= player.posZ + 1; z++)
					if (world.getBlock(x, y, z) == Blocks.fire && PlayerHelper.hasBreakPermission(((EntityPlayerMP) player), x, y, z))
					{
						world.setBlockToAir(x, y, z);
					}
	}

	public static void freezeInBoundingBox(World world, AxisAlignedBB box, EntityPlayer player, boolean random)
	{
		for (int x = (int) box.minX; x <= box.maxX; x++)
		{
			for (int y = (int) box.minY; y <= box.maxY; y++)
			{
				for (int z = (int) box.minZ; z <= box.maxZ; z++)
				{
					Block b = world.getBlock(x, y, z);

					if ((b == Blocks.water || b == Blocks.flowing_water) && (!random || world.rand.nextInt(128) == 0))
					{
						if (player != null)
						{
							PlayerHelper.checkedReplaceBlock(((EntityPlayerMP) player), x, y, z, Blocks.ice, 0);
						}
						else
						{
							world.setBlock(x, y, z, Blocks.ice);
						}
					}
					else if (b.isSideSolid(world, x, y, z, ForgeDirection.UP))
					{
						Block b2 = world.getBlock(x, y + 1, z);

						if (b2 == Blocks.air && (!random || world.rand.nextInt(128) == 0))
						{
							if (player != null)
							{
								PlayerHelper.checkedReplaceBlock(((EntityPlayerMP) player), x, y + 1, z, Blocks.snow_layer, 0);
							}
							else
							{
								world.setBlock(x, y + 1, z, Blocks.snow_layer);
							}
						}
					}
				}
			}
		}
	}

	public static List<TileEntity> getAdjacentTileEntities(World world, TileEntity tile)
	{
		return ImmutableList.copyOf(getAdjacentTileEntitiesMapped(world, tile).values());
	}

	public static Map<ForgeDirection, TileEntity> getAdjacentTileEntitiesMapped(final World world, final TileEntity tile)
	{
		Map<ForgeDirection, TileEntity> ret = new EnumMap<>(ForgeDirection.class);

		for (ForgeDirection dir : ForgeDirection.VALID_DIRECTIONS) {
			TileEntity candidate = world.getTileEntity(tile.xCoord + dir.offsetX, tile.yCoord + dir.offsetY, tile.zCoord + dir.offsetZ);
			if (candidate != null) {
				ret.put(dir, candidate);
			}
		}

		return ret;
	}

	public static AxisAlignedBB getBroadDeepBox(Coordinates coords, ForgeDirection direction, int offset)
	{
		if (direction.offsetX > 0)
		{
			return AxisAlignedBB.getBoundingBox(coords.x - offset, coords.y - offset, coords.z - offset, coords.x, coords.y + offset, coords.z + offset);
		}
		else if (direction.offsetX < 0)
		{
			return AxisAlignedBB.getBoundingBox(coords.x, coords.y - offset, coords.z - offset, coords.x + offset, coords.y + offset, coords.z + offset);
		}
		else if (direction.offsetY > 0)
		{
			return AxisAlignedBB.getBoundingBox(coords.x - offset, coords.y - offset, coords.z - offset, coords.x + offset, coords.y, coords.z + offset);
		}
		else if (direction.offsetY < 0)
		{
			return AxisAlignedBB.getBoundingBox(coords.x - offset, coords.y, coords.z - offset, coords.x + offset, coords.y + offset, coords.z + offset);
		}
		else if (direction.offsetZ > 0)
		{
			return AxisAlignedBB.getBoundingBox(coords.x - offset, coords.y - offset, coords.z - offset, coords.x + offset, coords.y + offset, coords.z);
		}
		else if (direction.offsetZ < 0)
		{
			return AxisAlignedBB.getBoundingBox(coords.x - offset, coords.y - offset, coords.z, coords.x + offset, coords.y + offset, coords.z + offset);
		}
		return AxisAlignedBB.getBoundingBox(0, 0, 0, 0, 0, 0);
	}

	/**
	 * Returns in AABB that is always 3x3 orthogonal to the side hit, but varies in depth in the direction of the side hit
	 */

	public static AxisAlignedBB getDeepBox(Coordinates coords, ForgeDirection direction, int depth)
	{
		if (direction.offsetX != 0)
		{
			if (direction.offsetX > 0)
			{
				return AxisAlignedBB.getBoundingBox(coords.x - depth, coords.y - 1, coords.z - 1, coords.x, coords.y + 1, coords.z + 1);
			}
			else return AxisAlignedBB.getBoundingBox(coords.x, coords.y - 1, coords.z - 1, coords.x + depth, coords.y + 1, coords.z + 1);
		}
		else if (direction.offsetY != 0)
		{
			if (direction.offsetY > 0)
			{
				return AxisAlignedBB.getBoundingBox(coords.x - 1, coords.y - depth, coords.z - 1, coords.x + 1, coords.y, coords.z + 1);
			}
			else return AxisAlignedBB.getBoundingBox(coords.x - 1, coords.y, coords.z - 1, coords.x + 1, coords.y + depth, coords.z + 1);
		}
		else
		{
			if (direction.offsetZ > 0)
			{
				return AxisAlignedBB.getBoundingBox(coords.x - 1, coords.y - 1, coords.z - depth, coords.x + 1, coords.y + 1, coords.z);
			}
			else return AxisAlignedBB.getBoundingBox(coords.x - 1, coords.y - 1, coords.z, coords.x + 1, coords.y + 1, coords.z + depth);
		}
	}

	/**
	 * Gets an AABB for AOE digging operations. The charge increases only the breadth of the box.
	 * Y level remains constant. As such, a direction hit is unneeded.
	 */

	public static AxisAlignedBB getFlatYBox(Coordinates coords, int offset)
	{
		return AxisAlignedBB.getBoundingBox(coords.x - offset, coords.y, coords.z - offset, coords.x + offset, coords.y, coords.z + offset);
	}

	public static List<TileEntity> getTileEntitiesWithinAABB(World world, AxisAlignedBB bBox)
	{
		List<TileEntity> list = Lists.newArrayList();

		for (int i = (int) bBox.minX; i <= bBox.maxX; i++)
			for (int j = (int) bBox.minY; j <= bBox.maxY; j++)
				for (int k = (int) bBox.minZ; k <= bBox.maxZ; k++)
				{
					TileEntity tile = world.getTileEntity(i, j, k);
					if (tile != null)
					{
						list.add(tile);
					}
				}

		return list;
	}

	/**
	 * Gravitates an entity, vanilla xp orb style, towards a position
	 * Code adapted from EntityXPOrb and OpenBlocks Vacuum Hopper, mostly the former
	 */

	public static void igniteNearby(World world, EntityPlayer player)
	{
		for (int x = (int) (player.posX - 8); x <= player.posX + 8; x++)
			for (int y = (int) (player.posY - 5); y <= player.posY + 5; y++)
				for (int z = (int) (player.posZ - 8); z <= player.posZ + 8; z++)
					if (world.rand.nextInt(128) == 0 && world.isAirBlock(x, y, z))
					{
						PlayerHelper.checkedPlaceBlock(((EntityPlayerMP) player), x, y, z, Blocks.fire, 0);
					}
	}
}
