Lab 1. W³asna biblioteka i javadoc.


Nale¿y napisaæ program do analizy kartami testów wyboru. Aplikacja powinna sk³adaæ siê z biblioteki pomocniczej (w postaci pliku .jar) oraz aplikacji g³ównej wykorzystuj¹cej tê bibliotekê.


Biblioteka powinna zawieraæ: 

Klasê karty odpowiedzi. Powinna zawieraæ informacje (np. w postaci odpowiednie kolekcji) jakie (które) odpowiedzi zosta³y zaznaczone na karcie. Liczba pytañ oraz liczba mo¿liwych opcji (sta³a dla ka¿dego pytania) powinny byæ parametrami klasy.

Klasê zestawu kart odpowiedzi (testu, egzaminu itp.). Jest to kolekcja kart odpowiedzi z wyszczególnion¹ kart¹, która stanowi klucz odpowiedzi.

Klasy do wyliczania statystyk. Statystyki powinny byæ wyliczane dla danego zestawu kart. Statystykami tymi mog¹ byæ (wystarcz¹ 2 lub 3): histogram (rozk³ad) zdobytych punktów ("wykres" liczby kart z zestawu dla których zdobyto dan¹ liczbê punktów), histogram ocen, histogram œredniej poprawnoœci odpowiedzi na poszczególne pytania, liczba prac niezaliczonych, œrednia liczba punktów zdobytych przez studenta, pytanie, na które studenci odpowiadali najgorzej/najlepiej.

Aplikacja mo¿e byæ w postaci tekstowej (konsolowej). Aplikacja powinna mieæ dokumentacjê (stworzon¹ narzêdziem javadoc). Aplikacja powinna umo¿liwiaæ wczytanie zestawu kart odpowiedzi oraz klucza odpowiedzi (specjalna karta) oraz wyliczanie wybranych statystyk dla wczytanego zestawu.


Zestaw kart powinien byæ zapisany w katalogu (tzn. jeden katalog na jeden zestaw/test/egzamin). Poszczególne karty powinny byæ przechowywane w formacie .csv. Sposób nazywania plików i sposobu oznaczania pliku klucza jest dowolny.