#!/bin/sh
#
# Autoevaluacion. Prueba 5 opcion B
#
echo "MiniMus. Prueba 5 b"
java MiniMus -c commands5.txt -o game5.txt
diff --strip-trailing-cr game5.txt rtest5b.txt > result5b.txt
cat result5b.txt
