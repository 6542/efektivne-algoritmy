/*
    110201 - Jolly jumpers
    ----------------------

    Nacitam riadok integerov a postupne zacnem odcitavatù prave cislo od laveho a ukladam si ich absol˙tnu
    hodnotu do pola. Potom pozriem ci s˙ v˝slednÈ hodnoty zoradenÈ od najv‰cöieho po najmenöie ak s˙ tak
    je to JOLLY JUMPER ak nie s˙ tak to nie je JOLLY JUMPER.
*/

public class Main
{
    public static final String JOLLY = "Jolly";
    public static final String NOT_JOLLY = "Not jolly";

    public static void main( String[] args ) throws IOException
    {
     final BufferedReader in = new BufferedReader(new InputStreamReader(
    System.in));
     String line;

  while ((line = in.readLine()) != null) {
   line = line.trim();

   if(!line.equals("")){
    StringTokenizer tokenizer = new StringTokenizer(line);
    int length = Integer.parseInt(tokenizer.nextToken());
    int[] sequence = new int[length];
    int i = 0;
    while(tokenizer.hasMoreTokens()){
     int val = Integer.parseInt(tokenizer.nextToken());
     if(i < length) sequence[i++] = val;
    }

    System.out.println(solve(sequence, length));
   }
  }
    }

    public static String solve(int[] sequence, int length){
     if(length == 1)
      return JOLLY;

     Set< Integer > diffs = new HashSet< Integer >();
     for(int i = 0; i < length - 1; i++){
      int diff = Math.abs(sequence[i + 1] - sequence[i]);
      if(diff <= length - 1 && diff > 0) diffs.add(diff);
     }

     return (diffs.size() == length - 1) ? JOLLY : NOT_JOLLY;
    }
}