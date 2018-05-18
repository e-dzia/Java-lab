Lab 11. Rozbudowa aplikacji Java o funkcje zaimplementowane w kodzie natywnym.


Wymagania: wiedza o sposobie implementacji i wykorzystaniu metod natywnych (JNI) oraz znajomoœæ tworzenia bibliotek ³adowanych dynamicznie w jêzyku C/C++.


Nale¿y stworzyæ aplikacjê Java'y, której jedna z klas zawiera metody w kodzie natywnym. Metody te to:


void helloWorld() – bezargumentowa metoda wypisuj¹ca napis "Hello world!".

bool isPrime( int num ) – jednoargumentowa funkcja zwracaj¹ca informacjê czy podana liczba ca³kowita num jest pierwsza.

float [] forEachElement( float [] array , float val , String op ) – trzyargumentowa funkcja, która do ka¿dego elementu tablicy array dodaje/odejmuje/itp. wartoœæ val w zale¿noœci od wartoœci parametru op (4 mo¿liwe wartoœci: "add", "subtract", "multiply", "divide"). Funkcja mo¿e zmieniaæ oryginaln¹ tablicê lub zwracaæ now¹ (w tym pierwszym przypadku funkcja ma typ zwracany void).

Nale¿y zaprezentowaæ dzia³anie aplikacji (wywo³ywanie metod z powy¿szej klasy). Kod natywny mo¿e byæ realizowany w C lub C++. Przyk³adowy tutorial JNI: https://www3.ntu.edu.sg/home/ehchua/programming/java/JavaNativeInterface.html