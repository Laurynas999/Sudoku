package sudoku;

import java.io.*;
import java.lang.*;
import java.util.*;
import java.nio.charset.StandardCharsets;

import eilutes_stulpeliai.*;

public class App {
	public static void main( String[] args ) throws IOException {
	
		Uzduotis nuskaitymas = new Uzduotis();			//issikvieciame teksto nuskaitymo klase
		Sprendimas mano_kvad = new Sprendimas();			//issikvieciame didziuju kvadratu langeliu reiksmiu nustatymo klase
		    
		String reiksmes = nuskaitymas.uzdavinys();		//ikelia sudoku skaiciu reiksmes kaip viena eilute
		String be_nuliu = nuskaitymas.be_nuliu();			//suformuoja turimas sudoku skaiciu reiksmes (String formatu) lenteleje, o vietoj nuliu padaro tuscia langeli
		
		System.out.println( be_nuliu );					//atspausdina lentele, kurioje nuliai yra pakeisti tarpais
		
		mano_kvad.galutinisSprendimas();				//sukarpo reiksmiu String'a ir sudelioja i didziuju kvadratu langelius.
												//po to pereina ciklas, kuris tikrina kiekviena didyji kvadrata ar jame yra skaicius 1-9
												//esant tam skaiciui paleidzia eiluciu ir stulpeliu tikrinima ar to skaiciaus nera ir juose
												//jei didziajame kvadrate lieka >1 nezinomasis, skaiciaus neiraso
												//jei yra tik 1 nezinomasis, i jo vieta irasomas tikrinamasis skaicius
												//nesant tam skaiciui pereinama prie kito didziojo kvadrato		
		
		System.out.print(mano_kvad.issprestas());
	}
}