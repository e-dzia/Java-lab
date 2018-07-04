Lab 14. Stworzenie narzêdzia do monitorowania i zmiany przebiegu dzia³ania aplikacji.


Wymagania: wiedza o zarz¹dzaniu aplikacjami Java'y za pomoc¹ JMX (MBean, MBeanServer, MXBean).


Nale¿y napisaæ program wielow¹tkowy cyklicznie analizuj¹cy treœæ plików tekstowych. Za³o¿enia s¹ nastêpuj¹ce:


Na dysku istnieje pewna liczba (najlepiej co najmniej 20 lub 30) plików tekstowych.

Pocz¹tkowa liczba w¹tków analizuj¹cych to 3.

Dostêpna jest pamiêæ podrêczna zawartoœci plików (np. mapa Stringów).

Pocz¹tkowy rozmiar pamiêci podrêcznej (maksymalna liczba pamiêtanych plików) to 5.

W¹tki analizuj¹ce dzia³aj¹ cyklicznie:

W¹tek losuje plik do analizy (zak³adamy sta³¹ liczbê plików).

W¹tek sprawdza czy dane pliku s¹ w pamiêci podrêcznej, jeœli nie to nale¿y plik wczytaæ do pamiêci podrêcznej (usuwaj¹c dane jakiegoœ istniej¹cego pliku w przypadku przekroczenia dopuszczalnego rozmiaru pamiêci podrêcznej).

Wylosowanie metody analizy pliku.

Analiza pliku i wypisaniu wyniku na ekran (numer w¹tku, rodzaj analizy, wynik).

Mo¿liwymi metodami analizy s¹ np. policzenie czy tekst zawiera wiêcej spó³g³osek ni¿ samog³osek, wypisanie histogramu/czêstoœci wystêpowania samog³osek, wypisanie najczêœciej wystêpuj¹cego s³owa itp. Rozmiar plików nale¿y dobraæ tak, aby czas wczytywania w przypadku braku pliku w pamiêci podrêcznej zauwa¿alnie wp³ywa³ na wydajnoœæ aplikacji. Mo¿na te¿ sztucznie wyd³u¿yæ czas wczytania pliku poprzez uœpienie w¹tku na chwilê.


Dostêp do pamiêci podrêcznej musi odbywaæ siê poprzez sekcjê krytyczn¹.


Korzystaj¹c z JMX nale¿y stworzyæ w aplikacji ziarenko MBean lub MXBean posiadaj¹ce:


W³aœciwoœæ pozwalaj¹c¹ ustawiæ liczbê analizuj¹cych w¹tków (od 0 wzwy¿).

W³aœciwoœæ pozwalaj¹c¹ ustawiæ rozmiar pamiêci podrêcznej (od 1 wzwy¿).

Metodê zwracaj¹c¹ wiadomoœæ o stanie aplikacji tj. (1) liczbie w¹tków, (2) stanie pamiêci podrêcznej (liczba zajêtych wpisów, liczba wolnych wpisów, zajmowana pamiêæ), (3) procencie b³êdów pamiêci podrêcznej (ile razy wczytano plik z dysku dzielone przez liczbê analiz pliku).

Nale¿y wykorzystaæ jmc lub jconsole by pod³¹czyæ siê do aplikacji i skorzystaæ z utworzonego ziarenka w celu monitorowania i zmiany przebiegu dzia³ania aplikacji.

Tutorial: https://www.journaldev.com/1352/what-is-jmx-mbean-jconsole-tutorial