package de.dhbw.barcodes.provider.demo;

import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.Properties;
import java.util.TreeSet;

import de.dhbw.barcodes.provider.api.impl.ABarcodeProviderConfig;

public class DemoProviderConfig extends ABarcodeProviderConfig
{
	public static final String PROVIDER_TYPE_ID = "demo";

	public static final String PROPERTY_BARCODE_API_IMAGEWIDTH = "image-width";
	public static final String PROPERTY_BARCODE_API_IMAGEHEIGHT = "image-height";
	public static final String PROPERTY_BARCODE_API_IMAGETYPE = "image-type";

	public static final Collection<String> VALID_PROPERTY_NAMES = Collections
	        .unmodifiableSet(new TreeSet<>(Arrays.asList(PROPERTY_BARCODE_API_IMAGEWIDTH,
	                PROPERTY_BARCODE_API_IMAGEHEIGHT, PROPERTY_BARCODE_API_IMAGETYPE)));

	static final int DEFAULT_IMAGEWIDTH = 50;
	static final int DEFAULT_IMAGEHIGHT = 50;
	static final String DEFAULT_IMAGETYPE = "png";

	private int imageWidth = DEFAULT_IMAGEWIDTH;
	private int imageHeight = DEFAULT_IMAGEHIGHT;
	private String imageType = DEFAULT_IMAGETYPE;

	private DemoProviderConfig()
	{

	}

	public static Properties getDefaultProperties()
	{
		Properties l_properties = new Properties();

		l_properties.setProperty(DemoProviderConfig.PROPERTY_BARCODE_API_IMAGEWIDTH,
		        String.valueOf(DEFAULT_IMAGEWIDTH));
		l_properties.setProperty(DemoProviderConfig.PROPERTY_BARCODE_API_IMAGEHEIGHT,
		        String.valueOf(DEFAULT_IMAGEHIGHT));
		l_properties.setProperty(DemoProviderConfig.PROPERTY_BARCODE_API_IMAGETYPE, String.valueOf(DEFAULT_IMAGETYPE));

		return l_properties;
	}

	public static DemoProviderConfig createConfig(final Properties p_properties)
	{
		DemoProviderConfig l_newConfig = new DemoProviderConfig();

		l_newConfig.load(p_properties);

		return l_newConfig;
	}

	public int getImageWidth()
	{
		return imageWidth;
	}

	public int getImageHeight()
	{
		return imageHeight;
	}

	public String getImageType()
	{
		return imageType;
	}

	@Override
	public Collection<String> getValidPropertyNames()
	{
		return VALID_PROPERTY_NAMES;
	}

	@Override
	protected void load(final Properties p_properties)
	{
		super.load(p_properties);

		imageWidth = Integer.parseInt(
		        p_properties.getProperty(PROPERTY_BARCODE_API_IMAGEWIDTH, String.valueOf(DEFAULT_IMAGEWIDTH)));

		imageHeight = Integer.parseInt(
		        p_properties.getProperty(PROPERTY_BARCODE_API_IMAGEHEIGHT, String.valueOf(DEFAULT_IMAGEHIGHT)));

		imageType = p_properties.getProperty(PROPERTY_BARCODE_API_IMAGETYPE, String.valueOf(DEFAULT_IMAGETYPE));
	}

}
