Lab 14. Stworzenie narz�dzia do monitorowania i zmiany przebiegu dzia�ania aplikacji.


Wymagania: wiedza o zarz�dzaniu aplikacjami Java'y za pomoc� JMX (MBean, MBeanServer, MXBean).


Nale�y napisa� program wielow�tkowy cyklicznie analizuj�cy tre�� plik�w tekstowych. Za�o�enia s� nast�puj�ce:


Na dysku istnieje pewna liczba (najlepiej co najmniej 20 lub 30) plik�w tekstowych.

Pocz�tkowa liczba w�tk�w analizuj�cych to 3.

Dost�pna jest pami�� podr�czna zawarto�ci plik�w (np. mapa String�w).

Pocz�tkowy rozmiar pami�ci podr�cznej (maksymalna liczba pami�tanych plik�w) to 5.

W�tki analizuj�ce dzia�aj� cyklicznie:

W�tek losuje plik do analizy (zak�adamy sta�� liczb� plik�w).

W�tek sprawdza czy dane pliku s� w pami�ci podr�cznej, je�li nie to nale�y plik wczyta� do pami�ci podr�cznej (usuwaj�c dane jakiego� istniej�cego pliku w przypadku przekroczenia dopuszczalnego rozmiaru pami�ci podr�cznej).

Wylosowanie metody analizy pliku.

Analiza pliku i wypisaniu wyniku na ekran (numer w�tku, rodzaj analizy, wynik).

Mo�liwymi metodami analizy s� np. policzenie czy tekst zawiera wi�cej sp�g�osek ni� samog�osek, wypisanie histogramu/cz�sto�ci wyst�powania samog�osek, wypisanie najcz�ciej wyst�puj�cego s�owa itp. Rozmiar plik�w nale�y dobra� tak, aby czas wczytywania w przypadku braku pliku w pami�ci podr�cznej zauwa�alnie wp�ywa� na wydajno�� aplikacji. Mo�na te� sztucznie wyd�u�y� czas wczytania pliku poprzez u�pienie w�tku na chwil�.


Dost�p do pami�ci podr�cznej musi odbywa� si� poprzez sekcj� krytyczn�.


Korzystaj�c z JMX nale�y stworzy� w aplikacji ziarenko MBean lub MXBean posiadaj�ce:


W�a�ciwo�� pozwalaj�c� ustawi� liczb� analizuj�cych w�tk�w (od 0 wzwy�).

W�a�ciwo�� pozwalaj�c� ustawi� rozmiar pami�ci podr�cznej (od 1 wzwy�).

Metod� zwracaj�c� wiadomo�� o stanie aplikacji tj. (1) liczbie w�tk�w, (2) stanie pami�ci podr�cznej (liczba zaj�tych wpis�w, liczba wolnych wpis�w, zajmowana pami��), (3) procencie b��d�w pami�ci podr�cznej (ile razy wczytano plik z dysku dzielone przez liczb� analiz pliku).

Nale�y wykorzysta� jmc lub jconsole by pod��czy� si� do aplikacji i skorzysta� z utworzonego ziarenka w celu monitorowania i zmiany przebiegu dzia�ania aplikacji.

Tutorial: https://www.journaldev.com/1352/what-is-jmx-mbean-jconsole-tutorial