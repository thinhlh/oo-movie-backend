#!/usr/bin/env bash

kill -9 $(lsof -t -i:8080)
echo "================== Killed process running on port 8080 =================="

java -jar "$1"
echo ""

echo '''
    ||========================================||
    ||                                        ||
    ||                                        ||  
    ||   Started server using on port 8080!   ||
    ||                                        ||
    ||                                        ||
    ||========================================||
    '''