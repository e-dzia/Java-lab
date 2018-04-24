Lab 4. Refleksja i ³adowanie klas.


Wymagania: wiedza dotycz¹ca dzia³ania mechanizmu refleksji oraz ³adowania klas (ewentualnie w³asne ³adowacze klas).


Nale¿y napisaæ program podaj¹cy dok³adne lub przybli¿one rozwi¹zanie problemu komiwoja¿era (zak³adamy graf pe³ny). Program powinienen posiadaæ mo¿liwoœæ wczytania grafu z dysku (format danych dowolny: mo¿e to byæ pe³na macierz, lista wspó³rzêdnych punktów, itp.). Dane grafu mo¿na przechowywaæ w programie jako jednowymiarow¹ tablicê.


Pocz¹tkowo aplikacja nie powinna posiadaæ ¿adnych algorytmów rozwi¹zania problemu komiwoja¿era. Algorytmy te powinny byæ dostarczane w postaci skompilowanych plików class. Klasy te nale¿y dodawaæ do programu i wykorzystywaæ z u¿yciem mechanizmu refleksji i ³adowaczy klas. Interfejs aplikacji powinien umo¿liwiæ za³adowanie nowych algorytmów, umo¿liwiaæ u¿ycie aktualnie za³adowanych algorytmów i wyœwietlaæ informacje o nich (klasy algorytmów powinny mieæ metody zawieraj¹ce nazwê i krótki opis algorytmu).


Mo¿liwymi do wykorzystania algorytmami s¹: przegl¹d zupe³ny, algorytm najbli¿szego s¹siada, algorytm losowy, algorytm 2-opt itp. Nale¿y te¿ zaimplementowaæ algorytm bestOf, który powinien wykonywaæ co najmniej 2 z poprzednich algorytmów i zwracaæ lepszy z wyników (klasa algorytmu bestOf musi odpalaæ metody klas tych 2 innych algorytmów).


Kod bajtowy ³adowanych klas powinien byæ umieszczany w dedykowanym do tego celu katalogu. Metody ³adowanych klas mog¹ mieæ atrybuty typu podstawowego, String lub tablice jednowymiarowych tych typów. Za³adowane klasy powinno daæ siê wy³adowaæ, czego potwierdzenie powinno daæ siê zauwa¿yæ korzystaj¹c z jconsole.