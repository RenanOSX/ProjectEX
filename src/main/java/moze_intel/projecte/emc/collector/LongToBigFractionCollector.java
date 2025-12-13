package moze_intel.projecte.emc.collector;

import moze_intel.projecte.emc.BigFraction;
import moze_intel.projecte.emc.arithmetics.IValueArithmetic;

import java.util.Map;

public class LongToBigFractionCollector<T> extends AbstractMappingCollector<T, Long, IValueArithmetic<BigFraction>> implements IExtendedMappingCollector<T, Long, IValueArithmetic<BigFraction>>
{
	private final IExtendedMappingCollector<T, BigFraction, IValueArithmetic<BigFraction>> inner;

	public LongToBigFractionCollector(IExtendedMappingCollector<T, BigFraction, IValueArithmetic<BigFraction>> inner)
	{
		super(inner.getArithmetic());
		this.inner = inner;
	}

	@Override
	public void setValueBefore(T something, Long value)
	{
		inner.setValueBefore(something, new BigFraction(value));
	}

	@Override
	public void setValueAfter(T something, Long value)
	{
		inner.setValueAfter(something, new BigFraction(value));
	}

	@Override
	public void setValueFromConversion(int outnumber, T something, Map<T, Integer> ingredientsWithAmount)
	{
		inner.setValueFromConversion(outnumber, something, ingredientsWithAmount);
	}

	@Override
	public void addConversion(int outnumber, T output, Map<T, Integer> ingredientsWithAmount, IValueArithmetic<BigFraction> arithmeticForConversion)
	{
		inner.addConversion(outnumber, output, ingredientsWithAmount, arithmeticForConversion);
	}
}
