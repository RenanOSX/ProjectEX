package moze_intel.projecte.api.impl;

import moze_intel.projecte.api.proxy.IConversionProxy;
import moze_intel.projecte.emc.ConversionBuffer;
import moze_intel.projecte.emc.NormalizedSimpleStack;

import java.util.Map;

public class ConversionProxyImpl implements IConversionProxy
{

	public static final ConversionProxyImpl instance = new ConversionProxyImpl();

	@Override
	public void addConversion(int amount, Object output, Map<Object, Integer> ingredients) {
		ConversionBuffer.addConversion(amount, output, ingredients);
	}

	public NormalizedSimpleStack objectToNSS(Object object)
	{
		return ConversionBuffer.objectToNSS(object);
	}
}
