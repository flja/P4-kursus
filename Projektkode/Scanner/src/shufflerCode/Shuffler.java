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
import java.util.Scanner;

public class Shuffler
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
    }

    public List<Player> GeneratePlayers(int cnt) throws Exception
    {
        List<Player> list = new ArrayList<Player>();
        for (int i = 0; i < cnt; i++)
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
            System.out.println("Hello");
            round.run();
        }
    }

    DeckClass gamedeck = new DeckClass(new String[]{"standard", "hearts", "2h"});

    public class Cards
    {
        public Cards() throws Exception
        {
            king.value = 10;
            queen.value = 10;
            jack.value = 10;
            ace.value = 11;
        }
    }

    public class Player
    {
        public void takeTurn() throws Exception
        {
            turn.run(this);
        }

        DeckClass Hand = new DeckClass();

        public Player() throws Exception
        {
        }
    }

    public class Table
    {
        DeckClass pilehidden = new DeckClass();
        DeckClass pilevisible = new DeckClass();

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
                item.Hand.drawfrom(gamedeck, 2);
            }
            table.pilehidden.drawfrom(gamedeck, 1);
            table.pilevisible.drawfrom(gamedeck, 1);
        }
    }

    public class Round
    {
        public void run() throws Exception
        {
            while ((table.pilehidden.totalValue + table.pilevisible.totalValue) < 17)
            {
                table.pilevisible.drawfrom(gamedeck, 1);
            }
            if (table.pilehidden.totalValue + table.pilevisible.totalValue < 21)
            {
                for (Player item : players)
                {
                    item.takeTurn();
                }
            }
            endcondition.check();
        }
    }

    public class Turn
    {
        public void run(Player turntaker) throws Exception
        {
            while (turntaker.Hand.totalValue < 21)
            {
                int _ActionCnt = 1;
                int[] _ActionMapping = new int[3];
                if (true)
                {
                    System.out.println(_ActionCnt + ": " + "Hit");
                    _ActionMapping[0] = _ActionCnt;
                    _ActionCnt++;
                }
                if (true)
                {
                    System.out.println(_ActionCnt + ": " + "Stand");
                    _ActionMapping[1] = _ActionCnt;
                    _ActionCnt++;
                }
                if (turntaker.Hand.size == 2 && turntaker.Hand.get(1) == turntaker.Hand.get(2))
                {
                    System.out.println(_ActionCnt + ": " + "Split");
                    _ActionMapping[2] = _ActionCnt;
                    _ActionCnt++;
                }
                System.out.println("Choose an action to perform: ");
                Scanner _ActionScanner = new Scanner(System.in);
                int _ActionInput = _ActionScanner.nextInt();
                switch (Arrays.asList(_ActionMapping).indexOf(_ActionInput))
                {
                    case 0:
                    {
                        turntaker.Hand.drawfrom(gamedeck, 1);
                    }
                    break;
                    case 1:
                    {
                    }
                    break;
                    case 2:
                    {
                    }
                    break;
                }
            }
            endcondition.check();
        }
    }

    public class Endcondition
    {
        Player winner;
        Player none = new Player();
        boolean end = false;

        public Endcondition() throws Exception
        {
        }

        public void check() throws Exception
        {
            if (table.pilehidden.totalValue + table.pilevisible.totalValue >= 21 || _playeranyfunc0())
            {
                if (table.pilehidden.totalValue + table.pilevisible.totalValue > 21)
                {
                    winner = players.get(1);
                }
                else if (table.pilehidden.totalValue + table.pilevisible.totalValue == 21)
                {
                    winner = none;
                }
                else if (players.get(1).Hand.totalValue == 21)
                {
                    winner = players.get(1);
                }
                else if (players.get(1).Hand.totalValue > table.pilehidden.totalValue + table.pilevisible.totalValue)
                {
                    winner = players.get(1);
                }
                else

                {
                    winner = none;
                }
                end = true;
                if (winner == none)
                {
                    System.out.println("No one wins");
                }
                else
                {
                    System.out.println(" The winner is player" + players.indexOf(winner));
                }
            }
        }
    }

    int myFunc(int i,
               int k)
    {
        int j;
        j = i;
        if (j > k)
        {
            k = j;
        }
        return i;
    }

    public boolean _playeranyfunc0()
    {
        for (Player p : players)
        {
            if (p.Hand.totalValue >= 21)
            {
                return true;
            }
        }
        return false;
    }

}