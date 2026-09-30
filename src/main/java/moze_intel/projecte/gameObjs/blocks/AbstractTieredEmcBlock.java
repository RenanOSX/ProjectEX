package moze_intel.projecte.gameObjs.blocks;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import moze_intel.projecte.gameObjs.tiles.TileEmc;
import moze_intel.projecte.utils.Constants;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.IIcon;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;

public abstract class AbstractTieredEmcBlock extends BlockDirection
{
	@SideOnly(Side.CLIENT)
	private IIcon front;
	@SideOnly(Side.CLIENT)
	private IIcon top;
	private final int tier;
	private final String texturePrefix;

	protected AbstractTieredEmcBlock(Material material, String blockName, String texturePrefix, int tier, float hardness)
	{
		super(material);
		this.tier = tier;
		this.texturePrefix = texturePrefix;
		this.setBlockName(blockName);
		int idx = MathHelper.clamp_int(tier - 1, 0, Constants.COLLECTOR_LIGHT_VALS.length - 1);
		this.setLightLevel(Constants.COLLECTOR_LIGHT_VALS[idx]);
		this.setHardness(hardness);
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
		this.blockIcon = register.registerIcon("projecte:" + texturePrefix + "/other");
		this.front = register.registerIcon("projecte:" + texturePrefix + "/front");
		this.top = register.registerIcon("projecte:" + texturePrefix + "/top_" + Integer.toString(tier));
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
}
