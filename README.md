
# Inzynierka
# Poczta internetowa dla niepełnosprawnych

W celu uruchomienia programu są dwie możliwości

1) Za pomocą Intellij w Run -> Edit Configurations -> Main ustawić VM options na -Djava.library.path=".\src\main\jars"

2) Z katalogu głównego cmd uruchomić przy pomocy 
  java -cp out/artifacts/Inzynierka_jar/Inzynierka.jar Main 
  -Djava.library.path="/src/main/jars"
  
 Po uruchomieniu należy w miejscu login wpisać
  0 - wersja podstawowa
  1 - wersja wzrokowa
  2 - wersja głosowa
