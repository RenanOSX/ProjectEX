package moze_intel.projecte;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class NumberFormatterTest
{
	@Test
	public void testPlainValues()
	{
		assertEquals("0", NumberFormatter.format(0));
		assertEquals("999", NumberFormatter.format(999));
	}

	@Test
	public void testIncrementalSuffixes()
	{
		assertEquals("1K", NumberFormatter.format(1_000));
		assertEquals("1.5K", NumberFormatter.format(1_500));
		assertEquals("999K", NumberFormatter.format(999_999));
		assertEquals("1M", NumberFormatter.format(1_000_000));
		assertEquals("2.5B", NumberFormatter.format(2_500_000_000L));
		assertEquals("1T", NumberFormatter.format(1_000_000_000_000L));
		assertEquals("3Q", NumberFormatter.format(3_000_000_000_000_000L));
		assertEquals("7Qi", NumberFormatter.format(7_000_000_000_000_000_000L));
		assertEquals("9.2Qi", NumberFormatter.format(Long.MAX_VALUE));
	}

	@Test
	public void testNegativeValues()
	{
		assertEquals("-1.5K", NumberFormatter.format(-1_500));
		assertEquals("-2M", NumberFormatter.format(-2_000_000));
	}
}
