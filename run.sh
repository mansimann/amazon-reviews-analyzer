#!/bin/bash
mvn clean compile
mvn exec:java -Dexec.executable="service.Main"