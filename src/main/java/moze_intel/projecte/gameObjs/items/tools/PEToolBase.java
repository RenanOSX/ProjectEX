package moze_intel.projecte.gameObjs.items.tools;

import com.google.common.collect.Sets;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import moze_intel.projecte.gameObjs.items.ItemMode;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.world.World;
import net.minecraftforge.common.ForgeHooks;
import java.util.Set;

public abstract class PEToolBase extends ItemMode
{
	public static final float HAMMER_BASE_ATTACK = 13.0F;
	public static final float DARKSWORD_BASE_ATTACK = 12.0F;
	public static final float REDSWORD_BASE_ATTACK = 16.0F;
	public static final float STAR_BASE_ATTACK = 20.0F;
	public static final float KATAR_BASE_ATTACK = 23.0F;
	protected String pePrimaryToolClass;
	protected String peToolMaterial;
	protected Set<Material> harvestMaterials;
	protected Set<String> secondaryClasses;

	public PEToolBase(String unlocalName, byte numCharge, String[] modeDescrp)
	{
		super(unlocalName, numCharge, modeDescrp);
		harvestMaterials = Sets.newHashSet();
		secondaryClasses = Sets.newHashSet();
	}

	public MovingObjectPosition getMovingObjectPosition(World world, EntityPlayer player, boolean liquids)
	{
		return getMovingObjectPositionFromPlayer(world, player, liquids);
	}

	@Override
	public boolean canHarvestBlock(Block block, ItemStack stack)
	{
		return harvestMaterials.contains(block.getMaterial());
	}

	@Override
	@SideOnly(Side.CLIENT)
	public boolean isFull3D()
	{
		return true;
	}

	@Override
	public int getHarvestLevel(ItemStack stack, String toolClass)
	{
		if (this.pePrimaryToolClass.equals(toolClass) || this.secondaryClasses.contains(toolClass))
		{
			return 4; // TiCon
		}
		return -1;
	}

	@Override
	public float getDigSpeed(ItemStack stack, Block block, int metadata)
	{
		if ("dm_tools".equals(this.peToolMaterial))
		{
			if (canHarvestBlock(block, stack) || ForgeHooks.canToolHarvestBlock(block, metadata, stack))
			{
				return 14.0f + (12.0f * this.getCharge(stack));
			}
		}
		else if ("rm_tools".equals(this.peToolMaterial))
		{
			if (canHarvestBlock(block, stack) || ForgeHooks.canToolHarvestBlock(block, metadata, stack))
			{
				return 16.0f + (14.0f * this.getCharge(stack));
			}
		}
		return 1.0F;
	}

	@Override
	public void registerIcons(IIconRegister register)
	{
		this.itemIcon = register.registerIcon(this.getTexture(peToolMaterial, pePrimaryToolClass));
	}

	protected void clearOdAOE(World world, ItemStack stack, EntityPlayer player, String odName, int emcCost)
	{
		ToolAOEHelper.clearOdAOE(this, world, stack, player, odName, emcCost);
	}

	protected void tillAOE(ItemStack stack, EntityPlayer player, World world, int x, int y, int z, int meta, int emcCost)
	{
		ToolAOEHelper.tillAOE(this, stack, player, world, x, y, z, meta, emcCost);
	}

	protected void digBasedOnMode(ItemStack stack, World world, Block block, int x, int y, int z, EntityLivingBase living)
	{
		ToolAOEHelper.digBasedOnMode(this, stack, world, block, x, y, z, living);
	}

	protected void digAOE(ItemStack stack, World world, EntityPlayer player, boolean affectDepth, int emcCost)
	{
		ToolAOEHelper.digAOE(this, stack, world, player, affectDepth, emcCost);
	}

	protected void attackWithCharge(ItemStack stack, EntityLivingBase damaged, EntityLivingBase damager, float baseDmg)
	{
		ToolAOEHelper.attackWithCharge(this, stack, damaged, damager, baseDmg);
	}

	protected void attackAOE(ItemStack stack, EntityPlayer player, boolean slayAll, float damage, int emcCost)
	{
		ToolAOEHelper.attackAOE(this, stack, player, slayAll, damage, emcCost);
	}

	protected void shearBlock(ItemStack stack, int x, int y, int z, EntityPlayer player)
	{
		ToolAOEHelper.shearBlock(this, stack, x, y, z, player);
	}

	protected void shearEntityAOE(ItemStack stack, EntityPlayer player, int emcCost)
	{
		ToolAOEHelper.shearEntityAOE(this, stack, player, emcCost);
	}

	protected void tryVeinMine(ItemStack stack, EntityPlayer player, MovingObjectPosition mop)
	{
		ToolAOEHelper.tryVeinMine(this, stack, player, mop);
	}

	protected void mineOreVeinsInAOE(ItemStack stack, EntityPlayer player)
	{
		ToolAOEHelper.mineOreVeinsInAOE(this, stack, player);
	}
}
