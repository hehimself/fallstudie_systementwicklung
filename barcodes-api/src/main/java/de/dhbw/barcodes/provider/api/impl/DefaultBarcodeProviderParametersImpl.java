package de.dhbw.barcodes.provider.api.impl;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

import de.dhbw.barcodes.provider.api.IBarcodeProviderParameters;

public class DefaultBarcodeProviderParametersImpl implements IBarcodeProviderParameters
{
	public static final String PARAMETER_IMAGEWIDTH = "image-width";
	public static final String PARAMETER_IMAGEHEIGHT = "image-height";
	public static final String PARAMETER_IMAGETYPE = "image-type";

	private Map<String, Object> parameters;

	public DefaultBarcodeProviderParametersImpl()
	{
		parameters = new HashMap<>();
	}

	@Override
	public boolean getBooleanParameter(final String p_paramName)
	{
		Object l_obj = findParameterValue(p_paramName);

		if (l_obj instanceof Boolean)
		{
			return ((Boolean) l_obj).booleanValue();
		}
		else if (l_obj instanceof Number)
		{
			return ((Number) l_obj).intValue() == 1;
		}
		else if (l_obj instanceof String)
		{
			return "true".equalsIgnoreCase(l_obj.toString());
		}

		return false;
	}

	@Override
	public byte getByteParameter(final String p_paramName)
	{
		Object l_obj = findParameterValue(p_paramName);

		if (l_obj instanceof Number)
		{
			return ((Number) l_obj).byteValue();
		}
		else if (l_obj instanceof String)
		{
			try
			{
				return Byte.parseByte(l_obj.toString());
			}
			catch (Exception ex)
			{
				//
			}
		}

		return (byte) 0;
	}

	@Override
	public char getCharParameter(final String p_paramName)
	{
		Object l_obj = findParameterValue(p_paramName);

		if (l_obj instanceof Character)
		{
			return ((Character) l_obj).charValue();
		}
		else if (l_obj instanceof String)
		{
			String l_val = l_obj.toString();
			if (l_val.length() == 1)
			{
				return l_val.charAt(0);
			}
		}

		return (char) 0;
	}

	@Override
	public double getDoubleParameter(final String p_paramName)
	{
		Object l_obj = findParameterValue(p_paramName);

		if (l_obj instanceof Number)
		{
			return ((Number) l_obj).doubleValue();
		}
		else if (l_obj instanceof String)
		{
			try
			{
				return Double.parseDouble(l_obj.toString());
			}
			catch (Exception ex)
			{
				//
			}
		}

		return 0D;
	}

	@Override
	public float getFloatParameter(final String p_paramName)
	{
		Object l_obj = findParameterValue(p_paramName);

		if (l_obj instanceof Number)
		{
			return ((Number) l_obj).floatValue();
		}
		else if (l_obj instanceof String)
		{
			try
			{
				return Float.parseFloat(l_obj.toString());
			}
			catch (Exception ex)
			{
				//
			}
		}

		return 0F;
	}

	@Override
	public int getIntParameter(final String p_paramName)
	{
		Object l_obj = findParameterValue(p_paramName);

		if (l_obj instanceof Number)
		{
			return ((Number) l_obj).intValue();
		}
		else if (l_obj instanceof String)
		{
			try
			{
				return Integer.parseInt(l_obj.toString());
			}
			catch (Exception ex)
			{
				//
			}
		}

		return 0;
	}

	@Override
	public long getLongParameter(final String p_paramName)
	{
		Object l_obj = findParameterValue(p_paramName);

		if (l_obj instanceof Number)
		{
			return ((Number) l_obj).longValue();
		}
		else if (l_obj instanceof String)
		{
			try
			{
				return Long.parseLong(l_obj.toString());
			}
			catch (Exception ex)
			{
				//
			}
		}

		return 0L;
	}

	@Override
	public Object getObjectParameter(final String p_paramName)
	{
		return findParameterValue(p_paramName);
	}

	@Override
	public short getShortParameter(final String p_paramName)
	{
		Object l_obj = findParameterValue(p_paramName);

		if (l_obj instanceof Number)
		{
			return ((Number) l_obj).shortValue();
		}
		else if (l_obj instanceof String)
		{
			try
			{
				return Short.parseShort(l_obj.toString());
			}
			catch (Exception ex)
			{
				//
			}
		}

		return (short) 0;
	}

	@Override
	public String getStringParameter(final String p_paramName)
	{
		return findParameterValue(p_paramName).toString();
	}

	@Override
	public void setParameter(final String p_paramName, final boolean p_value)
	{
		Objects.requireNonNull(p_paramName, "Name must pe not null!");

		parameters.put(p_paramName, Boolean.valueOf(p_value));
	}

