package de.dhbw.barcodes.provider.qrcode;

import java.util.Collection;
import java.util.Properties;

import de.dhbw.barcodes.provider.api.impl.ABarcodeProviderConfig;

public class BarcodeProviderConfig extends ABarcodeProviderConfig
{
	public static final String PROVIDER_TYPE_ID = "qrcode";

	@Override
	public Collection<String> getValidPropertyNames()
	{
		// TODO: implement your logic here
		throw new UnsupportedOperationException();
	}

	@Override
	protected void load(final Properties p_properties)
	{
		// TODO: implement your logic here
		throw new UnsupportedOperationException();
	}

}
