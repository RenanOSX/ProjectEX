package moze_intel.projecte.utils;

import moze_intel.projecte.api.item.IItemEmc;
import moze_intel.projecte.gameObjs.tiles.AlchChestTile;
import moze_intel.projecte.gameObjs.tiles.CollectorTile;
import moze_intel.projecte.gameObjs.tiles.CondenserTile;
import moze_intel.projecte.gameObjs.tiles.RMFurnaceTile;
import moze_intel.projecte.gameObjs.tiles.RelayTile;
import net.minecraft.inventory.Container;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

/**
 * Utility class to get comparator outputs for a block
 */
public final class ComparatorHelper
{
	public static int getForAlchChest(World world, int x, int y, int z)
	{
		return Container.calcRedstoneFromInventory(((AlchChestTile) world.getTileEntity(x, y, z)));
	}

	public static int getForCollector(World world, int x, int y, int z)
	{
		CollectorTile tile = ((CollectorTile) world.getTileEntity(x, y, z));
		return MathUtils.scaleToRedstone(tile.getStoredEmc(), tile.getMaximumEmc());
	}

	public static int getForCondenser(World world, int x, int y, int z)
	{
		return Container.calcRedstoneFromInventory(((CondenserTile) world.getTileEntity(x, y, z)));
	}

	public static int getForMatterFurnace(World world, int x, int y, int z)
	{
		return Container.calcRedstoneFromInventory(((RMFurnaceTile) world.getTileEntity(x, y, z)));
	}

	public static int getForRelay(World world, int x, int y, int z)
	{
		RelayTile relay = ((RelayTile) world.getTileEntity(x, y, z));
		return MathUtils.scaleToRedstone(relay.getStoredEmc(), relay.getMaximumEmc());
	}
}
