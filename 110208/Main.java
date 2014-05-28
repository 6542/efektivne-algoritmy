import java.util.Scanner;

class Edge {
 public int v, d;
 public Edge(int _v, int _d){
  v = _v; d = _d;
 }
}

class Roll {
public int[] categoryPoints = new int[13];
public Roll(char[] dice){
 calculateCategoryPoints(dice);

}

//bubble sort...
public void sort(char[] a){
    for(int i = 0; i < a.length-1; i++)
     for(int j = i+1; j < a.length; j++)
      if(a[i] > a[j]){ char t = a[i]; a[i] = a[j]; a[j] = t; }


   }
public void calculateCategoryPoints(char[] dice){
 sort(dice);

 boolean t, f, fi, ss, ls, fh;
 int[] count = new int[7];


 t = f = fi = false;
 for(int i = 0; i < 7; i++) count[i] = 0;
 for(char c: dice){
  count[c-'0']++;
  if(count[c-'0'] == 5) t = f = fi = true;
  else if(count[c-'0'] == 4) t = f = true;
  else if(count[c-'0'] == 3) t = true;

 }

 ls = ss = (dice[0] == dice[1] - 1) &&
     (dice[0] == dice[2] - 2) &&
     (dice[0] == dice[3] - 3) &&
     (dice[0] == dice[4] - 4);
 ss = (count[1] > 0 && count[2] > 0 && count[3] > 0 && count[4] > 0) ||
   (count[2] > 0 && count[3] > 0 && count[4] > 0 && count[5] > 0) ||
   (count[3] > 0 && count[4] > 0 && count[5] > 0 && count[6] > 0);

 fh = ((dice[0] == dice[1]) && (dice[2] == dice[4])) ||
   ((dice[0] == dice[2]) && (dice[3] == dice[4]));

 for(int cat = 0; cat < 13; cat++){
  switch(cat){
   case 0 : categoryPoints[0] = count[1]; break;
   case 1 : categoryPoints[1] = count[2] * 2; break;
   case 2 : categoryPoints[2] = count[3] * 3; break;
   case 3 : categoryPoints[3] = count[4] * 4; break;
   case 4 : categoryPoints[4] = count[5] * 5; break;
   case 5 : categoryPoints[5] = count[6] * 6; break;
   case 6 : categoryPoints[6] = (dice[0]-'0') +
           (dice[1]-'0') +
           (dice[2]-'0') +
           (dice[3]-'0') +
           (dice[4]-'0'); break;
   case 7 : categoryPoints[7] = t  ? categoryPoints[6] : 0; break;
   case 8 : categoryPoints[8] = f  ? categoryPoints[6] : 0; break;
   case 9 : categoryPoints[9] = fi ? 50 : 0; break;
   case 10 : categoryPoints[10] = ss ? 25 : 0; break;
   case 11 : categoryPoints[11] = ls ? 35 : 0; break;
   case 12 : categoryPoints[12] = fh ? 40 : 0; break;

  }
 }

}
}
class Main {


public int[] val;
public boolean[] visited;
public int[] card;
public int[] top;
public Edge[] edges;
public Roll[] rolls;

   public Main() {

   }

   public void init(){
    Scanner in = new Scanner(System.in);
    while(in.hasNextLine()){
     parseRolls(in);
     System.out.println(solve());
    }
   }
   public void parseRolls(Scanner in){
    rolls = new Roll[13];
    String line;
    for(int i = 0; i < rolls.length; i++)
     rolls[i] = new Roll(in.nextLine().replaceAll("\\s","").toCharArray());

   }
   public String solve(){
    val = new int[8192];
    visited = new boolean[8192];
    top = new int[8192];
    card = new int[13];
    edges = new Edge[8192];
    for(int i = 0; i < 8192; i++){ val[i] = 0; visited[i] = false; }
    visited[0] = true;
    int max = calc(13,8191);
    tallyScoreCard();
    String out = "";
    int sum = 0;
    for(int i = 0; i < 13; i++){
     if(i < 6) sum += card[i];
     out += ""+card[i]+" ";
    }
    if(sum > 62) out += "35 ";
    else out += "0 ";
    out += "" + max;
    return out.trim();

   }

   //calculate maximum summation for the nth category
   public int calc(int n, int mask){

    if(visited[mask]) return val[mask];

    val[mask] = 0;
    int _mask = mask;

    for(int i = 12, j = 0; i >= 0; i--, j++){
     int temp = mask & (1<<i);
        if(temp != 0){
      _mask = mask ^ (1<<i);

      int c = calc(n-1,_mask),
          e = rolls[j].categoryPoints[n-1],
          m = c + e;

      visited[mask] = true;
      if (m > val[mask]){
       val[mask] = m;
       edges[mask] = new Edge(n-1,e);
       top[mask] = _mask;
      }

     }
    }
    if(n == 6 && val[mask] > 62){
     val[mask] += 35;

    }

    return val[mask];

   }

   public void tallyScoreCard(){
    for(int mask = 8191; mask != 0;){
     card[edges[mask].v] = edges[mask].d;
     mask = top[mask];
    }
   }
   public static void main(String[] args){
    new Main().init();
   }


}