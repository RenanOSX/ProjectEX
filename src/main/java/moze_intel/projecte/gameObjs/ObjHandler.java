package moze_intel.projecte.gameObjs;

import cpw.mods.fml.common.registry.EntityRegistry;
import cpw.mods.fml.common.registry.GameRegistry;
import moze_intel.projecte.PECore;
import moze_intel.projecte.config.ProjectEConfig;
import moze_intel.projecte.gameObjs.blocks.AlchemicalChest;
import moze_intel.projecte.gameObjs.blocks.Collector;
import moze_intel.projecte.gameObjs.blocks.Condenser;
import moze_intel.projecte.gameObjs.blocks.CondenserMK2;
import moze_intel.projecte.gameObjs.blocks.FuelBlock;
import moze_intel.projecte.gameObjs.blocks.InterdictionTorch;
import moze_intel.projecte.gameObjs.blocks.MatterBlock;
import moze_intel.projecte.gameObjs.blocks.MatterFurnace;
import moze_intel.projecte.gameObjs.blocks.NovaCataclysm;
import moze_intel.projecte.gameObjs.blocks.NovaCatalyst;
import moze_intel.projecte.gameObjs.blocks.Pedestal;
import moze_intel.projecte.gameObjs.blocks.Relay;
import moze_intel.projecte.gameObjs.blocks.TransmutationStone;
import moze_intel.projecte.gameObjs.customRecipes.RecipeAlchemyBag;
import moze_intel.projecte.gameObjs.customRecipes.RecipeShapedKleinStar;
import moze_intel.projecte.gameObjs.customRecipes.RecipeShapedColossalStar;
import moze_intel.projecte.gameObjs.customRecipes.RecipeShapedMagnumStar;
import moze_intel.projecte.gameObjs.customRecipes.RecipeShapelessHidden;
import moze_intel.projecte.gameObjs.customRecipes.RecipesCovalenceRepair;
import moze_intel.projecte.gameObjs.entity.EntityFireProjectile;
import moze_intel.projecte.gameObjs.entity.EntityHomingArrow;
import moze_intel.projecte.gameObjs.entity.EntityLavaProjectile;
import moze_intel.projecte.gameObjs.entity.EntityLensProjectile;
import moze_intel.projecte.gameObjs.entity.EntityLootBall;
import moze_intel.projecte.gameObjs.entity.EntityMobRandomizer;
import moze_intel.projecte.gameObjs.entity.EntityNovaCataclysmPrimed;
import moze_intel.projecte.gameObjs.entity.EntityNovaCatalystPrimed;
import moze_intel.projecte.gameObjs.entity.EntitySWRGProjectile;
import moze_intel.projecte.gameObjs.entity.EntityWaterProjectile;
import moze_intel.projecte.gameObjs.items.AlchemicalBag;
import moze_intel.projecte.gameObjs.items.AlchemicalFuel;
import moze_intel.projecte.gameObjs.items.CataliticLens;
import moze_intel.projecte.gameObjs.items.CovalenceDust;
import moze_intel.projecte.gameObjs.items.DestructionCatalyst;
import moze_intel.projecte.gameObjs.items.DiviningRodHigh;
import moze_intel.projecte.gameObjs.items.DiviningRodLow;
import moze_intel.projecte.gameObjs.items.DiviningRodMedium;
import moze_intel.projecte.gameObjs.items.EvertideAmulet;
import moze_intel.projecte.gameObjs.items.GemEternalDensity;
import moze_intel.projecte.gameObjs.items.HyperkineticLens;
import moze_intel.projecte.gameObjs.items.KleinStar;
import moze_intel.projecte.gameObjs.items.ColossalStar;
import moze_intel.projecte.gameObjs.items.MagnumStar;
import moze_intel.projecte.gameObjs.items.Matter;
import moze_intel.projecte.gameObjs.items.MercurialEye;
import moze_intel.projecte.manual.PEManual;
import moze_intel.projecte.gameObjs.items.PhilosophersStone;
import moze_intel.projecte.gameObjs.items.RepairTalisman;
import moze_intel.projecte.gameObjs.items.TimeWatch;
import moze_intel.projecte.gameObjs.items.Tome;
import moze_intel.projecte.gameObjs.items.TransmutationTablet;
import moze_intel.projecte.gameObjs.items.VolcaniteAmulet;
import moze_intel.projecte.gameObjs.items.armor.DMArmor;
import moze_intel.projecte.gameObjs.items.armor.GemChest;
import moze_intel.projecte.gameObjs.items.armor.GemFeet;
import moze_intel.projecte.gameObjs.items.armor.GemHelmet;
import moze_intel.projecte.gameObjs.items.armor.GemLegs;
import moze_intel.projecte.gameObjs.items.armor.RMArmor;
import moze_intel.projecte.gameObjs.blocks.ItemAlchemyChestBlock;
import moze_intel.projecte.gameObjs.blocks.ItemCollectorBlock;
import moze_intel.projecte.gameObjs.blocks.ItemCondenserBlock;
import moze_intel.projecte.gameObjs.blocks.ItemDMFurnaceBlock;
import moze_intel.projecte.gameObjs.blocks.ItemFuelBlock;
import moze_intel.projecte.gameObjs.blocks.ItemMatterBlock;
import moze_intel.projecte.gameObjs.blocks.ItemPowerFlowerBlock;
import moze_intel.projecte.gameObjs.blocks.ItemRMFurnaceBlock;
import moze_intel.projecte.gameObjs.blocks.ItemRelayBlock;
import moze_intel.projecte.gameObjs.blocks.ItemTransmutationBlock;
import moze_intel.projecte.gameObjs.items.itemEntities.FireProjectile;
import moze_intel.projecte.gameObjs.items.itemEntities.LavaOrb;
import moze_intel.projecte.gameObjs.items.itemEntities.LensExplosive;
import moze_intel.projecte.gameObjs.items.itemEntities.LightningProjectile;
import moze_intel.projecte.gameObjs.items.itemEntities.LootBallItem;
import moze_intel.projecte.gameObjs.items.itemEntities.RandomizerProjectile;
import moze_intel.projecte.gameObjs.items.itemEntities.WaterOrb;
import moze_intel.projecte.gameObjs.items.rings.Arcana;
import moze_intel.projecte.gameObjs.items.rings.ArchangelSmite;
import moze_intel.projecte.gameObjs.items.rings.BlackHoleBand;
import moze_intel.projecte.gameObjs.items.rings.BodyStone;
import moze_intel.projecte.gameObjs.items.rings.HarvestGoddess;
import moze_intel.projecte.gameObjs.items.rings.Ignition;
import moze_intel.projecte.gameObjs.items.rings.IronBand;
import moze_intel.projecte.gameObjs.items.rings.LifeStone;
import moze_intel.projecte.gameObjs.items.rings.MindStone;
import moze_intel.projecte.gameObjs.items.rings.SWRG;
import moze_intel.projecte.gameObjs.items.rings.SoulStone;
import moze_intel.projecte.gameObjs.items.rings.VoidRing;
import moze_intel.projecte.gameObjs.items.rings.Zero;
import moze_intel.projecte.gameObjs.items.tools.DarkAxe;
import moze_intel.projecte.gameObjs.items.tools.DarkHammer;
import moze_intel.projecte.gameObjs.items.tools.DarkHoe;
import moze_intel.projecte.gameObjs.items.tools.DarkPick;
import moze_intel.projecte.gameObjs.items.tools.DarkShears;
import moze_intel.projecte.gameObjs.items.tools.DarkShovel;
import moze_intel.projecte.gameObjs.items.tools.DarkSword;
import moze_intel.projecte.gameObjs.items.tools.RedAxe;
import moze_intel.projecte.gameObjs.items.tools.RedHammer;
import moze_intel.projecte.gameObjs.items.tools.RedHoe;
import moze_intel.projecte.gameObjs.items.tools.RedKatar;
import moze_intel.projecte.gameObjs.items.tools.RedPick;
import moze_intel.projecte.gameObjs.items.tools.RedShears;
import moze_intel.projecte.gameObjs.items.tools.RedShovel;
import moze_intel.projecte.gameObjs.items.tools.RedStar;
import moze_intel.projecte.gameObjs.items.tools.RedSword;
import moze_intel.projecte.gameObjs.tiles.AlchChestTile;
import moze_intel.projecte.gameObjs.tiles.CollectorTile;
import moze_intel.projecte.gameObjs.tiles.CondenserMK2Tile;
import moze_intel.projecte.gameObjs.tiles.CondenserTile;
import moze_intel.projecte.gameObjs.tiles.DMFurnaceTile;
import moze_intel.projecte.gameObjs.tiles.DMPedestalTile;
import moze_intel.projecte.gameObjs.tiles.InterdictionTile;
import moze_intel.projecte.gameObjs.tiles.RMFurnaceTile;
import moze_intel.projecte.gameObjs.tiles.RelayTile;
import moze_intel.projecte.utils.Constants;
import moze_intel.projecte.gameObjs.items.armor.EnumArmorType;
import net.minecraft.block.Block;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.FurnaceRecipes;
import net.minecraftforge.oredict.OreDictionary;
import net.minecraftforge.oredict.RecipeSorter;
import net.minecraftforge.oredict.RecipeSorter.Category;
import net.minecraftforge.oredict.ShapedOreRecipe;
import moze_intel.projecte.gameObjs.tiles.PowerFlowerTile;
import moze_intel.projecte.gameObjs.blocks.PowerFlower;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map.Entry;

