package moze_intel.projecte.gameObjs;

import cpw.mods.fml.common.IFuelHandler;
import moze_intel.projecte.utils.Constants;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import static moze_intel.projecte.gameObjs.ObjHandler.fuelBlock;
import static moze_intel.projecte.gameObjs.ObjHandler.fuels;

public class FuelHandler implements IFuelHandler
{
	@Override
	public int getBurnTime(ItemStack fuel)
	{
		if (fuel.getItem() == fuels)
		{
			switch (fuel.getItemDamage())
			{
				case 0:
					return Constants.ALCH_BURN_TIME;
				case 1:
					return Constants.MOBIUS_BURN_TIME;
				case 2:
					return Constants.AETERNALIS_BUR_TIME;
			}
		} else if (fuel.getItem() == Item.getItemFromBlock(fuelBlock))
		{
			switch (fuel.getItemDamage())
			{
				case 0:
					return Constants.ALCH_BURN_TIME * 9;
				case 1:
					return Constants.MOBIUS_BURN_TIME * 9;
				case 2:
					return Constants.AETERNALIS_BUR_TIME * 9;
			}
		}

		return 0;
	}
}
