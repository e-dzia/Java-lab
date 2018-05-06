Lab 3. S³abe/miêkkie referencje i w¹tki.


Wymagania: wiedza dotycz¹ca dzia³ania i u¿ycia s³abych i miêkkich referencji. Tworzenie i synchronizacja w¹tków w Java'ie (tak¿e narzêdzia typu java.util.concurrent).


Nale¿y utworzyæ metodê, której argumentem bêdzie liczba int/long (pe³ni¹ca funkcjê ziarna) i która generuje kolekcjê wartoœci typu double. Lista powinna byæ du¿a – rozmiar powinien zale¿eæ od ziarna i byæ w zakresie od jednego do kilku megabajtów. Wartoœci w kolekcji powinny byæ zale¿ne od ziarna tzn. dwukrotne u¿ycie tego samego ziarna wygeneruje tak¹ sam¹ kolekcjê (rozmiar i wartoœci w kolekcji).


Nalezy utworzyæ pamiêæ podrêczn¹ dla tworzonych kolekcji: kluczem jest wartoœæ ziarna, a wartoœci¹ kolekcja odpowiadaj¹ca temu ziarnu. Nale¿y wykorzystaæ mechanizm miêkkich referencji, w celu zapewnienia, ¿e Garbage Collector w przypadku braku wolnej pamiêci usunie czêœæ wpisów, by zrobiæ miejsce na nowy wpis. Jak dzia³anie pamiêci podrêcznej zmieni siê przy u¿yciu zwyk³ych (silnych) lub s³abych referencji (zamiast miêkkich)?


Nale¿y utworzyæ kilka (co najmniej 5) w¹tków. Ka¿dy w¹tek periodycznie próbuje obliczyæ pewn¹ statystykê dla kolekcji o losowym ziarnie. Statystyki mog¹ byæ ró¿norodne: œrednia, wariancja, rozstêp, minimum, maksimum, mediana, liczba elementów dodatnich itp. W¹tki pracuj¹ na pamiêci podrêcznej: jeœli w¹tek ma obliczyæ statystykê dla kolekcji nie bêd¹cej obecnie w pamiêci podrêcznej, to musi najpierw t¹ kolekcjê wygenerowaæ (z ziarna) i umieœciæ j¹ w pamiêci. Dostêp do pamiêci podrêcznej powinien byæ kontrolowany (synchronizacja).


Ka¿dy w¹tek powinien drukowaæ log swojej dzia³alnoœci (obliczanie statystyki, umieszczanie kolekcji w pamiêci podrêcznej) do pliku (osobnego dla ka¿dego w¹tku) oraz na konsolê (wspóln¹ dla wszystkich w¹tków). Ponadto nale¿y wykorzystaæ odpowiednie mechanizmy (np. kolejki referencji) by zliczaæ sytuacje, w których jakaœ kolekcja zosta³a usuniêta z pamiêci podrêcznej. Pod koniec pracy aplikacji na konsoli powinna siê pojawiæ informacja ile razy nast¹pi³o usuniêcie wpisu z pamiêci podrêcznej.


Ograniczyæ mo¿liwe wartoœci ziarna (np. od 0 do kilku tysiêcy). W razie potrzeby nale¿y wykorzystaæ opcje maszyny wirtualnej Java'y s³u¿¹ce do kontrolowania rozmiaru sterty (-Xms, -Xmx), w celu u³atwienia obserwacji dzia³ania miêkkich referencji.