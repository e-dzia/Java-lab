Lab 1. W�asna biblioteka i javadoc.


Nale�y napisa� program do analizy kartami test�w wyboru. Aplikacja powinna sk�ada� si� z biblioteki pomocniczej (w postaci pliku .jar) oraz aplikacji g��wnej wykorzystuj�cej t� bibliotek�.


Biblioteka powinna zawiera�: 

Klas� karty odpowiedzi. Powinna zawiera� informacje (np. w postaci odpowiednie kolekcji) jakie (kt�re) odpowiedzi zosta�y zaznaczone na karcie. Liczba pyta� oraz liczba mo�liwych opcji (sta�a dla ka�dego pytania) powinny by� parametrami klasy.

Klas� zestawu kart odpowiedzi (testu, egzaminu itp.). Jest to kolekcja kart odpowiedzi z wyszczeg�lnion� kart�, kt�ra stanowi klucz odpowiedzi.

Klasy do wyliczania statystyk. Statystyki powinny by� wyliczane dla danego zestawu kart. Statystykami tymi mog� by� (wystarcz� 2 lub 3): histogram (rozk�ad) zdobytych punkt�w ("wykres" liczby kart z zestawu dla kt�rych zdobyto dan� liczb� punkt�w), histogram ocen, histogram �redniej poprawno�ci odpowiedzi na poszczeg�lne pytania, liczba prac niezaliczonych, �rednia liczba punkt�w zdobytych przez studenta, pytanie, na kt�re studenci odpowiadali najgorzej/najlepiej.

Aplikacja mo�e by� w postaci tekstowej (konsolowej). Aplikacja powinna mie� dokumentacj� (stworzon� narz�dziem javadoc). Aplikacja powinna umo�liwia� wczytanie zestawu kart odpowiedzi oraz klucza odpowiedzi (specjalna karta) oraz wyliczanie wybranych statystyk dla wczytanego zestawu.


Zestaw kart powinien by� zapisany w katalogu (tzn. jeden katalog na jeden zestaw/test/egzamin). Poszczeg�lne karty powinny by� przechowywane w formacie .csv. Spos�b nazywania plik�w i sposobu oznaczania pliku klucza jest dowolny.