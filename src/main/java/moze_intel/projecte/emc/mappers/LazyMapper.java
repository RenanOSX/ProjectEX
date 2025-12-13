package moze_intel.projecte.emc.mappers;

import moze_intel.projecte.emc.collector.IMappingCollector;
import moze_intel.projecte.emc.NormalizedSimpleStack;
import moze_intel.projecte.utils.ItemHelper;

import com.google.common.collect.ImmutableMap;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraftforge.common.config.Configuration;

public class LazyMapper implements IEMCMapper<NormalizedSimpleStack, Long> {

	IMappingCollector<NormalizedSimpleStack, Long> mapper;

	@Override
	public void addMappings(IMappingCollector<NormalizedSimpleStack, Long> mapper, Configuration config) {
		this.mapper = mapper;
		addMapping(new ItemStack(Blocks.cobblestone), 1L);
		addMapping(new ItemStack(Blocks.stone), 1L);
		addMapping(new ItemStack(Blocks.end_stone), 1L);
		addMapping(new ItemStack(Blocks.netherrack), 1L);
		addMapping(new ItemStack(Blocks.dirt), 1L);
		addMapping(new ItemStack(Blocks.dirt, 1, 2), 2L);
		mapper.addConversion(1, NormalizedSimpleStack.getFor(Blocks.grass), ImmutableMap.of(NormalizedSimpleStack.getFor(Blocks.dirt), 2));
		addMapping(new ItemStack(Blocks.mycelium), 2L);
		addMapping(new ItemStack(Blocks.leaves), 1L);
		addMapping(new ItemStack(Blocks.leaves2), 1L);
		addMapping(new ItemStack(Blocks.sand, 1, 0), 1L);
		addMapping(new ItemStack(Blocks.sand, 1, 1), 1L);
		addMapping(new ItemStack(Blocks.snow), 1L);
		addMapping(new ItemStack(Blocks.ice), 1L);
		addMapping(new ItemStack(Blocks.deadbush), 1L);
		addMapping(new ItemStack(Blocks.gravel), 4L);
		addMapping(new ItemStack(Blocks.cactus), 8L);
		addMapping(new ItemStack(Blocks.vine), 8L);
		addMapping(new ItemStack(Blocks.torch), 9L);
		addMapping(new ItemStack(Blocks.web), 12L);
		addMapping(new ItemStack(Items.wheat_seeds), 16L);
		addMapping(new ItemStack(Items.melon), 16L);
		addMapping(new ItemStack(Items.clay_ball), 16L);
		addMapping(new ItemStack(Blocks.waterlily), 16L);

		for (int i = 0; i <= 8; i++) {
			addMapping(new ItemStack(Blocks.red_flower, 1, i), 16L);
		}

		for (int i = 0; i <= 5; i++) {
			if (i == 2 || i == 3) {
				continue;
			}

			addMapping(new ItemStack(Blocks.double_plant, 1, i), 32L);
		}

		addMapping(new ItemStack(Blocks.yellow_flower), 16L);
		addMapping(new ItemStack(Items.wheat), 24L);
		addMapping(new ItemStack(Items.nether_wart), 24L);
		addMapping(new ItemStack(Items.stick), 4L);
		addMapping(new ItemStack(Blocks.red_mushroom), 32L);
		addMapping(new ItemStack(Blocks.brown_mushroom), 32L);
		addMapping(new ItemStack(Items.reeds), 32L);
		addMapping(new ItemStack(Blocks.soul_sand), 49L);
		addMapping(new ItemStack(Blocks.obsidian), 64L);

		for (int i = 0; i < 16; i++) {
			addMapping(new ItemStack(Blocks.stained_hardened_clay, 1, i), 64L);
		}

		addMapping(new ItemStack(Items.apple), 128L);
		//Cocoa beans
		addMapping(new ItemStack(Items.dye, 1, 3), 128L);
		addMapping(new ItemStack(Blocks.pumpkin), 144L);
		addMapping(new ItemStack(Items.bone), 144L);

		mapper.addConversion(1, NormalizedSimpleStack.getFor(Blocks.mossy_cobblestone), ImmutableMap.of(NormalizedSimpleStack.getFor(Blocks.cobblestone), 2));
		//Mossy Stone Bricks
		mapper.addConversion(1, NormalizedSimpleStack.getFor(new ItemStack(Blocks.stonebrick, 1, 1)), ImmutableMap.of(NormalizedSimpleStack.getFor(Blocks.stonebrick), 2));
		addMapping(new ItemStack(Blocks.stonebrick, 1, 2), 1L);
		addMapping(new ItemStack(Blocks.stonebrick, 1, 3), 1L);
		addMapping(new ItemStack(Items.saddle), 192L);
		addMapping(new ItemStack(Items.record_11), 2048L);
		addMapping(new ItemStack(Items.record_13), 2048L);
		addMapping(new ItemStack(Items.record_blocks), 2048L);
		addMapping(new ItemStack(Items.record_cat), 2048L);
		addMapping(new ItemStack(Items.record_chirp), 2048L);
		addMapping(new ItemStack(Items.record_far), 2048L);
		addMapping(new ItemStack(Items.record_mall), 2048L);
		addMapping(new ItemStack(Items.record_mellohi), 2048L);
		addMapping(new ItemStack(Items.record_stal), 2048L);
		addMapping(new ItemStack(Items.record_strad), 2048L);
		addMapping(new ItemStack(Items.record_wait), 2048L);
		addMapping(new ItemStack(Items.record_ward), 2048L);
		addMapping(new ItemStack(Items.string), 12L);
		addMapping(new ItemStack(Items.rotten_flesh), 32L);
		addMapping(new ItemStack(Items.slime_ball), 32L);
		addMapping(new ItemStack(Items.egg), 32L);
		addMapping(new ItemStack(Items.feather), 48L);
		addMapping(new ItemStack(Items.leather), 64L);
		addMapping(new ItemStack(Items.spider_eye), 128L);
		addMapping(new ItemStack(Items.gunpowder), 192L);
		addMapping(new ItemStack(Items.ender_pearl), 1024L);
		addMapping(new ItemStack(Items.blaze_rod), 1536L);
		addMapping(new ItemStack(Items.ghast_tear), 4096L);
		addMapping(new ItemStack(Blocks.dragon_egg), 262144L);
		addMapping(new ItemStack(Items.porkchop), 64L);
		addMapping(new ItemStack(Items.beef), 64L);
		addMapping(new ItemStack(Items.chicken), 64L);

		for (int i = 0; i < 4; i++) {
			addMapping(new ItemStack(Items.fish, 1, i), 64L);
		}

		addMapping(new ItemStack(Items.carrot), 64L);
		addMapping(new ItemStack(Items.potato), 64L);
		addMapping(new ItemStack(Items.poisonous_potato), 64L);
		addMapping(new ItemStack(Items.iron_ingot), 256L);
		addMapping(new ItemStack(Items.gold_ingot), 2048L);
		addMapping(new ItemStack(Items.diamond), 8192L);
		addMapping(new ItemStack(Items.flint), 4L);
		addMapping(new ItemStack(Items.coal), 128L);
		addMapping(new ItemStack(Items.redstone), 64L);
		addMapping(new ItemStack(Items.glowstone_dust), 384L);
		addMapping(new ItemStack(Items.quartz), 256L);
		//Lapis Lazuli
		addMapping(new ItemStack(Items.dye, 1, 4), 864L);

		//ink sac
		addMapping(new ItemStack(Items.dye, 1, 0), 16L);

		addMapping(new ItemStack(Items.enchanted_book), 2048L);
		addMapping(new ItemStack(Items.emerald), 16384L);

		addMapping(new ItemStack(Items.nether_star), 139264L);
		mapper.addConversion(1, NormalizedSimpleStack.getFor(Items.iron_horse_armor), ImmutableMap.of(NormalizedSimpleStack.getFor(Items.iron_ingot), 8));
		mapper.addConversion(1, NormalizedSimpleStack.getFor(Items.golden_horse_armor), ImmutableMap.of(NormalizedSimpleStack.getFor(Items.gold_ingot), 8));
		addMapping(new ItemStack(Items.diamond_horse_armor), 40960L);
		addMapping(new ItemStack(Blocks.tallgrass), 1L);
		addMapping(new ItemStack(Blocks.tallgrass, 1, 1), 1L);
		addMapping(new ItemStack(Blocks.tallgrass, 1, 2), 1L);
		addMapping(new ItemStack(Blocks.double_plant, 1, 2), 1L);
		addMapping(new ItemStack(Blocks.double_plant, 1, 3), 1L);
		addMapping(new ItemStack(Blocks.packed_ice), 4L);
		addMapping(new ItemStack(Items.snowball), 1L);
		addMapping(new ItemStack(Items.filled_map), 1472L);

		addMapping("appliedenergistics2:item.ItemMultiMaterial", 1, 256L);
		
		// Power Flowers
		for (int i = 0; i < 15; i++) {
			addMapping("projecte:power_flower_mk" + (i + 1), 0, moze_intel.projecte.utils.Constants.POWER_FLOWER_GEN[i] * 2000);
		}
	}

	protected void addMapping(ItemStack itemStack, long value) {
		this.mapper.setValueBefore(NormalizedSimpleStack.getFor(itemStack), value);
	}

	protected void addMapping(String unlocalName, int meta, long value) {
		ItemStack stack = ItemHelper.getStackFromString(unlocalName, meta);

		if (stack != null) {
			addMapping(stack, value);
		}
	}

	@Override
	public String getName() {
		return "LazyMapper";
	}

	@Override
	public String getDescription() {
		return "Default values for Items";
	}

	@Override
	public boolean isAvailable() {
		return true;
	}
}
