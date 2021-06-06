package eilutes_stulpeliai;

import java.io.*;
import java.lang.*;
import java.util.*;
import java.nio.charset.StandardCharsets;
import sudoku.*;

public class Eil_stulp_class {

	public  Eil_stulp_class(){
	}
	
	public static String[] eilutes_metodas()throws IOException{
	
		Uzduotis nuskaitymas = new Uzduotis();
		
		String reiksmes = nuskaitymas.uzdavinys();
		String []reiksmiu_masyvas = reiksmes.split ("");
		
		String []horiz_eilutes_nr = new String [9];
		String[] eilutes_langelio_nr = new String [9];
		
		int sss=0;
		
		for (int q=0;q<9;q++){
			horiz_eilutes_nr[q]="";
			
			for (int i=0;i<9;i++){
				
				eilutes_langelio_nr[i] = "";
				horiz_eilutes_nr[q]+=(eilutes_langelio_nr[i]=(reiksmiu_masyvas[sss]));
				sss++;
			}

		}

		return horiz_eilutes_nr;

	}
	
	public static String[] stulpelio_metodas()throws IOException{
	
		Uzduotis nuskaitymas = new Uzduotis();
		
		String reiksmes = nuskaitymas.uzdavinys();
		String []reiksmiu_masyvas = reiksmes.split ("");
		
		String []vert_stulpelio_nr = new String [9];
		String[] eilutes_langelio_nr = new String [9];
		
		int sss=0;
		
		for (int q=0;q<9;q++){
			vert_stulpelio_nr[q]="";
			
			sss=(0+(1*q));
			
			for (int w=0;w<9;w++){
				
				eilutes_langelio_nr[w] = "";
				vert_stulpelio_nr[q]+=(eilutes_langelio_nr[w]=(reiksmiu_masyvas[sss]));
				sss+=9;
			}

		}
		
		return vert_stulpelio_nr;
	}
}
