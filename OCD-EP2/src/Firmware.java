

import constantes.FlagTypeENUM;
import util.Conversoes;

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
	 private Registrador [] registradores = new Registrador[]{new Registrador("AX",6,7),//Reg 0
			 								new Registrador("BX",8,9),//Reg 1
			 								new Registrador("CX",10,11),//Reg 2
			 								new Registrador("DX",25,26),//Reg 3
			 								new Registrador("MBR",4,5,22,21),//Reg 4
			 								new Registrador("MAR",3,20),//Reg 5
			 								new Registrador("PC",1,2),//Reg 6
			 								new Registrador("P1",16,15),//Reg 7
			 								new Registrador("P2",14,13),//Reg 8
			 								};
	
	
	
	public Firmware(EP2OCD ep2ocd){
		this.ep2ocd = ep2ocd;
		//ep2ocd.setFlagTableValue(FlagTypeENUM.A.column, "<");
		//registradores[AX] = new int [] {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0};  
		//registradores[AX] = registradores[BX];
		//ep2ocd.setLbl();
		
		
	}
	
	public void atualiza (Registrador p1){
		if(p1.getNome() == "AX") ep2ocd.setLblAx(p1.getDados());
		if(p1.getNome() == "BX") ep2ocd.setLblBx(p1.getDados());
		if(p1.getNome() == "CX") ep2ocd.setLblCx(p1.getDados());
		if(p1.getNome() == "DX") ep2ocd.setLblDx(p1.getDados());
	}
	
	public void MOV(Registrador p1, Registrador p2, int tipo){
		
		switch(tipo){
		case 1:
			//MAR<--IR(P1)
			registradores[5].setDados(registradores[7].getDados());
			//atualiza interface
			atualiza(registradores[5]);
			//MBR<--AX
			registradores[4].setDados(registradores[0].getDados());
			//atualiza interface
			atualiza(registradores[4]);
			//MEMORIA<--MBR
			//MEMORIA(registrador[4].getDados())
			break;
		case 2:
			//MAR <-- IR(P2)
			registradores[5].setDados(registradores[8].getDados());
			//atualiza interface
			atualiza(registradores[5]);
			//MBR <-- (memoria)
			//registradores[4].setDados(memoria);
			//atualiza interface
			atualiza(registradores[4]);
			break;
		case 3:	
			//atualiza registrador
			p1.setDados(p2.getDados());
			//atualiza interface
			atualiza(p1);			
			break;
		}

		
	}
	
	public void Busca(){
	//IMPLEMENTAR	
	}
	
	public void Indirecao(int tipo){
		
		switch(tipo){
		case 1:
			//MAR <-- IR(P1)
			registradores[5].setDados(registradores[7].getDados());
			//Atualiza interface
			atualiza(registradores[5]);
			//MBR <-- (MEMORIA)
			//registradores[4].setDados(memoria);
			//Atualiza interface
			atualiza(registradores[4]);
			break;
		case 2:
			//MAR <-- IR(P2)
			registradores[5].setDados(registradores[8].getDados());
			//Atualiza interface
			atualiza(registradores[5]);
			//MBR <-- (MEMORIA)
			//registradores[4].setDados(memoria);
			//Atualiza interface
			atualiza(registradores[4]);
			break;
		
		}
	}
	
	public void ADD(Registrador p1, Registrador p2, int tipo){
		int p1_dec, p2_dec;
		
		switch (tipo){
		case 1:
			//MAR <-- IR(P1)
			registradores[5].setDados(registradores[7].getDados());
			//atualiza interface
			atualiza(registradores[5]);
			//MBR<--(memória)
			//registradores[4].setDados(memória);
			//atualiza interface
			atualiza(registradores[4]);
			
			p1_dec = Conversoes.bin2dec(Conversoes.bin(p1.getDados()));
			p2_dec = Conversoes.bin2dec(Conversoes.bin(p2.getDados()));
			p1_dec += p2_dec;
			
			//atualiza registrador
			registradores[4].setDados(Conversoes.int2array(Conversoes.dec2bin(p1_dec)));
			//atualiza interface
			atualiza(registradores[4]);
			//MEMORIA <-- MBR
			//MEMORIA.setDados(registrador[4].getDados());
			
			break;
		case 2:
			//MAR <-- IR(P2)
			registradores[5].setDados(registradores[8].getDados());
			//atualiza interface
			atualiza(registradores[5]);
			//MBR <-- (memoria)
			//registradores[4].setDados(memoria);
			//atualiza interface
			atualiza(registradores[4]);
			
			p1_dec = Conversoes.bin2dec(Conversoes.bin(p1.getDados()));
			p2_dec = Conversoes.bin2dec(Conversoes.bin(p2.getDados()));
			p1_dec += p2_dec;
			
			//atualiza registrador
			registradores[7].setDados(Conversoes.int2array(Conversoes.dec2bin(p1_dec)));
			//atualiza interface
			atualiza(registradores[7]);
			
			break;
			
		case 3: 
			p1_dec = Conversoes.bin2dec(Conversoes.bin(p1.getDados()));
			p2_dec = Conversoes.bin2dec(Conversoes.bin(p2.getDados()));
			p1_dec += p2_dec;
			
			//atualiza registrador
			p1.setDados(Conversoes.int2array(Conversoes.dec2bin(p1_dec)));
			//atualiza interface
			atualiza(p1);			
			break;
		}
	}
	
	public void SUB(Registrador p1, Registrador p2, int tipo){
		int p1_dec, p2_dec;
		
		switch (tipo){
		case 1:
			//MAR <-- IR(P1)
			registradores[5].setDados(registradores[7].getDados());
			//atualiza interface
			atualiza(registradores[5]);
			//MBR<--(memória)
			//registradores[4].setDados(memória);
			//atualiza interface
			atualiza(registradores[4]);
			
			p1_dec = Conversoes.bin2dec(Conversoes.bin(p1.getDados()));
			p2_dec = Conversoes.bin2dec(Conversoes.bin(p2.getDados()));
			p1_dec -= p2_dec;
			
			if(p1_dec < 0){
				//necessário atualizar flag 
			}
			
			//atualiza registrador
			registradores[4].setDados(Conversoes.int2array(Conversoes.dec2bin(p1_dec)));
			//atualiza interface
			atualiza(registradores[4]);
			//MEMORIA <-- MBR
			//MEMORIA.setDados(registrador[4].getDados());
			
			break;
		case 2: 
			
			//MAR <-- IR(P2)
			registradores[5].setDados(registradores[8].getDados());
			//atualiza interface
			atualiza(registradores[5]);
			//MBR <-- (memoria)
			//registradores[4].setDados(memoria);
			//atualiza interface
			atualiza(registradores[4]);
			
			p1_dec = Conversoes.bin2dec(Conversoes.bin(p1.getDados()));
			p2_dec = Conversoes.bin2dec(Conversoes.bin(p2.getDados()));
			p1_dec -= p2_dec;
			
			if(p1_dec < 0){
				//necessário atualizar flag
			}
			
			//atualiza registrador
			registradores[7].setDados(Conversoes.int2array(Conversoes.dec2bin(p1_dec)));
			//atualiza interface
			atualiza(registradores[7]);
			break;
			
		case 3:
			p1_dec = Conversoes.bin2dec(Conversoes.bin(p1.getDados()));
			p2_dec = Conversoes.bin2dec(Conversoes.bin(p2.getDados()));
			p1_dec -= p2_dec;
			
			if(p1_dec < 0){
				//necessário atualizar flag 
			}
			
			//atualiza registrador
			p1.setDados(Conversoes.int2array(Conversoes.dec2bin(p1_dec)));
			//atualiza interface
			atualiza(p1);	
		}
	}

}
