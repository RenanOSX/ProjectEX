package moze_intel.projecte.utils;

import com.google.common.collect.ImmutableMap;
import cpw.mods.fml.client.registry.RenderingRegistry;
import moze_intel.projecte.PECore;
import net.minecraft.util.ResourceLocation;

import java.text.DecimalFormat;

public final class Constants 
{
	public static final DecimalFormat EMC_FORMATTER = new DecimalFormat("##.##");
	public static final float PLAYER_WALK_SPEED = 0.1F;
	
	public static long[] MAX_KLEIN_EMC = new long[] {50000, 200000, 800000, 3200000, 12800000, 51200000};
	public static long[] MAX_MAGNUM_EMC = new long[] {204800000, 819200000, 3276800000L, 13107200000L, 52428800000L, 209715200000L};
	public static long[] MAX_COLOSSAL_EMC = new long[] {838860800000L,  3355443200000L, 13421772800000L, 53687091200000L, 214748364800000L, 858993459200000L};
	public static float[] COLLECTOR_LIGHT_VALS = new float[] {0.4375F, 0.6875F, 1.0F, 1.0F};
	
	public static float[] EXPLOSIVE_LENS_RADIUS = new float[] {4.0F, 8.0F, 12.0F, 16.0F, 16.0F, 16.0F, 16.0F, 16.0F};
	public static int[] EXPLOSIVE_LENS_COST = new int[] {384, 768, 1536, 2304, 2304, 2304, 2304, 2304};
	
	public static long TILE_MAX_EMC = Long.MAX_VALUE;
	
	public static long[] COLLECTOR_MK_MAX = new long[] {10000, 30000, 60000, 120000, 240000, 480000, 960000, 1920000, 3840000, 7680000, 15360000, 30720000, 61440000, 122880000, 245760000};
	public static long[] COLLECTOR_MK_GEN = new long[] {4, 12, 40, 160, 640, 2560, 10240, 40960, 163840, 655360, 2621440, 10485760, 41943040, 167772160, 671088640};
	public static long[] POWER_FLOWER_GEN = new long[] {90, 270, 900, 3600, 14220, 59580, 251820, 1007280, 4029120, 16116480, 64465920, 257863680, 1031454720, 4125818880L, 16503275520L};
	public static String[] MATTER_NAMES = new String[] {"dark", "red", "magenta", "pink", "purple", "violet", "blue", "cyan", "green", "lime", "yellow", "orange", "white", "fading"};
	
	public static long[] RELAY_MK_OUTPUT = new long[] {64, 192, 640, 2560, 10240, 40960, 163840, 655360, 2621440, 10485760, 41943040, 167772160, 671088640, 2684354560L, 10737418240L};
	public static double[] RELAY_MK_BONUS = new double[] {0.05, 0.15, 0.5, 2.0, 7.5, 37.5, 187.5, 750.0, 3000.0, 12000.0, 48000.0, 192000.0, 768000.0, 3072000.0, 12288000.0};
	public static long[] RELAY_MK_MAX = new long[] {100000, 1000000, 10000000, 100000000, 1000000000, 10000000000L, 100000000000L, 1000000000000L, 10000000000000L, 100000000000000L, 1000000000000000L, 10000000000000000L, 100000000000000000L, 1000000000000000000L, Long.MAX_VALUE};
	
	public static int COAL_BURN_TIME = 1600;
	public static int ALCH_BURN_TIME = COAL_BURN_TIME * 4;
	public static int MOBIUS_BURN_TIME = ALCH_BURN_TIME * 4;
	public static int AETERNALIS_BUR_TIME = MOBIUS_BURN_TIME * 4;
	
	public static final int ALCH_CHEST_GUI = 0;
	public static final int ALCH_BAG_GUI = 1;

	@Deprecated
	public static final int TRANSMUTE_STONE_GUI = 2;
	public static final int CONDENSER_GUI = 3;
	public static final int RM_FURNACE_GUI = 4;
	public static final int DM_FURNACE_GUI = 5;
	public static final int MERCURIAL_GUI = 12;
	public static final int PHILOS_STONE_GUI = 13;
	public static final int TRANSMUTATION_GUI = 14;
	public static final int ETERNAL_DENSITY_GUI = 15;
	public static final int CONDENSER_MK2_GUI = 16;
	public static final int PEDESTAL_GUI = 17;

	public static int MAX_CONDENSER_PROGRESS = 102;
	
	public static final int CHEST_RENDER_ID = RenderingRegistry.getNextAvailableRenderId();
	public static final int CONDENSER_RENDER_ID = RenderingRegistry.getNextAvailableRenderId();
	public static final int CONDENSER_MK2_RENDER_ID = RenderingRegistry.getNextAvailableRenderId();
	public static final int PEDESTAL_RENDER_ID = RenderingRegistry.getNextAvailableRenderId();
	public static final ResourceLocation PEDESTAL_MODELTEX_LOCATION = new ResourceLocation(PECore.MODID.toLowerCase(), "textures/models/pedestaltexmap.png");

	public static int MAX_VEIN_SIZE = 250;
	
	public static int ENCH_EMC_BONUS = 5000;

	public static final ImmutableMap<String, String> SPACE_STRIP_NAME_MAP;

	static {
		ImmutableMap.Builder<String, String> builder = ImmutableMap.builder();
		builder.put("Alchemical Chest", "alchemical_chest");
		builder.put("Interdiction Torch", "interdiction_torch");
		builder.put("Transmutation Stone", "transmutation_table");
		builder.put("Condenser", "condenser_mk1");
		builder.put("Condenser MK2", "condenser_mk2");
		builder.put("RM Furnace", "rm_furnace");
		builder.put("RM Furnace Lit", "rm_furnace_lit");
		builder.put("DM Furnace", "dm_furnace");
		builder.put("DM Furnace Lit", "dm_furnace_lit");
		builder.put("DM Pedestal", "dm_pedestal");
		builder.put("Matter Block", "matter_block");
		builder.put("Fuel Block", "fuel_block");
		builder.put("Collector MK1", "collector_mk1");
		builder.put("Collector MK2", "collector_mk2");
		builder.put("Collector MK3", "collector_mk3");
		builder.put("Relay MK1", "relay_mk1");
		builder.put("Relay MK2", "relay_mk2");
		builder.put("Relay MK3", "relay_mk3");
		builder.put("Nova Catalyst", "nova_catalyst");
		builder.put("Nova Cataclysm", "nova_cataclysm");
		SPACE_STRIP_NAME_MAP = builder.build();
	}
}
