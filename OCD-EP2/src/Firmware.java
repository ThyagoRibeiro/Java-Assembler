import java.util.ArrayList;

import constantes.FlagTypeENUM;

public class Firmware {
	
	private EP2OCD ep2ocd; 
	
	 private static final int AX = 0;
	 private static final int BX = 1;
	 private static final int CX = 2;
	 private static final int DX = 3;
	 private static final int PC = 4;
	 private static final int MBR = 5;
	 private static final int MAR = 6;
	 private static final int P1 = 7;
	 private static final int P2 = 8;
	 private static final int OP = 9;
	 private Registrador [] registradores = new Registrador[]{new Registrador("AX",6,7),
			 								new Registrador("BX",8,9),
			 								new Registrador("CX",10,11),
			 								new Registrador("DX",25,26),
			 								new Registrador("MBR",4,5,22,21),
			 								new Registrador("MAR",3,20),
			 								new Registrador("PC",1,2),
			 								new Registrador("P1",16,15),
			 								new Registrador("P2",14,13),
			 								};
	
	
	
	public Firmware(EP2OCD ep2ocd){
		this.ep2ocd = ep2ocd;
		//ep2ocd.setFlagTableValue(FlagTypeENUM.A.column, "<");
		//registradores[AX] = new int [] {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0};  
		//registradores[AX] = registradores[BX];
		//ep2ocd.setLbl();
		
	}
	
	public void atualiza (Registrador p1){
		if(p1.getNome() == "AX") ;//ep2ocd.setLblAxBin(p1.getDados());
		if(p1.getNome() == "BX") ;//ep2ocd.setLblBxBin(p1.getDados());
		if(p1.getNome() == "CX") ;//ep2ocd.setLblCxBin(p1.getDados());
		if(p1.getNome() == "DX") ;//ep2ocd.setLblDxBin(p1.getDados());
	}
	
	public void MOV(Registrador p1, Registrador p2, int tipo){
		
		switch(tipo){
		case 1:
			break;
		case 2:
			break;
		case 3:	
			//atualiza registrador
			p1.setDados(p2.getDados());
			//atualiza interface
			atualiza(p1);			
			break;
		}

		
	}
	

}
