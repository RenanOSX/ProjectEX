package moze_intel.projecte.world;

import com.google.common.collect.Lists;
import moze_intel.projecte.config.ProjectEConfig;
import moze_intel.projecte.model.Coordinates;
import moze_intel.projecte.utils.Constants;
import moze_intel.projecte.utils.PlayerHelper;
import net.minecraft.block.Block;
import net.minecraft.block.BlockFlower;
import net.minecraft.block.IGrowable;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.world.World;
import net.minecraftforge.common.IPlantable;
import net.minecraftforge.common.IShearable;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public final class HarvestHelper
{
	public static ArrayList<ItemStack> getBlockDrops(World world, EntityPlayer player, Block block, ItemStack stack, int x, int y, int z)
	{
		int meta = world.getBlockMetadata(x, y, z);

		if (EnchantmentHelper.getEnchantmentLevel(Enchantment.silkTouch.effectId, stack) > 0 && block.canSilkHarvest(world, player, x, y, z, meta))
		{
			return Lists.newArrayList(new ItemStack(block, 1, meta));
		}

		return block.getDrops(world, x, y, z, meta, EnchantmentHelper.getEnchantmentLevel(Enchantment.fortune.effectId, stack));
	}

	/**
	 * Gets an AABB for AOE digging operations. The offset increases both the breadth and depth of the box.
	 */

	public static void growNearbyRandomly(boolean harvest, World world, double xCoord, double yCoord, double zCoord, EntityPlayer player)
	{
		int chance = harvest ? 16 : 32;

		for (int x = (int) (xCoord - 5); x <= xCoord + 5; x++)
			for (int y = (int) (yCoord - 3); y <= yCoord + 3; y++)
				for (int z = (int) (zCoord - 5); z <= zCoord + 5; z++)
				{
					Block crop = world.getBlock(x, y, z);

					// Vines, leaves, tallgrass, deadbush, doubleplants
					if (crop instanceof IShearable)
					{
						if (harvest)
						{
							if (player != null && PlayerHelper.hasBreakPermission(((EntityPlayerMP) player), x, y, z))
							{
								world.func_147480_a(x, y, z, true);
							} else if (player == null)
							{
								world.func_147480_a(x, y, z, true);
							}
						}
					}
					// Carrot, cocoa, wheat, grass (creates flowers and tall grass in vicinity),
					// Mushroom, potato, sapling, stems, tallgrass
					else if (crop instanceof IGrowable)
					{
						IGrowable growable = (IGrowable) crop;
						if(harvest && !growable.func_149851_a(world, x, y, z, false))
						{
							if (player != null && PlayerHelper.hasBreakPermission(((EntityPlayerMP) player), x, y, z))
							{
								world.func_147480_a(x, y, z, true);
							} else if (player == null)
							{
								world.func_147480_a(x, y, z, true);
							}
						}
						else if (world.rand.nextInt(chance) == 0)
						{
							if (ProjectEConfig.harvBandGrass || !crop.getUnlocalizedName().toLowerCase(Locale.ROOT).contains("grass"))
							{
								growable.func_149853_b(world, world.rand, x, y, z);
							}
						}
					}
					// All modded
					// Cactus, Reeds, Netherwart, Flower
					else if (crop instanceof IPlantable)
					{
						if (world.rand.nextInt(chance / 4) == 0)
						{
							for (int i = 0; i < (harvest ? 8 : 4); i++)
							{
								crop.updateTick(world, x, y, z, world.rand);
							}
						}

						if (harvest)
						{
							if (crop instanceof BlockFlower)
							{
								if (player != null && PlayerHelper.hasBreakPermission(((EntityPlayerMP) player), x, y, z))
								{
									world.func_147480_a(x, y, z, true);
								} else if (player == null)
								{
									world.func_147480_a(x, y, z, true);
								}
							}
							if (crop == Blocks.reeds || crop == Blocks.cactus)
							{
								boolean shouldHarvest = true;

								for (int i = 1; i < 3; i++)
								{
									if (world.getBlock(x, y + i, z) != crop)
									{
										shouldHarvest = false;
										break;
									}
								}

								if (shouldHarvest)
								{
									for (int i = crop == Blocks.reeds ? 1 : 0; i < 3; i++)
									{
										if (player != null && PlayerHelper.hasBreakPermission(((EntityPlayerMP) player), x, y + i, z))
										{
											world.func_147480_a(x, y + i, z, true);
										} else if (player == null)
										{
											world.func_147480_a(x, y + i, z, true);
										}
									}
								}
							}
							if (crop == Blocks.nether_wart)
							{
								int meta = ((IPlantable) crop).getPlantMetadata(world, x, y, z);
								if (meta == 3)
								{
									if (player != null && PlayerHelper.hasBreakPermission(((EntityPlayerMP) player), x, y, z))
									{
										world.func_147480_a(x, y, z, true);
									} else if (player == null)
									{
										world.func_147480_a(x, y, z, true);
									}
								}
							}
						}
					}
				}
	}

	/**
	 * Recursively mines out a vein of the given Block, starting from the provided coordinates
	 */

	public static void harvestVein(World world, EntityPlayer player, ItemStack stack, Coordinates coords, Block target, List<ItemStack> currentDrops, int numMined)
	{
		if (numMined >= Constants.MAX_VEIN_SIZE)
		{
			return;
		}

		AxisAlignedBB b = AxisAlignedBB.getBoundingBox(coords.x - 1, coords.y - 1, coords.z - 1, coords.x + 1, coords.y + 1, coords.z + 1);

		for (int x = (int) b.minX; x <= b.maxX; x++)
			for (int y = (int) b.minY; y <= b.maxY; y++)
				for (int z = (int) b.minZ; z <= b.maxZ; z++)
				{
					Block block = world.getBlock(x, y, z);

					if (block == target || (target == Blocks.lit_redstone_ore && block == Blocks.redstone_ore))
					{
						numMined++;
						if (PlayerHelper.hasBreakPermission(((EntityPlayerMP) player), x, y, z))
						{
							currentDrops.addAll(getBlockDrops(world, player, block, stack, x, y, z));
							world.setBlockToAir(x, y, z);
							harvestVein(world, player, stack, new Coordinates(x, y, z), target, currentDrops, numMined);
						}
					}
				}
	}
}
