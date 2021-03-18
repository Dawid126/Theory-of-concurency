#!/bin/bash
for i in {1..10}
do
   for j in {1..10}
   do
		java Main "$i" "$j"
		echo $i $j
   done
done