public class ObjHandler
{
	public static final CreativeTabs cTab = new CreativeTab();
	public static Block alchChest = new AlchemicalChest();
	public static Block confuseTorch = new InterdictionTorch();
	public static Block transmuteStone = new TransmutationStone();
	public static Block condenser = new Condenser();
	public static Block condenserMk2 = new CondenserMK2();
	public static Block rmFurnaceOff = new MatterFurnace(false, true);
	public static Block rmFurnaceOn = new MatterFurnace(true, true);
	public static Block dmFurnaceOff = new MatterFurnace(false, false);
	public static Block dmFurnaceOn = new MatterFurnace(true, false);
	public static Block dmPedestal = new Pedestal();
	public static Block matterBlock = new MatterBlock();
	public static Block fuelBlock = new FuelBlock();
	public static Block[] collectorBlocks;
	public static Block[] relayBlocks;
	public static Block[] powerFlowerBlocks;
	public static Block novaCatalyst = new NovaCatalyst();
	public static Block novaCataclysm = new NovaCataclysm();

	static {
		collectorBlocks = new Block[Constants.COLLECTOR_MK_MAX.length];
		for (int i = 0; i < collectorBlocks.length; i++) {
			collectorBlocks[i] = new Collector(i + 1);
		}
		
		relayBlocks = new Block[Constants.RELAY_MK_MAX.length];
		for (int i = 0; i < relayBlocks.length; i++) {
			relayBlocks[i] = new Relay(i + 1);
		}

		powerFlowerBlocks = new Block[15];
		for (int i = 0; i < powerFlowerBlocks.length; i++) {
			powerFlowerBlocks[i] = new PowerFlower(i + 1);
		}
	}

