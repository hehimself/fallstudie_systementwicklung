package de.dhbw.barcodes.provider.aztec;

import java.util.*;

import de.dhbw.barcodes.provider.api.impl.ABarcodeProviderConfig;

public class BarcodeProviderConfig extends ABarcodeProviderConfig
{
	public static final String PROVIDER_TYPE_ID = "aztec";
	public static final String PROPERTY_BARCODE_API_IMAGEWIDTH = "image-width";
	public static final String PROPERTY_BARCODE_API_IMAGEHEIGHT = "image-height";
	public static final String PROPERTY_BARCODE_API_IMAGETYPE = "image-type";

	public static final Collection<String> VALID_PROPERTY_NAMES = Collections
			.unmodifiableSet(new TreeSet<>(Arrays.asList(PROPERTY_BARCODE_API_IMAGEWIDTH,
					PROPERTY_BARCODE_API_IMAGEHEIGHT, PROPERTY_BARCODE_API_IMAGETYPE)));

	static public final int DEFAULT_IMAGEWIDTH = 50;
	static public final int DEFAULT_IMAGEHIGHT = 50;
	static public final String DEFAULT_IMAGETYPE = "png";

	private int imageWidth = DEFAULT_IMAGEWIDTH;
	private int imageHeight = DEFAULT_IMAGEHIGHT;
	private String imageType = DEFAULT_IMAGETYPE;

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

	private BarcodeProviderConfig()
	{

	}
	public static Properties getDefaultProperties()
	{
		Properties l_properties = new Properties();

		l_properties.setProperty(BarcodeProviderConfig.PROPERTY_BARCODE_API_IMAGEWIDTH,
				String.valueOf(DEFAULT_IMAGEWIDTH));
		l_properties.setProperty(BarcodeProviderConfig.PROPERTY_BARCODE_API_IMAGEHEIGHT,
				String.valueOf(DEFAULT_IMAGEHIGHT));
		l_properties.setProperty(BarcodeProviderConfig.PROPERTY_BARCODE_API_IMAGETYPE, String.valueOf(DEFAULT_IMAGETYPE));

		return l_properties;
	}

	public static BarcodeProviderConfig createConfig(final Properties p_properties)
	{
		BarcodeProviderConfig l_newConfig = new BarcodeProviderConfig();

		l_newConfig.load(p_properties);

		return l_newConfig;
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
