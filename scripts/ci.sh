#!/bin/bash

set -e

export TERM=xterm-256color

cd find-buckfast
./gradlew clean test