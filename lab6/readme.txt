Lab 6. Aplikacja rozproszona z wykorzystaniem RMI.


Wymagania: wiedza o sposobie tworzenia aplikacji rozproszonych z wykorzystaniem RMI.


U�ywaj�c RMI nale�y napisa� aplikacj� rozproszon� b�d�c� prostym systemem do wymiany plik�w. System sk�ada si� z 1 w�z�a centralnego oraz pewnej liczby w�z��w klient-serwer (peer).


Dla uproszczenia zak�adamy, �e wymieniane s� pliki tekstowe. Pliki przechoywane s� w spos�b rozproszony np. na 3 w�z�ach, z kt�rych pierwszy posiada pierwsz� 1/3 pliku, drugi �rodkow� 1/3, a trzeci ostatni� 1/3.


W�ze� centralny musi wspiera� metody: 

registerNode � zarejestrowanie w�z�a i przypisanie mu identyfikatora.

uploadFile � wys�anie pliku do centrali. Centrala musi wtedy okre�li� 3 w�z�y, kt�re b�d� przechowywa� plik i wys�a� im cz�ci tego pliku (u�ywaj�c interfejsu w�z�a). Wysy�anie plik�w nie powinno dzia�a� (zwracaj�c np. false), dop�ki w systemie nie ma co najmniej 3 w�z��w typu peer.

getPeersForFile � pozwala uzyska� identyfikatory 3 w�z��w przechowuj�cych dany plik, wraz z informacj�, kt�ry w�ze� przechowuje kt�ry fragment (najpro�ciej za�o�y�, �e identyfikatory wysys�ane s� w kolejno�ci przechowywanych fragment�w) lub informacj� o braku takiego pliku w systemie.

W�ze� typu peer musi wspiera� metody: 

acceptFileChunk � u�ywana do przekazania w�z�owi fragmentu pliku. Musi by� okre�lone jaki to jest plik (nazwa) i kt�ra z trzech jego cz�ci jest przesy�ana.

getFileChunk � u�ywana do pobraniu od w�z�a fragmentu pliku o podanej nazwie.

Interfejs w�z�a centralnego (konsolowy lub GUI) powinien co najmniej wy�wietla� zarejestrowane obecnie w�z�y (ich identyfikatory). Dobrze gdyby r�wnie� wy�wietla� dost�pne pliki tj. ich nazwy i identyfikatory w�z��w, kt�re posiadaj� te pliki. Interfejs w�z�a typu peer powinien wy�wietla� identyfikator w�z�a oraz posiadane pliki tj. ich nazwy oraz, kt�ry fragment przechowuj�. Interfejs w�z�a powinien posiada� opcje/przyciski do: (1) zarejestrowania w�z�a, (2) wys�ania pliku, (3) pobrania pliku.


Ka�dy w�ze� typu peer powinien posiada� 2 katalogi na dysku: pierwszy dla fragment�w plik�w, kt�re otrzyma� do przechowania, drugi dla pobranych kompletnych plik�w. W przypadku realizacji konsolowej ka�dy w�ze� powinien by� uruchamiany w osobnej konsoli.