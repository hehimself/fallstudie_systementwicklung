package de.dhbw.barcodes.provider.demo;

import java.util.Properties;

import de.dhbw.barcodes.provider.api.IBarcodeProvider;
import de.dhbw.barcodes.provider.api.IBarcodeProviderFactory;

public class DemoProviderFactory implements IBarcodeProviderFactory
{
	@Override
	public String getType()
	{
		return DemoProviderConfig.PROVIDER_TYPE_ID;
	}

	@Override
	public String getDescription()
	{
		return "Simple Demo Barcode Provider";
	}

	@Override
	public IBarcodeProvider createNewServiceProvider(final String p_uid, final Properties p_properties)
	{
		Properties l_mergeProperties = getDefaultProperties();
		l_mergeProperties.putAll(p_properties);

		DemoProviderConfig l_translaterConfig = DemoProviderConfig.createConfig(p_properties);
		DemoProvider l_translatorService = new DemoProvider(p_uid, l_translaterConfig);
		return l_translatorService;

	}

	@Override
	public Properties getDefaultProperties()
	{
		return DemoProviderConfig.getDefaultProperties();
	}

	@Override
	public void init()
	{
		// TODO Auto-generated method stub

	}

	@Override
	public void destroy()
	{
		// TODO Auto-generated method stub

	}
}
