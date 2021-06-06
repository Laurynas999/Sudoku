package sudoku;

import java.io.*;
import java.lang.*;
import java.util.*;
import java.nio.charset.StandardCharsets;

import eilutes_stulpeliai.*;

public class Sprendimas {
	
	public String [] kvadrato_nr;
	public String [] horiz_eilutes_vietos_nr;
	public String [] vert_stulpelio_vietos_nr;
	
	public Sprendimas (){
	}
	
	public void pasiruosimas ()throws IOException{
		Kvadratu_reiksmiu_masyvas kvadratai_stringe = new Kvadratu_reiksmiu_masyvas();									//issikvieciame kvadratu reiksmiu stringe masyva
		kvadrato_nr=kvadratai_stringe.kvadratu_reiksmes_stringe();													//kvadrato_nr gauna reiksme is klases metodo
		
		Eil_stulp_class eiluciu_ir_stulpeliu_klase = new Eil_stulp_class();													//iskvieciama sulpeliu ir eiluciu klase

		horiz_eilutes_vietos_nr =eiluciu_ir_stulpeliu_klase.eilutes_metodas();												//priskiriamos reiksmes is klases metodu
		vert_stulpelio_vietos_nr =eiluciu_ir_stulpeliu_klase.stulpelio_metodas();
	}
	
	/**
	* Programos pradzia
	*/
	public void galutinisSprendimas()throws IOException{
		
		pasiruosimas();
		for (int pakartotinas_leidimas=0;pakartotinas_leidimas<5;pakartotinas_leidimas++){
			for (int ieskomas_sk = 1; ieskomas_sk<=9; ieskomas_sk++){													//Skaiciu tikrinimas kvadratuose
				for (int tikrinamas_kvadratas = 0; tikrinamas_kvadratas<9; tikrinamas_kvadratas++){							//Ejimas per kvadratus
					if(!kvadrato_nr[tikrinamas_kvadratas].contains(Integer.toString(ieskomas_sk))){
						int eilutes_pradmuo=eilutesPradmuo(tikrinamas_kvadratas); 																									//priskiria pradmeni nuo kurio bus imamos 3 eilutes ir stulpeliai
						int stulpelio_pradmuo=stulpelioPradmuo(tikrinamas_kvadratas); 

						skaiciuIrasymas (
							ieskomas_sk
							, tikrinamas_kvadratas
							, suradomeSkaiciuKurioNeraKvadrate(ieskomas_sk,tikrinamas_kvadratas,eilutes_pradmuo, stulpelio_pradmuo)
							, eilutes_pradmuo
							, stulpelio_pradmuo
						);
					}	
				}
			}
		}
		/*for(int patikrinimui = 0;patikrinimui<9;patikrinimui++){
			System.out.println(kvadrato_nr[patikrinimui]);
		}*/
		
	//	System.out.println("\033[36mThis is cyan\033[0m");
	//	System.out.print(visosReiksmes(kvadrato_nr,0,3)[0]);

	}
	
		//
		//Antras programos zingsnis, tikrina kuriame kurioje kvadrato vietoje ieskomasis skaicius negali buti
	public String [] suradomeSkaiciuKurioNeraKvadrate (int ieskomas_sk, int tikrinamas_kvadratas, int eilutes_pradmuo, int stulpelio_pradmuo) throws IOException{																// ieskomas_sk== skaiciu reiksmses (1-9)  //tikrinamas_kvadratas== didieji kvadratai (0-8) //pirmi_Trejetai== pirmieji trejetai (0-3*eilutems), stulpeliams reikia pridetinio sk. paklaidai
						
		String[] ispakavimas = kvadrato_nr[tikrinamas_kvadratas].split ("");																								//einamojo kvadrato reiksmiu masyvas suskaldomas i pavienius skaicius
		for (int ejimo_eilutemis_ir_stulpeliais_skaicius =0; ejimo_eilutemis_ir_stulpeliais_skaicius<3; ejimo_eilutemis_ir_stulpeliais_skaicius++){											//eiluciu ir stulpeliu kiekis kvadrate
			
			if(horiz_eilutes_vietos_nr[ejimo_eilutemis_ir_stulpeliais_skaicius+eilutes_pradmuo].contains(Integer.toString(ieskomas_sk))){												//tikrina ar eilutese yra tikrinamasis sk.
								//min (0+0)=0    //max (2+6)=8
				for(int eilutes_langelio_nr=0; eilutes_langelio_nr<3;eilutes_langelio_nr++){																				//esant tikrinamajam skaiciui for'as pasiima 3 skaicius pakeitimui

					ispakavimas[(eilutes_langelio_nr+(3*ejimo_eilutemis_ir_stulpeliais_skaicius))]=ispakavimas[(eilutes_langelio_nr+(3*ejimo_eilutemis_ir_stulpeliais_skaicius))].replace('0', 'x');	//ir ten kur eiluteje yra 0 ji pakeicia  x
								//min (0+(3*0))=0		//max (2+(3*2))=8																						//skaiciai masyve eina [0,1,2		eilutes eina i desine puse nuo virsaus i apacia
																																						//				3,4,5		stulpeliai eina zemyn is kaires i desine
																																						//				6,7,8]
				}
			}else{}
			
			if(vert_stulpelio_vietos_nr[ejimo_eilutemis_ir_stulpeliais_skaicius+stulpelio_pradmuo].contains(Integer.toString(ieskomas_sk))){												// Tikrina ar tikrinamojo kvadrato stulpeliuose yra ieskomas skaicius
																																						// Pasiimami tame stulpelyje esantys nuliai ir pakeiciami i x
				for(int stulpelio_langelio_nr=0; stulpelio_langelio_nr<3;stulpelio_langelio_nr++){
					ispakavimas[(3*stulpelio_langelio_nr)+(1*ejimo_eilutemis_ir_stulpeliais_skaicius)]=ispakavimas[(3*stulpelio_langelio_nr)+(1*ejimo_eilutemis_ir_stulpeliais_skaicius)].replace('0', 'x');				
				}
			}else{}
		}
		return ispakavimas;
	}
	
