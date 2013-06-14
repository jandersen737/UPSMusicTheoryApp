#!/bin/bash

rm -rf ./.temp
rm -rf ./.play

mkdir ./.temp
mkdir ./.temp/play
mkdir ./.play

echo "Downloading Play Framework..."
#wget -O ./.temp/play.zip http://downloads.typesafe.com/play/2.1.1/play-2.1.1.zip 
echo "Done."

echo "Installing Play Framework..."
#unzip -q ./.temp/play.zip -d ./.temp/play 
#mv ./.temp/play/play-2.1.1/* ./.play
chmod +x ./.play/play
echo "Done."

echo "Installing MySQL Server..."
#apt-get install mysql-server
echo "Done."

echo "Initializing database..."
mysql --user=root --password=root < ./scripts/sql/databases.sql
echo "Done."
echo "Preparing tables by starting play..."
#( cmpid=$BASHPID; (sleep 10; kill $cmdpid) & exec bash ./run.sh )
echo "Done."
echo "Filling tables..."
mysql --user=root --password=root < ./scripts/sql/answers.sql
mysql --user=root --password=root < ./scripts/sql/audio_files.sql
echo "Done."

echo "Cleaning up..."
rm -rf ./.temp
echo "Done."
