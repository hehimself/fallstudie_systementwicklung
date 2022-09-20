package de.dhbw.barcodes.provider.ean;

import java.util.Properties;

import de.dhbw.barcodes.provider.api.IBarcodeProvider;
import de.dhbw.barcodes.provider.api.IBarcodeProviderFactory;

public class BarcodeProviderFactory implements IBarcodeProviderFactory
{
	@Override
	public String getType()
	{
		return BarcodeProviderConfig.PROVIDER_TYPE_ID;
	}

	@Override
	public String getDescription()
	{
		return "TODO: add your description here";
	}

	@Override
	public IBarcodeProvider createNewServiceProvider(final String p_uid, final Properties p_properties)
	{
		// TODO: implement your logic here
		throw new UnsupportedOperationException();
	}

	@Override
	public Properties getDefaultProperties()
	{
		// TODO: implement your logic here
		throw new UnsupportedOperationException();
	}

	@Override
	public void init()
	{
		// TODO: implement your logic here
		throw new UnsupportedOperationException();
	}

	@Override
	public void destroy()
	{
		// TODO: implement your logic here
		throw new UnsupportedOperationException();
	}
}
