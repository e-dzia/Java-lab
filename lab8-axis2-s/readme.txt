Lab 8. Komunikacja sieciowa z u¿yciem SOAP.


Wymagania: wiedza o gniazdkach sieciowych, protokole HTTP, SOAP, XML i wdra¿aniu aplikacji serwerowych. Mo¿na te¿ korzystaæ z frameworku Apache Axis2 (z Java EE).


Nale¿y zaprojektowaæ warstwowy sieciowy system wymiany wiadomoœci, sk³adaj¹cy siê z pewnej liczby osobnych aplikacji (konsolowych lub okienkowych) w architekturze takiej jak na poni¿szym obrazku. Strza³ki reprezentuj¹ mo¿liwy kierunek przekazywania wiadomoœci.


Zadanie nale¿y zrealizowac z u¿yciem SOAP-a. Nale¿y wykorzystaæ odpowiednie czêœci koperty (envelope) SOAP-a: treœæ samej wiadomoœci powinna byæ przenoszona w ciele (body), zaœ dane potrzebne do realizacji komunikacji powinny znaleŸæ siê w nag³ówku (header). Nale¿y wykorzystaæ gniazdka TCP/IP (np. z wykorzystaniem pakietu javax.xml.soap.*) lub framework Apache Axis/Axis2.


System powinien wspieraæ 3 tryby adresowania: 

Unicast: dane wysy³ane do konkretnego wêz³a.

Layer broadcast: dane wysy³ane do wszystkich wêz³ów konkretnej warstwy.

Broadcast: dane wysy³ane do wszystkich wêz³ów.

Interfejs (konsolowy lub okienkowy) ka¿dej aplikacji powinien byæ podzielony na dwie czêœci: 

Czêœæ do wysy³ania wiadomoœci. Powinna umo¿liwiæ wybór adresata/trybu adresacji, zredagowanie wiadomoœci oraz jej wys³anie.

Czêœæ do odbierania wiadomoœci. Powinna wyœwietlaæ odebrane wiadomoœci przeznaczone dla danego wêz³a wraz z nadawc¹. Powinna te¿ w osobny sposób (np. w nawiasach lub wyszarzone) wyœwietlaæ informacje diagnostyczne np. fakt przes³ania wiadomoœci dalej.