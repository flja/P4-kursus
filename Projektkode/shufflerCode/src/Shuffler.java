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
public static void main(String[] args)
    {
        System.out.println("Hey");
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
}
DeckClass gamedeck = new DeckClass(new String[]{"standard", "hearts", "2h"}) ; 
public class Cards
{
public Cards() throws Exception
{
king.value = 10 ; 
queen.value = 10 ; 
jack.value = 10 ; 
ace.value = 11 ; 
}
}
public class Player
{
public int Number;public void takeTurn() throws Exception
{
turn.run(this);
}
DeckClass playerHand = new DeckClass() ; 
public Player() throws Exception
{
}
}
public class Table
{
DeckClass dealerHand = new DeckClass() ; 
public Table() throws Exception
{
}
}
public class Setup
{
public void run() throws Exception
{
gamedeck.shuffle() ; 
for(Player item : players)
{
item.playerHand.drawfrom(gamedeck, 2);
}
table.dealerHand.drawfrom(gamedeck, 2) ; 
}
}
public class Round
{
public void run() throws Exception
{
HelpMethods.printString("The dealers visible card is: ") ; 
HelpMethods.printCard(table.dealerHand.get(1-1)) ; 
HelpMethods.printString("\n") ; 
if(table.dealerHand.totalValue() < 21)
{
for(Player item : players)
{
item.takeTurn();
}
}
HelpMethods.printString("The dealers hand consists of ") ; 
HelpMethods.printDeck(table.dealerHand) ; 
HelpMethods.printString("\n Dealers cards add up to a total value of: ") ; 
HelpMethods.printNumber(table.dealerHand.totalValue()) ; 
HelpMethods.printString("\n") ; 
while(table.dealerHand.totalValue() < 17 &&_playeranyfunc0())
{
table.dealerHand.drawfrom(gamedeck, 1) ; 
HelpMethods.printString("Dealer draws\n Dealers hand now consists of: ") ; 
HelpMethods.printDeck(table.dealerHand) ; 
HelpMethods.printString("\n Dealers cards add up to a total value of: ") ; 
HelpMethods.printNumber(table.dealerHand.totalValue()) ; 
HelpMethods.printString("\n") ; 
}
endcondition.check() ; 
}
}
public class Turn
{
boolean endturn = false ; 
 int cnt ; 
public void run(Player turntaker) throws Exception
{
cnt = getPlayerCount() ; 
endturn =  false  ; 
HelpMethods.printString("Player ") ; 
HelpMethods.printNumber(turntaker.Number) ; 
HelpMethods.printString("\nYour hand contains: \n") ; 
HelpMethods.printDeck(turntaker.playerHand) ; 
HelpMethods.printString("\n Your cards add up to a total value of: ") ; 
HelpMethods.printNumber(turntaker.playerHand.totalValue()) ; 
HelpMethods.printString("\n") ; 
while(turntaker.playerHand.totalValue() < 21 &&endturn ==  false )
{
int _ActionCnt = 1;
ArrayList<Integer> _ActionMapping = new ArrayList<Integer>();
if(true )
{System.out.println(_ActionCnt + ": " + "Hit");
_ActionMapping.add(_ActionCnt);
_ActionCnt++;
}
if(true )
{System.out.println(_ActionCnt + ": " + "Stand");
_ActionMapping.add(_ActionCnt);
_ActionCnt++;
}
if(turntaker.playerHand.size() == 2 &&turntaker.playerHand.get(1-1) == turntaker.playerHand.get(2-1))
{System.out.println(_ActionCnt + ": " + "Split");
_ActionMapping.add(_ActionCnt);
_ActionCnt++;
}
System.out.println("Choose an action to perform: ");Scanner _ActionScanner = new Scanner(System.in);
Integer _ActionInput = _ActionScanner.nextInt();
switch (_ActionMapping.indexOf(_ActionInput))
{
case 0:
{
turntaker.playerHand.drawfrom(gamedeck, 1) ; 
HelpMethods.printString("you hit \n your hand now consists of: ") ; 
HelpMethods.printDeck(turntaker.playerHand) ; 
HelpMethods.printString("\n Your cards add up to a total value of: ") ; 
HelpMethods.printNumber(turntaker.playerHand.totalValue()) ; 
HelpMethods.printString("\n") ; 
}
break;
case 1:
{
endturn = true  ; 
}
break;
case 2:
{
endturn = true  ; 
HelpMethods.printString("No!") ; 
}
break;
}}
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
 int i ; 
 int playercount ; 
public void check() throws Exception
{
if(true )
{playercount = getPlayerCount() ; 
while(i < playercount)
{
 int j ; 
j = i + 1 ; 
if(players.get(j-1).playerHand.totalValue() > 21)
{
HelpMethods.printString("Player ") ; 
HelpMethods.printNumber(j) ; 
HelpMethods.printString(" has over 21\n") ; 
HelpMethods.printString("Dealer wins against ") ; 
HelpMethods.printString("Player ") ; 
HelpMethods.printNumber(j) ; 
HelpMethods.printString("\n") ; 
}
else
if(table.dealerHand.totalValue() > 21)
{
HelpMethods.printString("Dealer has over 21\n") ; 
HelpMethods.printString("Player ") ; 
HelpMethods.printNumber(j) ; 
HelpMethods.printString(" wins\n") ; 
}
else
if(players.get(j-1).playerHand.totalValue() == table.dealerHand.totalValue() &&table.dealerHand.totalValue() == 21)
{
HelpMethods.printString("Both Player ") ; 
HelpMethods.printNumber(j) ; 
HelpMethods.printString("and the dealer has 21\nPlayer ") ; 
HelpMethods.printNumber(j) ; 
HelpMethods.printString(" ties with the dealer\n") ; 
}
else
if(players.get(j-1).playerHand.totalValue() == table.dealerHand.totalValue())
{
HelpMethods.printString("The dealer and player ") ; 
HelpMethods.printNumber(j) ; 
HelpMethods.printString("Has the same amount of points, the dealer wins by tie\n") ; 
}
else
if(table.dealerHand.totalValue() == 21)
{
HelpMethods.printString("Dealer has 21\n") ; 
HelpMethods.printString("Dealer wins against ") ; 
HelpMethods.printString("Player ") ; 
HelpMethods.printNumber(j) ; 
HelpMethods.printString("\n") ; 
}
else
if(players.get(j-1).playerHand.totalValue() == 21)
{
HelpMethods.printString("Player ") ; 
HelpMethods.printNumber(j) ; 
HelpMethods.printString(" has 21\n") ; 
HelpMethods.printString("Player ") ; 
HelpMethods.printNumber(j) ; 
HelpMethods.printString(" wins\n") ; 
}
else
if(players.get(j-1).playerHand.totalValue() > table.dealerHand.totalValue())
{
HelpMethods.printString("Player ") ; 
HelpMethods.printNumber(j) ; 
HelpMethods.printString(" has a higher value than dealer\n") ; 
HelpMethods.printString("Player ") ; 
HelpMethods.printNumber(j) ; 
HelpMethods.printString(" wins\n") ; 
}
else

{
HelpMethods.printString("Dealer has a higher value than player") ; 
HelpMethods.printNumber(j) ; 
HelpMethods.printString("\nDealer wins against") ; 
HelpMethods.printString("Player ") ; 
HelpMethods.printNumber(j) ; 
HelpMethods.printString("\n") ; 
}
i = i + 1 ; 
HelpMethods.printString("\n") ; 
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
 int  myFunc( int i , 
 int k )
{
 int j ; 
j = i ; 
if(j > k)
{
k = j ; 
}
 return i ; 
}
public boolean _playeranyfunc0()
{
for(Player p : players)
{
if (p.playerHand.totalValue() < 21)
{
return true;
}
}
return false;
}

}