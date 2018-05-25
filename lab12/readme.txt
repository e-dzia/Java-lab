Lab 12. Wykorzystanie skryptów w aplikacjach wykorzystujących sztuczną inteligencję.


Wymagania: wiedza o wykorzystaniu silnika Nashorn interpretującego JavaScript razem z aplikacjami Java'y.


Należy napisać aplikację do gry z komputerem w prostą grę. Obaj gracze otrzymują po 10 losowych liczb (kart) z określonego przedziału (przy czym obaj gracze otrzymują dokładnie takie same zestawy kart!). Następnie w każdej turze obaj gracze wykładają po jednej karcie. Gracz z wyższą kartą zdobywa 2 punkty. Jeśli karty są takie same, obaj gracze otrzymują jeden punkt. Wynik gry (wygrana, remis, porażka) zależy od liczby punktów po wykorzystaniu wszystkich kart.


Aplikacja powinna korzystać z rozszerzeń – skryptów JavaScript dostarczających metod do generowania kolejnych ruchów (decyzji) gracza komputerowego według jakiejś strategii. Skrypty powinny być wczytywane ze wskazanego katalogu w sposób dynamiczny – ma być możliwość zmiany strategii (skryptu) podczas działania aplikacji, włącznie z dodaniem nowego skryptu do katalogu. Można założyć, że metody generujące kolejne ruchy mają taką samą listę argumentów oraz zwracają wartości takiego samego typu (np. pobierają stan (tablicę) kart gracza i przeciwnika, a zwracają indeks karty do użycia). W załadowanych skryptach dozwolone jest wykorzystanie klas Java'y.


Należy zaimplementować przynajmniej 3 różne strategie gracza komputerowego. Na wyższą ocenę powinna pojawić się jedna średniozaawansowana strategia. Warto zaznaczyć, że obaj gracze znają w danej chwili zestaw kart przeciwnika (początkowy zestaw przeciwnika jest taki sam jak nasz, a wiemy jakie karty gra przeciwnik).


Uwaga: można przyjąć jedną z dwóch wersji rozgrywki: (1) obaj gracze wystawiają kartę jednocześnie ("w ciemno", znając jedynie jakie karty zostały przeciwnikowi) lub (2) gracze wystawiają karty na przemian tzn. w pierwszej turze gracz 1 wystawia kartę pierwszy (więc gracz 2 podejmuje decyzję wiedząc na jaką kartę gra), w drugiej turze odwrotnie – gracz 2 wystawia kartę pierwszy, a gracz 1 reaguje. Przy 10 kartach daje to 5 tur, w której reaguje gracz 2 i 5 tur w których reaguje gracz 1.


Aplikacja (konsolowa lub okienkowa) powinna wyświetlać przebieg rozgrywki (jakie karty zostały zagrane), a przed każdą turą wyświetlać aktualną punktację i karty, które pozostały obu graczom. Warto zapewnić możliwość łatwego wyłączenia wyświetlania kart przeciwnika (by nie ułatwiać żywemu graczowi zadania).