import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.StringTokenizer;

public class Zad_2
{
    public static void main(String[] args) throws FileNotFoundException
    {
        File file = new File("cron.txt");
        Scanner in = new Scanner(file);
        String dane = in.nextLine();
        StringTokenizer st = new StringTokenizer(dane,"|");

        boolean pop_lenght = true;
        boolean pop_dane = true;
        int num_col = 1;

        String[] sheduleMinute = new String[6];
        String[] sheduleHour = new String[24];
        String[] sheduleDayOfMonth = new String[31];
        String[] sheduleDayOfWeek = new String[7];


        while(st.hasMoreElements() && pop_lenght && pop_dane)
        {
            int ele = 0;
            if(num_col == 1)
            {
                StringTokenizer st1 = new StringTokenizer(st.nextElement().toString(),",");

                while(st1.hasMoreElements() && pop_dane)
                {
                    String dana = st1.nextElement().toString();
                    if(ele == 6)
                    {
                        System.out.println("Za dozo argumentow w kolumnie minuta.");
                        pop_dane = false;
                    }

                    if(pop_dane)
                    {
                        pop_dane = minute(dana);
                        if(dana.equals("*") && pop_dane)
                        {
                            sheduleMinute[ele]=dana;
                            ele++;
                        }
                        else if(pop_dane)
                        {
                            boolean pop_powt=true;
                            for(int i=0; i < ele && pop_powt; i++)
                            {
                                if(sheduleMinute[i].equals(dana))
                                {
                                    pop_powt = false;
                                }
                            }

                            if(pop_powt)
                            {
                                sheduleMinute[ele] = dana;
                                ele++;
                            }
                        }
                        else
                        {
                            System.out.println("Niepoprawna dana w kolumnie minuta.");
                        }

                        if(pop_dane && sheduleMinute[0].equals("*") && ele >= 2 || pop_dane && sheduleMinute[ele-1].equals("*")&&ele-1 >= 1)
                        {
                            System.out.println("* moze wystepowac tylko jako jeden argument w kolumnie minuta.");
                            pop_dane = false;
                        }
                    }

                }

                num_col++;
            }
            else if(num_col == 2)
            {
                StringTokenizer st1 = new StringTokenizer(st.nextElement().toString(), ",");

                while (st1.hasMoreElements() && pop_dane)
                {
                    String dana = st1.nextElement().toString();
                    if (ele == 24)
                    {
                        System.out.println("Za dozo argumentow w kolumnie godzina.");
                        pop_dane = false;
                    }

                    if (dana.length() <= 2 && pop_dane)
                    {
                        if (pop_dane)
                        {
                            pop_dane = hour(dana);
                            if (dana.equals("*") && pop_dane)
                            {
                                sheduleHour[ele] = dana;
                                ele++;
                            }
                            else if(pop_dane)
                            {
                                boolean pop_powt = true;
                                for (int i = 0; i < ele && pop_powt; i++)
                                {
                                    if (sheduleHour[i].equals(dana))
                                    {
                                        pop_powt = false;
                                    }
                                }

                                if (pop_powt)
                                {
                                    sheduleHour[ele] = dana;
                                    ele++;
                                }
                            }
                            else
                            {
                                System.out.println("Niepoprawna dana w kolumnie godzina.");
                            }

                            if (pop_dane && sheduleHour[0].equals("*") && ele >= 2 || pop_dane && sheduleHour[ele - 1].equals("*") && ele - 1 >= 1) {
                                System.out.println("* moze wystepowac tylko jako jeden argument w kolumnie godzina.");
                                pop_dane = false;
                            }
                        }
                    }
                    else if (dana.length() > 2 && pop_dane)
                    {
                        StringTokenizer st2 = new StringTokenizer(dana,"-");


                        String dana1 = st2.nextElement().toString();
                        pop_dane = hour(dana1);

                        if(dana1.equals("*") && pop_dane)
                        {
                            System.out.println("* nie moze wystepowac w skladni - w kolumnie godzina");
                            pop_dane = false;
                        }
                        else if(pop_dane)
                        {
                            String dana2 = st2.nextElement().toString();
                            pop_dane = hour(dana2);

                            if(dana2.equals("*") && pop_dane)
                            {
                                System.out.println("* nie moze wystepowac w skladni - w kolumnie godzina");
                                pop_dane = false;
                            }
                            else if(pop_dane)
                            {
                                pop_dane = new Integer(dana1) < new Integer(dana2);

                                if(!pop_dane)
                                {
                                    System.out.println("Niepoprawna skladnia w kolumnie godzina .");
                                }

                                for(Integer i = new Integer(dana1); i <= new Integer(dana2)&&pop_dane ; i++)
                                {
                                    boolean pop_powt = true;
                                    for (int j = 0; j < ele && pop_powt; j++)
                                    {
                                        if (sheduleHour[j].equals(i.toString()))
                                        {
                                            pop_powt = false;
                                        }
                                    }

                                    if (pop_powt)
                                    {
                                        sheduleHour[ele] = i.toString();
                                        ele++;
                                    }
                                }

                            }
                            else
                            {
                                System.out.println("Niepoprawna dana w skladni - w kolumnie godzina.");
                            }
                        }
                        else
                        {
                            System.out.println("Niepoprawna dana w skladni - w kolumnie godzina.");
                        }
                    }
                }

                num_col++;
            }
            else if(num_col == 3)
            {
                StringTokenizer st1 = new StringTokenizer(st.nextElement().toString(), ",");

                while (st1.hasMoreElements() && pop_dane)
                {
                    String dana = st1.nextElement().toString();
                    if (ele == 31)
                    {
                        System.out.println("Za dozo argumentow w kolumnie dnia miesiaca.");
                        pop_dane = false;
                    }

                    if (dana.length() <= 2 && pop_dane)
                    {
                        if (pop_dane)
                        {
                            pop_dane = day_Month(dana);
                            if (dana.equals("*") && pop_dane)
                            {
                                sheduleDayOfMonth[ele] = dana;
                                ele++;
                            }
                            else if(pop_dane)
                            {
                                boolean pop_powt = true;
                                for (int i = 0; i < ele && pop_powt; i++)
                                {
                                    if (sheduleDayOfMonth[i].equals(dana))
                                    {
                                        pop_powt = false;
                                    }
                                }

                                if (pop_powt)
                                {
                                    sheduleDayOfMonth[ele] = dana;
                                    ele++;
                                }
                            }
                            else
                            {
                                System.out.println("Niepoprawna dana w kolumnie dnia miesiaca.");
                            }

                            if (pop_dane && sheduleDayOfMonth[0].equals("*") && ele >= 2 || pop_dane && sheduleDayOfMonth[ele - 1].equals("*") && ele - 1 >= 1) {
                                System.out.println("* moze wystepowac tylko jako jeden argument w kolumnie dnia miesiaca.");
                                pop_dane = false;
                            }
                        }
                    }
                    else if (dana.length() > 2 && pop_dane)
                    {
                        StringTokenizer st2 = new StringTokenizer(dana,"-");


                        String dana1 = st2.nextElement().toString();
                        pop_dane = day_Month(dana1);

                        if(dana1.equals("*") && pop_dane)
                        {
                            System.out.println("* nie moze wystepowac w skladani - w kolumnie dnia miesiaca.");
                            pop_dane = false;
                        }
                        else if(pop_dane)
                        {
                            String dana2 = st2.nextElement().toString();
                            pop_dane = day_Month(dana2);

                            if(dana2.equals("*") && pop_dane)
                            {
                                System.out.println("* nie moze wystepowac w skladni - w kolumnie dnia miesiaca.");
                                pop_dane = false;
                            }
                            else if(pop_dane)
                            {
                                pop_dane = new Integer(dana1) < new Integer(dana2);

                                if(!pop_dane)
                                {
                                    System.out.println("Niepoprawna skladnia w - w kolumnie dnia miesiaca..");
                                }

                                for(Integer i = new Integer(dana1); i <= new Integer(dana2)&&pop_dane ; i++)
                                {
                                    boolean pop_powt = true;
                                    for (int j = 0; j < ele && pop_powt; j++)
                                    {
                                        if (sheduleDayOfMonth[j].equals(i.toString()))
                                        {
                                            pop_powt = false;
                                        }
                                    }

                                    if (pop_powt)
                                    {
                                        sheduleDayOfMonth[ele] = i.toString();
                                        ele++;
                                    }
                                }

                            }
                            else
                            {
                                System.out.println("Niepoprawna dana w skladni - w kolumnie dnia miesiaca..");
                            }
                        }
                        else
                        {
                            System.out.println("Niepoprawna dana w skladni - w kolumnie dnia miesiaca..");
                        }
                    }
                }
                num_col++;
            }
            else if(num_col == 4)
            {
                StringTokenizer st1 = new StringTokenizer(st.nextElement().toString(), ",");

                while (st1.hasMoreElements() && pop_dane)
                {
                    String dana = st1.nextElement().toString();
                    if (ele == 7) {
                        System.out.println("Za dozo argumentow w kolumnie dnia tygodnia.");
                        pop_dane = false;
                    }

                    if (dana.length() <= 2 && pop_dane)
                    {
                        if (pop_dane)
                        {
                            pop_dane = day_Week(dana);
                            if (dana.equals("*") && pop_dane)
                            {
                                sheduleDayOfWeek[ele] = dana;
                                ele++;
                            }
                            else if(pop_dane)
                            {
                                boolean pop_powt = true;
                                for (int i = 0; i < ele && pop_powt; i++)
                                {
                                    if (sheduleDayOfWeek[i].equals(dana))
                                    {
                                        pop_powt = false;
                                    }
                                }

                                if (pop_powt)
                                {
                                    sheduleDayOfWeek[ele] = dana;
                                    ele++;
                                }
                            }
                            else
                            {
                                System.out.println("Niepoprawna dana w kolumnie dniu tygodnia.");
                            }

                            if (pop_dane && sheduleDayOfWeek[0].equals("*") && ele >= 2 || pop_dane && sheduleDayOfWeek[ele - 1].equals("*") && ele - 1 >= 1) {
                                System.out.println("* moze wystepowac tylko jako jeden argument w kolumnie dniu tygodnia.");
                                pop_dane = false;
                            }
                        }
                    }
                    else if (dana.length() > 2 && pop_dane)
                    {
                        StringTokenizer st2 = new StringTokenizer(dana,"-");


                            String dana1 = st2.nextElement().toString();
                            pop_dane = day_Week(dana1);

                            if(dana1.equals("*") && pop_dane)
                            {
                                System.out.println("* nie moze wystepowac w skladni - w kolumnie dnia tygodnia.");
                                pop_dane = false;
                            }
                            else if(pop_dane)
                            {
                                String dana2 = st2.nextElement().toString();
                                pop_dane = day_Week(dana2);

                                if(dana2.equals("*") && pop_dane)
                                {
                                    System.out.println("* nie moze wystepowac w skladni - w kolumnie dnia tygodnia.");
                                    pop_dane = false;
                                }
                                else if(pop_dane)
                                {
                                    pop_dane = new Integer(dana1) < new Integer(dana2);

                                    if(!pop_dane)
                                    {
                                        System.out.println("Niepoprawna skladnia w - w kolumnie dnia tygodnia..");
                                    }

                                    for(Integer i = new Integer(dana1); i <= new Integer(dana2)&&pop_dane ; i++)
                                    {
                                        boolean pop_powt = true;
                                        for (int j = 0; j < ele && pop_powt; j++)
                                        {
                                            if (sheduleDayOfWeek[j].equals(i.toString()))
                                            {
                                                pop_powt = false;
                                            }
                                        }

                                        if (pop_powt)
                                        {
                                            sheduleDayOfWeek[ele] = i.toString();
                                            ele++;
                                        }
                                    }

                                }
                                else
                                {
                                    System.out.println("Niepoprawna dana w skladni - w kolumnie dnia tygodnia..");
                                }
                            }
                            else
                            {
                                System.out.println("Niepoprawna dana w skladni - w kolumnie dnia tygodnia.");
                            }
                    }
                }
                num_col++;
            }
            else
            {
                pop_lenght = false;
                num_col++;
            }

        }

        if( num_col < 5 && pop_lenght && pop_dane)
        {
            System.out.println("Za krotka informacja zostala podana.");
        }
        else if(num_col > 5 && !pop_lenght && pop_dane)
        {
            System.out.println("Za dluga informacja zostala podana.");
        }
        else if(pop_dane && pop_lenght)
        {

            System.out.print("Minuty: ");
            if(sheduleMinute[0].equals("*"))
            {
                for (int i = 0; i < sheduleMinute.length ;i++)
                {
                    System.out.print(i+1 + " ");
                }
            }
            else
            {
                for (int i = 0; sheduleMinute[i] != null;i++)
                {
                    System.out.print(sheduleMinute[i] + " ");
                }
                System.out.println();
            }

            System.out.print("Godziny: ");
            if(sheduleHour[0].equals("*"))
            {
                for (int i = 0; i < sheduleHour.length ;i++)
                {
                    System.out.print(i+1 + " ");
                }
            }
            else
            {
                for (int i = 0; sheduleHour[i] != null;i++)
                {
                    System.out.print(sheduleHour[i] + " ");
                }
                System.out.println();
            }

            System.out.print("Dzien miesiaca: ");
            if(sheduleDayOfMonth[0].equals("*"))
            {
                for (int i = 0; i < sheduleDayOfMonth.length ;i++)
                {
                    System.out.print(i+1 + " ");
                }
            }
            else
            {
                for (int i = 0; sheduleDayOfMonth[i] != null;i++)
                {
                    System.out.print(sheduleDayOfMonth[i] + " ");
                }
                System.out.println();
            }

            System.out.print("Dzien tygodnia: ");
            if(sheduleDayOfWeek[0].equals("*"))
            {
                for (int i = 0; i < sheduleDayOfWeek.length ;i++)
                {
                    System.out.print(i+1 + " ");
                }
            }
            else
            {
                for (int i = 0; sheduleDayOfWeek[i] != null;i++)
                {
                    System.out.print(sheduleDayOfWeek[i] + " ");
                }
                System.out.println();
            }
        }

    }

