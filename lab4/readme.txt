Lab 4. Refleksja i �adowanie klas.


Wymagania: wiedza dotycz�ca dzia�ania mechanizmu refleksji oraz �adowania klas (ewentualnie w�asne �adowacze klas).


Nale�y napisa� program podaj�cy dok�adne lub przybli�one rozwi�zanie problemu komiwoja�era (zak�adamy graf pe�ny). Program powinienen posiada� mo�liwo�� wczytania grafu z dysku (format danych dowolny: mo�e to by� pe�na macierz, lista wsp�rz�dnych punkt�w, itp.). Dane grafu mo�na przechowywa� w programie jako jednowymiarow� tablic�.


Pocz�tkowo aplikacja nie powinna posiada� �adnych algorytm�w rozwi�zania problemu komiwoja�era. Algorytmy te powinny by� dostarczane w postaci skompilowanych plik�w class. Klasy te nale�y dodawa� do programu i wykorzystywa� z u�yciem mechanizmu refleksji i �adowaczy klas. Interfejs aplikacji powinien umo�liwi� za�adowanie nowych algorytm�w, umo�liwia� u�ycie aktualnie za�adowanych algorytm�w i wy�wietla� informacje o nich (klasy algorytm�w powinny mie� metody zawieraj�ce nazw� i kr�tki opis algorytmu).


Mo�liwymi do wykorzystania algorytmami s�: przegl�d zupe�ny, algorytm najbli�szego s�siada, algorytm losowy, algorytm 2-opt itp. Nale�y te� zaimplementowa� algorytm bestOf, kt�ry powinien wykonywa� co najmniej 2 z poprzednich algorytm�w i zwraca� lepszy z wynik�w (klasa algorytmu bestOf musi odpala� metody klas tych 2 innych algorytm�w).


Kod bajtowy �adowanych klas powinien by� umieszczany w dedykowanym do tego celu katalogu. Metody �adowanych klas mog� mie� atrybuty typu podstawowego, String lub tablice jednowymiarowych tych typ�w. Za�adowane klasy powinno da� si� wy�adowa�, czego potwierdzenie powinno da� si� zauwa�y� korzystaj�c z jconsole.