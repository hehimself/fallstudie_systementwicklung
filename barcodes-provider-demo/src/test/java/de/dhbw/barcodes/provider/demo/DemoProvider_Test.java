package de.dhbw.barcodes.provider.demo;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Properties;
import java.util.UUID;

import org.junit.jupiter.api.ClassOrderer;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestClassOrder;
import org.junit.jupiter.api.TestMethodOrder;

import de.dhbw.barcodes.provider.api.IBarcodeProvider;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@TestClassOrder(ClassOrderer.OrderAnnotation.class)
@Order(2)
public class DemoProvider_Test
{

	@Test
	@DisplayName("instantiate provider object")
	@Order(1)
	void test_1()
	{
		Properties l_props = new Properties();
		DemoProviderConfig l_config = DemoProviderConfig.createConfig(l_props);

		String l_rndProviderId = UUID.randomUUID().toString();

		IBarcodeProvider l_provider = new DemoProvider(l_rndProviderId, l_config);

		assertEquals(l_rndProviderId, l_provider.getId());
		assertEquals(DemoProviderConfig.PROVIDER_TYPE_ID, l_provider.getType());
	}

	@Test
	@DisplayName("imagetype support")
	@Order(2)
	void test_2()
	{
		Properties l_props = new Properties();
		DemoProviderConfig l_config = DemoProviderConfig.createConfig(l_props);

		IBarcodeProvider l_provider = new DemoProvider("any_id", l_config);

		assertTrue(l_provider.isImageTypeSupported("png"), "png not supported");
		assertTrue(l_provider.isImageTypeSupported("jpg"), "jpg not supported");

		assertFalse(l_provider.isImageTypeSupported("bmp"), "bmp supported");
	}

}