		//
		//Masyvu atnaujinimas, atkeiciant x i nulius, jei buvo rastas skaicius ji iraso
	public void skaiciuIrasymas (int ieskomas_sk, int tikrinamas_kvadratas, String [] ispakavimas,int eilutes_pradmuo,int stulpelio_pradmuo) throws IOException{
		
		int count=0;	//kvadrate likusiu galimu poziciju skaicius i kurias galima irasyti ieskomas_sk
		kvadrato_nr[tikrinamas_kvadratas]="";																	//nunulina einamojo kvadrato masyva
		int nulio_vieta = 0;
		
		for(int i=0;i<9;i++){																				//po replace serijos, skaiciuoja likusius 0
			if(ispakavimas[i].contains("0")){
				count++;
				nulio_vieta=i;
			}
		}
		if (count ==1) {																					//jei lieka tik vienas 0, jo vietoj iraso tikrinamaji skaiciu, nes atmetimo budu lieka tik 1 vieta
			//String aaa ="\033[36m";
			//String bbb= "\033[97m";
			//String ccc= Integer.toString(ieskomas_sk);
			//String zzz=/*aaa+*/ccc+bbb;
			
			for(int i=0;i<9;i++){
				ispakavimas[i]=ispakavimas[i].replace("0",Integer.toString(ieskomas_sk));									//patikrina visus ispakavimo masyvo elementus ir jei randa 0, pakeicia ji i tikrinamaji skaiciu ieskomas_sk			
			}
			horiz_eilutes_vietos_nr[gautoSkaiciausEilutesNumeris(nulio_vieta,eilutes_pradmuo)]+=Integer.toString(ieskomas_sk);
			vert_stulpelio_vietos_nr[gautoSkaiciausStulpelioNumeris(nulio_vieta,stulpelio_pradmuo)]+=Integer.toString(ieskomas_sk);
			
		}																				//jei lieka daugiau nei vienas 0 programa nezino skaiciaus vietos, atkeicia x i nulius ir grazina juos i ispakuoto kubo masyva1
		for(int i=0;i<9;i++){
		
			ispakavimas[i]=ispakavimas[i].replace('x','0');												//likusius nezinomuosius x atkeicia i 0
			kvadrato_nr[tikrinamas_kvadratas]+=(ispakavimas[i]);									//is naujo iraso masyva is atnaujintu ispakavimo reiksmiu 
		}	
	}

		//
		//Eilutes pradmens nustatymas. Pradmuo bus naudojamas tikrinamajam kvadratui priklausanciu 3 eiluciu pradzios taskui nustatyti
	public static int eilutesPradmuo(int tikrinamas_kvadratas){
		if (tikrinamas_kvadratas<3){
			return 0;
		}else if(tikrinamas_kvadratas<6&&tikrinamas_kvadratas>2){
			return 3;
		}else{
			return 6;
		}
	}
		//
		//Stulpelio pradmens nustatymas. Pradmuo bus naudojamas tikrinamajam kvadratui priklausanciu 3 stulpeliu pradzios taskui nustatyti
	public static int stulpelioPradmuo(int tikrinamas_kvadratas){
	
		if (tikrinamas_kvadratas==0||tikrinamas_kvadratas==3||tikrinamas_kvadratas==6){
			return 0;
		}else if(tikrinamas_kvadratas==1||tikrinamas_kvadratas==4||tikrinamas_kvadratas==7){
			return 3;
		}else{
			return 6;
		}
	}
	
	
	
