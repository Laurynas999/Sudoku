package sudoku;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertArrayEquals;

import org.junit.Test;
import eilutes_stulpeliai.*;
import java.io.*;

public class AppTest {
	
	Sprendimas sprendziame = new Sprendimas();
	//Kvadratu_reiksmiu_masyvas pirminis = new Kvadratu_reiksmiu_masyvas();
	
	String [] tikimas_kvadrato_nr = {
	
		"625483197",	
		"731296548",	
		"984715236",	
		"734216958",	
		"852379614",	
		"691458327",	
		"571849362",	
		"963127485",	
		"842563179"	
	};
	@Test
    public void shouldAnswerWithTrue(){
        assertTrue( true );
    }
    
	@Test
    public void kvadratoNrTikrinimas() throws IOException{
	    
		for(int a=0; a<9;a++){
			sprendziame.galutinisSprendimas();
			String kvadrato_nr = sprendziame.kvadrato_nr[a];
			assertEquals("Gautas "+a+" kvadratas neatitinka, kurio tiketasi",tikimas_kvadrato_nr[a],kvadrato_nr);
		}
	}
	@Test
    public void tikrinameIspakavima() throws IOException{
	Kvadratu_reiksmiu_masyvas kvadratai_stringe = new Kvadratu_reiksmiu_masyvas();									//issikvieciame kvadratu reiksmiu stringe masyva
	sprendziame.kvadrato_nr=kvadratai_stringe.kvadratu_reiksmes_stringe();
	
	sprendziame.pasiruosimas();
	String tikimas = "2";
	int pozicija = 2;
	String []ispakavimas=sprendziame.suradomeSkaiciuKurioNeraKvadrate(Integer.parseInt(tikimas),8,6,6);
	
		assertEquals(tikimas+" nera pozicijoje <"+pozicija+">",tikimas,ispakavimas[pozicija].replace("0",tikimas));
	
	}
	@Test
    public void skaiciuIrasymas() throws IOException{
	    
	Kvadratu_reiksmiu_masyvas kvadratai_stringe = new Kvadratu_reiksmiu_masyvas();									//issikvieciame kvadratu reiksmiu stringe masyva
	sprendziame.kvadrato_nr=kvadratai_stringe.kvadratu_reiksmes_stringe();
	   
	sprendziame.pasiruosimas();
	String tikimas = "8";
	String []ispakavimas=sprendziame.suradomeSkaiciuKurioNeraKvadrate(Integer.parseInt(tikimas),8,6,6);
	sprendziame.skaiciuIrasymas (Integer.parseInt(tikimas), 8, ispakavimas,6,6);
	    
	assertEquals ("8 kvadratas neatitinka  ", "000500070",sprendziame.kvadrato_nr[8]);
    }    
	
}
