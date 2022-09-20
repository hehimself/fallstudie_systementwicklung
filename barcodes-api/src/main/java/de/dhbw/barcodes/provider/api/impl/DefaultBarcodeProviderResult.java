package de.dhbw.barcodes.provider.api.impl;

import de.dhbw.barcodes.provider.api.IBarcodeProviderResult;

public class DefaultBarcodeProviderResult implements IBarcodeProviderResult
{
	private int imageWidth;
	private int imageHeight;
	private byte[] imageData;
	private String imageType;

	public void setImageWidth(final int p_imageWidth)
	{
		imageWidth = p_imageWidth;
	}

	public void setImageHeight(final int p_imageHeight)
	{
		imageHeight = p_imageHeight;
	}

	public void setImageData(final byte[] p_imageData)
	{
		imageData = p_imageData;
	}

	public void setImageType(final String p_imageType)
	{
		imageType = p_imageType;
	}

	@Override
	public int getImageWidth()
	{
		return imageWidth;
	}

	@Override
	public int getImageHeight()
	{
		return imageHeight;
	}

	@Override
	public byte[] getImageData()
	{
		return imageData;
	}

	@Override
	public String getImageType()
	{
		return imageType;
	}

}
