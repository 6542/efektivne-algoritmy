/*
Euklidova uloha. pre akekolvek kladne cislo A a B existuje
cele cislo X a Y take ze AX + BY  = D, kde D je najvasci spolocny delitel
cisel A a B. Ulohou je najst X,Y a D pre zadane A a B.
*/
#include<cstdio>
// cele cisla A,B
int A,B;

/*Struktura triple
    d = najvasci spolocny delitel
    x = X
    y = Y
*/
struct Triple{
int d, x, y;
Triple() {}
Triple( int q, int w, int e ) : d( q ), x( w ), y( e ) {}
};
Triple egcd( int a, int b ){
if(!b) return Triple(a, 1, 0);
Triple q = egcd(b, a%b);
return Triple(q.d, q.y, q.x-a/b*q.y);
}
Triple t;
int main(void){
    while(scanf("%d%d",&A,&B)!=EOF)
    // Podmienka pre vypis X<=Y
        if(A>=B) t = egcd(A,B),printf("%d %d %d\n",t.x,t.y,t.d);
        else t = egcd(B,A),printf("%d %d %d\n",t.y,t.x,t.d);
    return 0;
}
