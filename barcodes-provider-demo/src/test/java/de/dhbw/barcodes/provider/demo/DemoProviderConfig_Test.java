package de.dhbw.barcodes.provider.demo;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.Properties;

import org.junit.jupiter.api.ClassOrderer;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestClassOrder;
import org.junit.jupiter.api.TestMethodOrder;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@TestClassOrder(ClassOrderer.OrderAnnotation.class)
@Order(1)

public class DemoProviderConfig_Test
{

	@Test
	@DisplayName("instantiate config object")
	@Order(1)
	void loadTest_1()
	{
		Properties l_props = new Properties();

		DemoProviderConfig l_config = DemoProviderConfig.createConfig(l_props);

		assertNotNull(l_config, "Config object was null");

		assertEquals(DemoProviderConfig.DEFAULT_IMAGEHIGHT, l_config.getImageHeight());
		assertEquals(DemoProviderConfig.DEFAULT_IMAGETYPE, l_config.getImageType());
		assertEquals(DemoProviderConfig.DEFAULT_IMAGEWIDTH, l_config.getImageWidth());
	}

	@Test
	@DisplayName("load config data")
	@Order(2)
	void loadTest_2()
	{
		final int l_imageHeight = 551;
		final String l_imageType = "jpg";
		final int l_imageWidth = 10;

		Properties l_props = new Properties();
		l_props.setProperty(DemoProviderConfig.PROPERTY_BARCODE_API_IMAGEHEIGHT, String.valueOf(l_imageHeight));
		l_props.setProperty(DemoProviderConfig.PROPERTY_BARCODE_API_IMAGETYPE, l_imageType);
		l_props.setProperty(DemoProviderConfig.PROPERTY_BARCODE_API_IMAGEWIDTH, String.valueOf(l_imageWidth));

		DemoProviderConfig l_config = DemoProviderConfig.createConfig(l_props);

		assertNotNull(l_config, "Config object was null");

		assertEquals(l_imageHeight, l_config.getImageHeight());
		assertEquals(l_imageType, l_config.getImageType());
		assertEquals(l_imageWidth, l_config.getImageWidth());
	}

}