	public static Item philosStone = new PhilosophersStone();
	public static Item alchBag = new AlchemicalBag();
	public static Item repairTalisman = new RepairTalisman();
	public static Item kleinStars = new KleinStar();
	public static Item magnumStar = new MagnumStar();
	public static Item colossalStar = new ColossalStar();
	public static Item fuels = new AlchemicalFuel();
	public static Item covalence = new CovalenceDust();
	public static Item matter = new Matter();

	public static Item dmPick = new DarkPick();
	public static Item dmAxe = new DarkAxe();
	public static Item dmShovel = new DarkShovel();
	public static Item dmSword = new DarkSword();
	public static Item dmHoe = new DarkHoe();
	public static Item dmShears = new DarkShears();
	public static Item dmHammer = new DarkHammer();

	public static Item rmPick = new RedPick();
	public static Item rmAxe = new RedAxe();
	public static Item rmShovel = new RedShovel();
	public static Item rmSword = new RedSword();
	public static Item rmHoe = new RedHoe();
	public static Item rmShears = new RedShears();
	public static Item rmHammer = new RedHammer();
	public static Item rmKatar = new RedKatar();
	public static Item rmStar = new RedStar();

	public static Item dmHelmet = new DMArmor(EnumArmorType.HEAD);
	public static Item dmChest = new DMArmor(EnumArmorType.CHEST);
	public static Item dmLegs = new DMArmor(EnumArmorType.LEGS);
	public static Item dmFeet = new DMArmor(EnumArmorType.FEET);

