#!/usr/bin/env bash

mvn clean package -DskipTests
cp target/auction-system-0.0.1-SNAPSHOT.jar .