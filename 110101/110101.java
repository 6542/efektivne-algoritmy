/*
    110101 - The 3n + 1 problem
    ---------------------------

    Nacitavanie dvoch císel v riadku, predpokladáme že prvé je menšie ako druhé ak nie tak ich vymeníme,
    postupne prechádzam všetky císla medzi prvý a druhým a aplikujem algoritmus ak je císlo párne, vydelím ho dvoma,
    ak je nepárne vynásobim ho troma a pripocítam jedna. Pocitam si cykly, ktoré sú potrebné k tomu aby  som sa dostal k jednotke.
    Ak prejdme vsetký cisla vratim maximalný pocet ciklov.
*/

public class Main
{
   public static void main(String[] args) throws IOException
   {
      final BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
      String line;
      while((line = in.readLine()) != null)
      {
         String REGEX_WHITESPACE = "\\s+";
         String cleanLine = line.trim().replaceAll(REGEX_WHITESPACE, " ");
         String[] tokens = cleanLine.split(REGEX_WHITESPACE);

         if (tokens.length == 2) {
           int a1 = Integer.parseInt(tokens[0]);
           int a2 = Integer.parseInt(tokens[1]);
           System.out.println(cleanLine + " " + solve(a1, a2));
         }
      }
   }

   static int solve(int a1, int a2) throws IOException
   {
     int maxCycleLength = 0;
     final int from, to;
     if(a1 < a2){
       from = a1;
       to = a2;
     }else{
       from = a2;
       to = a1;
     }

     for(int i = from; i <= to; i++)
     {
       int currentCycleLength = calculateCycleLength(i);
       if(currentCycleLength > maxCycleLength)
         maxCycleLength = currentCycleLength;
     }

     return maxCycleLength;
   }

   static int calculateCycleLength(int num)
   {
     int cycleLength = 1;

     while(num != 1)
     {
       cycleLength++;

       if(num % 2 == 0) //number is even
         num = num / 2;
       else //number is odd
         num = num * 3 + 1;
      }

      return cycleLength;
   }
}