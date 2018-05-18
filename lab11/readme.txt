Lab 11. Rozbudowa aplikacji Java o funkcje zaimplementowane w kodzie natywnym.


Wymagania: wiedza o sposobie implementacji i wykorzystaniu metod natywnych (JNI) oraz znajomo�� tworzenia bibliotek �adowanych dynamicznie w j�zyku C/C++.


Nale�y stworzy� aplikacj� Java'y, kt�rej jedna z klas zawiera metody w kodzie natywnym. Metody te to:


void helloWorld() � bezargumentowa metoda wypisuj�ca napis "Hello world!".

bool isPrime( int num ) � jednoargumentowa funkcja zwracaj�ca informacj� czy podana liczba ca�kowita num jest pierwsza.

float [] forEachElement( float [] array , float val , String op ) � trzyargumentowa funkcja, kt�ra do ka�dego elementu tablicy array dodaje/odejmuje/itp. warto�� val w zale�no�ci od warto�ci parametru op (4 mo�liwe warto�ci: "add", "subtract", "multiply", "divide"). Funkcja mo�e zmienia� oryginaln� tablic� lub zwraca� now� (w tym pierwszym przypadku funkcja ma typ zwracany void).

Nale�y zaprezentowa� dzia�anie aplikacji (wywo�ywanie metod z powy�szej klasy). Kod natywny mo�e by� realizowany w C lub C++. Przyk�adowy tutorial JNI: https://www3.ntu.edu.sg/home/ehchua/programming/java/JavaNativeInterface.html