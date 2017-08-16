#!/bin/bash

#Author: Bryan<talk2kamp@gmail.com>
#Purpose: Saves me the stress

#PARAMETERS: either an s or e after script name


#If you get permission denied when you try to run as ./open_SE.sh [OPTION], do either of:
#1: run as bash open_SE.sh [OPTION]
# OR change the permission of the user by typing:
#2: chmod 700 open_SE.sh
#Then  run as ./open_SE.sh [OPTION]

writeHelp()
{
	echo "Invalid parameter, enter either \"e\" for Editor or \"s\" for Simulator after typing ./fileName"
}

EXEC=""

if [ "$1" = "e" ] 
then 
	EXEC="LC3Edit.exe"
elif [ "$1" = "s" ] 
then
	EXEC="Simulate.exe"
else
	writeHelp
	exit 0
fi

SCRIPTPATH="/Users/$(whoami)/Applications/$EXEC"
wine $SCRIPTPATH
