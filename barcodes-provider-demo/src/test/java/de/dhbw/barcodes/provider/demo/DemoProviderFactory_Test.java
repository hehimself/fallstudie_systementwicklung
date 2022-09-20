package de.dhbw.barcodes.provider.demo;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Properties;
import java.util.ServiceLoader;
import java.util.UUID;

import org.junit.jupiter.api.ClassOrderer;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestClassOrder;
import org.junit.jupiter.api.TestMethodOrder;

import de.dhbw.barcodes.provider.api.IBarcodeProvider;
import de.dhbw.barcodes.provider.api.IBarcodeProviderFactory;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@TestClassOrder(ClassOrderer.OrderAnnotation.class)
@Order(3)
public class DemoProviderFactory_Test
{
	@Test
	@DisplayName("instantiate factory object")
	@Order(1)
	void test_1()
	{
		IBarcodeProviderFactory l_factory = new DemoProviderFactory();

		assertEquals(DemoProviderConfig.PROVIDER_TYPE_ID, l_factory.getType());
	}

	@Test
	@DisplayName("instantiate factory object and read default properties")
	@Order(2)
	void test_2()
	{
		IBarcodeProviderFactory l_factory = new DemoProviderFactory();

		Properties l_properties = l_factory.getDefaultProperties();

		assertNotNull(l_properties, "default properties object was null");

		assertEquals(String.valueOf(DemoProviderConfig.DEFAULT_IMAGEHIGHT),
		        l_properties.get(DemoProviderConfig.PROPERTY_BARCODE_API_IMAGEHEIGHT));
		assertEquals(String.valueOf(DemoProviderConfig.DEFAULT_IMAGEWIDTH),
		        l_properties.get(DemoProviderConfig.PROPERTY_BARCODE_API_IMAGEWIDTH));
		assertEquals(String.valueOf(DemoProviderConfig.DEFAULT_IMAGETYPE),
		        l_properties.get(DemoProviderConfig.PROPERTY_BARCODE_API_IMAGETYPE));
	}

	@Test
	@DisplayName("instantiate provider object")
	@Order(3)
	void test_3()
	{
		Properties l_props = new Properties();

		IBarcodeProviderFactory l_factory = new DemoProviderFactory();

		final String l_rndProviderId = UUID.randomUUID().toString();

		IBarcodeProvider l_provider = l_factory.createNewServiceProvider(l_rndProviderId, l_props);

		assertNotNull(l_provider, "provider object was null");
		assertEquals(l_rndProviderId, l_provider.getId());
	}

	@Test
	@DisplayName("instantiate factory object threw ServiceLoader")
	@Order(4)
	void test_4()
	{
		ServiceLoader<IBarcodeProviderFactory> serviceLoader = ServiceLoader.load(IBarcodeProviderFactory.class);

		assertTrue(serviceLoader.stream()
		        .anyMatch(fac -> DemoProviderConfig.PROVIDER_TYPE_ID.equals(fac.get().getType())));
	}
}
