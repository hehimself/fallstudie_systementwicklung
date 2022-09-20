package de.dhbw.barcodes.provider.api;

import de.dhbw.barcodes.provider.api.exception.BarcodeProviderException;

/**
 * The Provider Interface
 *
 * @author usimschleg
 *
 */
public interface IBarcodeProvider
{
	/**
	 * Returns the Provider Type.
	 *
	 * @return Provider Type Id
	 */
	String getType();

	/**
	 * Returns the unique Provider Id.
	 *
	 * @return Provider Id
	 */
	String getId();

	/**
	 * Returns the provider configuration.
	 *
	 * @return Configuration {@link IBarcodeProviderConfig}
	 */
	IBarcodeProviderConfig getConfig();

	/**
	 * Returns the unique Provider Id.
	 *
	 * @return Provider Id
	 */
	boolean isImageTypeSupported(String p_imageType);

	/**
	 * Creates the barcode.
	 *
	 * @param p_dataToEncode
	 * @param p_parameters
	 * @return
	 *
	 * @throws BarcodeProviderException
	 */
	IBarcodeProviderResult createBarcode(String p_dataToEncode, IBarcodeProviderParameters p_parameters)
	        throws BarcodeProviderException;
}
