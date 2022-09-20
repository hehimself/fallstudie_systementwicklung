package de.dhbw.barcodes.app;

import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.OutputStream;
import java.nio.file.Files;
import java.time.Instant;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Optional;
import java.util.ServiceLoader;
import java.util.stream.Collectors;

import org.controlsfx.control.PropertySheet;

import de.dhbw.barcodes.provider.api.IBarcodeProvider;
import de.dhbw.barcodes.provider.api.IBarcodeProviderFactory;
import de.dhbw.barcodes.provider.api.IBarcodeProviderResult;
import de.dhbw.barcodes.provider.api.impl.DefaultBarcodeProviderParametersImpl;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.beans.InvalidationListener;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.geometry.Insets;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.SeparatorMenuItem;
import javafx.scene.control.TextField;
import javafx.scene.control.ToolBar;
import javafx.scene.control.Tooltip;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

public class BarcodeTester extends Application
{

	public static void main(final String args[])
	{
		launch(args);
	}

	private final List<IBarcodeProviderFactory> l_factories;
	private IBarcodeProvider selectedProvider;
	private IBarcodeProviderResult lastResult;
	private ObservableList<PropertySheet.Item> propertiesList;

	private ImageView barcodeImageView;
	private TextField dataField;

	public BarcodeTester()
	{
		propertiesList = FXCollections.observableArrayList();

		ServiceLoader<IBarcodeProviderFactory> serviceLoader = ServiceLoader.load(IBarcodeProviderFactory.class);
		l_factories = serviceLoader.stream().map(v -> v.get())
		        .sorted((fac1, fac2) -> fac1.getType().compareTo(fac2.getType())).collect(Collectors.toList());
	}

	@Override
	public void start(final Stage p_primaryStage) throws Exception
	{
		barcodeImageView = new ImageView();
		dataField = new TextField();
		dataField.setTooltip(new Tooltip("Hier die zu codierenden Daten eingeben!"));

		MenuBar l_menuBar = new MenuBar();

		Menu l_barcodesMenu = new Menu("File");

		MenuItem l_saveMenuItem = new MenuItem("Speichern...");
		l_saveMenuItem.setOnAction(evt -> {
			if (lastResult == null)
			{
				return;
			}
			FileChooser fileChooser = new FileChooser();

			String l_imageType = lastResult.getImageType();
			String l_filterDescription = String.format("%1$s files (*.%2$s)", l_imageType.toUpperCase(),
			        l_imageType.toLowerCase());
			String l_filterExtension = String.format("*.%1$s", l_imageType.toLowerCase());
			fileChooser.getExtensionFilters()
			        .add(new FileChooser.ExtensionFilter(l_filterDescription, l_filterExtension));
			fileChooser.setInitialFileName(selectedProvider.getType() + "_" + DateTimeFormatter
			        .ofPattern("yyyyMMdd-HHmmss").withZone(ZoneId.systemDefault()).format(Instant.now()));

			File file = fileChooser.showSaveDialog(p_primaryStage);

			if (file != null)
			{
				try (OutputStream out = Files.newOutputStream(file.toPath()))
				{
					out.write(lastResult.getImageData());
					out.flush();
				}
				catch (Exception ex)
				{
					ex.printStackTrace();
					Alert l_alert = new Alert(AlertType.ERROR);
					l_alert.setContentText("Irgend etwas ging beim Speichern schief! Siehe Console-Stacktrace!");
					l_alert.show();
				}
			}
		});

		MenuItem l_exitMenuItem = new MenuItem("Beenden...");
		l_exitMenuItem.setOnAction(evt -> Platform.exit());

		l_menuBar.getMenus().addAll(l_barcodesMenu);
		l_barcodesMenu.getItems().addAll(l_saveMenuItem, new SeparatorMenuItem(), l_exitMenuItem);

		ObservableList<String> l_barcodeFactories = FXCollections.observableArrayList();
		l_factories.forEach(fac -> {
			l_barcodeFactories.add(fac.getType());
		});

		ChoiceBox<String> l_barcodeTypes = new ChoiceBox<>(l_barcodeFactories);
		l_barcodeTypes.setTooltip(new Tooltip("Wähle Barcode Provider!"));
		l_barcodeTypes.getSelectionModel().selectedIndexProperty().addListener(this::barcodeFactoryChanged);

		Button l_execute = new Button("Generate");
		l_execute.setTooltip(new Tooltip("Starte Generator-Prozess"));
		l_execute.setOnAction(this::process);

		ToolBar l_toolbar = new ToolBar(new Label("Barcode:"), l_barcodeTypes, dataField, l_execute);

		Group l_barcodeImageGroup = new Group(barcodeImageView);

		PropertySheet l_properties = new PropertySheet(propertiesList);
		l_properties.setSearchBoxVisible(false);

		VBox l_topBox = new VBox(l_menuBar, l_toolbar);

		VBox l_leftBox = new VBox();
		l_leftBox.setPadding(new Insets(10d));
		l_leftBox.setSpacing(15d);
		l_leftBox.fillWidthProperty().set(true);
		l_leftBox.getChildren().add(l_barcodeTypes);
		l_leftBox.getChildren().add(l_execute);
		l_leftBox.getChildren().add(l_properties);
		VBox.setVgrow(l_leftBox.getChildren().get(2), Priority.ALWAYS);

		BorderPane l_borderPane = new BorderPane(l_barcodeImageGroup);
		l_borderPane.setTop(l_topBox);
		l_borderPane.setLeft(l_leftBox);

		GraphicsDevice gd = GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice();
		int l_width = (int) (gd.getDisplayMode().getWidth() * 75 / 100d);
		int l_height = (int) (gd.getDisplayMode().getHeight() * 75 / 100d);

		Scene scene = new Scene(l_borderPane, l_width, l_height);
		p_primaryStage.setScene(scene);
		p_primaryStage.show();
	}

