package eilutes_stulpeliai;

import java.io.*;
import java.lang.*;
import java.util.*;
import java.nio.charset.StandardCharsets;
import sudoku.*;

public class Kvadratu_reiksmiu_masyvas {

	public  Kvadratu_reiksmiu_masyvas(){
	}
	
	public static String[] kvadratu_reiksmes_stringe ()throws IOException{
		
		Uzduotis nuskaitymas = new Uzduotis();
		    
		String reiksmes = nuskaitymas.uzdavinys();
		    
		String []reiksmiu_masyvas = reiksmes.split ("");
		
		String[] kvadrato_nr = new String[9];														//isskiriam kvadrato_nr vietu sk
		
		int x_asies_pridetinis = 0;      		//pridetinis sk, kuris dideja kas 3 paimtas reiksmes didziojo kvadrato.
		int y_asies_pridetinis = 0;			//pridetinis sk, kuris dideja perejus i zemesni didziuju kvadratu eile t.y. kas 3 didyji kvadrata
		int z_asies_pridetinis = 0;			//pridetinis sk, kuris dideja einant is vieno didziojo kvadrato i kita
			
					
		for (int kubo_nr = 0; kubo_nr<9; kubo_nr++){
				
			if (kubo_nr == 3){
				z_asies_pridetinis = 18;
			}if (kubo_nr == 6){
				z_asies_pridetinis = 36;
			}
			
			kvadrato_nr [kubo_nr] = "";
			
			for (int y=0; y<9;y++){
				
				kvadrato_nr[kubo_nr]+=(reiksmiu_masyvas[(x_asies_pridetinis+y+z_asies_pridetinis+y_asies_pridetinis)]);	//sudeda sudoku skaiciu reiksmes i didziuju kvadratu String'u masyvus pvz. kvadratas[0] = (0,1,2,9,10,11,18,19,20,);
				
				if (y==2){
					x_asies_pridetinis=6;
				}if (y==5){
					x_asies_pridetinis=12;
				}
			}
			x_asies_pridetinis=0;
			y_asies_pridetinis+=3;
		}
		/*System.out.println(kvadrato_nr.length);
		for(int a=0;a<9;a++){ System.out.println(kvadrato_nr[a]);}*/
		
		return kvadrato_nr;
	}
}