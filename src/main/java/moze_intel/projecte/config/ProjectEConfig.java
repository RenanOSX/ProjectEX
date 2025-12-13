package moze_intel.projecte.config;

import moze_intel.projecte.utils.Constants;
import moze_intel.projecte.utils.PELogger;
import net.minecraftforge.common.config.Configuration;

import java.io.File;

public final class ProjectEConfig 
{
	public static boolean showUnlocalizedNames;
	public static boolean showODNames;
	public static boolean enableDebugLog;
	public static boolean showEMCTooltip;
	public static boolean showStatTooltip;
	public static boolean showPedestalTooltip;
	public static boolean showPedestalTooltipInGUI;

	public static boolean enableTimeWatch;

	public static boolean craftableTome;
	public static boolean altCraftingMat;
	public static boolean useOldDamage;
	public static int archangelPedCooldown;
	public static int bodyPedCooldown;
	public static int evertidePedCooldown;
	public static int harvestPedCooldown;
	public static int ignitePedCooldown;
	public static int lifePedCooldown;
	public static int repairPedCooldown;
	public static int swrgPedCooldown;
	public static int soulPedCooldown;
	public static int volcanitePedCooldown;

	public static int zeroPedCooldown;
	public static int timePedBonus;
	public static float timePedMobSlowness;
	public static boolean interdictionMode;
	public static boolean pickaxeAoeVeinMining;
	public static boolean harvBandGrass;
	public static boolean useLootBalls;
	public static boolean pulsatingOverlay;
	public static boolean unsafeKeyBinds;
	public static boolean offensiveAbilities;
	public static float katarDeathAura;
	public static int projectileCooldown;
	public static boolean disableAllRadiusMining;
	public static int gemChestCooldown;

