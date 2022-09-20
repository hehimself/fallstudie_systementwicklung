package de.dhbw.barcodes.provider.api;

import java.util.Collection;

/**
 * The Barcode Provider Config Interface.
 *
 * @author usimschleg
 *
 */
public interface IBarcodeProviderConfig
{
	/**
	 * Returns a unmodifiable collection of all valid property names.
	 *
	 * @return Property names
	 */
	Collection<String> getValidPropertyNames();

	/**
	 * Returns the value for a given property or null if the property doesn't
	 * exists.
	 *
	 * @param p_propertyName Name of the property
	 * @return value or null
	 */
	String getProperty(String p_propertyName);

	/**
	 * Returns the value for a given property or a given default value will be
	 * returned if the property doesn't exists.
	 *
	 * @param p_propertyName Name of the property
	 * @param p_defaultValue Default value
	 * @return value or default value
	 */
	String getProperty(String p_propertyName, String p_defaultValue);

	/**
	 * Returns the value for a given property as int value. If the property doesn't
	 * exists or the value cannot be parsed into a int value, <code>-1</code> must
	 * be returned.
	 *
	 * @param p_propertyName Name of the property
	 * @return value
	 */
	int getPropertyAsInteger(String p_propertyName);

	/**
	 * Returns the value for a given property as int value or a given default value
	 * will be returned if the property doesn't exists.
	 *
	 * @param p_propertyName Name of the property
	 * @param p_defaultValue Default value
	 * @return value or default value
	 */
	int getPropertyAsInteger(String p_propertyName, int p_defaultValue);

	/**
	 * Returns the value for a given property as boolean value. If the property
	 * doesn't exists or the value cannot be parsed into a boolean value,
	 * <code>false</code> must be returned.
	 *
	 * @param p_propertyName Name of the property
	 * @return value or false
	 */
	boolean getPropertyAsBoolean(String p_propertyName);

	/**
	 * Returns the value for a given property as boolean value or a given default
	 * value will be returned if the property doesn't exists.
	 *
	 * @param p_propertyName Name of the property
	 * @param p_defaultValue Default value
	 * @return value or default value
	 */
	boolean getPropertyAsBoolean(String p_propertyName, boolean p_defaultValue);

}
