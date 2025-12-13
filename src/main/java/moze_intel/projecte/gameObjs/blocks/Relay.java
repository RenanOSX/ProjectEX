package moze_intel.projecte.gameObjs.blocks;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import moze_intel.projecte.PECore;
import moze_intel.projecte.gameObjs.tiles.RelayTile;
import moze_intel.projecte.gameObjs.tiles.TileEmc;
import moze_intel.projecte.utils.ComparatorHelper;
import moze_intel.projecte.utils.Constants;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.IIcon;
import net.minecraft.world.World;

public class Relay extends BlockDirection
{
	@SideOnly(Side.CLIENT)
	private IIcon front;
	@SideOnly(Side.CLIENT)
	private IIcon top;
	private int tier;
	
	public Relay(int tier) 
	{
		super(Material.rock);
		this.setBlockName("pe_relay_MK" + Integer.toString(tier));
		int idx = net.minecraft.util.MathHelper.clamp_int(tier - 1, 0, Constants.COLLECTOR_LIGHT_VALS.length - 1);
		this.setLightLevel(Constants.COLLECTOR_LIGHT_VALS[idx]);
		this.setHardness(10.0f);
		this.tier = tier;
	}
	
	public int getTier()
	{
		return tier;
	}
	
	@Override
	public boolean onBlockActivated(World world, int x, int y, int z, EntityPlayer player, int side, float hitX, float hitY, float hitZ)
	{
		return true;
	}
	
	@Override
	public void onBlockPlacedBy(World world, int x, int y, int z, EntityLivingBase entLiving, ItemStack stack)
	{
		setFacingMeta(world, x, y, z, ((EntityPlayer) entLiving));
		
		TileEntity tile = world.getTileEntity(x, y, z);
		
		if (stack.hasTagCompound() && stack.stackTagCompound.getBoolean("ProjectEBlock") && tile instanceof TileEmc)
		{
			stack.stackTagCompound.setInteger("x", x);
			stack.stackTagCompound.setInteger("y", y);
			stack.stackTagCompound.setInteger("z", z);
			
			tile.readFromNBT(stack.stackTagCompound);
		}
	}
	
	@Override
	@SideOnly(Side.CLIENT)
	public void registerBlockIcons(IIconRegister register)
	{
		this.blockIcon = register.registerIcon("projecte:relays/other");
		this.front = register.registerIcon("projecte:relays/front");
		this.top = register.registerIcon("projecte:relays/top_"+Integer.toString(tier));
	}
	
	@Override
	@SideOnly(Side.CLIENT)
	public IIcon getIcon(int side, int meta)
	{
		if (meta == 0 && side == 3) 
		{
			return front;
		}
		
		if (side == 1)
		{
			return top;
		}
		
		return side != meta ? this.blockIcon : front;
	}

	@Override
	public boolean hasTileEntity(int meta)
	{
		return true;
	}

	@Override
	public TileEntity createTileEntity(World world, int meta)
	{
		return new RelayTile(tier);
	}

	@Override
	public boolean hasComparatorInputOverride()
	{
		return true;
	}

	@Override
	public int getComparatorInputOverride(World world, int x, int y, int z, int meta)
	{
		return ComparatorHelper.getForRelay(world, x, y, z);
	}
}