	public static void init(File configFile)
	{
		Configuration config = new Configuration(configFile);
		
		try
		{
			config.load();

			enableDebugLog = config.getBoolean("debugLogging", "misc", false, "Enable a more verbose debug logging");
			showUnlocalizedNames = config.getBoolean("unToolTips", "misc", false, "Show item unlocalized names in tooltips (useful for custom EMC registration)");
			showODNames = config.getBoolean("odToolTips", "misc", false, "Show item Ore Dictionary names in tooltips (useful for custom EMC registration)");
			showEMCTooltip = config.getBoolean("emcToolTips", "misc", true, "Show the EMC value as a tooltip on items and blocks");
			showStatTooltip = config.getBoolean("statToolTips", "misc", true, "Show stats as tooltips for various ProjectE blocks");
			showPedestalTooltip = config.getBoolean("pedestalToolTips", "misc", true, "Show DM pedestal functions in item tooltips");
			showPedestalTooltipInGUI = config.getBoolean("pedestalToolTipsInGUI", "misc", false, "Show pedestal function tooltips only in pedestal GUI");
			useLootBalls = config.getBoolean("useLootBalls", "misc", true, "Make loot balls for drops. Disabling this may potentially cause bad performance when large amounts of loot are spawned!");
			pulsatingOverlay = config.getBoolean("pulsatingOverlay", "misc", false, "The Philosopher's Stone overlay softly pulsates");
			unsafeKeyBinds = config.getBoolean("unsafeKeyBinds", "misc", false, "False requires your hand be empty for Gem Armor Offensive Abilities to be readied or triggered");
			projectileCooldown = config.getInt("projectileCooldown", "misc", 0, 0, Integer.MAX_VALUE, "A cooldown (in ticks) for firing projectiles");
			gemChestCooldown = config.getInt("gemChestCooldown", "misc", 0, 0, Integer.MAX_VALUE, "A cooldown (in ticks) for Gem Chestplate explosion");

			enableTimeWatch = config.getBoolean("enableTimeWatch", "items", true, "Enable Watch of Flowing Time");

			craftableTome = config.getBoolean("craftableTome", "difficulty", false, "The Tome of Knowledge can be crafted.");
			altCraftingMat = config.getBoolean("altCraftingMat", "difficulty", false, "If true some ProjectE items require a nether star instead of a diamond.");
			useOldDamage = config.getBoolean("useOldDamage", "difficulty", false, "If true the old damage amounts from ProjectE 1.4.7 and before will be used for weapons.");
			offensiveAbilities = config.getBoolean("offensiveAbilities", "difficulty", true, "Set to false to disable Gem Armor offensive abilities (helmet zap and chestplate explosion)");
			katarDeathAura = config.getFloat("katarDeathAura", "difficulty", 1000F, 0, Integer.MAX_VALUE, "Amount of damage Katar 'C' key deals");

			config.getCategory("pedestalcooldown").setComment("Cooldown for various items within the pedestal. A cooldown of -1 will disable the functionality.\n" +
					"A cooldown of 0 will cause the actions to happen every tick. Use caution as a very low value could cause TPS issues.");

			archangelPedCooldown = config.getInt("archangelPedCooldown", "pedestalcooldown", 40, -1, Integer.MAX_VALUE, "Delay between Archangel Smite shooting arrows while in the pedestal.");

			bodyPedCooldown = config.getInt("bodyPedCooldown", "pedestalcooldown", 10, -1, Integer.MAX_VALUE, "Delay between Body Stone healing 0.5 shanks while in the pedestal.");

			evertidePedCooldown = config.getInt("evertidePedCooldown", "pedestalcooldown", 20, -1, Integer.MAX_VALUE, "Delay between Evertide Amulet trying to start rain while in the pedestal.");

			harvestPedCooldown = config.getInt("harvestPedCooldown", "pedestalcooldown", 10, -1, Integer.MAX_VALUE, "Delay between Harvest Goddess trying to grow and harvest while in the pedestal.");

			ignitePedCooldown = config.getInt("ignitePedCooldown", "pedestalcooldown", 40, -1, Integer.MAX_VALUE, "Delay between Ignition Ring trying to light entities on fire while in the pedestal.");

			lifePedCooldown = config.getInt("lifePedCooldown", "pedestalcooldown", 5, -1, Integer.MAX_VALUE, "Delay between Life Stone healing both food and hunger by 0.5 shank/heart while in the pedestal.");

			repairPedCooldown = config.getInt("repairPedCooldown", "pedestalcooldown", 20, -1, Integer.MAX_VALUE, "Delay between Talisman of Repair trying to repair player items while in the pedestal.");

			swrgPedCooldown = config.getInt("swrgPedCooldown", "pedestalcooldown", 70, -1, Integer.MAX_VALUE, "Delay between SWRG trying to smite mobs while in the pedestal.");

			soulPedCooldown = config.getInt("soulPedCooldown", "pedestalcooldown", 10, -1, Integer.MAX_VALUE, "Delay between Soul Stone healing 0.5 hearts while in the pedestal.");

			volcanitePedCooldown = config.getInt("volcanitePedCooldown", "pedestalcooldown", 20, -1, Integer.MAX_VALUE, "Delay between Volcanite Amulet trying to stop rain while in the pedestal.");

			zeroPedCooldown = config.getInt("zeroPedCooldown", "pedestalcooldown", 40, -1, Integer.MAX_VALUE, "Delay between Zero Ring trying to extinguish entities and freezing ground while in the pedestal.");


			timePedBonus = config.getInt("timePedBonus", "effects", 18, 0, 256, "Bonus ticks given by the Watch of Flowing Time while in the pedestal. 0 = effectively no bonus.");
			timePedMobSlowness = config.getFloat("timePedMobSlowness", "effects", 0.10F, 0.0F, 1.0F, "Factor the Watch of Flowing Time slows down mobs by while in the pedestal. Set to 1.0 for no slowdown.");
			interdictionMode = config.getBoolean("interdictionMode", "effects", true, "If true the Interdiction Torch only affects hostile mobs. If false it affects all non blacklisted living entities.");

			pickaxeAoeVeinMining = config.getBoolean("pickaxeAoeVeinMining", "items", false, "Instead of vein mining the ore you right click with your Dark/Red Matter Pick/Star it vein mines all ores in an AOE around you like it did in ProjectE before version 1.4.4.");
			harvBandGrass = config.getBoolean("harvBandGrass", "items", false, "Allows the Harvest Goddess Band to passively grow tall grass, flowers, etc, on top of grass blocks.");
			disableAllRadiusMining = config.getBoolean("disableAllRadiusMining", "items", false, "If set to true, disables all radius-based mining functionaliy (right click of tools)");

			String maxEmc = config.getString("tileMaxEMC", "blocks", String.valueOf(Constants.TILE_MAX_EMC), "The maximum amount of EMC that can be stored in any ProjectE tile entity (Condensers, Furnaces, etc.).");

			String[] maxKleinStarsEMC = config.getStringList("maxKleinStarsEMC", "items", new String[] {"50000", "200000", "800000", "3200000", "12800000", "51200000"}, "The maximum EMC storage for each tier of Klein Star. Must contain exactly 6 values corresponding to tiers Ein through Omega.");
			if (maxKleinStarsEMC.length == 6) {
				for (int i = 0; i < 6; i++) Constants.MAX_KLEIN_EMC[i] = Long.parseLong(maxKleinStarsEMC[i]);
			}
			
			double[] collectorLightVals = config.get("blocks", "collectorLightVals", new double[] {0.4375, 0.6875, 1.0, 1.0}, "The light level efficiency factor for Collectors. Must contain exactly 4 values for MK1, MK2, MK3, and MK4.").getDoubleList();
			if (collectorLightVals.length == 4) {
				for (int i = 0; i < 4; i++) Constants.COLLECTOR_LIGHT_VALS[i] = (float) collectorLightVals[i];
			}
			
			double[] explosiveLensRadius = config.get("items", "explosiveLensRadius", new double[] {4.0, 8.0, 12.0, 16.0, 16.0, 16.0, 16.0, 16.0}, "The blast radius for the Destruction Catalyst/Hyperkinetic Lens at different charge levels. Must contain exactly 8 values.").getDoubleList();
			if (explosiveLensRadius.length == 8) {
				for (int i = 0; i < 8; i++) Constants.EXPLOSIVE_LENS_RADIUS[i] = (float) explosiveLensRadius[i];
			}

			Constants.EXPLOSIVE_LENS_COST = config.get("items", "explosiveLensCost", Constants.EXPLOSIVE_LENS_COST, "The EMC cost for using the Destruction Catalyst/Hyperkinetic Lens at different charge levels. Must contain exactly 8 values.").getIntList();
			
			String[] collectorMkMax = config.getStringList("collectorMkMax", "blocks", new String[] {"10000", "30000", "60000", "120000"}, "The maximum EMC storage capacity for the Energy Collector MK1-MK4. Must contain exactly 4 values.");
			if (collectorMkMax.length == 4) {
				for (int i = 0; i < 4; i++) Constants.COLLECTOR_MK_MAX[i] = Long.parseLong(collectorMkMax[i]);
			}

			String[] collectorMkGen = config.getStringList("collectorMkGen", "blocks", new String[] {"4", "12", "40", "80"}, "The amount of EMC generated per tick (at maximum light) by the Energy Collector MK1-MK4. Must contain exactly 4 values.");
			if (collectorMkGen.length == 4) {
				for (int i = 0; i < 4; i++) Constants.COLLECTOR_MK_GEN[i] = Long.parseLong(collectorMkGen[i]);
			}
			
			String[] defaultRelayOutput = new String[Constants.RELAY_MK_OUTPUT.length];
			for(int i=0; i<Constants.RELAY_MK_OUTPUT.length; i++) defaultRelayOutput[i] = String.valueOf(Constants.RELAY_MK_OUTPUT[i]);
			
			String[] relayMkOutput = config.getStringList("relayMkOutput", "blocks", defaultRelayOutput, "The amount of EMC the Anti-Matter Relay MK1-MK15 can transfer to neighbors per tick. Must contain exactly 15 values.");
			
			if (relayMkOutput.length == Constants.RELAY_MK_OUTPUT.length) {
				for (int i = 0; i < Constants.RELAY_MK_OUTPUT.length; i++) {
					try {
						Constants.RELAY_MK_OUTPUT[i] = Long.parseLong(relayMkOutput[i]);
					} catch (NumberFormatException e) {
						e.printStackTrace();
					}
				}
			}
			
			String[] relayMkMax = config.getStringList("relayMkMax", "blocks", new String[] {"100000", "1000000", "10000000"}, "The maximum EMC storage capacity for the Anti-Matter Relay MK1-MK3. Must contain exactly 3 values.");
			if (relayMkMax.length == 3) {
				for (int i = 0; i < 3; i++) Constants.RELAY_MK_MAX[i] = Long.parseLong(relayMkMax[i]);
			}

			String[] defaultPowerFlowerGen = new String[Constants.POWER_FLOWER_GEN.length];
			for(int i=0; i<Constants.POWER_FLOWER_GEN.length; i++) defaultPowerFlowerGen[i] = String.valueOf(Constants.POWER_FLOWER_GEN[i]);

			String[] powerFlowerGen = config.getStringList("powerFlowerGen", "blocks", defaultPowerFlowerGen, "The amount of EMC per tick that Power Flowers generate. Must contain exactly 15 values for MK1 through MK15.");
			
			if (powerFlowerGen.length == Constants.POWER_FLOWER_GEN.length) {
				for (int i = 0; i < Constants.POWER_FLOWER_GEN.length; i++) {
					try {
						Constants.POWER_FLOWER_GEN[i] = Long.parseLong(powerFlowerGen[i]);
					} catch (NumberFormatException e) {
						e.printStackTrace();
					}
				}
			}
			
			Constants.COAL_BURN_TIME = config.getInt("coalBurnTime", "misc", Constants.COAL_BURN_TIME, 1, Integer.MAX_VALUE, "The burn time in ticks for standard Coal (used as a baseline).");
			Constants.ALCH_BURN_TIME = config.getInt("alchBurnTime", "misc", Constants.ALCH_BURN_TIME, 1, Integer.MAX_VALUE, "The burn time in ticks for Alchemical Coal.");
			Constants.MOBIUS_BURN_TIME = config.getInt("mobiusBurnTime", "misc", Constants.MOBIUS_BURN_TIME, 1, Integer.MAX_VALUE, "The burn time in ticks for Mobius Fuel.");
			Constants.AETERNALIS_BUR_TIME = config.getInt("aeternalisBurnTime", "misc", Constants.AETERNALIS_BUR_TIME, 1, Integer.MAX_VALUE, "The burn time in ticks for Aeternalis Fuel.");
			
			Constants.MAX_CONDENSER_PROGRESS = config.getInt("maxCondenserProgress", "blocks", Constants.MAX_CONDENSER_PROGRESS, 1, Integer.MAX_VALUE, "The internal progress value required for the Energy Condenser to complete one operation. Higher values make it slower.");
			Constants.MAX_VEIN_SIZE = config.getInt("maxVeinSize", "items", Constants.MAX_VEIN_SIZE, 1, Integer.MAX_VALUE, "The maximum number of blocks that can be mined in a single vein mining operation.");
			Constants.ENCH_EMC_BONUS = config.getInt("enchEmcBonus", "misc", Constants.ENCH_EMC_BONUS, 0, Integer.MAX_VALUE, "The EMC value added to an item for each level of enchantment it has.");

			PELogger.logInfo("Loaded configuration file.");
		}
		catch (Exception e)
		{
			PELogger.logFatal("Caught exception while loading config file!");
			e.printStackTrace();
		}
		finally
		{
			if (config.hasChanged())
			{
				config.save();
			}
		}
	}
}
