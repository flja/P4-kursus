package shufflerCode;
import shufflerCode.HelpMethods;
import shufflerCode.ActionClass;
import shufflerCode.CardClass;
import shufflerCode.DeckClass;
import shufflerCode.two;
import shufflerCode.three;
import shufflerCode.four;
import shufflerCode.five;
import shufflerCode.six;
import shufflerCode.seven;
import shufflerCode.eight;
import shufflerCode.nine;
import shufflerCode.ten;
import shufflerCode.jack;
import shufflerCode.queen;
import shufflerCode.king;
import shufflerCode.joker;
import shufflerCode.ace;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;public class Shuffler
{
public static void main(String[] args) throws Exception
    {
        new Shuffler().ShufflerRun();
    }int _playerCnt = 2;
Cards cards = new Cards();
List<Player> players = GeneratePlayers(_playerCnt);
Table table = new Table();
Setup setup = new Setup();
Round round = new Round();
Turn turn = new Turn();
Endcondition endcondition = new Endcondition();
public Shuffler() throws Exception
 {
 }
public int getPlayerCount()
{
return _playerCnt;}
public List<Player> GeneratePlayers(int cnt) throws Exception
{
List<Player> list = new ArrayList<Player>();
for(int i = 0; i < cnt; i++)
{
list.add(new Player());
list.get(i).Number = i+1;
}
return list;
}
public void ShufflerRun() throws Exception
{
setup.run();
while (!endcondition.end) 
{
round.run();
}
System.out.println("Press any key to terminate program");
System.in.read();
}
DeckClass gamedeck = new DeckClass(new String[]{"standard"}) ; 
public class Cards
{
public Cards() throws Exception
{
ace.value = 14 ; 
}
}
public class Player
{
public int Number;public void takeTurn() throws Exception
{
turn.run(this);
}
DeckClass mainDeck = new DeckClass() ; 
DeckClass trickDeck = new DeckClass() ; 
DeckClass warDeck = new DeckClass() ; 
public Player() throws Exception
{
}
}
public class Table
{
public Table() throws Exception
{
}
}
public class Setup
{
 int i ; 
 int j ; 
public void run() throws Exception
{
i = gamedeck.size() / 2 ; 
gamedeck.shuffle() ; 
for(Player item : players)
{
item.mainDeck.drawfrom(gamedeck, i);
}
}
}
public class Round
{
 int decksize ; 
 int i ; 
 int j ; 
public void run() throws Exception
{
for(Player item : players)
{
item.takeTurn();
}
i = players.get(1-1).warDeck.size() ; 
j = players.get(2-1).warDeck.size() ; 
checkShuffle() ; 
if(players.get(1-1).warDeck.get(i-1).Value() > players.get(2-1).warDeck.get(j-1).Value())
{
HelpMethods.printString("Player 1 played a: ") ; 
HelpMethods.printCard(players.get(1-1).warDeck.get(i-1)) ; 
HelpMethods.printString("\nPlayer 2 played a: ") ; 
HelpMethods.printCard(players.get(2-1).warDeck.get(j-1)) ; 
HelpMethods.printString("\nPlayer 1 won this battle\n\n\n\n") ; 
players.get(1-1).trickDeck.drawfrom(players.get(1-1).warDeck, i) ; 
players.get(1-1).trickDeck.drawfrom(players.get(2-1).warDeck, j) ; 
checkShuffle() ; 
endcondition.check() ; 
}
else
if(players.get(1-1).warDeck.get(i-1).Value() < players.get(2-1).warDeck.get(j-1).Value())
{
HelpMethods.printString("Player 1 played a: ") ; 
HelpMethods.printCard(players.get(1-1).warDeck.get(i-1)) ; 
HelpMethods.printString("\nPlayer 2 played a: ") ; 
HelpMethods.printCard(players.get(2-1).warDeck.get(j-1)) ; 
HelpMethods.printString("\nPlayer 2 won this battle\n\n\n\n") ; 
players.get(2-1).trickDeck.drawfrom(players.get(1-1).warDeck, i) ; 
players.get(2-1).trickDeck.drawfrom(players.get(2-1).warDeck, j) ; 
checkShuffle() ; 
endcondition.check() ; 
}
else

{
HelpMethods.printString("Let the war... BEGIN!!!!!\n") ; 
checkShuffle() ; 
for(Player item : players)
{
item.takeTurn();
}
for(Player item : players)
{
item.takeTurn();
}
for(Player item : players)
{
item.takeTurn();
}
}
HelpMethods.printString("\n\nPlayer 1 deck size = ") ; 
decksize = players.get(1-1).mainDeck.size() + players.get(1-1).trickDeck.size() + players.get(1-1).warDeck.size() ; 
HelpMethods.printNumber(decksize) ; 
decksize = players.get(2-1).mainDeck.size() + players.get(2-1).trickDeck.size() + players.get(2-1).warDeck.size() ; 
HelpMethods.printString("\nPlayer 2 deck size = ") ; 
HelpMethods.printNumber(decksize) ; 
HelpMethods.printString("\n\n") ; 
}
}
public class Turn
{
public void run(Player turntaker) throws Exception
{
int _ActionCnt = 1;
ArrayList<Integer> _ActionMapping = new ArrayList<Integer>();
if(true )
{System.out.println(_ActionCnt + ": " + "Play a card");
_ActionMapping.add(_ActionCnt);
_ActionCnt++;
}
if(true )
{System.out.println(_ActionCnt + ": " + "Surrender");
_ActionMapping.add(_ActionCnt);
_ActionCnt++;
}
System.out.println("Choose an action to perform: ");Scanner _ActionScanner = new Scanner(System.in);
Integer _ActionInput = _ActionScanner.nextInt();
switch (_ActionMapping.indexOf(_ActionInput))
{
case 0:
{
turntaker.warDeck.drawfrom(turntaker.mainDeck, 1) ; 
}
break;
case 1:
{
turntaker.warDeck.drawfrom(turntaker.mainDeck, turntaker.mainDeck.size()) ; 
}
break;
}checkShuffle() ; 
}
}
public class Endcondition
{
Player none = new Player();
Player winner = none;
boolean end = false;
public Endcondition() throws Exception
{
}
public void check() throws Exception
{
if(_playeranyfunc0())
{HelpMethods.printString("Player 1: \n") ; 
HelpMethods.printString("mainDeck \n") ; 
HelpMethods.printDeck(players.get(1-1).mainDeck) ; 
HelpMethods.printString("trickDeck \n") ; 
HelpMethods.printDeck(players.get(1-1).trickDeck) ; 
HelpMethods.printString("\n\nPlayer 2: \n") ; 
HelpMethods.printString("mainDeck \n") ; 
HelpMethods.printDeck(players.get(2-1).mainDeck) ; 
HelpMethods.printString("trickDeck \n") ; 
HelpMethods.printDeck(players.get(2-1).trickDeck) ; 
if(players.get(1-1).mainDeck.size() <=  0 )
{
winner = players.get(2-1) ; 
}
else

{
winner = players.get(1-1) ; 
}
end = true;
if(winner == none)
{
}
else
{
System.out.println(" The winner is player" + (players.indexOf(winner) + 1));
}
}
}
}
void  checkShuffle()
{
if(players.get(1-1).mainDeck.size() <=  0 )
{
players.get(1-1).mainDeck.drawfrom(players.get(1-1).trickDeck, players.get(1-1).trickDeck.size()) ; 
players.get(1-1).mainDeck.shuffle() ; 
}
if(players.get(2-1).mainDeck.size() <=  0 )
{
players.get(2-1).mainDeck.drawfrom(players.get(2-1).trickDeck, players.get(2-1).trickDeck.size()) ; 
players.get(2-1).mainDeck.shuffle() ; 
}
}
public boolean _playeranyfunc0()
{
for(Player p : players)
{
if (p.mainDeck.size() <=  0 )
{
return true;
}
}
return false;
}

}