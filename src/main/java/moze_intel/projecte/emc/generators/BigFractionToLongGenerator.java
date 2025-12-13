package moze_intel.projecte.emc.generators;

import com.google.common.collect.Maps;
import moze_intel.projecte.emc.BigFraction;

import java.util.Map;

public class BigFractionToLongGenerator<T> implements IValueGenerator<T, Long>
{
	private final IValueGenerator<T, BigFraction> inner;

	public BigFractionToLongGenerator(IValueGenerator<T, BigFraction> inner) {
		this.inner = inner;
	}

	@Override
	public Map<T, Long> generateValues()
	{
		Map<T, BigFraction> innerReslt = inner.generateValues();
		Map<T, Long> myResult = Maps.newHashMap();
		for (Map.Entry<T, BigFraction> entry: innerReslt.entrySet())
		{
			BigFraction value = entry.getValue();
			if (value.compareTo(BigFraction.ZERO) > 0)
			{
				myResult.put(entry.getKey(), value.longValue());
			}
		}
		return myResult;
	}
}
