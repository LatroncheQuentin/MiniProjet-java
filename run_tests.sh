#!/bin/bash
mkdir -p bin

# Compile tests
javac -cp "lib/junit-platform-console-standalone-1.9.0.jar:src" -d bin \
  src/fr/ildeilc/model/*.java \
  test/*.java

# Lancer les tests
java -jar lib/junit-platform-console-standalone-1.9.0.jar \
  --class-path bin \
  --scan-class-path
