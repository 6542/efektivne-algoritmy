import java.io.File;
import java.io.IOException;
import java.io.Reader;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Scanner;

/*
    110206 - Erdos Numbers
    ----------------------

	Ka�d� kto nap�sal cl�nok s Erdosom m� c�slo 1, a potom ka�d� kto p�sal cl�nok s t�m kto p�sal priamo s
	Erdosom m� c�slo 2, a ten kto p�sal cl�nok s t�m , kto m� c�slo 2 m� c�slo 3 atd.
	Input obsahuje databazu autorov a cl�nkov. Najprv je c�slo poctu pr�padov. Potom s� dve c�sla,
	prv� c�slo je pocet riadkov obsahuj�cich autorov s cl�nkom, ktor� nap�sali. Druh� c�slo pocet autorov v zozname
	pod cl�nkami. To s� autori pre ktor�ch m�me vypoc�ta� erdos c�slo. T� ktor� nemaj� �iadne prepojenie s Erdosom maj� erdos c�slo "infinity".

	Pracuje to priblizne takto:

	- spracujeme kolko m� byt vstupov atd. ako vzdy
	- autorou si rozdelime autoriClanku = clanok.split("\\.\\: |\\.\\, ");
	- pri prechadzani autori clanku nepocitam s poslednim polickom lebo tam je nazov clanku
	  for(int i1 =0;i1<autoriClanku.length-1;i1++)
	- prejdem vsetkych autorov v clanku zistim ci je tam erdos, ak tam je tak si to poznacim a breaknem
	- dalej ak tam teda ten erdos bol tak vsetkych autorov daneho clanku vlozim do hashmapy s hodnotou level(nastaveny je na jedna),
	inak ak tam nieje idem dalej znovu prehladavam vsetkych autorov a hladam ci niektory z nich uz nieje v hash mape a m� nastavenu hodnotu level,
	ak tam dakto taky je oznacim si ho, zvisim level a breaknem, ak tam nik taki nebol tak si to poznacim
	- dalej ak tam teda je taky tak znovu prejdem vsetkych clenov clanku , ak sa nenachadzaju v hashmape tak ich tam vlozim a dam im hodnotu level (co je teraz uz zvysena hodnota),
	inak znovu prejdem vsetkych clenov clanku, vlozim ich do hashmapy s cislom -1 (co potom bude predstavovat hodnotu infiinty)
	- uz treba len vypisat hodnoty u tych pozadovanych autorov - to som nerobil
	- vycistit si zoznam hashmapy a prejdeme k dalsiemu pr�padu
*/

public class Main {

	/**
	 * @param args
	 */
	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		File fl = new File("C:\\Users\\Jaro\\workspaceA\\Erdos\\src\\input.txt");
		Scanner sc = new Scanner(fl);
		int pocetPripadov=Integer.parseInt(sc.nextLine());
		int pocetClankov;
		int pocetAutorov;
		HashMap<String, Integer> autorAErdosCislo = new HashMap<String, Integer>();
		for(int i =0;i<pocetPripadov;i++){
			pocetClankov = sc.nextInt();
			pocetAutorov = sc.nextInt();
			sc.nextLine();
			String clanok;
			String[] autoriClanku;
			boolean jeTamErdos = false;
			boolean jeTam = false;
			int level = 1;
			for(int k =0;k<pocetClankov;k++){
				clanok = sc.nextLine();
				autoriClanku = clanok.split("\\.\\: |\\.\\, ");

				for(int i1 =0;i1<autoriClanku.length-1;i1++) {
					autoriClanku[i1]=autoriClanku[i1]+".";
					System.out.println(autoriClanku[i1]);
				}

				for(int j =0;j<autoriClanku.length-1;j++){
					if(autoriClanku[j].equals("Erdos, P.")){
						jeTamErdos = true;
						break;
					}else{
						jeTamErdos = false;
					}
				}
				System.out.println(jeTamErdos);
				//ak tam je erdos tak zvysnim dam cislo 1
				if(jeTamErdos){
					for(int j =0;j<autoriClanku.length-1;j++){
						autorAErdosCislo.put(autoriClanku[j],level);
					}
				}else{
					for(int j =0;j<autoriClanku.length-1;j++){
						//ak tam nieje erdos moze tam byt dakto iny v zozname s cislom 1
						//prehladam autorov clanko ak tam je dakto kto je v zozname a ma jednotku tak
						//to breaknem a znovu prejdem cely zoznam, kazdemu okrem nehe dam 2 ak tiez niesu v zozname
						if(autorAErdosCislo.containsKey(autoriClanku[j]) && autorAErdosCislo.get(autoriClanku[j])==level){
							jeTam = true;
							level++;
							break;
						}else{
							jeTam = false;
						}
					}
						if(jeTam){
							for(int w =0;w<autoriClanku.length-1;w++){
								if(!autorAErdosCislo.containsKey(autoriClanku[w])){
									autorAErdosCislo.put(autoriClanku[w],level);
								}
							}
						}else{
							for(int p =0;p<autoriClanku.length-1;p++){
								if(!autorAErdosCislo.containsKey(autoriClanku[p])){
									autorAErdosCislo.put(autoriClanku[p],-1);
								}
							}
						}

					}

			}//pocet clankov
			for(int k =0;k<pocetAutorov;k++){
				sc.nextLine();
			}
			Iterator<String> keySetIterator = autorAErdosCislo.keySet().iterator();

			while(keySetIterator.hasNext()){
			  String key = keySetIterator.next();
			  System.out.println("key: " + key + " value: " + autorAErdosCislo.get(key));
			}
		}//pocet pripadov
		autorAErdosCislo.clear();
	}

}
