## Problem ograniczonego bufora (producentów-konsumentów)
Dany jest bufor, do którego producent może wkładać dane, a konsument pobierać. Napisać program, który zorganizuje takie

działanie producenta i konsumenta, w którym zapewniona będzie własność bezpieczeństwa i żywotności.

Zrealizować program:

1.    przy pomocy metod wait()/notify(). Kod szkieletu

a.     dla przypadku 1 producent/1 konsument

b.    dla przypadku n1 producentów/n2 konsumentów (n1>n2, n1=n2, n1<n2)

c.     wprowadzić wywołanie metody sleep() i wykonać pomiary, obserwując zachowanie producentów/konsumentów

2.    przy pomocy operacji P()/V() dla semafora:

a.     n1=n2=1

b.    n1>1, n2>1

 

##Przetwarzanie potokowe z buforem

Bufor o rozmiarze N - wspolny dla wszystkich procesow!
Proces A bedacy producentem.
Proces Z bedacy konsumentem.
Procesy B, C, ..., Y bedace procesami przetwarzajacymi. Kazdy proces otrzymuje dana wejsciowa od procesu poprzedniego, jego wyjscie zas jest konsumowane przez proces nastepny.
Procesy przetwarzaja dane w miejscu, po czym przechodza do kolejnej komorki bufora i znowu przetwarzaja ja w miejscu.
Procesy dzialaja z roznymi predkosciami.
Uwaga:

1.    W implementacji nie jest dozwolone korzystanie/implementowanie wlasnych kolejek FIFO, nalezy uzywac tylko mechanizmu monitorow lub semaforow !

2.    Zaimplementowac rozwiazanie przetwarzania potokowego (Przykladowe zalozenia: bufor rozmiaru 100, 1 producent, 1 konsument, 5 uszeregowanych procesow przetwarzajacych.) Od czego zalezy predkosc obrobki w tym systemie ? Rozwiązanie za pomocą semaforów lub monitorów (dowolnie). Zrobić sprawozdanie z przetwarzania potokowego.