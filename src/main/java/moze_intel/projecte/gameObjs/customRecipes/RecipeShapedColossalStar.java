package moze_intel.projecte.gameObjs.customRecipes;

import moze_intel.projecte.gameObjs.ObjHandler;
import moze_intel.projecte.gameObjs.items.KleinStar;
import net.minecraft.inventory.InventoryCrafting;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.IRecipe;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.world.World;

public class RecipeShapedColossalStar extends RecipeShapedKleinStar
{
	/**
	 * How many horizontal slots this recipe is wide.
	 */
	public final int recipeWidth;
	/**
	 * How many vertical slots this recipe uses.
	 */
	public final int recipeHeight;
	/**
	 * Is a array of ItemStack that composes the recipe.
	 */
	public final ItemStack[] recipeItems;
	/**
	 * Is the ItemStack that you get when craft the recipe.
	 */
	private ItemStack recipeOutput;
	
	public RecipeShapedColossalStar(int width, int height, ItemStack[] items, ItemStack output)
	{
		super(width, height, items, output);
		this.recipeWidth = width;
		this.recipeHeight = height;
		this.recipeItems = items;
		this.recipeOutput = output;
	}
	
}