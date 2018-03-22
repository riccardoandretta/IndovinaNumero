package it.polito.tdp.indonumero;

import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.fxml.FXMLLoader;

public class Main extends Application { // Separiamo ora la logica dei dati, da quella dell'interfaccia
	@Override
	public void start(Stage primaryStage) {
		try {
			
			//PATTERN DA SEGUIRE - MVC

			FXMLLoader loader = new FXMLLoader(getClass().getResource("IndoNumero.fxml"));
			BorderPane root = (BorderPane) loader.load();
			Scene scene = new Scene(root);
			scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());

			Model model = new Model();
			((IndoNumeroController) loader.getController()).setModel(model); // devo collegare il modello al controller,
																				// ma l'oggetto controller non l'ho
																				// creato e non so dov'è; l'ha creato il
																				// FXMLLoader, quindi me lo faccio dire
																				// da lui

			primaryStage.setScene(scene);
			primaryStage.show();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		launch(args);
	}
}
