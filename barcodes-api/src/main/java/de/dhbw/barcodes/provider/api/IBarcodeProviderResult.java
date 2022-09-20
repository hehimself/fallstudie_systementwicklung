package de.dhbw.barcodes.provider.api;

public interface IBarcodeProviderResult
{
	int getImageWidth();

	int getImageHeight();

	byte[] getImageData();

	String getImageType();
}