	private void process(final ActionEvent p_event)
	{
		if (selectedProvider != null)
		{
			try
			{
				lastResult = null;

				DefaultBarcodeProviderParametersImpl l_parameters = new DefaultBarcodeProviderParametersImpl();

				propertiesList.forEach(item -> {
					l_parameters.setParameter(item.getName(), item.getValue());
				});

				barcodeImageView.setImage(null);

				lastResult = selectedProvider.createBarcode(dataField.getText(), l_parameters);

				ByteArrayInputStream l_inputStream = new ByteArrayInputStream(lastResult.getImageData());

				Image l_imageToRender = new Image(l_inputStream);

				barcodeImageView.setImage(l_imageToRender);
			}
			catch (Exception ex)
			{
				ex.printStackTrace();
				Alert l_alert = new Alert(AlertType.ERROR);
				l_alert.setContentText("Irgend etwas ging beim Generieren schief! Siehe Console-Stacktrace!");
				l_alert.show();
			}
		}
	}

	private void barcodeFactoryChanged(final ObservableValue<? extends Number> p_observalable, final Number p_oldIndex,
	        final Number p_newIndex)
	{
		propertiesList.clear();
		lastResult = null;
		selectedProvider = null;
		barcodeImageView.setImage(null);

		try
		{
			IBarcodeProviderFactory l_selectedFactory = l_factories.get(p_newIndex.intValue());
			selectedProvider = l_selectedFactory.createNewServiceProvider("Selected Provider",
			        l_selectedFactory.getDefaultProperties());

			for (String l_name : selectedProvider.getConfig().getValidPropertyNames())
			{
				String l_value = selectedProvider.getConfig().getProperty(l_name, "");
				propertiesList.add(new StringPropertyItem(l_name, l_value));
			}
		}
		catch (Exception ex)
		{
			ex.printStackTrace();
			Alert l_alert = new Alert(AlertType.ERROR);
			l_alert.setContentText("Irgend etwas ging beim Laden des Providers schief! Siehe Console-Stacktrace!");
			l_alert.show();
		}
	}

	private static class StringPropertyItem implements PropertySheet.Item, ObservableValue<String>
	{
		private String value = "";
		private final String name;

		StringPropertyItem(final String p_name, final String p_value)
		{
			name = p_name;
			value = p_value == null ? "" : p_value;
		}

		@Override
		public Class<?> getType()
		{
			return String.class;
		}

		@Override
		public String getCategory()
		{
			return "Default";
		}

		@Override
		public String getName()
		{
			return name;
		}

		@Override
		public String getDescription()
		{
			return null;
		}

		@Override
		public void setValue(final Object p_value)
		{
			value = p_value == null ? "" : p_value.toString();
		}

		@Override
		public Optional<ObservableValue<? extends Object>> getObservableValue()
		{
			return Optional.of(this);
		}

		@Override
		public void addListener(final InvalidationListener p_listener)
		{
		}

		@Override
		public void removeListener(final InvalidationListener p_listener)
		{
		}

		@Override
		public void addListener(final ChangeListener<? super String> p_listener)
		{
		}

		@Override
		public void removeListener(final ChangeListener<? super String> p_listener)
		{
		}

		@Override
		public String getValue()
		{
			return value;
		}

	}
}
