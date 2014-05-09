import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        
    	Scanner in = new Scanner(System.in);
        long N;
        while ((N = in.nextLong()) != 0) {
            
        	long sq = (long) Math.floor(Math.sqrt(N)); // vypo��tame odmocninu na��tan�ho ��sla a zaokruhlime 
            
            if(sq * sq == N){ // ak sa vyn�soben� odmocnica rovn� p�vodn�mu ��slu tak svetlo svieti
            	System.out.println("yes");
            }
            else {
            	System.out.println("no");
            }
        }   
    }
}