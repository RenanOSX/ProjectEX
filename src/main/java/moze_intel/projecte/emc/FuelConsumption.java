package moze_intel.projecte.emc;

import com.google.common.collect.Maps;
import moze_intel.projecte.api.item.IItemEmc;
import moze_intel.projecte.emc.FuelMapper;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import java.util.LinkedHashMap;
import java.util.Map;

public final class FuelConsumption
{
	public static long consumePlayerFuel(EntityPlayer player, long minFuel)
	{
		if (player.capabilities.isCreativeMode)
		{
			return minFuel;
		}

		IInventory inv = player.inventory;
		LinkedHashMap<Integer, Integer> map = Maps.newLinkedHashMap();
		boolean metRequirement = false;
		long emcConsumed = 0;

		for (int i = 0; i < inv.getSizeInventory(); i++)
		{
			ItemStack stack = inv.getStackInSlot(i);

			if (stack == null)
			{
				continue;
			}
			else if (stack.getItem() instanceof IItemEmc)
			{
				IItemEmc itemEmc = ((IItemEmc) stack.getItem());
				if (itemEmc.getStoredEmc(stack) >= minFuel)
				{
					itemEmc.extractEmc(stack, minFuel);
					player.inventoryContainer.detectAndSendChanges();
					return minFuel;
				}
			}
			else if (!metRequirement)
			{
				if(FuelMapper.isStackFuel(stack))
				{
					long emc = EmcLookup.getEmcValue(stack);
					int toRemove = ((int) Math.ceil((minFuel - emcConsumed) / (double) emc));

					if (stack.stackSize >= toRemove)
					{
						map.put(i, toRemove);
						emcConsumed += emc * toRemove;
						metRequirement = true;
					}
					else
					{
						map.put(i, stack.stackSize);
						emcConsumed += emc * stack.stackSize;

						if (emcConsumed >= minFuel)
						{
							metRequirement = true;
						}
					}

				}
			}
		}

		if (metRequirement)
		{
			for (Map.Entry<Integer, Integer> entry : map.entrySet())
			{
				inv.decrStackSize(entry.getKey(), entry.getValue());
			}

			player.inventoryContainer.detectAndSendChanges();
			return emcConsumed;
		}

		return -1;
	}
}
