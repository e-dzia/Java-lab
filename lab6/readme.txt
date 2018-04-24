Lab 6. Aplikacja rozproszona z wykorzystaniem RMI.


Wymagania: wiedza o sposobie tworzenia aplikacji rozproszonych z wykorzystaniem RMI.


U¿ywaj¹c RMI nale¿y napisaæ aplikacjê rozproszon¹ bêd¹c¹ prostym systemem do wymiany plików. System sk³ada siê z 1 wêz³a centralnego oraz pewnej liczby wêz³ów klient-serwer (peer).


Dla uproszczenia zak³adamy, ¿e wymieniane s¹ pliki tekstowe. Pliki przechoywane s¹ w sposób rozproszony np. na 3 wêz³ach, z których pierwszy posiada pierwsz¹ 1/3 pliku, drugi œrodkow¹ 1/3, a trzeci ostatni¹ 1/3.


Wêze³ centralny musi wspieraæ metody: 

registerNode — zarejestrowanie wêz³a i przypisanie mu identyfikatora.

uploadFile — wys³anie pliku do centrali. Centrala musi wtedy okreœliæ 3 wêz³y, które bêd¹ przechowywaæ plik i wys³aæ im czêœci tego pliku (u¿ywaj¹c interfejsu wêz³a). Wysy³anie plików nie powinno dzia³aæ (zwracaj¹c np. false), dopóki w systemie nie ma co najmniej 3 wêz³ów typu peer.

getPeersForFile — pozwala uzyskaæ identyfikatory 3 wêz³ów przechowuj¹cych dany plik, wraz z informacj¹, który wêze³ przechowuje który fragment (najproœciej za³o¿yæ, ¿e identyfikatory wysys³ane s¹ w kolejnoœci przechowywanych fragmentów) lub informacjê o braku takiego pliku w systemie.

Wêze³ typu peer musi wspieraæ metody: 

acceptFileChunk — u¿ywana do przekazania wêz³owi fragmentu pliku. Musi byæ okreœlone jaki to jest plik (nazwa) i która z trzech jego czêœci jest przesy³ana.

getFileChunk — u¿ywana do pobraniu od wêz³a fragmentu pliku o podanej nazwie.

Interfejs wêz³a centralnego (konsolowy lub GUI) powinien co najmniej wyœwietlaæ zarejestrowane obecnie wêz³y (ich identyfikatory). Dobrze gdyby równie¿ wyœwietla³ dostêpne pliki tj. ich nazwy i identyfikatory wêz³ów, które posiadaj¹ te pliki. Interfejs wêz³a typu peer powinien wyœwietlaæ identyfikator wêz³a oraz posiadane pliki tj. ich nazwy oraz, który fragment przechowuj¹. Interfejs wêz³a powinien posiadaæ opcje/przyciski do: (1) zarejestrowania wêz³a, (2) wys³ania pliku, (3) pobrania pliku.


Ka¿dy wêze³ typu peer powinien posiadaæ 2 katalogi na dysku: pierwszy dla fragmentów plików, które otrzyma³ do przechowania, drugi dla pobranych kompletnych plików. W przypadku realizacji konsolowej ka¿dy wêze³ powinien byæ uruchamiany w osobnej konsoli.