	@Override
	public void setParameter(final String p_paramName, final byte p_value)
	{
		Objects.requireNonNull(p_paramName, "Name must pe not null!");

		parameters.put(p_paramName, Byte.valueOf(p_value));
	}

	@Override
	public void setParameter(final String p_paramName, final char p_value)
	{
		Objects.requireNonNull(p_paramName, "Name must pe not null!");

		parameters.put(p_paramName, Character.valueOf(p_value));
	}

	@Override
	public void setParameter(final String p_paramName, final double p_value)
	{
		Objects.requireNonNull(p_paramName, "Name must pe not null!");

		parameters.put(p_paramName, Double.valueOf(p_value));
	}

	@Override
	public void setParameter(final String p_paramName, final float p_value)
	{
		Objects.requireNonNull(p_paramName, "Name must pe not null!");

		parameters.put(p_paramName, Float.valueOf(p_value));
	}

	@Override
	public void setParameter(final String p_paramName, final int p_value)
	{
		Objects.requireNonNull(p_paramName, "Name must pe not null!");

		parameters.put(p_paramName, Integer.valueOf(p_value));
	}

	@Override
	public void setParameter(final String p_paramName, final long p_value)
	{
		Objects.requireNonNull(p_paramName, "Name must pe not null!");

		parameters.put(p_paramName, Long.valueOf(p_value));
	}

	@Override
	public void setParameter(final String p_paramName, final Object p_value)
	{
		Objects.requireNonNull(p_paramName, "Name must pe not null!");
		Objects.requireNonNull(p_value, "Value must pe not null!");

		parameters.put(p_paramName, p_value);
	}

	@Override
	public void setParameter(final String p_paramName, final short p_value)
	{
		Objects.requireNonNull(p_paramName, "Name must pe not null!");

		parameters.put(p_paramName, Short.valueOf(p_value));
	}

	private Object findParameterValue(final String p_paramName)
	{
		Object l_obj = parameters.get(p_paramName);

		Objects.requireNonNull(l_obj, "Value for '" + p_paramName + "' not found!");

		return l_obj;
	}

	@Override
	public boolean getBooleanParameter(final String p_paramName, final boolean p_defaultValue)
	{
		try
		{
			return getBooleanParameter(p_paramName);
		}
		catch (Exception l_ex)
		{
			return p_defaultValue;
		}
	}

	@Override
	public byte getByteParameter(final String p_paramName, final byte p_defaultValue)
	{
		try
		{
			return getByteParameter(p_paramName);
		}
		catch (Exception l_ex)
		{
			return p_defaultValue;
		}
	}

	@Override
	public char getCharParameter(final String p_paramName, final char p_defaultValue)
	{
		try
		{
			return getCharParameter(p_paramName);
		}
		catch (Exception l_ex)
		{
			return p_defaultValue;
		}
	}

	@Override
	public double getDoubleParameter(final String p_paramName, final double p_defaultValue)
	{
		try
		{
			return getDoubleParameter(p_paramName);
		}
		catch (Exception l_ex)
		{
			return p_defaultValue;
		}
	}

	@Override
	public float getByteParameter(final String p_paramName, final float p_defaultValue)
	{
		try
		{
			return getFloatParameter(p_paramName);
		}
		catch (Exception l_ex)
		{
			return p_defaultValue;
		}
	}

	@Override
	public int getIntParameter(final String p_paramName, final int p_defaultValue)
	{
		try
		{
			return getIntParameter(p_paramName);
		}
		catch (Exception l_ex)
		{
			return p_defaultValue;
		}
	}

	@Override
	public long getLongParameter(final String p_paramName, final long p_defaultValue)
	{
		try
		{
			return getLongParameter(p_paramName);
		}
		catch (Exception l_ex)
		{
			return p_defaultValue;
		}
	}

	@Override
	public Object getObjectParameter(final String p_paramName, final Object p_defaultValue)
	{
		try
		{
			return getObjectParameter(p_paramName);
		}
		catch (Exception l_ex)
		{
			return p_defaultValue;
		}
	}

	@Override
	public short getShortParameter(final String p_paramName, final short p_defaultValue)
	{
		try
		{
			return getShortParameter(p_paramName);
		}
		catch (Exception l_ex)
		{
			return p_defaultValue;
		}
	}

	@Override
	public String getStringParameter(final String p_paramName, final String p_defaultValue)
	{
		try
		{
			return getStringParameter(p_paramName);
		}
		catch (Exception l_ex)
		{
			return p_defaultValue;
		}
	}
}
