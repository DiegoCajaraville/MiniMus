#!/bin/sh
#
# Autoevaluacion. Prueba 2
#
echo "MiniMus. Prueba 2"
java MiniMus -j cards.txt -p players.txt -o game2.txt
diff game2.txt rtest2.txt > result2.txt
cat result2.txt