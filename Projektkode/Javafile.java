package shufflerCode;
import com.company.CodeGenerator.TemplateCode;
public class Main 
    {
    public static void main(String[] args)
{
}
DeckClass gamedeck = new DeckClass()= new DeckClass(new String[]{standard, hearts, 2h}); 
public class Cards
{
public Cards()
{
king.value= 10; 
queen.value= 10; 
jack.value= 10; 
ace.value= 11; 
}
}
public class Player
{
hand Hand = new hand(); 
public Player()
{
Hand.visibility= visible; 
}
}
int PlayerCnt = 1;
public class Table
{
DeckClass pilehidden = new DeckClass(); 
DeckClass pilevisible = new DeckClass(); 
public Table()
{
pilehidden.visibility= hidden; 
pilevisible.visibility= visible; 
}
}

{
gamedeck.shuffle(); 
players.Hand.drawfrom(gamedeck, 2); 
table.pilehidden.drawfrom(gamedeck, 1); 
table.pilevisible.drawfrom(gamedeck, 1); 
}

{
while ((table.pilehidden.totalValue+ table.pilevisible.totalValue)< 17)
{
table.pilevisible.drawfrom(gamedeck, 1); 
}
; 
if (table.pilehidden.totalValue+ table.pilevisible.totalValue< 21)
{
players.takeTurn(); 
}
; 
endcondition.check(); 
}

{
while (turntaker.Hand.totalValue< 21)
{
int i; 

{
"Hit"if (true )
{
turntaker.Hand.drawfrom(gamedeck, 1); 
}
; 
"Stand"if (true )
{
endTurn(); 
}
; 
"Split"if (turntaker.Hand.size== 2&&turntaker.Hand.1== turntaker.Hand.2)
{
}
; 
}
; 
}
; 
endcondition.check(); 
}
(table.pilehidden.totalValue+ table.pilevisible.totalValue>= 21or players.any.Hand.totalValue>= 21)
{
if (table.pilehidden.totalValue+ table.pilevisible.totalValue> 21)
{
winner= player.1; 
}
else if (table.pilehidden.totalValue+ table.pilevisible.totalValue== 21)
{
winner= none; 
}
else if (player.1.Hand.totalValue== 21)
{
winner= player.1; 
}
else if (player.1.Hand.totalValue> table.pilehidden.totalValue+ table.pilevisible.totalValue)
{
winner= player.1; 
}
else 
{
winner= none; 
}
; 
; 
; 
; 
}

{
void myFunc(int i; 
int k; 
)
{
int j; 
j= i; 
if (j> k)
{
k= j; 
}
; 
}
}
