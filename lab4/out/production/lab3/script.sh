#!/bin/bash
for i in {1..5}
do
   for j in {1..5}
   do
		java Main "$i" "$j"
   done
done
