package moze_intel.projecte.emc;

import moze_intel.projecte.emc.mappers.APICustomConversionMapper;
import moze_intel.projecte.emc.mappers.APICustomEMCMapper;
import moze_intel.projecte.emc.mappers.Chisel2Mapper;
import moze_intel.projecte.emc.mappers.CraftingMapper;
import moze_intel.projecte.emc.mappers.CustomEMCMapper;
import moze_intel.projecte.emc.mappers.FluidMapper;
import moze_intel.projecte.emc.mappers.IEMCMapper;
import moze_intel.projecte.emc.mappers.LazyMapper;
import moze_intel.projecte.emc.mappers.OreDictionaryMapper;
import moze_intel.projecte.emc.mappers.SmeltingMapper;
import moze_intel.projecte.emc.mappers.customConversions.CustomConversionMapper;

import java.util.Arrays;
import java.util.List;

public final class EmcMapperRegistry
{
	private EmcMapperRegistry() {}

	public static List<IEMCMapper<NormalizedSimpleStack, Long>> defaultMappers()
	{
		return Arrays.asList(
				new OreDictionaryMapper(),
				new LazyMapper(),
				new Chisel2Mapper(),
				APICustomEMCMapper.instance,
				new CustomConversionMapper(),
				new CustomEMCMapper(),
				new CraftingMapper(),
				new FluidMapper(),
				new SmeltingMapper(),
				new APICustomConversionMapper()
		);
	}
}
