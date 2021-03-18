## Blokowanie drobnoziarniste
1.      Zamek (lock) jest przydatny wtedy, gdy operacje zamykania/otwierania nie mogą być umieszczone w jednej metodzie lub bloku synchronized. Przykładem jest zakładanie blokady (lock) na elementy struktury danych, np. listy. Podczas przeglądania listy stosujemy następujący algorytm:

1.      zamknij zamek na pierwszym elemencie listy

2.      zamknij zamek na drugim elemencie

3.      otwórz zamek na pierwszym elemencie

4.      zamknij zamek na trzecim elemencie

5.      otwórz zamek na drugim elemencie

6.      powtarzaj dla kolejnych elementów

Dzięki temu unikamy konieczności blokowania całej listy i wiele wątków może równocześnie przeglądać i modyfikować różne jej fragmenty.