Lab 3. S�abe/mi�kkie referencje i w�tki.


Wymagania: wiedza dotycz�ca dzia�ania i u�ycia s�abych i mi�kkich referencji. Tworzenie i synchronizacja w�tk�w w Java'ie (tak�e narz�dzia typu java.util.concurrent).


Nale�y utworzy� metod�, kt�rej argumentem b�dzie liczba int/long (pe�ni�ca funkcj� ziarna) i kt�ra generuje kolekcj� warto�ci typu double. Lista powinna by� du�a � rozmiar powinien zale�e� od ziarna i by� w zakresie od jednego do kilku megabajt�w. Warto�ci w kolekcji powinny by� zale�ne od ziarna tzn. dwukrotne u�ycie tego samego ziarna wygeneruje tak� sam� kolekcj� (rozmiar i warto�ci w kolekcji).


Nalezy utworzy� pami�� podr�czn� dla tworzonych kolekcji: kluczem jest warto�� ziarna, a warto�ci� kolekcja odpowiadaj�ca temu ziarnu. Nale�y wykorzysta� mechanizm mi�kkich referencji, w celu zapewnienia, �e Garbage Collector w przypadku braku wolnej pami�ci usunie cz�� wpis�w, by zrobi� miejsce na nowy wpis. Jak dzia�anie pami�ci podr�cznej zmieni si� przy u�yciu zwyk�ych (silnych) lub s�abych referencji (zamiast mi�kkich)?


Nale�y utworzy� kilka (co najmniej 5) w�tk�w. Ka�dy w�tek periodycznie pr�buje obliczy� pewn� statystyk� dla kolekcji o losowym ziarnie. Statystyki mog� by� r�norodne: �rednia, wariancja, rozst�p, minimum, maksimum, mediana, liczba element�w dodatnich itp. W�tki pracuj� na pami�ci podr�cznej: je�li w�tek ma obliczy� statystyk� dla kolekcji nie b�d�cej obecnie w pami�ci podr�cznej, to musi najpierw t� kolekcj� wygenerowa� (z ziarna) i umie�ci� j� w pami�ci. Dost�p do pami�ci podr�cznej powinien by� kontrolowany (synchronizacja).


Ka�dy w�tek powinien drukowa� log swojej dzia�alno�ci (obliczanie statystyki, umieszczanie kolekcji w pami�ci podr�cznej) do pliku (osobnego dla ka�dego w�tku) oraz na konsol� (wsp�ln� dla wszystkich w�tk�w). Ponadto nale�y wykorzysta� odpowiednie mechanizmy (np. kolejki referencji) by zlicza� sytuacje, w kt�rych jaka� kolekcja zosta�a usuni�ta z pami�ci podr�cznej. Pod koniec pracy aplikacji na konsoli powinna si� pojawi� informacja ile razy nast�pi�o usuni�cie wpisu z pami�ci podr�cznej.


Ograniczy� mo�liwe warto�ci ziarna (np. od 0 do kilku tysi�cy). W razie potrzeby nale�y wykorzysta� opcje maszyny wirtualnej Java'y s�u��ce do kontrolowania rozmiaru sterty (-Xms, -Xmx), w celu u�atwienia obserwacji dzia�ania mi�kkich referencji.