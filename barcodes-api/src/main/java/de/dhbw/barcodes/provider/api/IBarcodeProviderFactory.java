package de.dhbw.barcodes.provider.api;

import java.util.Properties;

/**
 * The Translation Barcode Provider Factory Interface
 *
 * @author usimschleg
 *
 */
public interface IBarcodeProviderFactory
{
	/**
	 * Returns the unique Provider Type.
	 *
	 * @return Provider Type
	 */
	String getType();

	/**
	 * Returns a short description.
	 *
	 * @return Provider description
	 */
	String getDescription();

	/**
	 * Returns a Service Provider Instance. <br />
	 * Every time this method will be called, a new provider instance should be
	 * returned.
	 *
	 * @param p_uid        A unique provider name / id
	 * @param p_properties Init properties
	 *
	 * @return Provider instance
	 */
	IBarcodeProvider createNewServiceProvider(final String p_uid, final Properties p_properties);

	/**
	 * Returns the default properties for a provider.
	 *
	 * @return Default properties
	 */
	Properties getDefaultProperties();

	/**
	 * This method is typically called after the factory instance is created with
	 * the new operator.
	 * <p>
	 * It is used for additional initialization of resources.
	 */
	void init();

	/**
	 * This method is normally called before the factory instance is removed and so
	 * no longer used.
	 * <p>
	 * It is used to release no longer needed and blocked resources.
	 */
	void destroy();

}
