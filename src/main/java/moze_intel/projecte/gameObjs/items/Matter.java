package moze_intel.projecte.gameObjs.items;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import moze_intel.projecte.utils.AchievementHandler;
import moze_intel.projecte.utils.Constants;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;

import java.util.List;

public class Matter extends ItemPE 
{
	@SideOnly(Side.CLIENT)
	private IIcon[] icons;
	
	public Matter()
	{
		this.setUnlocalizedName("matter");
		this.setHasSubtypes(true);
		this.setMaxDamage(0);
	}
	
	@Override
	public String getUnlocalizedName(ItemStack stack)
	{	
		if (stack.getItemDamage() > Constants.MATTER_NAMES.length - 1)
		{
			return super.getUnlocalizedName() + "_" + Constants.MATTER_NAMES[0];
		}
		return super.getUnlocalizedName() + "_" + Constants.MATTER_NAMES[stack.getItemDamage()];
	}
	
	@Override
	public void onCreated(ItemStack stack, World world, EntityPlayer player) 
	{
		super.onCreated(stack, world, player);
		
		if (!world.isRemote)
		{
			if (stack.getItemDamage() == 0)
			{
				player.addStat(AchievementHandler.DARK_MATTER, 1);
			}
			else if (stack.getItemDamage() == 1)
			{
				player.addStat(AchievementHandler.RED_MATTER, 1);
			}
		}
	}
	
	@SideOnly(Side.CLIENT)
	public void getSubItems(Item item, CreativeTabs cTab, List list)
	{
		for (int i = 0; i < Constants.MATTER_NAMES.length; i++)
		{
			list.add(new ItemStack(item, 1, i));
		}
	}
	
	@SideOnly(Side.CLIENT)
	public IIcon getIconFromDamage(int par1)
	{
		return icons[MathHelper.clamp_int(par1, 0, Constants.MATTER_NAMES.length - 1)];
	}
	
	@Override
	@SideOnly(Side.CLIENT)
	public void registerIcons(IIconRegister register)
	{
		icons = new IIcon[Constants.MATTER_NAMES.length];
		
		for (int i = 0; i < Constants.MATTER_NAMES.length; i++)
		{
			icons[i] = register.registerIcon(this.getTexture("matter", Constants.MATTER_NAMES[i]));
		}
	}
}
