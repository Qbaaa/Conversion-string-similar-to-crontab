# Lab_4_Zad_2

Napisz program pozwalający na konwersję zapisu ciągu znaków na 5 tablic string.
Ciąg znaków będzie reprezentować zapis bardzo zbliżony do zapisów „crontab”
gdzie poszczególne informacje rozdzielone są znakiem | i przechowywane są informacje o minutach, godzinach, dniach miesiąca, dniach tygodnia np zapis oznacza.
10|10|*|*
minuta 10, godzina 10, każdy dzień miesiąca, każdy dzień tygodnia.
Dopuszczalne informacje o minutach to: 10,20,30,40,50,60 oraz *
Dopuszczalne informacje o godzinach to: 0,1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18,19,20,21,22,23 oraz *
Dopuszczalne informacje o dniach miesiąca to: 1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18,19,20,21,22,23,24,25,26,27,28,29,30,31 oraz *
Dopuszczalne informacje o dniu tygodnia to: 1,2,3,4,5,6,7 oraz *

Jeśli informacje mają być wysyłane np. o godzinie 11,12,13 to można uprościć zapis pisząc 11-13. Takie uproszczenia nie są dopuszczalne w informacjach o minutach.
Inny przykład poprawnego zapisu:
10|12,16,20-22|1,14|*
Oznacza minuta 10; godzina 12,16,20,21,22; dzień miesiąca 1,14; każdy dzień tygodnia.
W celu przechowywania danych w pamięci zastosuj tablicę string np.:

public String[] sheduleMinute;
public String[] sheduleHour;
public String[] sheduleDayOfMonth;
public String[] sheduleDayOfWeek;

Program powinien pobierać informacje z pliku tekstowego o nazwie cron.txt
Program powinien wypisać w konsoli zawartość tablic np.:
Minuty: 10
Godziny: 12 16 20 21 22
dzień miesiąca: 1 14
dzień tygodnia: 1 2 3 4 5 6 7
Program powinien uwzględniać błędnie wprowadzone dane (np. za krótka informacja *|*; za długa informacja 10|10|10|10|10|10|10|10; informacja spoza zakresu np. 99|-2|3|3; niezrozumiałe znaki np. DD|!!!!!)
