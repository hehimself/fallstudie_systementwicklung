package de.dhbw.barcodes.provider.qrcode;

import de.dhbw.barcodes.provider.api.IBarcodeProviderParameters;
import de.dhbw.barcodes.provider.api.IBarcodeProviderResult;
import de.dhbw.barcodes.provider.api.exception.BarcodeProviderException;
import de.dhbw.barcodes.provider.api.impl.ABarcodeProvider;

public class BarcodeProvider extends ABarcodeProvider
{

	BarcodeProvider(final String p_uid, final BarcodeProviderConfig p_config)
	{
		super(p_uid, p_config);
	}

	@Override
	public String getType()
	{
		return BarcodeProviderConfig.PROVIDER_TYPE_ID;
	}

	@Override
	public IBarcodeProviderResult createBarcode(final String p_dataToEncode,
	        final IBarcodeProviderParameters p_parameters) throws BarcodeProviderException
	{
		// TODO: implement your logic here
		throw new UnsupportedOperationException();
	}

	@Override
	public boolean isImageTypeSupported(final String p_imageType)
	{
		// TODO: implement your logic here
		throw new UnsupportedOperationException();
	}
}