	public static Item rmHelmet = new RMArmor(EnumArmorType.HEAD);
	public static Item rmChest = new RMArmor(EnumArmorType.CHEST);
	public static Item rmLegs = new RMArmor(EnumArmorType.LEGS);
	public static Item rmFeet = new RMArmor(EnumArmorType.FEET);

	public static Item gemHelmet = new GemHelmet();
	public static Item gemChest = new GemChest();
	public static Item gemLegs = new GemLegs();
	public static Item gemFeet = new GemFeet();

	public static Item ironBand = new IronBand();
	public static Item blackHole = new BlackHoleBand();
	public static Item angelSmite = new ArchangelSmite();
	public static Item harvestGod = new HarvestGoddess();
	public static Item ignition = new Ignition();
	public static Item zero = new Zero();
	public static Item swrg = new SWRG();
	public static Item timeWatch = new TimeWatch();
	public static Item everTide = new EvertideAmulet();
	public static Item volcanite = new VolcaniteAmulet();
	public static Item eternalDensity = new GemEternalDensity();
	public static Item dRod1 = new DiviningRodLow();
	public static Item dRod2 = new DiviningRodMedium();
	public static Item dRod3 = new DiviningRodHigh();
	public static Item mercEye = new MercurialEye();
	public static Item voidRing = new VoidRing();
	public static Item arcana = new Arcana();

	public static Item dCatalyst = new DestructionCatalyst();
	public static Item hyperLens = new HyperkineticLens();
	public static Item cataliticLens = new CataliticLens();

	public static Item bodyStone = new BodyStone();
	public static Item soulStone = new SoulStone();
	public static Item mindStone = new MindStone();
	public static Item lifeStone = new LifeStone();

	public static Item tome = new Tome();

	public static Item waterOrb = new WaterOrb();
	public static Item lavaOrb = new LavaOrb();
	public static Item lootBall = new LootBallItem();
	public static Item mobRandomizer = new RandomizerProjectile();
	public static Item lensExplosive = new LensExplosive();
	public static Item fireProjectile = new FireProjectile();
	public static Item windProjectile = new LightningProjectile();
	public static Item transmutationTablet = new TransmutationTablet();
	public static Item manual = new PEManual();

