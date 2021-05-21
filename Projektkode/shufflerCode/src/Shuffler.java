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
import java.util.Scanner;

public class Shuffler
{
    public static void main(String[] args) throws Exception
    {
        try
        {
            new Shuffler().ShufflerRun();
        } catch (Exception e)
        {
            System.out.println(e.getMessage());
            System.out.println("Press any key to terminate");
            System.in.read();
        }
    }

    int _playerCnt = 3;
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
        return _playerCnt;
    }

    public List<Player> GeneratePlayers(int cnt) throws Exception
    {
        List<Player> list = new ArrayList<Player>();
        for (int i = 0; i < cnt; i++)
        {
            list.add(new Player());
            list.get(i).Number = i + 1;
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

    DeckClass gamedeck = new DeckClass(new String[]{"standard"});

    public class Cards
    {
        public Cards() throws Exception
        {
        }
    }

    public class Player
    {
        public int Number;

        public void takeTurn() throws Exception
        {
            turn.run(this);
        }

        boolean out = false;
        DeckClass playerHand = new DeckClass();
        DeckClass trickDeck = new DeckClass();

        public Player() throws Exception
        {
            out = false;
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
        public void run() throws Exception
        {
            gamedeck.shuffle();
            for (Player item : players)
            {
                item.playerHand.drawfrom(gamedeck, 7);
            }
        }
    }

    public class Round
    {
        public void run() throws Exception
        {
            for (Player item : players)
            {
                item.takeTurn();
            }
        }
    }

    public class Turn
    {
        int playernumber;
        int i;
        boolean hasAce = false;
        boolean hasTwo = false;
        boolean hasThree = false;
        boolean hasFour = false;
        boolean hasFive = false;
        boolean hasSix = false;
        boolean hasSeven = false;
        boolean hasEight = false;
        boolean hasNine = false;
        boolean hasTen = false;
        boolean hasJack = false;
        boolean hasQueen = false;
        boolean hasKing = false;

        public void run(Player turntaker) throws Exception
        {
            hasAce = hasCard(ace.value, turntaker.Number);
            hasTwo = hasCard(two.value, turntaker.Number);
            hasThree = hasCard(three.value, turntaker.Number);
            hasFour = hasCard(four.value, turntaker.Number);
            hasFive = hasCard(five.value, turntaker.Number);
            hasSix = hasCard(six.value, turntaker.Number);
            hasSeven = hasCard(seven.value, turntaker.Number);
            hasEight = hasCard(eight.value, turntaker.Number);
            hasNine = hasCard(nine.value, turntaker.Number);
            hasTen = hasCard(ten.value, turntaker.Number);
            hasJack = hasCard(jack.value, turntaker.Number);
            hasQueen = hasCard(queen.value, turntaker.Number);
            hasKing = hasCard(king.value, turntaker.Number);
            HelpMethods.printString("Player ");
            HelpMethods.printNumber(turntaker.Number);
            HelpMethods.printString(" your hand consists of: ");
            HelpMethods.printDeck(turntaker.playerHand);
            HelpMethods.printString("\nChoose a player to ask\n");
            {
                int _ActionCnt = 1;
                ArrayList<Integer> _ActionMapping = new ArrayList<Integer>();
                if (turntaker.Number != 1)
                {
                    System.out.println(_ActionCnt + ": " + "Player 1");
                    _ActionMapping.add(_ActionCnt);
                    _ActionCnt++;
                }
                if (turntaker.Number != 2)
                {
                    System.out.println(_ActionCnt + ": " + "Player 2");
                    _ActionMapping.add(_ActionCnt);
                    _ActionCnt++;
                }
                if (turntaker.Number != 3)
                {
                    System.out.println(_ActionCnt + ": " + "Player 3");
                    _ActionMapping.add(_ActionCnt);
                    _ActionCnt++;
                }
                System.out.println("Choose an action to perform: ");
                Scanner _ActionScanner = new Scanner(System.in);
                Integer _ActionInput = _ActionScanner.nextInt();
                switch (_ActionMapping.indexOf(_ActionInput))
                {
                    case 0:
                    {
                        playernumber = 1;
                    }
                    break;
                    case 1:
                    {
                        playernumber = 2;
                    }
                    break;
                    case 2:
                    {
                        playernumber = 3;
                    }
                    break;
                }
            }
            HelpMethods.printString("Choose a card to ask for\n");
            {
                int _ActionCnt = 1;
                ArrayList<Integer> _ActionMapping = new ArrayList<Integer>();
                if (hasAce)
                {
                    System.out.println(_ActionCnt + ": " + "aces");
                    _ActionMapping.add(_ActionCnt);
                    _ActionCnt++;
                }
                if (hasTwo)
                {
                    System.out.println(_ActionCnt + ": " + "twos");
                    _ActionMapping.add(_ActionCnt);
                    _ActionCnt++;
                }
                if (hasThree)
                {
                    System.out.println(_ActionCnt + ": " + "threes");
                    _ActionMapping.add(_ActionCnt);
                    _ActionCnt++;
                }
                if (hasFour)
                {
                    System.out.println(_ActionCnt + ": " + "fours");
                    _ActionMapping.add(_ActionCnt);
                    _ActionCnt++;
                }
                if (hasFive)
                {
                    System.out.println(_ActionCnt + ": " + "fives");
                    _ActionMapping.add(_ActionCnt);
                    _ActionCnt++;
                }
                if (hasSix)
                {
                    System.out.println(_ActionCnt + ": " + "six'");
                    _ActionMapping.add(_ActionCnt);
                    _ActionCnt++;
                }
                if (hasSeven)
                {
                    System.out.println(_ActionCnt + ": " + "sevens");
                    _ActionMapping.add(_ActionCnt);
                    _ActionCnt++;
                }
                if (hasEight)
                {
                    System.out.println(_ActionCnt + ": " + "eights");
                    _ActionMapping.add(_ActionCnt);
                    _ActionCnt++;
                }
                if (hasNine)
                {
                    System.out.println(_ActionCnt + ": " + "nines");
                    _ActionMapping.add(_ActionCnt);
                    _ActionCnt++;
                }
                if (hasTen)
                {
                    System.out.println(_ActionCnt + ": " + "tens");
                    _ActionMapping.add(_ActionCnt);
                    _ActionCnt++;
                }
                if (hasJack)
                {
                    System.out.println(_ActionCnt + ": " + "jacks");
                    _ActionMapping.add(_ActionCnt);
                    _ActionCnt++;
                }
                if (hasQueen)
                {
                    System.out.println(_ActionCnt + ": " + "queens");
                    _ActionMapping.add(_ActionCnt);
                    _ActionCnt++;
                }
                if (hasKing)
                {
                    System.out.println(_ActionCnt + ": " + "kings");
                    _ActionMapping.add(_ActionCnt);
                    _ActionCnt++;
                }
                System.out.println("Choose an action to perform: ");
                Scanner _ActionScanner = new Scanner(System.in);
                Integer _ActionInput = _ActionScanner.nextInt();
                System.out.println("\n\n\n" + _ActionInput + " " + _ActionMapping.indexOf(_ActionInput)+ "\n\n\n");
                switch (_ActionMapping.indexOf(_ActionInput))
                {
                    case 0:
                    {
                        i = ace.value;
                        askForCards(turntaker.Number, playernumber, i);
                    }
                    break;
                    case 1:
                    {
                        askForCards(turntaker.Number, playernumber, two.value);
                    }
                    break;
                    case 2:
                    {
                        i = three.value;
                        HelpMethods.printNumber(i);
                        HelpMethods.printNumber(three.value);
                        askForCards(turntaker.Number, playernumber, i);
                    }
                    break;
                    case 3:
                    {
                        askForCards(turntaker.Number, playernumber, four.value);
                    }
                    break;
                    case 4:
                    {
                        askForCards(turntaker.Number, playernumber, five.value);
                    }
                    break;
                    case 5:
                    {
                        askForCards(turntaker.Number, playernumber, six.value);
                    }
                    break;
                    case 6:
                    {
                        askForCards(turntaker.Number, playernumber, seven.value);
                    }
                    break;
                    case 7:
                    {
                        askForCards(turntaker.Number, playernumber, eight.value);
                    }
                    break;
                    case 8:
                    {
                        askForCards(turntaker.Number, playernumber, nine.value);
                    }
                    break;
                    case 9:
                    {
                        askForCards(turntaker.Number, playernumber, ten.value);
                    }
                    break;
                    case 10:
                    {
                        askForCards(turntaker.Number, playernumber, jack.value);
                    }
                    break;
                    case 11:
                    {
                        askForCards(turntaker.Number, playernumber, queen.value);
                    }
                    break;
                    case 12:
                    {
                        askForCards(turntaker.Number, playernumber, king.value);
                    }
                    break;
                }
            }
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
            if (true)
            {
                end = true;
                if (winner == none)
                {
                }
                else
                {
                    System.out.println(" The winner is player" + (players.indexOf(winner) + 1));
                }
            }
        }
    }

    void askForCards(int to,
                     int from,
                     int askValue) throws Exception
    {
        DeckClass temp = new DeckClass();
        int CardsRecieved = 0;
        int handsize;
        handsize = players.get(from - 1).playerHand.size();
        while (handsize != 0)
        {
            HelpMethods.printString("\nTwos value: ");
            HelpMethods.printNumber(two.value);
            HelpMethods.printString("\nhandsize: ");
            HelpMethods.printNumber(handsize);
            HelpMethods.printString("\nCardValue: ");
            HelpMethods.printNumber(players.get(from - 1).playerHand.get(handsize - 1).Value());
            HelpMethods.printString("\nAskValue: ");
            HelpMethods.printNumber(askValue);
            HelpMethods.printString("\n");
            HelpMethods.printDeck(players.get(from - 1).playerHand);
            if (players.get(from - 1).playerHand.get(handsize - 1).Value() == askValue)
            {
                players.get(to - 1).playerHand.drawfrom(players.get(from - 1).playerHand, 1);
                CardsRecieved = CardsRecieved + 1;
            }
            else

            {
                temp.drawfrom(players.get(from - 1).playerHand, 1);
            }
            handsize = players.get(from - 1).playerHand.size();
        }
        HelpMethods.printString("\nPlayer ");
        HelpMethods.printNumber(to);
        HelpMethods.printString(" recieved ");
        HelpMethods.printNumber(CardsRecieved);
        HelpMethods.printString(" Cards ");
        HelpMethods.printString("from player ");
        HelpMethods.printNumber(from);
        HelpMethods.printString("\n");
        players.get(from - 1).playerHand.drawfrom(temp, temp.size());
    }

    boolean hasCard(int cardValue,
                    int playerNumber) throws Exception
    {
        int i = 1;
        while (i < players.get(playerNumber - 1).playerHand.size())
        {
            if (players.get(playerNumber - 1).playerHand.get(i - 1).Value() == cardValue)
            {
                return true;
            }
            i = i + 1;
        }
        return false;
    }

}