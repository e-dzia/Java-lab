Lab 10. Wdro�enie aplikacji Java z wykorzystaniem JavaWS.


Wymagania: do realizacji zadania potrzebna b�dzie wiedza o JavaWS (Java Web Start) oraz Java Network Launching Protocol (JNLP).


Nale�y napisa� program, kt�ry mo�na b�dzie uruchomi� poprzez JavaWS. Podczas realizacji zadania nale�y wygenerowa� plik JNLP oraz przetestowa� jego za�adowanie na lokalnej maszynie. Podczas realizacji �wiczenia mo�na skorzysta� z narz�dzi typu serwer Apache Tomcat (na nim opublikowane powinny by� plik JNLP oraz jar z aplikacj�).


Aplikacja powinna by� okienkowa. Tematyka samej aplikacji jest dowolna (mo�na np. wykorzysta� program z laboratorium 2), nale�y jednak zwr�ci� uwag� na ograniczenia wynikaj�ce z mechanizm�w JavaWS/JNPL.

help:
D:\PWr\S6\Java lab\lab10\out\artifacts\lab10_jar>jarsigner -keystore keystore.jks -signedjar ..\..\..\SignedLab10.jar lab10.jar mykey

https://www.mkyong.com/java/java-web-start-jnlp-tutorial-unofficial-guide/
http://localhost:8080/webstart.jnlp