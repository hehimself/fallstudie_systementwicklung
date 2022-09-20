package de.dhbw.barcodes.app;

import java.io.IOException;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ServiceLoader;

import de.dhbw.barcodes.provider.api.IBarcodeProvider;
import de.dhbw.barcodes.provider.api.IBarcodeProviderFactory;
import de.dhbw.barcodes.provider.api.IBarcodeProviderResult;
import de.dhbw.barcodes.provider.api.exception.BarcodeProviderException;
import de.dhbw.barcodes.provider.api.impl.DefaultBarcodeProviderParametersImpl;

public class ConsoleApp
{

	public ConsoleApp()
	{
		// TODO Auto-generated constructor stub
	}

	public static void main(final String[] args)
	{
		ServiceLoader<IBarcodeProviderFactory> serviceLoader = ServiceLoader.load(IBarcodeProviderFactory.class);

		serviceLoader.forEach(fac -> {
			System.out.println(fac.getType() + ":\n" + fac.getDescription());
		});

		IBarcodeProviderFactory l_demoFac = null;

		for (IBarcodeProviderFactory l_fac : serviceLoader)
		{
			if ("demo".equals(l_fac.getType()))
			{
				l_demoFac = l_fac;
				break;
			}
		}

		IBarcodeProvider l_demoBarcodeProvider = l_demoFac.createNewServiceProvider("demo1",
		        l_demoFac.getDefaultProperties());

		DefaultBarcodeProviderParametersImpl l_parameters = new DefaultBarcodeProviderParametersImpl();
		l_parameters.setParameter(DefaultBarcodeProviderParametersImpl.PARAMETER_IMAGETYPE, "png");

		try
		{
			IBarcodeProviderResult l_result = l_demoBarcodeProvider.createBarcode("Hallo", l_parameters);

			try (OutputStream l_out = Files.newOutputStream(Path.of(".", "demo-barcode." + l_result.getImageType())))
			{
				l_out.write(l_result.getImageData());
				l_out.flush();
			}
			catch (IOException ex)
			{
				// TODO Auto-generated catch block
				ex.printStackTrace();
			}
		}
		catch (BarcodeProviderException ex)
		{
			// TODO Auto-generated catch block
			ex.printStackTrace();
		}
	}

}
