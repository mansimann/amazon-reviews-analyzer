#!/bin/bash
mvn clean compile
mvn exec:java -Dexec.executable="Main"