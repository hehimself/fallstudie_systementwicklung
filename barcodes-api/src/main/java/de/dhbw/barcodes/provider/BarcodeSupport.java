package de.dhbw.barcodes.provider;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.ServiceConfigurationError;
import java.util.ServiceLoader;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import de.dhbw.barcodes.provider.api.IBarcodeProviderFactory;

/**
 * The Barcode provider entry point.
 *
 * @author usimschleg
 *
 */
public class BarcodeSupport
{
	/**
	 * Logger
	 */
	private static final Logger LOG = LoggerFactory.getLogger(BarcodeSupport.class);

	/**
	 * Holds all registered provider factories
	 */
	private List<IBarcodeProviderFactory> providerFactories;

	/**
	 * Private constructor ;-)
	 */
	private BarcodeSupport()
	{
		providerFactories = initProviderFactories();
	}

	/**
	 * Returns all registered provider factories
	 *
	 * @return All provider factories
	 */
	public Iterable<IBarcodeProviderFactory> providerFactories()
	{
		return providerFactories;
	}

	/**
	 * Checks if some provider factories are loaded/registered
	 *
	 * @return <code>true</code> or <code>false</code>
	 */
	public boolean hasProviderFactories()
	{
		return !providerFactories.isEmpty();
	}

	/**
	 * Checks if a provider factory is loaded/registered withe given id
	 *
	 * @param p_type Unique provider type
	 *
	 * @return <code>true</code> or <code>false</code>
	 */
	public boolean hasProviderFactoryWithType(final String p_type)
	{
		Objects.requireNonNull(p_type, "type must not be null");

		return providerFactories.stream().anyMatch(fac -> p_type.equals(fac.getType()));
	}

	/**
	 * Returns a factory for given unique type
	 *
	 * @param p_type Unique provider factory type
	 *
	 * @return Provider factory
	 */
	public Optional<IBarcodeProviderFactory> getProviderFactoryByType(final String p_type)
	{
		Objects.requireNonNull(p_type, "type must not be null");

		return providerFactories.stream().filter(fac -> p_type.equals(fac.getType())).findFirst();
	}

	/**
	 * Destroy/unload loaded provider factories
	 */
	public void destroy()
	{
		destroyLoadedProviderFactories();
	}

	/**
	 * Loads and init registered service provider
	 *
	 * @return Unmodifiable list of loaded provider factories
	 */
	private List<IBarcodeProviderFactory> initProviderFactories()
	{
		ServiceLoader<IBarcodeProviderFactory> l_loader;
		try
		{
			l_loader = ServiceLoader.load(IBarcodeProviderFactory.class);
		}
		catch (ServiceConfigurationError ex)
		{
			LOG.warn("ServiceProviderFactory could not load provider factories.", ex);

			return Collections.emptyList();
		}

		List<IBarcodeProviderFactory> l_initializedProviderFactories = new ArrayList<>(5);

		for (IBarcodeProviderFactory l_providerFactory : getIterableFromIterator(l_loader.iterator()))
		{
			try
			{
				LOG.info("ServiceProviderFactory {} found", l_providerFactory.getClass().getName());

				l_providerFactory.init();

				LOG.info("ServiceProviderFactory {} initialized", l_providerFactory.getClass().getName());

				l_initializedProviderFactories.add(l_providerFactory);

				LOG.info("ServiceProviderFactory {} registered", l_providerFactory.getClass().getName());
			}
			catch (Exception initEx)
			{
				if (LOG.isDebugEnabled())
				{
					LOG.warn("ServiceProviderFactory {} not initialized: {}", l_providerFactory.getClass().getName(),
					        initEx.getMessage(), initEx);
				}
				else
				{
					LOG.warn("ServiceProviderFactory {} not initialized: {}", l_providerFactory.getClass().getName(),
					        initEx.getMessage());
				}

				try
				{
					l_providerFactory.destroy();
				}
				catch (Exception destroyEx)
				{
					LOG.debug("ServiceProviderFactory {} not destroyed!", l_providerFactory.getClass().getName(),
					        destroyEx);
				}

				LOG.info("ServiceProviderFactory {} not registered", l_providerFactory.getClass().getName());
			}
		}

		return Collections.unmodifiableList(l_initializedProviderFactories);
	}

	/**
	 * Destroys all loaded providers
	 */
	private void destroyLoadedProviderFactories()
	{
		for (IBarcodeProviderFactory l_providerFactory : providerFactories)
		{
			try
			{
				l_providerFactory.destroy();
				LOG.info("ServiceProviderFactory {} destroyed successful", l_providerFactory.getClass().getName());
			}
			catch (Exception destroyEx)
			{
				LOG.warn("ServiceProviderFactory {} not destroyed", l_providerFactory.getClass().getName(), destroyEx);
			}
		}
	}

	/**
	 * Returns the singleton instance
	 *
	 * @return Instance
	 */
	public static BarcodeSupport getInstance()
	{
		return BarcodeServiceSingleton.INSTANCE;
	}

	/**
	 * Helper class to initialize the singleton instance without need of
	 * synchronization blocks
	 *
	 * @author usimschleg
	 *
	 */
	private static class BarcodeServiceSingleton
	{
		private static final BarcodeSupport INSTANCE = new BarcodeSupport();
	}

	/**
	 * Helper method to create an iterable from iterator
	 *
	 * @param <T>        Generic type
	 * @param p_iterator The iterator
	 *
	 * @return the iterable
	 */
	private static <T> Iterable<T> getIterableFromIterator(final Iterator<T> p_iterator)
	{
		return () -> p_iterator;
	}

}
