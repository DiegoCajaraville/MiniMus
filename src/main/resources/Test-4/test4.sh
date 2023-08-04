#!/bin/sh
#
# Autoevaluacion. Prueba 4
#
echo "MiniMus. Prueba 4"
java MiniMus -c commands4.txt -o game4.txt
diff game4.txt rtest4.txt > result4.txt
cat result4.txt
#
# Comprobacion de los ficheros de jugadas aleatorias
#
echo "random1.txt tendra 10 lineas":
cat random1.txt | wc -l
echo "random2.txt tendra 20 lineas":
cat random2.txt | wc -l
echo "Inspeccione visualmente los archivos random1.txt y random2.txt."
#
# Comprobacion de partida con jugadores por defecto
#
echo Comprobando partida con jugadores por defecto
diff game4a.txt rtest4a.txt > result4ga.txt
cat result4ga.txt
#
# Comprobacion de partida con carga previa de jugdores
#
echo Comprobando partida con carga previa de jugdores
diff game4b.txt rtest4b.txt > result4gb.txt
cat result4gb.txt

