package de.dhbw.barcodes.provider.demo;

import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;

import javax.imageio.ImageIO;

import de.dhbw.barcodes.provider.api.IBarcodeProviderParameters;
import de.dhbw.barcodes.provider.api.IBarcodeProviderResult;
import de.dhbw.barcodes.provider.api.exception.BarcodeProviderException;
import de.dhbw.barcodes.provider.api.impl.ABarcodeProvider;
import de.dhbw.barcodes.provider.api.impl.DefaultBarcodeProviderParametersImpl;
import de.dhbw.barcodes.provider.api.impl.DefaultBarcodeProviderResult;

public class DemoProvider extends ABarcodeProvider
{

	DemoProvider(final String p_uid, final DemoProviderConfig p_config)
	{
		super(p_uid, p_config);
	}

	@Override
	public String getType()
	{
		return DemoProviderConfig.PROVIDER_TYPE_ID;
	}

	@Override
	public IBarcodeProviderResult createBarcode(final String p_dataToEncode,
	        final IBarcodeProviderParameters p_parameters) throws BarcodeProviderException
	{
		DemoProviderConfig l_config = (DemoProviderConfig) getConfig();

		String l_imageType = p_parameters.getStringParameter(DefaultBarcodeProviderParametersImpl.PARAMETER_IMAGETYPE,
		        l_config.getImageType());
		int l_imageWidth = p_parameters.getIntParameter(DefaultBarcodeProviderParametersImpl.PARAMETER_IMAGEWIDTH,
		        l_config.getImageWidth());
		int l_imageHeight = p_parameters.getIntParameter(DefaultBarcodeProviderParametersImpl.PARAMETER_IMAGEHEIGHT,
		        l_config.getImageHeight());

		BufferedImage l_image = new BufferedImage(l_imageWidth, l_imageHeight, BufferedImage.TYPE_INT_RGB);

		Font font = new Font("Arial", Font.BOLD, 18);

		Graphics l_graphics = l_image.getGraphics();

		FontMetrics l_metrics = l_graphics.getFontMetrics(font);
		int l_positionX = (l_image.getWidth() - l_metrics.stringWidth(p_dataToEncode)) / 2;
		int l_positionY = (l_image.getHeight() - l_metrics.getHeight()) / 2 + l_metrics.getAscent();

		l_graphics.setFont(font);
		l_graphics.setColor(Color.GREEN);

		l_graphics.drawString(p_dataToEncode, l_positionX, l_positionY);

		DefaultBarcodeProviderResult l_result = new DefaultBarcodeProviderResult();
		l_result.setImageHeight(l_image.getHeight());
		l_result.setImageWidth(l_image.getWidth());
		l_result.setImageType(l_imageType);

		try (ByteArrayOutputStream l_data = new ByteArrayOutputStream())
		{
			ImageIO.write(l_image, l_imageType, l_data);
			l_data.flush();
			l_result.setImageData(l_data.toByteArray());
		}
		catch (IOException ex)
		{
			throw new BarcodeProviderException("Could notserialize image data!", ex);
		}

		return l_result;
	}

	@Override
	public boolean isImageTypeSupported(final String p_imageType)
	{
		if (p_imageType == null)
		{
			return false;
		}

		switch (p_imageType.toLowerCase())
		{
		case "png":
		case "jpg":
			return true;
		default:
			return false;
		}
	}
}
