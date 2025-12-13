package moze_intel.projecte.emc.arithmetics;

import moze_intel.projecte.emc.BigFraction;

public class BigFractionArithmetic implements IValueArithmetic<BigFraction>
{
	@Override
	public boolean isZero(BigFraction value)
	{
		return value.compareTo(BigFraction.ZERO) == 0;
	}

	@Override
	public BigFraction getZero()
	{
		return BigFraction.ZERO;
	}

	@Override
	public BigFraction add(BigFraction a, BigFraction b)
	{
		if (isFree(a)) return b;
		if (isFree(b)) return a;

		return a.add(b);
	}

	@Override
	public BigFraction mul(int a, BigFraction b)
	{
		if (this.isFree(b)) return getFree();
		return b.multiply(new BigFraction(a));
	}

	@Override
	public BigFraction div(BigFraction a, int b)
	{
		if (this.isFree(a)) return getFree();
		return a.divide(new BigFraction(b));
	}

	@Override
	public BigFraction getFree()
	{
		return new BigFraction(Long.MIN_VALUE);
	}

	@Override
	public boolean isFree(BigFraction value)
	{
		return value.longValue() == Long.MIN_VALUE;
	}
}
