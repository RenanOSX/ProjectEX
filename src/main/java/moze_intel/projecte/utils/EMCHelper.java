package moze_intel.projecte.utils;

import net.minecraft.block.Block;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import moze_intel.projecte.emc.EmcLookup;
import moze_intel.projecte.emc.FuelConsumption;



/**
 * Helper class for EMC.
 * Notice: Please try to keep methods tidy and alphabetically ordered. Thanks!
 */
public final class EMCHelper
{

	public static long consumePlayerFuel(EntityPlayer player, long minFuel)
	{
		return FuelConsumption.consumePlayerFuel(player, minFuel);
	}

	public static boolean doesBlockHaveEmc(Block block)
	{
		return EmcLookup.doesBlockHaveEmc(block);
	}

	public static boolean doesItemHaveEmc(ItemStack stack)
	{
		return EmcLookup.doesItemHaveEmc(stack);
	}

	public static boolean doesItemHaveEmc(Item item)
	{
		return EmcLookup.doesItemHaveEmc(item);
	}

	public static long getEmcValue(Block Block)
	{
		return EmcLookup.getEmcValue(Block);
	}

	public static long getEmcValue(Item item)
	{
		return EmcLookup.getEmcValue(item);
	}

	public static long getEmcValue(ItemStack stack)
	{
		return EmcLookup.getEmcValue(stack);
	}

	public static long getEnchantEmcBonus(ItemStack stack)
	{
		return EmcLookup.getEnchantEmcBonus(stack);
	}

	public static long getKleinStarMaxEmc(ItemStack stack)
	{
		return EmcLookup.getKleinStarMaxEmc(stack);
	}

	public static long getStoredEMCBonus(ItemStack stack)
	{
		return EmcLookup.getStoredEMCBonus(stack);
	}
}
