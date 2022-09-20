package de.dhbw.barcodes.provider.api.exception;

/**
 *
 * @author usimschleg
 *
 */
public class BarcodeProviderException extends Exception
{
	/**
	 *
	 */
	private static final long serialVersionUID = 1L;

	/**
	 *
	 * @param p_message
	 * @param p_cause
	 * @param p_enableSuppression
	 * @param p_writableStackTrace
	 */
	public BarcodeProviderException(final String p_message, final Throwable p_cause, final boolean p_enableSuppression,
	        final boolean p_writableStackTrace)
	{
		super(p_message, p_cause, p_enableSuppression, p_writableStackTrace);
	}

	/**
	 *
	 * @param p_message
	 * @param p_cause
	 */
	public BarcodeProviderException(final String p_message, final Throwable p_cause)
	{
		super(p_message, p_cause);
	}

	/**
	 *
	 * @param p_message
	 */
	public BarcodeProviderException(final String p_message)
	{
		super(p_message);
	}

	/**
	 *
	 * @param p_cause
	 */
	public BarcodeProviderException(final Throwable p_cause)
	{
		super(p_cause);
	}

	public static BarcodeProviderException createBarcodeNotSupportedException(final String p_barcodeName)
	{
		return new BarcodeProviderException(String.format("Barcode '%s' not suported!", p_barcodeName));
	}

}
