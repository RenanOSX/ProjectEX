package moze_intel.projecte.gameObjs;

import cpw.mods.fml.common.registry.GameRegistry;
import moze_intel.projecte.config.ProjectEConfig;
import moze_intel.projecte.gameObjs.blocks.Condenser;
import moze_intel.projecte.gameObjs.blocks.Pedestal;
import moze_intel.projecte.gameObjs.customRecipes.RecipeAlchemyBag;
import moze_intel.projecte.gameObjs.customRecipes.RecipeShapedKleinStar;
import moze_intel.projecte.gameObjs.customRecipes.RecipeShapelessHidden;
import moze_intel.projecte.gameObjs.customRecipes.RecipesCovalenceRepair;
import moze_intel.projecte.gameObjs.items.Matter;
import moze_intel.projecte.gameObjs.items.Tome;
import moze_intel.projecte.gameObjs.items.TransmutationTablet;
import moze_intel.projecte.utils.Constants;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.FurnaceRecipes;
import net.minecraftforge.oredict.OreDictionary;
import net.minecraftforge.oredict.RecipeSorter;
import net.minecraftforge.oredict.RecipeSorter.Category;
import net.minecraftforge.oredict.ShapedOreRecipe;
import java.util.HashMap;
import java.util.Map.Entry;
import static moze_intel.projecte.gameObjs.ObjHandler.alchBag;
import static moze_intel.projecte.gameObjs.ObjHandler.alchChest;
import static moze_intel.projecte.gameObjs.ObjHandler.angelSmite;
import static moze_intel.projecte.gameObjs.ObjHandler.arcana;
import static moze_intel.projecte.gameObjs.ObjHandler.blackHole;
import static moze_intel.projecte.gameObjs.ObjHandler.bodyStone;
import static moze_intel.projecte.gameObjs.ObjHandler.cataliticLens;
import static moze_intel.projecte.gameObjs.ObjHandler.collectorBlocks;
import static moze_intel.projecte.gameObjs.ObjHandler.colossalStar;
import static moze_intel.projecte.gameObjs.ObjHandler.condenser;
import static moze_intel.projecte.gameObjs.ObjHandler.condenserMk2;
import static moze_intel.projecte.gameObjs.ObjHandler.confuseTorch;
import static moze_intel.projecte.gameObjs.ObjHandler.covalence;
import static moze_intel.projecte.gameObjs.ObjHandler.dCatalyst;
import static moze_intel.projecte.gameObjs.ObjHandler.dRod1;
import static moze_intel.projecte.gameObjs.ObjHandler.dRod2;
import static moze_intel.projecte.gameObjs.ObjHandler.dRod3;
import static moze_intel.projecte.gameObjs.ObjHandler.dmAxe;
import static moze_intel.projecte.gameObjs.ObjHandler.dmChest;
import static moze_intel.projecte.gameObjs.ObjHandler.dmFeet;
import static moze_intel.projecte.gameObjs.ObjHandler.dmFurnaceOff;
import static moze_intel.projecte.gameObjs.ObjHandler.dmHammer;
import static moze_intel.projecte.gameObjs.ObjHandler.dmHelmet;
import static moze_intel.projecte.gameObjs.ObjHandler.dmHoe;
import static moze_intel.projecte.gameObjs.ObjHandler.dmLegs;
import static moze_intel.projecte.gameObjs.ObjHandler.dmPedestal;
import static moze_intel.projecte.gameObjs.ObjHandler.dmPick;
import static moze_intel.projecte.gameObjs.ObjHandler.dmShears;
import static moze_intel.projecte.gameObjs.ObjHandler.dmShovel;
import static moze_intel.projecte.gameObjs.ObjHandler.dmSword;
import static moze_intel.projecte.gameObjs.ObjHandler.eternalDensity;
import static moze_intel.projecte.gameObjs.ObjHandler.everTide;
import static moze_intel.projecte.gameObjs.ObjHandler.fuelBlock;
import static moze_intel.projecte.gameObjs.ObjHandler.fuels;
import static moze_intel.projecte.gameObjs.ObjHandler.gemChest;
import static moze_intel.projecte.gameObjs.ObjHandler.gemFeet;
import static moze_intel.projecte.gameObjs.ObjHandler.gemHelmet;
import static moze_intel.projecte.gameObjs.ObjHandler.gemLegs;
import static moze_intel.projecte.gameObjs.ObjHandler.harvestGod;
import static moze_intel.projecte.gameObjs.ObjHandler.hyperLens;
import static moze_intel.projecte.gameObjs.ObjHandler.ignition;
import static moze_intel.projecte.gameObjs.ObjHandler.ironBand;
import static moze_intel.projecte.gameObjs.ObjHandler.kleinStars;
import static moze_intel.projecte.gameObjs.ObjHandler.lifeStone;
import static moze_intel.projecte.gameObjs.ObjHandler.magnumStar;
import static moze_intel.projecte.gameObjs.ObjHandler.matter;
import static moze_intel.projecte.gameObjs.ObjHandler.matterBlock;
import static moze_intel.projecte.gameObjs.ObjHandler.mercEye;
import static moze_intel.projecte.gameObjs.ObjHandler.mindStone;
import static moze_intel.projecte.gameObjs.ObjHandler.novaCataclysm;
import static moze_intel.projecte.gameObjs.ObjHandler.novaCatalyst;
import static moze_intel.projecte.gameObjs.ObjHandler.philosStone;
import static moze_intel.projecte.gameObjs.ObjHandler.powerFlowerBlocks;
import static moze_intel.projecte.gameObjs.ObjHandler.relayBlocks;
import static moze_intel.projecte.gameObjs.ObjHandler.repairTalisman;
import static moze_intel.projecte.gameObjs.ObjHandler.rmAxe;
import static moze_intel.projecte.gameObjs.ObjHandler.rmChest;
import static moze_intel.projecte.gameObjs.ObjHandler.rmFeet;
import static moze_intel.projecte.gameObjs.ObjHandler.rmFurnaceOff;
import static moze_intel.projecte.gameObjs.ObjHandler.rmHammer;
import static moze_intel.projecte.gameObjs.ObjHandler.rmHelmet;
import static moze_intel.projecte.gameObjs.ObjHandler.rmHoe;
import static moze_intel.projecte.gameObjs.ObjHandler.rmKatar;
import static moze_intel.projecte.gameObjs.ObjHandler.rmLegs;
import static moze_intel.projecte.gameObjs.ObjHandler.rmPick;
import static moze_intel.projecte.gameObjs.ObjHandler.rmShears;
import static moze_intel.projecte.gameObjs.ObjHandler.rmShovel;
import static moze_intel.projecte.gameObjs.ObjHandler.rmStar;
import static moze_intel.projecte.gameObjs.ObjHandler.rmSword;
import static moze_intel.projecte.gameObjs.ObjHandler.soulStone;
import static moze_intel.projecte.gameObjs.ObjHandler.swrg;
import static moze_intel.projecte.gameObjs.ObjHandler.timeWatch;
import static moze_intel.projecte.gameObjs.ObjHandler.tome;
import static moze_intel.projecte.gameObjs.ObjHandler.transmutationTablet;
import static moze_intel.projecte.gameObjs.ObjHandler.transmuteStone;
import static moze_intel.projecte.gameObjs.ObjHandler.voidRing;
import static moze_intel.projecte.gameObjs.ObjHandler.volcanite;
import static moze_intel.projecte.gameObjs.ObjHandler.zero;

