package sudoku;

import java.io.*;
import java.lang.*;
import java.util.*;
import java.nio.charset.StandardCharsets;

public class Uzduotis {
	
	public String [] kvadrato_nr;
	
	public Uzduotis(){
	}
	
	public String uzdavinys () throws IOException{
		File sudoku_sar = new File("C:\\Users\\Mokinys\\Desktop\\darbai\\sprendsudoku\\sudoku_var1.csv");
		BufferedReader sarasas = new BufferedReader( new FileReader( sudoku_sar ) );
		
		String prad_reiksmes;
		String reiksmes="";
		int k=0;
		
		while ((prad_reiksmes = sarasas.readLine()) !=null){
			
			String[] arrOfprad_reiksmes = prad_reiksmes.split(",");
			
			for (int i=0; i<9; i++){
				
				if ((i==2)||(i==5)||(i==8)){
				}
				reiksmes =(reiksmes+ arrOfprad_reiksmes [i]);
			}
			k++;
		}
		
		return reiksmes;
	}
	
	public String be_nuliu () throws IOException{
		File sudoku_sar = new File("C:\\Users\\Mokinys\\Desktop\\darbai\\sprendsudoku\\sudoku_var1.csv");
		BufferedReader sarasas = new BufferedReader( new FileReader( sudoku_sar ) );
		
		int k=0;
		String prad_reiksmes;
		String su_nuliais =("╔═══════════╦═══════════╦═══════════╗\n");
		
		while ((prad_reiksmes = sarasas.readLine()) !=null){
			
			if((k==3)||(k==6)){
				su_nuliais=(su_nuliais+("\n╠═══════════╬═══════════╬═══════════╣\n"));
			}if((k==1)||(k==2)||(k==4)||(k==5)||(k==7)||(k==8)){
				su_nuliais=(su_nuliais+("\n║ ─   ─   ─ ║ ─   ─   ─ ║ ─   ─   ─ ║\n║"));
			}else{
				su_nuliais=(su_nuliais+("║"));
			}
			
			String[] arrOfprad_reiksmes = prad_reiksmes.split(",");
			
			for (int i=0; i<9; i++){
				
				String bruksniukas = " │";
				
				if ((i==2)||(i==5)||(i==8)){
					bruksniukas=" ║";
				}
				su_nuliais=(su_nuliais+(" "+ arrOfprad_reiksmes [i]+bruksniukas));
				
			}
			
			k++;
		}
		su_nuliais=( su_nuliais+ ("\n╚═══════════╩═══════════╩═══════════╝"));

		String be_nuliu=su_nuliais.replace('0', ' ');
		
		return be_nuliu;
	}
	
	
}