	public static int gautoSkaiciausEilutesNumeris(int nulio_vieta,int eilutes_pradmuo){
		
		int eilutes_masyvo_numeris;
		if(nulio_vieta<3){
			eilutes_masyvo_numeris=(eilutes_pradmuo+0);
			return eilutes_masyvo_numeris;
		}else if((nulio_vieta>2)&&(nulio_vieta<6)){
			eilutes_masyvo_numeris=(eilutes_pradmuo+1);
			return eilutes_masyvo_numeris;
		}
		eilutes_masyvo_numeris=(eilutes_pradmuo+2);
		return eilutes_masyvo_numeris;
	}
	
	public static int gautoSkaiciausStulpelioNumeris(int nulio_vieta,int stulpelio_pradmuo){
		
		int stulpelio_masyvo_numeris;
		if(nulio_vieta==0||nulio_vieta==3||nulio_vieta==6){
			stulpelio_masyvo_numeris=(stulpelio_pradmuo+0);
			return stulpelio_masyvo_numeris;
		}else if(nulio_vieta==1||nulio_vieta==4||nulio_vieta==7){
			stulpelio_masyvo_numeris=(stulpelio_pradmuo+1);
			return stulpelio_masyvo_numeris;
		}
		stulpelio_masyvo_numeris=(stulpelio_pradmuo+2);
		return stulpelio_masyvo_numeris;
	}

	//Sudeda kvadratu reiksmes ir issplitina po viena. Skaiciai kvadrate eina 0,1,2
	//												   3,4,5
	//												   6,7,8
	public String [] visosReiksmes (String [] turimos_reiksmes, int nuo, int iki,int pirminis) throws IOException{
		String reiksmiu_stringas ="";
		for (int i=pirminis;i<(3+pirminis);i++){
			reiksmiu_stringas+=turimos_reiksmes[i].substring(nuo,iki);
		}
		String []reiksmes_po_viena=reiksmiu_stringas.split ("");
		
		return reiksmes_po_viena;
	}	
		
	public String issprestas () throws IOException{
		
		int k=0;
		
		String issprestas_spausdinam =("╔═══════════╦═══════════╦═══════════╗\n");
		String bruksniukas = (" │ ");
		for (int i=0;i<=2;i++) {
			issprestas_spausdinam +=("║ ");
			for(int j=0;j<=8;j++){
				if (j ==2||j==5||j==8){bruksniukas=(" ║ ");
				}else{bruksniukas=(" │ ");
				}
				issprestas_spausdinam +=(visosReiksmes (kvadrato_nr,0,3,k)[j]+bruksniukas);	//substringina pirmus 3 skaicius is 3 kvadratu, pradedant nuo nulio
			}issprestas_spausdinam +=("\n║ ─   ─   ─ ║ ─   ─   ─ ║ ─   ─   ─ ║\n║ ");
			for(int j=0;j<=8;j++){
				if (j ==2||j==5||j==8){bruksniukas=(" ║ ");
				}else{bruksniukas=(" │ ");
				}
				issprestas_spausdinam +=(visosReiksmes (kvadrato_nr,3,6,k)[j]+bruksniukas);	//substringina pirmus 3 skaicius is 3 kvadratu, pradedant nuo nulio
			}issprestas_spausdinam +=("\n║ ─   ─   ─ ║ ─   ─   ─ ║ ─   ─   ─ ║\n║ ");
			for(int j=0;j<=8;j++){
				if (j ==2||j==5||j==8){bruksniukas=(" ║ ");
				}else{bruksniukas=(" │ ");
				}
				issprestas_spausdinam +=(visosReiksmes (kvadrato_nr,6,9,k)[j]+bruksniukas);	//substringina pirmus 3 skaicius is 3 kvadratu, pradedant nuo nulio
			}
			if(i!=2){
				issprestas_spausdinam +=("\n╠═══════════╬═══════════╬═══════════╣\n");
			}
			k+=3;
		}
		issprestas_spausdinam=( issprestas_spausdinam+ "\n╚═══════════╩═══════════╩═══════════╝");
		return issprestas_spausdinam;
	}
}	