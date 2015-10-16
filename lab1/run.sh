#!/bin/bash

javac dandc.java
java dandc
diff Selection.txt merge.txt
# get the return code from an echo
ret="$(echo $?)"

if [ $ret = 0 ]; then
		echo "all good"
fi
