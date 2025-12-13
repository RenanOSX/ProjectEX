package moze_intel.projecte.gameObjs.items.itemBlocks;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import moze_intel.projecte.gameObjs.blocks.PowerFlower;
import moze_intel.projecte.utils.Constants;
import net.minecraft.block.Block;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumChatFormatting;
import moze_intel.projecte.NumberFormatter;

import java.util.List;

public class ItemPowerFlowerBlock extends ItemBlock
{
	public ItemPowerFlowerBlock(Block block)
	{
		super(block);
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void addInformation(ItemStack stack, EntityPlayer player, List list, boolean par4)
	{
		if (this.field_150939_a instanceof PowerFlower)
		{
			PowerFlower powerFlower = (PowerFlower) this.field_150939_a;
			int tier = powerFlower.getTier();
			String formattedNumber = NumberFormatter.format(Constants.POWER_FLOWER_GEN[tier - 1]);
			list.add(EnumChatFormatting.BLUE + "Generate Passive EMC when chunk loaded");
			list.add(EnumChatFormatting.DARK_PURPLE + String.format("Generation Rate: ") + EnumChatFormatting.YELLOW + String.format("%s EMC/s", formattedNumber));
		}
	}
}
