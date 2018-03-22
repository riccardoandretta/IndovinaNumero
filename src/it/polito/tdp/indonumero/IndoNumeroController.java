/**
  * Sample Skeleton for 'IndoNumero.fxml' Controller Class
 */

package it.polito.tdp.indonumero;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;

public class IndoNumeroController {

	private Model model; // NON STO CREANDO IL MODELLO!! --> devo solo sapere dov'è --> qualcuno lo deve
							// settare

	@FXML // ResourceBundle that was given to the FXMLLoader
	private ResourceBundle resources;

	@FXML // URL location of the FXML file that was given to the FXMLLoader
	private URL location;

	@FXML // fx:id="btnNuova"
	private Button btnNuova; // Value injected by FXMLLoader

	@FXML // fx:id="txtCurr"
	private TextField txtCurr; // Value injected by FXMLLoader

	@FXML // fx:id="txtMax"
	private TextField txtMax; // Value injected by FXMLLoader

	@FXML // fx:id="boxGioco"
	private HBox boxGioco; // Value injected by FXMLLoader

	@FXML // fx:id="txtTentativo"
	private TextField txtTentativo; // Value injected by FXMLLoader

	@FXML // fx:id="btnProva"
	private Button btnProva; // Value injected by FXMLLoader

	@FXML // fx:id="txtLog"
	private TextArea txtLog; // Value injected by FXMLLoader

	@FXML
	void handleNuova(ActionEvent event) {

		model.newGame();

		// inizia la partita quindi devo abilitare/disabilitare i box
		btnNuova.setDisable(true); // disabilito
		boxGioco.setDisable(false); // abilito
		txtCurr.setText(String.format("%d", model.getTentativi())); // trasformo il numero in stringa
		txtMax.setText(String.format("%d", model.getTMAX()));
		txtLog.clear();
		txtTentativo.clear();

		txtLog.setText(String.format("Indovina un numero tra %d e %d\n", 1, model.getNMAX()));
	}

	@FXML
	void handleProva(ActionEvent event) { // il compito del controller è sempre quella di gestire l'interazione con
											// l'utente

		// rimane di pertinenza del controller
		String numS = txtTentativo.getText();
		// verifico che abbia inserito qualcosa
		if (numS.length() == 0) {
			txtLog.appendText("Devi inserire un numero\n");
			return;
		}
		try {
			int num = Integer.parseInt(numS); // rimane di pertinenza del controller (nel model non ho stringhe, ma
												// interi e non devo gestire valori non "puliti");
												// il model lavora con gli oggetti, non con le stringhe
			// numero era effettivamente un intero
			// if (num < 1 || num > model.getNMAX()) { // questa riga l'ho già scritta nel
			// model: sto definendo una regola
			// di validità del dato, che può cambiare col tempo, in baase
			// all'implementazione
			if (!model.valoreValido(num)) {
				txtLog.appendText("Valore fuori dall'intervallo consentito");
				return;
			}

			int risultato = model.tentativo(num);
			txtCurr.setText(String.format("%d", model.getTentativi())); // trasformo il numero in stringa

			if (risultato == 0) {
				// ha indovinato
				txtLog.appendText("Hai vinto!\n");
			} else if (risultato < 0) {
				txtLog.appendText("Troppo basso\n");
			} else {
				txtLog.appendText("Troppo alto\n");
			}

			if (!model.isInGame()) {
				// la partita è finita (vittoria o sconfitta)
				if (risultato != 0) {
					txtLog.appendText("Hai perso\n");
					txtLog.appendText(String.format("Il numero segreto era: %d\n", model.getSegreto()));
				}

				// "chiudi" la partita
				boxGioco.setDisable(true);
				btnNuova.setDisable(false);
			}

		} catch (NumberFormatException ex) {
			txtLog.appendText("Il dato inserito non è numerico\n");
			return;
		}

	}

	@FXML // This method is called by the FXMLLoader when initialization is complete
	void initialize() {
		assert btnNuova != null : "fx:id=\"btnNuova\" was not injected: check your FXML file 'IndoNumero.fxml'.";
		assert txtCurr != null : "fx:id=\"txtCurr\" was not injected: check your FXML file 'IndoNumero.fxml'.";
		assert txtMax != null : "fx:id=\"txtMax\" was not injected: check your FXML file 'IndoNumero.fxml'.";
		assert boxGioco != null : "fx:id=\"boxGioco\" was not injected: check your FXML file 'IndoNumero.fxml'.";
		assert txtTentativo != null : "fx:id=\"txtTentativo\" was not injected: check your FXML file 'IndoNumero.fxml'.";
		assert btnProva != null : "fx:id=\"btnProva\" was not injected: check your FXML file 'IndoNumero.fxml'.";
		assert txtLog != null : "fx:id=\"txtLog\" was not injected: check your FXML file 'IndoNumero.fxml'.";

	}

	public void setModel(Model model) { // Il model sarà settato dal MAIN
		this.model = model;
	}
}
