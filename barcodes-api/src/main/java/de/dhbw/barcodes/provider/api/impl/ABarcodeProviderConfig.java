package de.dhbw.barcodes.provider.api.impl;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Properties;

import de.dhbw.barcodes.provider.api.IBarcodeProviderConfig;

public abstract class ABarcodeProviderConfig implements IBarcodeProviderConfig
{
	private Map<String, String> properties = new LinkedHashMap<>();

	/**
	 * Helper method
	 *
	 * @param p_properties
	 */
	protected void load(final Properties p_properties)
	{
		if (p_properties == null)
		{
			return;
		}

		p_properties.stringPropertyNames().stream().filter(key -> getValidPropertyNames().contains(key))
		        .forEach(key -> {
			        String l_value = p_properties.getProperty(key);

			        properties.put(key, l_value);
		        });
	}

	@Override
	public String getProperty(final String p_propertyName)
	{
		return properties.get(p_propertyName);
	}

	@Override
	public String getProperty(final String p_propertyName, final String p_defaultValue)
	{
		return properties.getOrDefault(p_propertyName, p_defaultValue);
	}

	@Override
	public int getPropertyAsInteger(final String p_propertyName)
	{
		String l_value = getProperty(p_propertyName);

		if (l_value != null)
		{
			try
			{
				return Integer.parseInt(l_value);
			}
			catch (NumberFormatException ex)
			{
				// ignore it
			}
		}

		return 0;
	}

	@Override
	public int getPropertyAsInteger(final String p_propertyName, final int p_defaultValue)
	{
		String l_value = getProperty(p_propertyName);

		if (l_value != null)
		{
			try
			{
				return Integer.parseInt(l_value);
			}
			catch (NumberFormatException ex)
			{
				// ignore it
			}
		}

		return p_defaultValue;
	}

	@Override
	public boolean getPropertyAsBoolean(final String p_propertyName)
	{
		String l_value = getProperty(p_propertyName);

		return Boolean.parseBoolean(l_value);
	}

	@Override
	public boolean getPropertyAsBoolean(final String p_propertyName, final boolean p_defaultValue)
	{
		String l_value = getProperty(p_propertyName);

		if (l_value != null)
		{
			return Boolean.parseBoolean(l_value);
		}

		return p_defaultValue;
	}

}
