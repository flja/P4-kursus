#include <stdio.h>
#include <string.h>

/* I er ansat i politiet's cybercrime afdeling, med ansvar for at overvÃ¥ge de datalogiske bander i Danmark. Der er stor frygt for at de to datalogiske bander, Big-Endians og Little-Endians, er ved at aftale tid og sted for en datalogisk kode-kamp.

Hint: Overvej maskin repræssentation af forskellige datatyper, og prøv evt. at lave et C-program, som fortolker dataene på forskellig vis.

Besked 1:
Denne besked er blevet opsnappet af deres kommunikation, men ingen har hidtil kunnet finde ud af hvad den betyder:
539913291 539767857 759570537 1651862635 560883042


Kan I finde ud af hvor og hvornÃ¥r kode-kampen skal foregå? */

void show_bytes(char *start, int len)
{
    int i;
    for (i=0; i < len; i++)
        printf(".2x", start[i]);
    printf("\n");
}

void show_ints(int *start, int len)
{
    int i;
    for (i=0; i<len; i++)
    {
        printf("%d", start[i]);
        printf("\n");
    }
}
    int msg1[] = {539913291, 539767857, 759570537, 1651862635, 560883042};

    int main() 
    {
        int len= 5*sizeof(int);
        printf("Viser xd bytes\n", len);
        show_bytes((char*) msg1, len);
        printf("Message: \"%s\"n", (char*) msg1);
    }