	public static void register()
	{
		// Blocks without ItemBlock
		GameRegistry.registerBlock(confuseTorch, "interdiction_torch");
		GameRegistry.registerBlock(condenserMk2, "condenser_mk2");
		GameRegistry.registerBlock(rmFurnaceOn, "rm_furnace_lit");
		GameRegistry.registerBlock(dmFurnaceOn, "dm_furnace_lit");
		GameRegistry.registerBlock(dmPedestal, "dm_pedestal");
		GameRegistry.registerBlock(novaCatalyst, "nova_catalyst");
		GameRegistry.registerBlock(novaCataclysm, "nova_cataclysm");

		// Blocks with ItemBlock
		GameRegistry.registerBlock(alchChest, ItemAlchemyChestBlock.class, "alchemical_chest");
		GameRegistry.registerBlock(transmuteStone, ItemTransmutationBlock.class, "transmutation_table");
		GameRegistry.registerBlock(condenser, ItemCondenserBlock.class, "condenser_mk1");
		GameRegistry.registerBlock(rmFurnaceOff, ItemRMFurnaceBlock.class, "rm_furnace");
		GameRegistry.registerBlock(dmFurnaceOff, ItemDMFurnaceBlock.class, "dm_furnace");
		GameRegistry.registerBlock(matterBlock, ItemMatterBlock.class, "matter_block");
		GameRegistry.registerBlock(fuelBlock, ItemFuelBlock.class, "fuel_block");
		
		for (int i = 0; i < collectorBlocks.length; i++) {
			GameRegistry.registerBlock(collectorBlocks[i], ItemCollectorBlock.class, "collector_mk" + (i + 1));
		}
		for (int i = 0; i < relayBlocks.length; i++) {
			GameRegistry.registerBlock(relayBlocks[i], ItemRelayBlock.class, "relay_mk" + (i + 1));
		}

		for (int i = 0; i < powerFlowerBlocks.length; i++) {
			GameRegistry.registerBlock(powerFlowerBlocks[i], ItemPowerFlowerBlock.class, "power_flower_mk" + (i + 1));
		}

		// Power Flower TileEntity
		for (int i = 1; i <= 15; i++) {
			GameRegistry.registerTileEntityWithAlternatives(PowerFlowerTile.class, "PowerFlower_" + i + "Tile", "Power Flower " + i + " Tile");
		}

		//Items
		GameRegistry.registerItem(philosStone, philosStone.getUnlocalizedName());
		GameRegistry.registerItem(alchBag, alchBag.getUnlocalizedName());
		GameRegistry.registerItem(repairTalisman, repairTalisman.getUnlocalizedName());
		GameRegistry.registerItem(kleinStars, kleinStars.getUnlocalizedName());
		GameRegistry.registerItem(colossalStar, colossalStar.getUnlocalizedName());
		GameRegistry.registerItem(magnumStar, magnumStar.getUnlocalizedName());
		GameRegistry.registerItem(fuels, fuels.getUnlocalizedName());
		GameRegistry.registerItem(covalence, covalence.getUnlocalizedName());
		GameRegistry.registerItem(matter, matter.getUnlocalizedName());

		GameRegistry.registerItem(dmPick, dmPick.getUnlocalizedName());
		GameRegistry.registerItem(dmAxe, dmAxe.getUnlocalizedName());
		GameRegistry.registerItem(dmShovel, dmShovel.getUnlocalizedName());
		GameRegistry.registerItem(dmSword, dmSword.getUnlocalizedName());
		GameRegistry.registerItem(dmHoe, dmHoe.getUnlocalizedName());
		GameRegistry.registerItem(dmShears, dmShears.getUnlocalizedName());
		GameRegistry.registerItem(dmHammer, dmHammer.getUnlocalizedName());

		GameRegistry.registerItem(rmPick, rmPick.getUnlocalizedName());
		GameRegistry.registerItem(rmAxe, rmAxe.getUnlocalizedName());
		GameRegistry.registerItem(rmShovel, rmShovel.getUnlocalizedName());
		GameRegistry.registerItem(rmSword, rmSword.getUnlocalizedName());
		GameRegistry.registerItem(rmHoe, rmHoe.getUnlocalizedName());
		GameRegistry.registerItem(rmShears, rmShears.getUnlocalizedName());
		GameRegistry.registerItem(rmHammer, rmHammer.getUnlocalizedName());
		GameRegistry.registerItem(rmKatar, rmKatar.getUnlocalizedName());
		GameRegistry.registerItem(rmStar, rmStar.getUnlocalizedName());

		GameRegistry.registerItem(dmHelmet, dmHelmet.getUnlocalizedName());
		GameRegistry.registerItem(dmChest, dmChest.getUnlocalizedName());
		GameRegistry.registerItem(dmLegs, dmLegs.getUnlocalizedName());
		GameRegistry.registerItem(dmFeet, dmFeet.getUnlocalizedName());

		GameRegistry.registerItem(rmHelmet, rmHelmet.getUnlocalizedName());
		GameRegistry.registerItem(rmChest, rmChest.getUnlocalizedName());
		GameRegistry.registerItem(rmLegs, rmLegs.getUnlocalizedName());
		GameRegistry.registerItem(rmFeet, rmFeet.getUnlocalizedName());

		GameRegistry.registerItem(gemHelmet, gemHelmet.getUnlocalizedName());
		GameRegistry.registerItem(gemChest, gemChest.getUnlocalizedName());
		GameRegistry.registerItem(gemLegs, gemLegs.getUnlocalizedName());
		GameRegistry.registerItem(gemFeet, gemFeet.getUnlocalizedName());

		GameRegistry.registerItem(ironBand, ironBand.getUnlocalizedName());
		GameRegistry.registerItem(blackHole, blackHole.getUnlocalizedName());
		GameRegistry.registerItem(angelSmite, angelSmite.getUnlocalizedName());
		GameRegistry.registerItem(harvestGod, harvestGod.getUnlocalizedName());
		GameRegistry.registerItem(ignition, ignition.getUnlocalizedName());
		GameRegistry.registerItem(zero, zero.getUnlocalizedName());
		GameRegistry.registerItem(swrg, swrg.getUnlocalizedName());
		GameRegistry.registerItem(timeWatch, timeWatch.getUnlocalizedName());
		GameRegistry.registerItem(eternalDensity, eternalDensity.getUnlocalizedName());
		GameRegistry.registerItem(dRod1, dRod1.getUnlocalizedName());
		GameRegistry.registerItem(dRod2, dRod2.getUnlocalizedName());
		GameRegistry.registerItem(dRod3, dRod3.getUnlocalizedName());
		GameRegistry.registerItem(mercEye, mercEye.getUnlocalizedName());
		GameRegistry.registerItem(voidRing, voidRing.getUnlocalizedName());
		GameRegistry.registerItem(arcana, arcana.getUnlocalizedName());

		GameRegistry.registerItem(bodyStone, bodyStone.getUnlocalizedName());
		GameRegistry.registerItem(soulStone, soulStone.getUnlocalizedName());
		GameRegistry.registerItem(mindStone, mindStone.getUnlocalizedName());
		GameRegistry.registerItem(lifeStone, lifeStone.getUnlocalizedName());

		GameRegistry.registerItem(everTide, everTide.getUnlocalizedName());
		GameRegistry.registerItem(volcanite, volcanite.getUnlocalizedName());

		GameRegistry.registerItem(waterOrb, waterOrb.getUnlocalizedName());
		GameRegistry.registerItem(lavaOrb, lavaOrb.getUnlocalizedName());
		GameRegistry.registerItem(lootBall, lootBall.getUnlocalizedName());
		GameRegistry.registerItem(mobRandomizer, mobRandomizer.getUnlocalizedName());
		GameRegistry.registerItem(lensExplosive, lensExplosive.getUnlocalizedName());
		GameRegistry.registerItem(fireProjectile, fireProjectile.getUnlocalizedName());
		GameRegistry.registerItem(windProjectile, windProjectile.getUnlocalizedName());

		GameRegistry.registerItem(dCatalyst, dCatalyst.getUnlocalizedName());
		GameRegistry.registerItem(hyperLens, hyperLens.getUnlocalizedName());
		GameRegistry.registerItem(cataliticLens, cataliticLens.getUnlocalizedName());

		GameRegistry.registerItem(tome, tome.getUnlocalizedName());
		GameRegistry.registerItem(transmutationTablet, transmutationTablet.getUnlocalizedName());
		GameRegistry.registerItem(manual, manual.getUnlocalizedName());

		//Tile Entities
		GameRegistry.registerTileEntityWithAlternatives(AlchChestTile.class, "AlchChestTile", "Alchemical Chest Tile");
		GameRegistry.registerTileEntityWithAlternatives(InterdictionTile.class, "InterdictionTile", "Interdiction Torch Tile");
		GameRegistry.registerTileEntityWithAlternatives(CondenserTile.class, "CondenserTile", "Condenser Tile");
		GameRegistry.registerTileEntityWithAlternatives(CondenserMK2Tile.class, "CondenserMK2Tile", "Condenser MK2 Tile");
		GameRegistry.registerTileEntityWithAlternatives(RMFurnaceTile.class, "RMFurnaceTile", "RM Furnace Tile");
		GameRegistry.registerTileEntityWithAlternatives(DMFurnaceTile.class, "DMFurnaceTile", "DM Furnace Tile");
		
		List<String> collectorNames = new ArrayList<>();
		collectorNames.add("CollectorTile");
		for (int i = 1; i <= collectorBlocks.length; i++) {
			collectorNames.add("CollectorMK" + i + "Tile");
			collectorNames.add("Energy Collector MK" + i + " Tile");
		}
		GameRegistry.registerTileEntityWithAlternatives(CollectorTile.class, collectorNames.get(0), collectorNames.subList(1, collectorNames.size()).toArray(new String[0]));
		
		List<String> relayNames = new ArrayList<>();
		relayNames.add("RelayTile");
		for (int i = 1; i <= relayBlocks.length; i++) {
			relayNames.add("RelayMK" + i + "Tile");
			relayNames.add("AM Relay MK" + i + " Tile");
		}
		GameRegistry.registerTileEntityWithAlternatives(RelayTile.class, relayNames.get(0), relayNames.subList(1, relayNames.size()).toArray(new String[0]));
		
		List<String> powerFlowerNames = new ArrayList<>();
		powerFlowerNames.add("PowerFlowerTile");
		for (int i = 1; i <= 15; i++) {
			powerFlowerNames.add("PowerFlower_" + i + "Tile");
		}
		GameRegistry.registerTileEntityWithAlternatives(PowerFlowerTile.class, powerFlowerNames.get(0), powerFlowerNames.subList(1, powerFlowerNames.size()).toArray(new String[0]));

		GameRegistry.registerTileEntityWithAlternatives(DMPedestalTile.class, "DMPedestalTile", "DM Pedestal Tile");

		//Entities
		EntityRegistry.registerModEntity(EntityWaterProjectile.class, "WaterProjectile", 1, PECore.instance, 256, 10, true);
		EntityRegistry.registerModEntity(EntityLavaProjectile.class, "LavaProjectile", 2, PECore.instance, 256, 10, true);
		EntityRegistry.registerModEntity(EntityLootBall.class, "LootBall", 3, PECore.instance, 64, 10, true);
		EntityRegistry.registerModEntity(EntityMobRandomizer.class, "MobRandomizer", 4, PECore.instance, 256, 10, true);
		EntityRegistry.registerModEntity(EntityLensProjectile.class, "LensProjectile", 5, PECore.instance, 256, 10, true);
		EntityRegistry.registerModEntity(EntityNovaCatalystPrimed.class, "NovaCatalystPrimed", 6, PECore.instance, 256, 10, true);
		EntityRegistry.registerModEntity(EntityNovaCataclysmPrimed.class, "NovaCataclysmPrimed", 7, PECore.instance, 256, 10, true);
		EntityRegistry.registerModEntity(EntityHomingArrow.class, "HomingArrow", 8, PECore.instance, 256, 10, true);
		EntityRegistry.registerModEntity(EntityFireProjectile.class, "FireProjectile", 9, PECore.instance, 256, 10, true);
		EntityRegistry.registerModEntity(EntitySWRGProjectile.class, "LightningProjectile", 10, PECore.instance, 256, 10, true);
	}

	public static void addRecipes()
	{
		RecipeRegistrar.addRecipes();
	}

	public static void registerPhiloStoneSmelting()
	{
		RecipeRegistrar.registerPhiloStoneSmelting();
	}
}
