#!/bin/bash

# compile
javac src/*.java

# run
java -cp src Main

# cleanup
rm -rf src/*.class
