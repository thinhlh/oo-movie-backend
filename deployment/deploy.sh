#!/usr/bin/env bash

# Specify the version, it must be identical and same as version inside pom.xml
# There is no space when assigning value
version="0.0.0"

#while getopts "v:" flag; do
#    case "${flag}" in
#        # k) key-path=${OPTARG};;
#        # l) local-path=${OPTARG};;
#        # r) remote-path=${OPTARG};;
#        # u) username=${OPTARG};;
#        # n) filename=${OPTARG};;
#        v) version=${OPTARG}
#        ;;
#    esac
#done

rm -rf ./target/
echo "Deleted target folder"

echo "====================================== Generating jar file ======================================"
echo ""
./mvnw versions:set -DnewVersion="$version"
./mvnw package
echo '''
    ||=======================||
    ||                       ||
    ||                       ||  
    ||Generate jar completed!||
    ||                       ||
    ||                       ||
    ||=======================||
    '''
# Copy execute_commands_on_ec2.sh file which has commands to be executed on server... Here we are copying this file
# every time to automate this process through 'deploy.sh' so that whenever that file changes, it's taken care of
scp -i "./deployment/aws-oomovie.pem" ./deployment/execute_commands_on_ec2.sh ec2-user@ec2-13-251-81-100.ap-southeast-1.compute.amazonaws.com:/home/ec2-user/server
echo "Copied latest 'execute_commands_on_ec2.sh' file from local machine to ec2 instance"

scp -i "./deployment/aws-oomovie.pem" ./target/Movie-${version}-SNAPSHOT.jar ec2-user@ec2-13-251-81-100.ap-southeast-1.compute.amazonaws.com:/home/ec2-user/server
echo "Copied jar file from local machine to ec2 instance"

# Limited the permission and public of file
chmod 400 ./deployment/aws-oomovie.pem

echo "Connecting to ec2 instance and starting server using java -jar command"
ssh -i "./deployment/aws-oomovie.pem" -o StrictHostKeyChecking=no ec2-user@ec2-13-251-81-100.ap-southeast-1.compute.amazonaws.com ./server/execute_commands_on_ec2.sh "./server/Movie-${version}-SNAPSHOT.jar"
echo "============ Connecting to ec2 instance succeed ============"
