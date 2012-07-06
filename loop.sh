#!/bin/bash
echo -n > out
for i in {1..20}
do
    java -jar "/home/andreas/JavaProjects/jEvoSnakeSolver/dist/jEvoSnakeSolver.jar" >> out
done
