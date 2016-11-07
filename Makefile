# création de l'exécutable 'Programme'
all: main.o fonctions.o
        gcc main.o fonctions.o -o Programme
 
main.o: main.c fonctions.h
        gcc -c main.c -o main.o
 
fonctions.o: fonctions.c
        gcc -c fonctions.c -o fonctions.o
 
# suppression des fichiers temporaires
clean:
        rm -rf *.o
 
# suppression de tous les fichiers, sauf les sources,
# en vue d'une reconstruction complète
mrproper: clean
        rm -rf Programme
