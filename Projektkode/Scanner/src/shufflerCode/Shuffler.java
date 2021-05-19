package shufflerCode;
import com.company.CodeGenerator.TemplateCode.HelpMethods;
import com.company.CodeGenerator.TemplateCode.ActionClass;
import com.company.CodeGenerator.TemplateCode.CardClass;
import com.company.CodeGenerator.TemplateCode.DeckClass;
import com.company.CodeGenerator.TemplateCode.two;
import com.company.CodeGenerator.TemplateCode.three;
import com.company.CodeGenerator.TemplateCode.four;
import com.company.CodeGenerator.TemplateCode.five;
import com.company.CodeGenerator.TemplateCode.six;
import com.company.CodeGenerator.TemplateCode.seven;
import com.company.CodeGenerator.TemplateCode.eight;
import com.company.CodeGenerator.TemplateCode.nine;
import com.company.CodeGenerator.TemplateCode.ten;
import com.company.CodeGenerator.TemplateCode.jack;
import com.company.CodeGenerator.TemplateCode.queen;
import com.company.CodeGenerator.TemplateCode.king;
import com.company.CodeGenerator.TemplateCode.joker;
import com.company.CodeGenerator.TemplateCode.ace;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;public class Shuffler
{
int _playerCnt = 1;
Cards cards = new Cards();
List<Player> players = GeneratePlayers(_playerCnt);
Table table = new Table();
Setup setup = new Setup();
Round round = new Round();
Turn turn = new Turn();
Endcondition endcondition = new Endcondition();
public Shuffler() throws Exception
 {
 }public List<Player> GeneratePlayers(int cnt) throws Exception
{
List<Player> list = new ArrayList<Player>();
for(int i = 0; i < cnt; i++)
{
list.add(new Player());
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
public void takeTurn() throws Exception
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
HelpMethods.printCard(table.dealerHand.get(0)) ; 
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
while(table.dealerHand.totalValue() < 17 &&players.get(0).playerHand.totalValue() < 22)
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
public void run(Player turntaker) throws Exception
{
endturn =  false  ; 
HelpMethods.printString("Your hand contains: \n") ; 
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
if(turntaker.playerHand.size() == 2 &&turntaker.playerHand.get(0) == turntaker.playerHand.get(1))
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
public void check() throws Exception
{
if(true )
{if(table.dealerHand.totalValue() > 21)
{
HelpMethods.printString("Dealer has over 21\n") ; 
winner = players.get(0) ; 
}
else
if(players.get(0).playerHand.totalValue() > 21)
{
HelpMethods.printString("The player has over 21\n") ; 
HelpMethods.printString("Dealer wins") ; 
}
else
if(players.get(0).playerHand.totalValue() == table.dealerHand.totalValue() &&table.dealerHand.totalValue() == 21)
{
HelpMethods.printString("The game is a tie") ; 
}
else
if(players.get(0).playerHand.totalValue() == table.dealerHand.totalValue())
{
HelpMethods.printString("The dealer has the same card as the player the dealer wins by tie\n") ; 
HelpMethods.printString("Dealer wins") ; 
}
else
if(table.dealerHand.totalValue() == 21)
{
HelpMethods.printString("Dealer has 21\n") ; 
HelpMethods.printString("Dealer wins") ; 
}
else
if(players.get(0).playerHand.totalValue() == 21)
{
HelpMethods.printString("Player has 21\n") ; 
winner = players.get(0) ; 
}
else
if(players.get(0).playerHand.totalValue() > table.dealerHand.totalValue())
{
HelpMethods.printString("Player has a higher value than dealer\n") ; 
winner = players.get(0) ; 
}
else

{
HelpMethods.printString("Dealer has a higher value than player\n") ; 
HelpMethods.printString("Dealer wins") ; 
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

}