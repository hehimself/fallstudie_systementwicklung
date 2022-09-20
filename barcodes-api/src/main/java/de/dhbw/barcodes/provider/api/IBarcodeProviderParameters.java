package de.dhbw.barcodes.provider.api;

public interface IBarcodeProviderParameters
{
	/**
	 * A convenience method to return a parameter as a boolean.
	 *
	 * @param p_paramName return
	 */
	boolean getBooleanParameter(String p_paramName);

	/**
	 * A convenience method to return a parameter as a boolean.
	 *
	 * @param p_paramName
	 * @param p_defaultValue
	 */
	boolean getBooleanParameter(String p_paramName, boolean p_defaultValue);

	/**
	 * A convenience method to return a parameter as a byte.
	 *
	 * @param p_paramName
	 * @return
	 */
	byte getByteParameter(String p_paramName);

	/**
	 * A convenience method to return a parameter as a byte.
	 *
	 * @param p_paramName
	 * @param p_defaultValue
	 */
	byte getByteParameter(String p_paramName, byte p_defaultValue);

	/**
	 * A convenience method to return a parameter as a char.
	 *
	 * @param p_paramName
	 * @return
	 */
	char getCharParameter(String p_paramName);

	/**
	 * A convenience method to return a parameter as a char.
	 *
	 * @param p_paramName
	 * @param p_defaultValue
	 */
	char getCharParameter(String p_paramName, char p_defaultValue);

	/**
	 * A convenience method to return a parameter as a double.
	 *
	 * @param p_paramName
	 * @return
	 */
	double getDoubleParameter(String p_paramName);

	/**
	 * A convenience method to return a parameter as a double.
	 *
	 * @param p_paramName
	 * @param p_defaultValue
	 */
	double getDoubleParameter(String p_paramName, double p_defaultValue);

	/**
	 * A convenience method to return a parameter as a float.
	 *
	 * @param p_paramName
	 * @return
	 */
	float getFloatParameter(String p_paramName);

	/**
	 * A convenience method to return a parameter as a float.
	 *
	 * @param p_paramName
	 * @param p_defaultValue
	 */
	float getByteParameter(String p_paramName, float p_defaultValue);

	/**
	 * A convenience method to return a parameter as an int.
	 *
	 * @param p_paramName
	 * @return
	 */
	int getIntParameter(String p_paramName);

	/**
	 * A convenience method to return a parameter as a int.
	 *
	 * @param p_paramName
	 * @param p_defaultValue
	 */
	int getIntParameter(String p_paramName, int p_defaultValue);

	/**
	 * A convenience method to return a parameter as a long.
	 *
	 * @param p_paramName
	 * @return
	 */
	long getLongParameter(String p_paramName);

	/**
	 * A convenience method to return a parameter as a long.
	 *
	 * @param p_paramName
	 * @param p_defaultValue
	 */
	long getLongParameter(String p_paramName, long p_defaultValue);

	/**
	 * Gets a named parameter as an Object.
	 *
	 * @param p_paramName
	 * @return
	 */
	Object getObjectParameter(String p_paramName);

	/**
	 * A convenience method to return a parameter as a object.
	 *
	 * @param p_paramName
	 * @param p_defaultValue
	 */
	Object getObjectParameter(String p_paramName, Object p_defaultValue);

	/**
	 * A convenience method to return a parameter as a short.
	 *
	 * @param p_paramName
	 * @return
	 */
	short getShortParameter(String p_paramName);

	/**
	 * A convenience method to return a parameter as a short.
	 *
	 * @param p_paramName
	 * @param p_defaultValue
	 * @return
	 */
	short getShortParameter(String p_paramName, short p_defaultValue);

	/**
	 * Gets a named parameter as an string.
	 *
	 * @param p_paramName
	 * @return
	 */
	String getStringParameter(String p_paramName);

	/**
	 * A convenience method to return a parameter as a string.
	 *
	 * @param p_paramName
	 * @param p_defaultValue
	 */
	String getStringParameter(String p_paramName, String p_defaultValue);

	/**
	 * Sets a named parameter to a boolean value.
	 *
	 * @param p_paramName
	 * @param p_value
	 */
	void setParameter(String p_paramName, boolean p_value);

	/**
	 * Sets a named parameter to a byte value.
	 *
	 * @param p_paramName
	 * @param p_value
	 * @return
	 */
	void setParameter(String p_paramName, byte p_value);

	/**
	 * Sets a named parameter to a char value.
	 *
	 * @param p_paramName
	 * @param p_value
	 */
	void setParameter(String p_paramName, char p_value);

	/**
	 * Sets a named parameter to a double value.
	 *
	 * @param p_paramName
	 * @param p_value
	 */
	void setParameter(String p_paramName, double p_value);

	/**
	 * Sets a named parameter to a float value.
	 *
	 * @param p_paramName
	 * @param p_value
	 */
	void setParameter(String p_paramName, float p_value);

	/**
	 * Sets a named parameter to an int value.
	 *
	 * @param p_paramName
	 * @param p_value
	 */
	void setParameter(String p_paramName, int p_value);

	/**
	 * Sets a named parameter to a long value.
	 *
	 * @param p_paramName
	 * @param p_value
	 */
	void setParameter(String p_paramName, long p_value);

	/**
	 * Sets a named parameter to an Object value.
	 *
	 * @param p_paramName
	 * @param p_value
	 */
	void setParameter(String p_paramName, Object p_value);

	/**
	 * Sets a named parameter to an short value.
	 *
	 * @param p_paramName
	 * @param p_value
	 */
	void setParameter(String p_paramName, short p_value);
}
