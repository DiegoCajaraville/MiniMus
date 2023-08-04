#!/bin/sh
#
# Autoevaluacion. Prueba 5 opcion A
#
echo "MiniMus. Prueba 5 a"
java MiniMus -c commands5.txt -o game5.txt
diff --strip-trailing-cr game5.txt rtest5a.txt > result5a.txt
cat result5a.txt
