@echo off
javac src\*.java
java -cp src Main
del /q src\*.class
