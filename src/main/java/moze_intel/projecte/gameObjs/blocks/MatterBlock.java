package moze_intel.projecte.gameObjs.blocks;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import moze_intel.projecte.gameObjs.ObjHandler;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;
import net.minecraft.world.World;
import net.minecraft.util.MathHelper;

import java.util.List;

public class MatterBlock extends Block
{
	@SideOnly(Side.CLIENT)
	private IIcon[] icons;
	
	public MatterBlock() 
	{
		super(Material.iron);
		this.setCreativeTab(ObjHandler.cTab);
		this.setBlockName("pe_matter_block");
	}
	
	@Override
	public float getBlockHardness(World world, int x, int y, int z)
	{
		int meta = world.getBlockMetadata(x, y, z);
		
		if (meta == 0)
		{
			return 1000000.0F;
		}

		return 2000000.0F;
	}
	
	@Override
	public boolean canHarvestBlock(EntityPlayer player, int meta)
	{
		ItemStack stack = player.getHeldItem();
		
		if (stack != null)
		{
			if (meta == 0)
			{
				return stack.getItem() == ObjHandler.rmPick || stack.getItem() == ObjHandler.dmPick || stack.getItem() == ObjHandler.rmStar;
			}
			else
			{
				return stack.getItem() == ObjHandler.rmPick || stack.getItem() == ObjHandler.rmStar;
			}
		}
		
		return false;
	}
	
	@Override
	public int damageDropped(int meta)
	{
		return meta;
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void getSubBlocks(Item matterBlock, CreativeTabs cTab, List list)
	{
		for (int i = 0; i < moze_intel.projecte.utils.Constants.MATTER_NAMES.length; i++)
		{
			list.add(new ItemStack(matterBlock , 1, i));
		}
	}
	
	@Override
	@SideOnly(Side.CLIENT)
	public void registerBlockIcons(IIconRegister register)
	{
		icons = new IIcon[moze_intel.projecte.utils.Constants.MATTER_NAMES.length];
		String[] shortNames = new String[]{"dm", "rm", "mm", "pm", "ppm", "vm", "bm", "cm", "gm", "lm", "ym", "om", "wm", "fm"};
		for (int i = 0; i < icons.length; i++)
		{
			if (i < shortNames.length)
			{
				icons[i] = register.registerIcon("projecte:" + shortNames[i]);
			}
			else
			{
				icons[i] = register.registerIcon("projecte:dm");
			}
		}
	}
	
	@Override
	@SideOnly(Side.CLIENT)
	public IIcon getIcon(int side, int meta)
	{
		int idx = MathHelper.clamp_int(meta, 0, icons.length - 1);
		return icons[idx];
	}
	
}
