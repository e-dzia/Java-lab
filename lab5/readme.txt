Lab 5. Ziarna Java'y.


Wymagania: wiedza o ziarnach Java'y, wzorcach projektowych wykorzystywanych przy ich tworzeniu i mechanizmie retrospekcji. Wiedza odnosnie tworzeniu paczek jar (z odpowiednim manifestem) oraz ich w³¹czaniu do projektów.


M¿liwe jest uzyskanie oceny 5.0 z tego æwiczenia bez wykonania wszystkich zadañ.


Zaprojektuj ziarenko posiadaj¹ce:


w³aœciwoœci wszystkich mo¿liwych typów (proste, ograniczone, wi¹zane),

graficzn¹ reprezentacjê,

klasê opisow¹ BeanInfo z klasami pomocniczymi edytorów (nale¿y zwróciæ uwagê na metodê getJavaInitializationString) i customizera (nale¿y zwróciæ uwagê na metodê setObject), s³u¿¹cymi do zmian w³aœciwoœci ziarenka

Ziarenko nale¿y przetestowaæ w œrodowisku programowania, tzn. najpierw utworzyæ paczkê jar ze wszystkimi klasami niezbêdnymi do funkcjnowania ziarenka, nastêpnie w³¹czyæ ten jar do testowego projektu, w którym ziarenko zostanie wstawione na jakiœ panel za pomoc¹ wizarda (przy okazji powinno daæ siê zmodyfikowaæ parametry ziarenka), a na koniec sprawdziæ dzia³anie stworzonej aplikacji.


Rola jak¹ pe³niæ bêdzie ziarenko jest do wyboru. Mo¿e to byæ np. terminarz z mo¿liwoœci¹ edytowania datowanych notatek (np. dodawanie, wyszukiwanie notatek z danego okresu itp.). W tym przypadku w³aœciwoœciami ziarenka mog¹ byæ: tytu³ panelu (w³aœciwoœæ prosta), rozmiar pola tekstowego (w³aœciwoœæ wi¹zana), ramy czasowe terminarza (w³aœciwoœæ ograniczona).