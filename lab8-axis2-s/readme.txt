Lab 8. Komunikacja sieciowa z u�yciem SOAP.


Wymagania: wiedza o gniazdkach sieciowych, protokole HTTP, SOAP, XML i wdra�aniu aplikacji serwerowych. Mo�na te� korzysta� z frameworku Apache Axis2 (z Java EE).


Nale�y zaprojektowa� warstwowy sieciowy system wymiany wiadomo�ci, sk�adaj�cy si� z pewnej liczby osobnych aplikacji (konsolowych lub okienkowych) w architekturze takiej jak na poni�szym obrazku. Strza�ki reprezentuj� mo�liwy kierunek przekazywania wiadomo�ci.


Zadanie nale�y zrealizowac z u�yciem SOAP-a. Nale�y wykorzysta� odpowiednie cz�ci koperty (envelope) SOAP-a: tre�� samej wiadomo�ci powinna by� przenoszona w ciele (body), za� dane potrzebne do realizacji komunikacji powinny znale�� si� w nag��wku (header). Nale�y wykorzysta� gniazdka TCP/IP (np. z wykorzystaniem pakietu javax.xml.soap.*) lub framework Apache Axis/Axis2.


System powinien wspiera� 3 tryby adresowania: 

Unicast: dane wysy�ane do konkretnego w�z�a.

Layer broadcast: dane wysy�ane do wszystkich w�z��w konkretnej warstwy.

Broadcast: dane wysy�ane do wszystkich w�z��w.

Interfejs (konsolowy lub okienkowy) ka�dej aplikacji powinien by� podzielony na dwie cz�ci: 

Cz�� do wysy�ania wiadomo�ci. Powinna umo�liwi� wyb�r adresata/trybu adresacji, zredagowanie wiadomo�ci oraz jej wys�anie.

Cz�� do odbierania wiadomo�ci. Powinna wy�wietla� odebrane wiadomo�ci przeznaczone dla danego w�z�a wraz z nadawc�. Powinna te� w osobny spos�b (np. w nawiasach lub wyszarzone) wy�wietla� informacje diagnostyczne np. fakt przes�ania wiadomo�ci dalej.