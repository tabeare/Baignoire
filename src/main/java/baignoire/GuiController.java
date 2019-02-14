package baignoire;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.Slider;

public class GuiController {
	@FXML
	private ComboBox<Integer> combobox;
	@FXML
	private Label texte;
	@FXML
	private Slider slider_entree;
	@FXML
	private Slider slider_fuite;
	@FXML
	private ProgressBar progressbar;
	
	ScheduledExecutorService pool = Executors.newScheduledThreadPool(3);
	
	@FXML
	public void initialize() {
		combobox.getItems().addAll(100, 110, 120, 130, 140, 150, 160, 170, 180, 190, 200, 210, 220, 230, 240, 250);
		combobox.getSelectionModel().selectFirst();
		
		final Baignoire baignoire = new Baignoire(combobox.getSelectionModel().getSelectedItem()*100);

		final Runnable arret = new Runnable() {
			public void run() {
				while (!baignoire.isFull()) {
					try {
						baignoire.setCapacite(combobox.getSelectionModel().getSelectedItem()*100);
						progressbar.setProgress((double)baignoire.getFill()/baignoire.getCapacite());
						
						Integer sup = (int)slider_entree.getValue()/100;
						Integer ded = (int)slider_fuite.getValue()/100;
						String text = baignoire.temps(sup, ded);
						Platform.runLater(() -> texte.setText(text));
						
//						System.out.println(baignoire.isFull());
//						if (baignoire.isFull()) {
//							Alert alert = new Alert(AlertType.INFORMATION);
//							alert.setTitle("Information Dialog");
//							alert.setHeaderText("Look, an Information Dialog");
//							alert.setContentText("La Baignoire est pleine!");
//							alert.showAndWait();
//						}
//						
						Thread.sleep(10);
					} catch (InterruptedException e) {
					}
					
					pool.shutdown();
				}
			}
		};
		
		final Runnable fill = new Runnable() {
			public void run() {
				while (!baignoire.isFull()) {
					try {
						double sup = (double)slider_entree.getValue()/100;
						baignoire.remplirBaignoire(sup);
						Thread.sleep(10);
		
					} catch (InterruptedException e) {
					}
					pool.shutdown();
				}
			}
		};
		
		final Runnable empty = new Runnable() {
			public void run() {
				while (!baignoire.isFull()) {
					try {
						double ded = (double)slider_fuite.getValue()/100;
						baignoire.viderBaignoire(ded);
						Thread.sleep(10);
								
					} catch (InterruptedException e) {
					}
					pool.shutdown();
				}
			}
		};
		
		pool.execute(arret);
		pool.execute(fill);
		pool.execute(empty);
		
	}
}