    public static boolean minute(String m)
    {
        if(m.equals("10") || m.equals("20") || m.equals("30") || m.equals("40") || m.equals("50") || m.equals("60") || m.equals("*"))
        {

            return true;
        }
		else
        {
            return false;
        }

    }


    public static boolean hour(String h)
    {

        if(h.equals("0") || h.equals("1") || h.equals("2") || h.equals("3") || h.equals("4") || h.equals("5")
                || h.equals("6") || h.equals("7")|| h.equals("8")|| h.equals("9") || h.equals("10")|| h.equals("11")
                || h.equals("12") || h.equals("13") || h.equals("14") || h.equals("15") || h.equals("16") || h.equals("17")
                || h.equals("18") || h.equals("19") || h.equals("20") || h.equals("21") || h.equals("22") || h.equals("23")
                || h.equals("24")|| h.equals("*"))
        {
            return true;
        }
		else
        {
            return false;
        }

    }

    public static boolean day_Month(String dm)
    {
        if(dm.equals("0") || dm.equals("1") || dm.equals("2") || dm.equals("3") || dm.equals("4") || dm.equals("5")
                || dm.equals("6") || dm.equals("7")|| dm.equals("8")|| dm.equals("9") || dm.equals("10")|| dm.equals("11")
                || dm.equals("12") || dm.equals("13") || dm.equals("14") || dm.equals("15") || dm.equals("16") || dm.equals("17")
                || dm.equals("18") || dm.equals("19") || dm.equals("20") || dm.equals("21") || dm.equals("22") || dm.equals("23")
                || dm.equals("24") || dm.equals("25") || dm.equals("26") || dm.equals("27")|| dm.equals("28")|| dm.equals("28")
                || dm.equals("29") || dm.equals("30")|| dm.equals("31") || dm.equals("*"))
        {
            return true;
        }
        else
        {
            return false;
        }

    }

    public static boolean day_Week(String dw)
    {
        if(dw.equals("1") || dw.equals("2") || dw.equals("3") || dw.equals("4") || dw.equals("5") || dw.equals("6")
                || dw.equals("7") || dw.equals("*"))
        {
            return true;
        }
        else
        {
            return false;
        }
    }



}