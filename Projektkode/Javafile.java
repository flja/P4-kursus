package shufflerCode;

import com.company.CodeGenerator.TemplateCode;

public class Main
{
    public static void main(String[] args)
    {
    }

    DeckClass gamedeck = new DeckClass() = new DeckClass(new String[]{standard, hearts, 2h});

    public class Cards
    {
        public Cards()
        {
            king.value = 10;
            queen.value = 10;
            jack.value = 10;
            ace.value = 11;
        }
    }

    public class Player
    {
        hand Hand = new hand();

        public Player()
        {
            Hand.visibility = visible;
        }
    }

    int PlayerCnt = 1;

    public class Table
    {
        DeckClass pilehidden = new DeckClass();
        DeckClass pilevisible = new DeckClass();

        public Table()
        {
            pilehidden.visibility = hidden;
            pilevisible.visibility = visible;
        }
    }

    public class Setup
    {
        public void run()
        {
            gamedeck.shuffle();
            players.Hand.drawfrom(gamedeck, 2);
            table.pilehidden.drawfrom(gamedeck, 1);
            table.pilevisible.drawfrom(gamedeck, 1);
        }
    }

    public class Round
    {
        public void run()
        {
            while ((table.pilehidden.totalValue + table.pilevisible.totalValue) < 17)
            {
                table.pilevisible.drawfrom(gamedeck, 1);
            }
            if (table.pilehidden.totalValue + table.pilevisible.totalValue < 21)
            {
                players.takeTurn();
            }
            endcondition.check();
        }
    }

    public class Turn
    {
        int i = 0;

        public void run()
        {
            switch (i)
            {
                case 0:
                    i = 3;
                    break;
                default:
            }
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
                        endTurn();
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
        Player none = new player();
        boolean end = false;

        public void check()
        {
            if (table.pilehidden.totalValue + table.pilevisible.totalValue >= 21 || players.any.Hand.totalValue >= 21)
            {
                if (table.pilehidden.totalValue + table.pilevisible.totalValue > 21)
                {
                    winner = player.get(1);
                } else if (table.pilehidden.totalValue + table.pilevisible.totalValue == 21)
                {
                    winner = none;
                } else if (player.get(1).Hand.totalValue == 21)
                {
                    winner = player.get(1);
                } else if (player.get(1).Hand.totalValue > table.pilehidden.totalValue + table.pilevisible.totalValue)
                {
                    winner = player.get(1);
                } else

                {
                    winner = none;
                }
                end = true;
            }
        }
    }

    int myFunc(int i, int k)
    {
        int j;
        j = i;
        if (j > k)
        {
            k = j;
        }
        return i;
    }
}