public class RecipeRegistrar
{
	public static void addRecipes()
	{
		ItemStack diamondReplacement = new ItemStack(Items.diamond);
		ItemStack diamondBlockReplacement = new ItemStack(Blocks.diamond_block);

		if (ProjectEConfig.altCraftingMat)
		{
			diamondReplacement = new ItemStack(Items.nether_star);
			diamondBlockReplacement = new ItemStack(Items.nether_star);
		}

		//Shaped Recipes
		//Philos Stone
		GameRegistry.addRecipe(new ItemStack(philosStone), "RGR", "GDG", "RGR", 'R', Items.redstone, 'G', Items.glowstone_dust, 'D', diamondReplacement);

		GameRegistry.addRecipe(new ItemStack(philosStone), "GRG", "RDR", "GRG", 'R', Items.redstone, 'G', Items.glowstone_dust, 'D', diamondReplacement);

		//Interdiction torch
		GameRegistry.addRecipe(new ItemStack(confuseTorch, 2), "RDR", "DPD", "GGG", 'R', Blocks.redstone_torch, 'G', Items.glowstone_dust, 'D', Items.diamond, 'P', philosStone);

		//Repair Talisman
		GameRegistry.addRecipe(new ItemStack(repairTalisman), "LMH", "SPS", "HML", 'P', Items.paper, 'S', Items.string, 'L', new ItemStack(covalence, 1, 0), 'M', new ItemStack(covalence, 1, 1), 'H', new ItemStack(covalence, 1, 2));

		//Klein Star Ein
		GameRegistry.addRecipe(new ItemStack(kleinStars, 1, 0), "MMM", "MDM", "MMM", 'M', new ItemStack(fuels, 1, 1), 'D', Items.diamond);
		
		//Magnum Star
		GameRegistry.addShapelessRecipe(new ItemStack(magnumStar), new ItemStack(kleinStars, 0, 5), new ItemStack(kleinStars, 0, 5), new ItemStack(kleinStars, 0, 5), new ItemStack(kleinStars, 0, 5));

		//Colossal Star
		GameRegistry.addShapelessRecipe(new ItemStack(colossalStar), new ItemStack(magnumStar, 0, 5), new ItemStack(magnumStar, 0, 5), new ItemStack(magnumStar, 0, 5), new ItemStack(magnumStar, 0, 5));

		//Dark Matter (base tier uses diamond block and fuel)
		GameRegistry.addRecipe(new ItemStack(matter, 1, 0), "AAA", "ADA", "AAA", 'D', Blocks.diamond_block, 'A', new ItemStack(fuels, 1, 2));

		//Higher Matter tiers (each tier crafted from the previous tier + fuel)
		for (int i = 1; i < Constants.MATTER_NAMES.length; i++) {
			// Same shape variants as red matter used previously
			GameRegistry.addRecipe(new ItemStack(matter, 1, i), "AAA", "DDD", "AAA", 'D', new ItemStack(matter, 1, i - 1), 'A', new ItemStack(fuels, 1, 2));
			GameRegistry.addRecipe(new ItemStack(matter, 1, i), "ADA", "ADA", "ADA", 'D', new ItemStack(matter, 1, i - 1), 'A', new ItemStack(fuels, 1, 2));
		}

		//Alchemical Chest
		GameRegistry.addRecipe(new ItemStack(alchChest), "LMH", "SDS", "ICI", 'D', diamondReplacement, 'L', new ItemStack(covalence, 1, 0), 'M', new ItemStack(covalence, 1, 1), 'H', new ItemStack(covalence, 1, 2), 'S', Blocks.stone, 'I', Items.iron_ingot, 'C', Blocks.chest);

		//Alchemical Bags
		for (int i = 0; i < 16; i++)
		{
			GameRegistry.addRecipe(new ItemStack(alchBag, 1, i), "CCC", "WAW", "WWW", 'C', new ItemStack(covalence, 1, 2), 'A', alchChest, 'W', new ItemStack(Blocks.wool, 1, i));
		}

		//Condenser
		GameRegistry.addRecipe(new ItemStack(condenser), "ODO", "DCD", "ODO", 'D', Items.diamond, 'O', new ItemStack(Blocks.obsidian), 'C', new ItemStack(alchChest));

		//Condenser MK2
		GameRegistry.addRecipe(new ItemStack(condenserMk2), "RDR", "DCD", "RDR", 'D', new ItemStack(matterBlock, 1, 0), 'R', new ItemStack(matterBlock, 1, 1), 'C', condenser);

		//Transmutation Table
		GameRegistry.addRecipe(new ItemStack(transmuteStone), "OSO", "SPS", "OSO", 'S', Blocks.stone, 'O', Blocks.obsidian, 'P', philosStone);

		//Matter Blocks (each block crafted from 2x2 of its corresponding matter tier)
		for (int i = 0; i < Constants.MATTER_NAMES.length; i++) {
			GameRegistry.addRecipe(new ItemStack(matterBlock, 1, i), "DD", "DD", 'D', new ItemStack(matter, 1, i));
		}

		//Matter Furnaces
		GameRegistry.addRecipe(new ItemStack(dmFurnaceOff), "DDD", "DFD", "DDD", 'D', new ItemStack(matterBlock, 1, 0), 'F', Blocks.furnace);
		GameRegistry.addRecipe(new ItemStack(rmFurnaceOff), "XRX", "RFR", 'R', new ItemStack(matterBlock, 1, 1), 'F', dmFurnaceOff);

		// DM Pedestal
		GameRegistry.addRecipe(new ItemStack(dmPedestal), "RDR", "RDR", "DDD", 'R', new ItemStack(matter, 1, 1), 'D', new ItemStack(matterBlock, 1, 0));

		//Collectors
		GameRegistry.addRecipe(new ItemStack(collectorBlocks[0]), "GTG", "GDG", "GFG", 'G', Blocks.glowstone, 'F', Blocks.furnace, 'D', diamondBlockReplacement, 'T', Blocks.glass);
		for (int i = 1; i < collectorBlocks.length; i++) {
			ItemStack upgradeItem = new ItemStack(matter, 1, Math.min(i - 1, 13));
			GameRegistry.addRecipe(new ItemStack(collectorBlocks[i]), "GMG", "GCG", "GGG", 'G', Blocks.glowstone, 'C', collectorBlocks[i - 1], 'M', upgradeItem);
		}

		//Power Flowers
		for (int i = 1; i < powerFlowerBlocks.length; i++) {
			ItemStack upgradeItem = new ItemStack(matter, 1, Math.min(i - 1, 13));
			GameRegistry.addRecipe(new ItemStack(powerFlowerBlocks[i]), "GMG", "GCG", "GGG", 'G', Blocks.glowstone, 'C', powerFlowerBlocks[i - 1], 'M', upgradeItem);
		}

		//AM Relays
		GameRegistry.addRecipe(new ItemStack(relayBlocks[0]), "OSO", "ODO", "OOO", 'S', Blocks.glass, 'D', Blocks.diamond_block, 'O', Blocks.obsidian);
		for (int i = 1; i < relayBlocks.length; i++) {
			ItemStack upgradeItem = new ItemStack(matter, 1, Math.min(i - 1, 13));
			GameRegistry.addRecipe(new ItemStack(relayBlocks[i]), "OMO", "OAO", "OOO", 'A', relayBlocks[i - 1], 'M', upgradeItem, 'O', Blocks.obsidian);
		}

		//DM Tools
		GameRegistry.addRecipe(new ItemStack(dmPick), "MMM", "XDX", "XDX", 'D', Items.diamond, 'M', matter);
		GameRegistry.addRecipe(new ItemStack(dmAxe), "MMX", "MDX", "XDX", 'D', Items.diamond, 'M', matter);
		GameRegistry.addRecipe(new ItemStack(dmShovel), "XMX", "XDX", "XDX", 'D', Items.diamond, 'M', matter);
		GameRegistry.addRecipe(new ItemStack(dmSword), "XMX", "XMX", "XDX", 'D', Items.diamond, 'M', matter);
		GameRegistry.addRecipe(new ItemStack(dmHoe), "MMX", "XDX", "XDX", 'D', Items.diamond, 'M', matter);
		GameRegistry.addRecipe(new ItemStack(dmShears), "XM", "DX", 'D', Items.diamond, 'M', matter);
		GameRegistry.addRecipe(new ItemStack(dmHammer), "MDM", "XDX", "XDX", 'D', Items.diamond, 'M', matter);

		//RM Tools
		GameRegistry.addRecipe(new ItemStack(rmPick), "RRR", "XPX", "XMX", 'R', new ItemStack(matter, 1, 1), 'P', dmPick, 'M', matter);
		GameRegistry.addRecipe(new ItemStack(rmAxe), "RRX", "RAX", "XMX", 'R', new ItemStack(matter, 1, 1), 'A', dmAxe, 'M', matter);
		GameRegistry.addRecipe(new ItemStack(rmShovel), "XRX", "XSX", "XMX", 'R', new ItemStack(matter, 1, 1), 'S', dmShovel, 'M', matter);
		GameRegistry.addRecipe(new ItemStack(rmSword), "XRX", "XRX", "XSX", 'R', new ItemStack(matter, 1, 1), 'S', dmSword);
		GameRegistry.addRecipe(new ItemStack(rmHoe), "RRX", "XHX", "XMX", 'R', new ItemStack(matter, 1, 1), 'H', dmHoe, 'M', matter);
		GameRegistry.addRecipe(new ItemStack(rmShears), "XR", "SX", 'R', new ItemStack(matter, 1, 1), 'S', dmShears);
		GameRegistry.addRecipe(new ItemStack(rmHammer), "RMR", "XHX", "XMX", 'R', new ItemStack(matter, 1, 1), 'H', dmHammer, 'M', matter);
		GameRegistry.addRecipe(new ItemStack(rmKatar), "123", "4RR", "RRR", '1', rmShears, '2', rmAxe, '3', rmSword, '4', rmHoe, 'R', new ItemStack(matter, 1, 1));
		GameRegistry.addRecipe(new ItemStack(rmStar), "123", "RRR", "RRR", '1', rmHammer, '2', rmPick, '3', rmShovel, 'R', new ItemStack(matter, 1, 1));

		//Armor
		GameRegistry.addRecipe(new ItemStack(dmHelmet), "MMM", "MXM", 'M', matter);
		GameRegistry.addRecipe(new ItemStack(dmChest), "MXM", "MMM", "MMM", 'M', matter);
		GameRegistry.addRecipe(new ItemStack(dmLegs), "MMM", "MXM", "MXM", 'M', matter);
		GameRegistry.addRecipe(new ItemStack(dmFeet), "MXM", "MXM", 'M', matter);

		GameRegistry.addRecipe(new ItemStack(rmHelmet), "MMM", "MDM", 'M', new ItemStack(matter, 1, 1), 'D', dmHelmet);
		GameRegistry.addRecipe(new ItemStack(rmChest), "MDM", "MMM", "MMM", 'M', new ItemStack(matter, 1, 1), 'D', dmChest);
		GameRegistry.addRecipe(new ItemStack(rmLegs), "MMM", "MDM", "MXM", 'M', new ItemStack(matter, 1, 1), 'D', dmLegs);
		GameRegistry.addRecipe(new ItemStack(rmFeet), "MDM", "MXM", 'M', new ItemStack(matter, 1, 1), 'D', dmFeet);

		//Rings
		GameRegistry.addRecipe(new ItemStack(ironBand), "III", "ILI", "III", 'I', Items.iron_ingot, 'L', Items.lava_bucket);
		GameRegistry.addRecipe(new ItemStack(ironBand), "III", "ILI", "III", 'I', Items.iron_ingot, 'L', volcanite);
		GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(harvestGod), "SFS", "DID", "SFS", 'I', ironBand, 'S', "treeSapling", 'F', Blocks.red_flower, 'F', Blocks.red_flower, 'D', matter));
		GameRegistry.addRecipe(new ItemStack(swrg), "DFD", "FIF", "DFD", 'I', ironBand, 'F', Items.feather, 'D', matter);
		GameRegistry.addRecipe(new ItemStack(ignition), "FMF", "DID", "FMF", 'I', ironBand, 'F', new ItemStack(Items.flint_and_steel, 1, OreDictionary.WILDCARD_VALUE), 'D', matter, 'M', new ItemStack(fuels, 1, 1));
		GameRegistry.addRecipe(new ItemStack(bodyStone), "SSS", "RLR", "SSS", 'R', new ItemStack(matter, 1, 1), 'S', Items.sugar, 'L', new ItemStack(Items.dye, 1, 4));
		GameRegistry.addRecipe(new ItemStack(soulStone), "GGG", "RLR", "GGG", 'R', new ItemStack(matter, 1, 1), 'G', Items.glowstone_dust, 'L', new ItemStack(Items.dye, 1, 4));
		GameRegistry.addRecipe(new ItemStack(mindStone), "BBB", "RLR", "BBB", 'R', new ItemStack(matter, 1, 1), 'B', Items.book, 'L', new ItemStack(Items.dye, 1, 4));
		GameRegistry.addRecipe(new ItemStack(blackHole), "SSS", "DID", "SSS", 'I', ironBand, 'S', Items.string, 'D', matter);
		GameRegistry.addRecipe(new ItemStack(everTide), "WWW", "DDD", "WWW", 'W', Items.water_bucket, 'D', matter);
		GameRegistry.addRecipe(new ItemStack(volcanite), "LLL", "DDD", "LLL", 'L', Items.lava_bucket, 'D', matter);
		GameRegistry.addRecipe(new ItemStack(eternalDensity), "DOD", "MDM", "DOD", 'D', Items.diamond, 'O', Blocks.obsidian, 'M', matter);
		GameRegistry.addRecipe(new ItemStack(zero), "SBS", "MIM", "SBS", 'S', Blocks.snow, 'B', Items.snowball, 'M', matter, 'I', ironBand);
		GameRegistry.addShapelessRecipe(new ItemStack(voidRing), blackHole, eternalDensity, new ItemStack(matter, 1, 1), new ItemStack(matter, 1, 1));
		GameRegistry.addRecipe(new ItemStack(arcana), "ZIH", "SMM", "MMM", 'Z', zero, 'I', ignition, 'H', harvestGod, 'S', swrg, 'M', new ItemStack(matter, 1, 1));
		GameRegistry.addRecipe(new ItemStack(angelSmite), "BFB", "MIM", "BFB", 'B', Items.bow, 'F', Items.feather, 'M', matter, 'I', ironBand);

		//Watch of flowing time
		GameRegistry.addRecipe(new ItemStack(timeWatch), "DOD", "GCG", "DOD", 'D', matter, 'O', Blocks.obsidian, 'G', Blocks.glowstone, 'C', Items.clock);
		GameRegistry.addRecipe(new ItemStack(timeWatch), "DGD", "OCO", "DGD", 'D', matter, 'O', Blocks.obsidian, 'G', Blocks.glowstone, 'C', Items.clock);

		//Divining rods
		GameRegistry.addRecipe(new ItemStack(dRod1), "DDD", "DSD", "DDD", 'D', covalence, 'S', Items.stick);
		GameRegistry.addRecipe(new ItemStack(dRod2), "DDD", "DSD", "DDD", 'D', new ItemStack(covalence, 1, 1), 'S', dRod1);
		GameRegistry.addRecipe(new ItemStack(dRod3), "DDD", "DSD", "DDD", 'D', new ItemStack(covalence, 1, 2), 'S', dRod2);

		//Explosive items
		GameRegistry.addRecipe(new ItemStack(dCatalyst), "NMN", "MFM", "NMN", 'N', novaCatalyst, 'M', new ItemStack(fuels, 1, 1), 'F', new ItemStack(Items.flint_and_steel, 1, OreDictionary.WILDCARD_VALUE));
		GameRegistry.addRecipe(new ItemStack(hyperLens), "DDD", "MNM", "DDD", 'N', novaCatalyst, 'M', matter, 'D', Items.diamond);
		GameRegistry.addRecipe(new ItemStack(cataliticLens), "MMM", "HMD", "MMM", 'M', matter, 'H', hyperLens, 'D', dCatalyst);
		GameRegistry.addRecipe(new ItemStack(cataliticLens), "MMM", "DMH", "MMM", 'M', matter, 'H', hyperLens, 'D', dCatalyst);

		//Fuel Blocks
		GameRegistry.addRecipe(new ItemStack(fuelBlock, 1, 0), "FFF", "FFF", "FFF", 'F', fuels);
		GameRegistry.addRecipe(new ItemStack(fuelBlock, 1, 1), "FFF", "FFF", "FFF", 'F', new ItemStack(fuels, 1, 1));
		GameRegistry.addRecipe(new ItemStack(fuelBlock, 1, 2), "FFF", "FFF", "FFF", 'F', new ItemStack(fuels, 1, 2));

		//Tome
		if (ProjectEConfig.craftableTome)
		{
			GameRegistry.addRecipe(new ItemStack(tome), "HML", "KBK", "LMH", 'L', new ItemStack(covalence, 1, 0), 'M', new ItemStack(covalence, 1, 1), 'H', new ItemStack(covalence, 1, 2), 'B', Items.book, 'K', new ItemStack(kleinStars, 1, 5));
		}

		//TransmutationTablet
		GameRegistry.addRecipe(new ItemStack(transmutationTablet), "DSD", "STS", "DSD", 'D', new ItemStack(matterBlock, 1, 0), 'S', Blocks.stone, 'T', transmuteStone);

		//Mercurial Eye
		GameRegistry.addRecipe(new ItemStack(mercEye), "OBO", "BRB", "BDB", 'O', Blocks.obsidian, 'B', Blocks.brick_block, 'R', new ItemStack(matter, 1, 1), 'D', Items.diamond);

		//Shapeless Recipes
		//Philos Stone exchanges
		GameRegistry.addShapelessRecipe(new ItemStack(Items.ender_pearl), philosStone, Items.iron_ingot, Items.iron_ingot, Items.iron_ingot, Items.iron_ingot);
		GameRegistry.addShapelessRecipe(new ItemStack(Items.iron_ingot, 8), philosStone, Items.gold_ingot);
		GameRegistry.addShapelessRecipe(new ItemStack(Items.gold_ingot), philosStone, Items.iron_ingot, Items.iron_ingot, Items.iron_ingot, Items.iron_ingot, Items.iron_ingot, Items.iron_ingot, Items.iron_ingot, Items.iron_ingot);
		GameRegistry.addShapelessRecipe(new ItemStack(Items.diamond), philosStone, Items.gold_ingot, Items.gold_ingot, Items.gold_ingot, Items.gold_ingot);
		GameRegistry.addShapelessRecipe(new ItemStack(Items.gold_ingot, 4), philosStone, Items.diamond);
		GameRegistry.addShapelessRecipe(new ItemStack(Items.emerald), philosStone, Items.diamond, Items.diamond);
		GameRegistry.addShapelessRecipe(new ItemStack(Items.diamond, 2), philosStone, Items.emerald);
		GameRegistry.addShapelessRecipe(new ItemStack(fuels, 1, 0), philosStone, Items.coal, Items.coal, Items.coal, Items.coal);
		GameRegistry.addShapelessRecipe(new ItemStack(Items.coal, 4), philosStone, new ItemStack(fuels, 1, 0));
		GameRegistry.addShapelessRecipe(new ItemStack(fuels, 1, 1), philosStone, new ItemStack(fuels, 1, 0), new ItemStack(fuels, 1, 0), new ItemStack(fuels, 1, 0), new ItemStack(fuels, 1, 0));
		GameRegistry.addShapelessRecipe(new ItemStack(fuels, 4, 0), philosStone, new ItemStack(fuels, 1, 1));
		GameRegistry.addShapelessRecipe(new ItemStack(fuels, 1, 2), philosStone, new ItemStack(fuels, 1, 1), new ItemStack(fuels, 1, 1), new ItemStack(fuels, 1, 1), new ItemStack(fuels, 1, 1));
		GameRegistry.addShapelessRecipe(new ItemStack(fuels, 4, 1), philosStone, new ItemStack(fuels, 1, 2));

		//Covalence dust
		GameRegistry.addShapelessRecipe(new ItemStack(covalence, 40, 0), Blocks.cobblestone, Blocks.cobblestone, Blocks.cobblestone, Blocks.cobblestone, Blocks.cobblestone, Blocks.cobblestone, Blocks.cobblestone, Blocks.cobblestone, new ItemStack(Items.coal, 1, 1));
		GameRegistry.addShapelessRecipe(new ItemStack(covalence, 40, 1), Items.iron_ingot, Items.redstone);
		GameRegistry.addShapelessRecipe(new ItemStack(covalence, 40, 2), Items.diamond, Items.coal);

		//Klein Stars
		for (int i = 1; i < 6; i++)
		{
			ItemStack input = new ItemStack(kleinStars, 1, i - 1);
			ItemStack output = new ItemStack(kleinStars, 1, i);
			GameRegistry.addRecipe(new RecipeShapelessHidden(output, input, input, input, input));

			input = new ItemStack(magnumStar, 1, i - 1);
			output = new ItemStack(magnumStar, 1, i);
			GameRegistry.addRecipe(new RecipeShapelessHidden(output, input, input, input, input));

			input = new ItemStack(colossalStar, 1, i - 1);
			output = new ItemStack(colossalStar, 1, i);
			GameRegistry.addRecipe(new RecipeShapelessHidden(output, input, input, input, input));
		}

		//Other items
		GameRegistry.addShapelessRecipe(new ItemStack(novaCatalyst, 2), Blocks.tnt, new ItemStack(fuels, 1, 1));
		GameRegistry.addShapelessRecipe(new ItemStack(novaCataclysm, 2), novaCatalyst, new ItemStack(fuels, 1, 2));
		GameRegistry.addShapelessRecipe(new ItemStack(lifeStone), bodyStone, soulStone);
		GameRegistry.addShapelessRecipe(new ItemStack(Blocks.ice), new ItemStack(zero, 1, OreDictionary.WILDCARD_VALUE), Items.water_bucket);
		GameRegistry.addShapelessRecipe(new ItemStack(Items.lava_bucket), volcanite, Items.bucket, Items.redstone);

		GameRegistry.addShapelessRecipe(new ItemStack(gemHelmet), rmHelmet, new ItemStack(kleinStars, 1, 5), everTide, soulStone);
		GameRegistry.addShapelessRecipe(new ItemStack(gemChest), rmChest, new ItemStack(kleinStars, 1, 5), volcanite, bodyStone);
		GameRegistry.addShapelessRecipe(new ItemStack(gemLegs), rmLegs, new ItemStack(kleinStars, 1, 5), blackHole, timeWatch);
		GameRegistry.addShapelessRecipe(new ItemStack(gemFeet), rmFeet, new ItemStack(kleinStars, 1, 5), swrg, swrg);

		GameRegistry.addShapelessRecipe(new ItemStack(matter, 4, 0), matterBlock);
		GameRegistry.addShapelessRecipe(new ItemStack(matter, 4, 1), new ItemStack(matterBlock, 1, 1));

		GameRegistry.addShapelessRecipe(new ItemStack(fuels, 9, 0), new ItemStack(fuelBlock, 1, 0));
		GameRegistry.addShapelessRecipe(new ItemStack(fuels, 9, 1), new ItemStack(fuelBlock, 1, 1));
		GameRegistry.addShapelessRecipe(new ItemStack(fuels, 9, 2), new ItemStack(fuelBlock, 1, 2));

		// need a recipe for each arcana mode, there's probably a better way to do this
		GameRegistry.addShapelessRecipe(new ItemStack(Blocks.ice), new ItemStack(arcana, 1, 0), Items.water_bucket);
		GameRegistry.addShapelessRecipe(new ItemStack(Blocks.ice), new ItemStack(arcana, 1, 1), Items.water_bucket);
		GameRegistry.addShapelessRecipe(new ItemStack(Blocks.ice), new ItemStack(arcana, 1, 2), Items.water_bucket);
		GameRegistry.addShapelessRecipe(new ItemStack(Blocks.ice), new ItemStack(arcana, 1, 3), Items.water_bucket);

		GameRegistry.addShapelessRecipe(new ItemStack(Blocks.grass), new ItemStack(arcana, 1, 0), Blocks.dirt);
		GameRegistry.addShapelessRecipe(new ItemStack(Blocks.grass), new ItemStack(arcana, 1, 1), Blocks.dirt);
		GameRegistry.addShapelessRecipe(new ItemStack(Blocks.grass), new ItemStack(arcana, 1, 2), Blocks.dirt);
		GameRegistry.addShapelessRecipe(new ItemStack(Blocks.grass), new ItemStack(arcana, 1, 3), Blocks.dirt);

		//Custom Recipe managment
		for(int i = 1; i <= 15; i++){
			GameRegistry.addRecipe(new RecipeAlchemyBag(new ItemStack(alchBag, 1, 15-i), new ItemStack(alchBag, 1, 0), new ItemStack(Items.dye, 1, i)));
			GameRegistry.addRecipe(new RecipeAlchemyBag(new ItemStack(alchBag, 1, 0), new ItemStack(alchBag, 1, i), new ItemStack(Items.dye, 1, 15)));
		}
		GameRegistry.addRecipe(new RecipesCovalenceRepair());
		RecipeSorter.register("Alchemical Bags Recipes", RecipeAlchemyBag.class, Category.SHAPELESS, "before:minecraft:shaped");
		RecipeSorter.register("Covalence Repair Recipes", RecipesCovalenceRepair.class, Category.SHAPELESS, "before:minecraft:shaped");
		RecipeSorter.register("", RecipeShapedKleinStar.class, Category.SHAPED, "after:minecraft:shaped before:minecraft:shapeless");
		RecipeSorter.register("", RecipeShapelessHidden.class, Category.SHAPELESS, "before:minecraft:shaped");

		//Fuel Values
		GameRegistry.registerFuelHandler(new FuelHandler());
	}

	/**
	 * Philosopher's stone smelting recipes, EE3 style
	 */
	public static void registerPhiloStoneSmelting()
	{

		for (Entry<ItemStack, ItemStack> entry : (((HashMap<ItemStack, ItemStack>) FurnaceRecipes.smelting().getSmeltingList()).entrySet()))
		{
			if (entry.getKey() == null || entry.getValue() == null)
			{
				continue;
			}

			ItemStack input = entry.getKey();
			ItemStack output = entry.getValue().copy();
			output.stackSize *= 7;

			GameRegistry.addRecipe(new RecipeShapelessHidden(output, philosStone, input, input, input, input, input, input, input, new ItemStack(Items.coal, 1, OreDictionary.WILDCARD_VALUE)));

		}
		RecipeSorter.register("Philosopher's Smelting Recipes", RecipeShapelessHidden.class, Category.SHAPELESS, "before:minecraft:shaped");
	}
}
