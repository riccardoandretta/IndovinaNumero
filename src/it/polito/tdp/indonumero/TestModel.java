package it.polito.tdp.indonumero;

public class TestModel {

	public static void main(String[] args) {

		Model model = new Model();
		
		model.newGame();
		
		int min = 1;
		int max = model.getNMAX();
		
		while(model.isInGame()) {
			int t = (min+max)/2; //troncato mi da 50 la prima volta
			
			System.out.format("Tra %d e %d provo %d\n", min, max, t);
			
			int ris = model.tentativo(t);
			if (ris > 0) { //se il numero inserito è troppo grande --> riduco il max
				max = t-1;
			}else 
				min = t+1; //sposto il min se tentativo era troppo piccolo
			System.out.println(ris);
		}

	}

}
