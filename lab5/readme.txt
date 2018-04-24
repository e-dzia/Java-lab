Lab 5. Ziarna Java'y.


Wymagania: wiedza o ziarnach Java'y, wzorcach projektowych wykorzystywanych przy ich tworzeniu i mechanizmie retrospekcji. Wiedza odnosnie tworzeniu paczek jar (z odpowiednim manifestem) oraz ich w��czaniu do projekt�w.


M�liwe jest uzyskanie oceny 5.0 z tego �wiczenia bez wykonania wszystkich zada�.


Zaprojektuj ziarenko posiadaj�ce:


w�a�ciwo�ci wszystkich mo�liwych typ�w (proste, ograniczone, wi�zane),

graficzn� reprezentacj�,

klas� opisow� BeanInfo z klasami pomocniczymi edytor�w (nale�y zwr�ci� uwag� na metod� getJavaInitializationString) i customizera (nale�y zwr�ci� uwag� na metod� setObject), s�u��cymi do zmian w�a�ciwo�ci ziarenka

Ziarenko nale�y przetestowa� w �rodowisku programowania, tzn. najpierw utworzy� paczk� jar ze wszystkimi klasami niezb�dnymi do funkcjnowania ziarenka, nast�pnie w��czy� ten jar do testowego projektu, w kt�rym ziarenko zostanie wstawione na jaki� panel za pomoc� wizarda (przy okazji powinno da� si� zmodyfikowa� parametry ziarenka), a na koniec sprawdzi� dzia�anie stworzonej aplikacji.


Rola jak� pe�ni� b�dzie ziarenko jest do wyboru. Mo�e to by� np. terminarz z mo�liwo�ci� edytowania datowanych notatek (np. dodawanie, wyszukiwanie notatek z danego okresu itp.). W tym przypadku w�a�ciwo�ciami ziarenka mog� by�: tytu� panelu (w�a�ciwo�� prosta), rozmiar pola tekstowego (w�a�ciwo�� wi�zana), ramy czasowe terminarza (w�a�ciwo�� ograniczona).