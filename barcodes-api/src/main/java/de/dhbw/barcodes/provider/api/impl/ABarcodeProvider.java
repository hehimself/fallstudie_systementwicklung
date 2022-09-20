package de.dhbw.barcodes.provider.api.impl;

import de.dhbw.barcodes.provider.api.IBarcodeProvider;
import de.dhbw.barcodes.provider.api.IBarcodeProviderConfig;

public abstract class ABarcodeProvider implements IBarcodeProvider
{
	private final String uid;
	private final IBarcodeProviderConfig config;

	protected ABarcodeProvider(final String p_uid, final IBarcodeProviderConfig p_config)
	{
		this.uid = p_uid;
		this.config = p_config;
	}

	@Override
	public String getId()
	{
		return uid;
	}

	@Override
	public IBarcodeProviderConfig getConfig()
	{
		return config;
	}
}
