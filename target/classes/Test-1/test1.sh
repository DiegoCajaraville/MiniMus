#!/bin/sh
#
# Autoevaluacion. Prueba 1
#
echo "MiniMus. Prueba 1"
java MiniMus -j cards.txt -o game1.txt
diff game1.txt rtest1.txt > result1.txt
cat result1.txt

