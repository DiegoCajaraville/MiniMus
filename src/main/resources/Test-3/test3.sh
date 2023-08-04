#!/bin/sh
#
# Autoevaluacion. Prueba 3
#
echo "MiniMus. Prueba 3"
java MiniMus -c commands3.txt -o game3.txt
diff game3.txt rtest3.txt > result3.txt
cat result3.txt
# 
# el siguiente fichero estara vacio
#
echo Comprobando fichero de jugadores vacio
cat rplayers3e.txt
#
# comprobando jugadores
#
echo Comprobando fichero jugadores creado
diff rplayers3d.txt rptest3.txt > resultp3.txt
cat resultp3.txt

