package moze_intel.projecte.emc;

import com.google.common.collect.Maps;

import java.util.Map;
import java.util.Set;

public final class EmcValueStore
{
	private EmcValueStore() {}

	public static boolean mapContains(SimpleStack key)
	{
		SimpleStack copy = key.copy();
		copy.qnty = 1;

		return EMCMapper.emc.containsKey(copy);
	}

	public static long getEmcValue(SimpleStack stack)
	{
		SimpleStack copy = stack.copy();
		copy.qnty = 1;

		return EMCMapper.emc.get(copy);
	}

	public static int size()
	{
		return EMCMapper.emc.size();
	}

	public static Set<SimpleStack> keySet()
	{
		return EMCMapper.emc.keySet();
	}

	public static Map<SimpleStack, Long> snapshot()
	{
		return Maps.newLinkedHashMap(EMCMapper.emc);
	}
}
