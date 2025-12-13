package moze_intel.projecte.emc;

import java.math.BigInteger;

public class BigFraction implements Comparable<BigFraction>
{
	public static final BigFraction ZERO = new BigFraction(BigInteger.ZERO);
	public static final BigFraction ONE = new BigFraction(BigInteger.ONE);

	private final BigInteger numerator;
	private final BigInteger denominator;

	public BigFraction(BigInteger numerator, BigInteger denominator)
	{
		if (denominator.equals(BigInteger.ZERO))
		{
			throw new ArithmeticException("Divide by zero");
		}
		
		if (denominator.signum() < 0)
		{
			numerator = numerator.negate();
			denominator = denominator.negate();
		}
		
		BigInteger gcd = numerator.gcd(denominator);
		this.numerator = numerator.divide(gcd);
		this.denominator = denominator.divide(gcd);
	}

	public BigFraction(BigInteger numerator)
	{
		this(numerator, BigInteger.ONE);
	}

	public BigFraction(long numerator)
	{
		this(BigInteger.valueOf(numerator), BigInteger.ONE);
	}
	
	public BigFraction(long numerator, long denominator)
	{
		this(BigInteger.valueOf(numerator), BigInteger.valueOf(denominator));
	}

	public BigFraction add(BigFraction other)
	{
		return new BigFraction(
				numerator.multiply(other.denominator).add(other.numerator.multiply(denominator)),
				denominator.multiply(other.denominator)
		);
	}

	public BigFraction multiply(BigFraction other)
	{
		return new BigFraction(numerator.multiply(other.numerator), denominator.multiply(other.denominator));
	}

	public BigFraction divide(BigFraction other)
	{
		return new BigFraction(numerator.multiply(other.denominator), denominator.multiply(other.numerator));
	}

	public long longValue()
	{
		return numerator.divide(denominator).longValue();
	}

	@Override
	public int compareTo(BigFraction o)
	{
		return numerator.multiply(o.denominator).compareTo(o.numerator.multiply(denominator));
	}
	
	@Override
	public String toString()
	{
		if (denominator.equals(BigInteger.ONE))
		{
			return numerator.toString();
		}
		return numerator.toString() + "/" + denominator.toString();
	}
	
	@Override
	public boolean equals(Object obj)
	{
		if (obj instanceof BigFraction)
		{
			BigFraction other = (BigFraction) obj;
			return numerator.equals(other.numerator) && denominator.equals(other.denominator);
		}
		return false;
	}
	
	@Override
	public int hashCode()
	{
		return numerator.hashCode() ^ denominator.hashCode();
	}
}
