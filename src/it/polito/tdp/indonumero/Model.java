package it.polito.tdp.indonumero;

import java.security.InvalidParameterException;

public class Model { // Model non sa nulla di JavaFX
						// il model lavora con gli oggetti, non con le stringhe
						// anche se avessi diverse lingue, il model è sempre lo stesso

	private int NMAX = 100;
	private int TMAX = 7; // 100/2 --> 50/2 --> 25/2 --> ... log2(100) = 7

	private int segreto; // numero da indovinare
	private int tentativi; // tentativi già fatti

	private boolean inGame; // mi dice se c'e' una partita in corso

	public Model() {
		this.inGame = false;
	}

	/**
	 * Avvia una nuova partita, generando un nuovo numero segreto
	 */
	public void newGame() {

		this.segreto = (int) (Math.random() * NMAX) + 1; // numero da indovinare

		this.tentativi = 0;
		this.inGame = true;
	}

	/**
	 * Fai un nuovo tentativo di indovinare il numero segreto
	 * 
	 * @param t
	 *            valore numerico del tentativo
	 * @return 0 se è indovinato, +1 se è troppo grande, -1 se è troppo piccolo
	 */
	public int tentativo(int t) { // Ci penserà il controller a far sì che il metodo non venga chiamato se i
									// valori passati non sono corretti, o la partita è finita

		if (!inGame) {
			throw new IllegalStateException("Partita non attiva");
		}

		if (!valoreValido(t)) {
			throw new InvalidParameterException("Tentativo fuori range");
		}
		// devo inserire la logica di vittoria e sconfitta qui nel model

		this.tentativi++;
		if (this.tentativi == this.TMAX) {
			this.inGame = false;
		}
		if (t == this.segreto) {
			this.inGame = false;
			return 0;
		}
		if (t < this.segreto)
			return -1;
		return 1;
	}

	/**
	 * Controlla se il tentativo fornito rispetta le regole formali del gioco, cioè
	 * è nel range [1, NMAX]
	 * 
	 * @param tentativo
	 * @return {@code true} se il tentativo è valido
	 */
	public boolean valoreValido(int tentativo) {
		return tentativo >= 1 && tentativo <= this.NMAX;
	}

	public boolean isInGame() {
		return inGame;
	}

	public int getTentativi() {
		return tentativi;
	}

	public int getNMAX() {
		return NMAX;
	}

	public int getTMAX() {
		return TMAX;
	}
	
	public int getSegreto() {
		return this.segreto;
	}

}
