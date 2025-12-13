package moze_intel.projecte.events;

import com.google.common.math.LongMath;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import moze_intel.projecte.api.item.IItemEmc;
import moze_intel.projecte.api.item.IPedestalItem;
import moze_intel.projecte.config.ProjectEConfig;
import moze_intel.projecte.gameObjs.ObjHandler;
import moze_intel.projecte.gameObjs.gui.GUIPedestal;
import moze_intel.projecte.utils.Constants;
import moze_intel.projecte.utils.EMCHelper;
import net.minecraft.block.Block;
import net.minecraft.client.Minecraft;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.util.StatCollector;
import net.minecraftforge.event.entity.player.ItemTooltipEvent;
import net.minecraftforge.fluids.BlockFluidBase;
import net.minecraftforge.fluids.Fluid;
import net.minecraftforge.fluids.FluidRegistry;
import net.minecraftforge.oredict.OreDictionary;
import moze_intel.projecte.NumberFormatter;

import java.util.List;
import java.util.NavigableMap;
import java.util.TreeMap;

@SideOnly(Side.CLIENT)
public class ToolTipEvent 
{
	@SubscribeEvent
	public void tTipEvent(ItemTooltipEvent event)
	{
		ItemStack current = event.itemStack;
		Item currentItem = current.getItem();
		Block currentBlock = Block.getBlockFromItem(currentItem);

		if (current == null)
		{
			return;
		}

		if (currentBlock == ObjHandler.dmPedestal)
		{
			event.toolTip.add(StatCollector.translateToLocal("pe.pedestal.tooltip1"));
			event.toolTip.add(StatCollector.translateToLocal("pe.pedestal.tooltip2"));
		}

		if (currentItem == ObjHandler.manual)
		{
			event.toolTip.add(StatCollector.translateToLocal("pe.manual.tooltip1"));
		}

		if (ProjectEConfig.showPedestalTooltip
			&& currentItem instanceof IPedestalItem)
		{
			if (ProjectEConfig.showPedestalTooltipInGUI)
			{
				if (Minecraft.getMinecraft().currentScreen instanceof GUIPedestal)
				{
					event.toolTip.add(EnumChatFormatting.DARK_PURPLE + StatCollector.translateToLocal("pe.pedestal.on_pedestal") + " ");
					List<String> description = ((IPedestalItem) currentItem).getPedestalDescription();
					if (description.isEmpty())
					{
						event.toolTip.add(IPedestalItem.TOOLTIPDISABLED);
					}
					else
					{
						event.toolTip.addAll(((IPedestalItem) currentItem).getPedestalDescription());
					}
				}
			}
			else
			{
				event.toolTip.add(EnumChatFormatting.DARK_PURPLE + StatCollector.translateToLocal("pe.pedestal.on_pedestal") + " ");
				List<String> description = ((IPedestalItem) currentItem).getPedestalDescription();
				if (description.isEmpty())
				{
					event.toolTip.add(IPedestalItem.TOOLTIPDISABLED);
				}
				else
				{
					event.toolTip.addAll(((IPedestalItem) currentItem).getPedestalDescription());
				}
			}
			
		}

		if (ProjectEConfig.showUnlocalizedNames)
		{
			event.toolTip.add("UN: " + Item.itemRegistry.getNameForObject(current.getItem()));
		}
		
		if (ProjectEConfig.showODNames)
		{
			for (int id : OreDictionary.getOreIDs(current))
			{
				event.toolTip.add("OD: " + OreDictionary.getOreName(id));
			}
			if (currentBlock instanceof BlockFluidBase) {
				event.toolTip.add("Fluid: " + ((BlockFluidBase) currentBlock).getFluid().getName());
			}
		}

		if (ProjectEConfig.showEMCTooltip)
		{
			if (EMCHelper.doesItemHaveEmc(current))
			{
				long value = EMCHelper.getEmcValue(current);

				String formattedValue = NumberFormatter.format(value);

				event.toolTip.add(EnumChatFormatting.YELLOW +
						StatCollector.translateToLocal("pe.emc.emc_tooltip_prefix") + " " + EnumChatFormatting.WHITE + formattedValue);

				if (current.stackSize > 1)
				{
					long total;
					try
					{
						total = LongMath.checkedMultiply(value, current.stackSize);
					} catch (ArithmeticException e) {
						total = Long.MAX_VALUE;
					}
					if (total < 0 || total <= value)
					{
						event.toolTip.add(EnumChatFormatting.YELLOW + StatCollector.translateToLocal("pe.emc.stackemc_tooltip_prefix") + " " + EnumChatFormatting.OBFUSCATED + StatCollector.translateToLocal("pe.emc.too_much"));
					}
					else
					{
						event.toolTip.add(EnumChatFormatting.YELLOW + StatCollector.translateToLocal("pe.emc.stackemc_tooltip_prefix") + " " + EnumChatFormatting.WHITE + NumberFormatter.format(total));
					}

				}
			}
		}

		if (ProjectEConfig.showStatTooltip)
		{
			/**
			 * Collector ToolTips
			 */
			String unit = StatCollector.translateToLocal("pe.emc.name");
			String rate = StatCollector.translateToLocal("pe.emc.rate");

			for (int i = 0; i < ObjHandler.collectorBlocks.length; i++)
			{
				if (currentBlock == ObjHandler.collectorBlocks[i])
				{
					if (i < Constants.COLLECTOR_MK_GEN.length && i < Constants.COLLECTOR_MK_MAX.length)
					{
						event.toolTip.add(EnumChatFormatting.DARK_PURPLE
									+ StatCollector.translateToLocal("pe.emc.maxgenrate_tooltip")
									+ " " + EnumChatFormatting.BLUE + NumberFormatter.format(Constants.COLLECTOR_MK_GEN[i]) + " " + rate);
						event.toolTip.add(EnumChatFormatting.DARK_PURPLE
									+ StatCollector.translateToLocal("pe.emc.maxstorage_tooltip")
									+ " " + EnumChatFormatting.BLUE + NumberFormatter.format(Constants.COLLECTOR_MK_MAX[i]) + " " + unit);
					}
				}
			}

			for (int i = 0; i < ObjHandler.relayBlocks.length; i++)
			{
				if (currentBlock == ObjHandler.relayBlocks[i])
				{
					if (i < Constants.RELAY_MK_OUTPUT.length && i < Constants.RELAY_MK_MAX.length)
					{
						event.toolTip.add(EnumChatFormatting.DARK_PURPLE
									+ StatCollector.translateToLocal("pe.emc.maxoutrate_tooltip")
									+ " " + EnumChatFormatting.BLUE + NumberFormatter.format(Constants.RELAY_MK_OUTPUT[i]) + " " + rate);
						event.toolTip.add(EnumChatFormatting.DARK_PURPLE
									+ StatCollector.translateToLocal("pe.emc.maxstorage_tooltip")
									+ " " + EnumChatFormatting.BLUE + NumberFormatter.format(Constants.RELAY_MK_MAX[i]) + " " + unit);
					}
				}
			}
		}

		if (current.hasTagCompound())
		{
			if (current.stackTagCompound.getBoolean("ProjectEBlock"))
			{
				event.toolTip.add(EnumChatFormatting.GREEN + StatCollector.translateToLocal("pe.misc.wrenched_block"));
				
				if (current.stackTagCompound.getDouble("EMC") > 0)
				{
event.toolTip.add(EnumChatFormatting.YELLOW + StatCollector.translateToLocal("pe.emc.storedemc_tooltip") + " " + EnumChatFormatting.RESET + NumberFormatter.format((long) current.stackTagCompound.getDouble("EMC")));
				}
			}
			if (current.getItem() instanceof IItemEmc || current.stackTagCompound.hasKey("StoredEMC"))
			{
				double value = 0;
				if (current.stackTagCompound.hasKey("StoredEMC"))
				{
					value = current.stackTagCompound.getDouble("StoredEMC");
				} else
				{
					value = ((IItemEmc) current.getItem()).getStoredEmc(current);
				}

					String storedFormatted;
					if (value >= 1000 || value <= -1000) {
						storedFormatted = NumberFormatter.format((long) value);
					} else {
						storedFormatted = Constants.EMC_FORMATTER.format(value);
					}
					event.toolTip.add(EnumChatFormatting.YELLOW + StatCollector.translateToLocal("pe.emc.storedemc_tooltip") + " " + EnumChatFormatting.RESET + storedFormatted);
			}

			if (current.stackTagCompound.hasKey("StoredXP"))
			{
				event.toolTip.add(String.format(EnumChatFormatting.DARK_GREEN + StatCollector.translateToLocal("pe.misc.storedxp_tooltip") + " " + EnumChatFormatting.GREEN + "%,d", current.stackTagCompound.getInteger("StoredXP")));
			}
		}
	}
}
