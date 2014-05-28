/*
    110305 - Automated Judge Script
    -------------------------------

    Vytvorenie hodnotiaceho systemu pre rozhodcov.
    Berie do uvahy 3 odpovede: accept, presentation error, wronganswer.
    Vstupom pre kazdu otazku/vstup je kladne cislo n < 100 ktore urcuje pocet
    riadkov spravnej odpovede otazky. Dalsi riadok s 'n' predstavuje odpoved.
    Premenna m < 100 predstavuje pocet riadkov danej odpovede, dalsie riadky s 'm'
    obsahuju uz priamo odpoved. Ziadny riadok nesmie mat viac ako 100 znakov.
*/

#include <stdio.h>
#include <string.h>
#include <ctype.h>

char standard [12100];
char team [12100];
int n; //vstup
int m; // vystup

bool accepted ()  // spravna odpoved
{
    if ( n != m ) // otazka a odpoved su neni rovne
        return false;
    if ( strcmp ( standard, team ) ) // porovnava retazce, otazky a odpovede
        return false;
    return true;
}

bool presentation ()   //prezentation error
{     // porovnava vsetky znaky, ci su zhodne zo zadanymi v otazke ?
    char temp1 [12100];
    int index = 0;
    for ( int i = 0; standard [i] != 0; i++ ) {
        if( isdigit (standard [i]) )
            temp1 [index++] = standard [i];
    }
    temp1 [index] = 0;
    index = 0;
    char temp2 [12100];
    for ( int i = 0; team [i] != 0; i++ ) {
        if ( isdigit (team [i]) )
            temp2 [index++] = team [i];
    }
    temp2 [index] = 0;
    if ( strcmp (temp1, temp2) == 0 )
        return true;
    return false;
}
int main () //main
{
    int run = 0;
    while ( scanf ("%d", &n) && n ) {   //caka na zadanie n
        getchar ();    				// co predstavuje vlastne pocet riadkov otazky
        standard [0] = 0;
    char temp [125];

        for ( int i = 0; i < n; i++ ) {
        gets (temp);
            strcat (standard, temp);  // prepoji retazce, z otazkov a docasnym retazcom ktory sa porovnava z odpovedou 'team'
        }
        //printf ("%s\n", standard); // vypise odpoved
        scanf ("%d", &m);
        getchar ();
        team [0] = 0;
        for ( int i = 0; i < m; i++ ) {
       gets (temp);
            strcat (team, temp);  //prepoji a porovnava odpoved 'team' z otazkou prepojenou z standard do temp' doacastnej premennej-novej
        }
        //printf ("%s\n", team);
        if ( accepted () )  // podmienka ak premenna 'n' sa rovna odpovedi v premennej 'team'
            printf ("Run #%d: Accepted\n", ++run);  //tak spravne
        else if ( presentation () )
            printf ("Run #%d: Presentation Error\n", ++run);  //ak sa nezhoduju znaky z odpovede a otazky error
        else
            printf ("Run #%d: Wrong Answer\n", ++run);   // ak ani jedno tak je zle
    }
    return 0;
}