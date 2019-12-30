#!/bin/bash
ZIP="${ZIP_TO_ENCRYPT}"
DEST="${ZIP%%.*}"
OUT="${ENCRYPTED_FILE}"
ENCRYPT=0
DECRYPT=0
REMOVE_FOLDER=0

usage="Usage: cmd [-e] [-er] [-d]"

if [ $# -eq 0 ]; then
    echo $usage
    exit 1
fi

while getopts ":edr" opt; do
    case ${opt} in
        e )
            ENCRYPT=1
            echo "Compressing and securing folder"
            ;;
        d )
            DECRYPT=1
            echo "Decrypting folder"
            ;;
        r )
            REMOVE_FOLDER=1
            echo "Deleting folder after process"
            ;;
        \? )
            echo $usage
            ;;
    esac
done

cd ~
if [ $ENCRYPT -ne 0 ]; then
    zip -q -r $DEST $DEST
    openssl enc -aes-256-cbc -e -salt -in $ZIP -out $OUT -pbkdf2 -pass env:FILES_SECURE_AUTH
    if [ $REMOVE_FOLDER -ne 0 ]; then
        echo "Deleting: $DEST $ZIP"
        rm -r $DEST
    fi
elif [ $DECRYPT -ne 0 ]; then
    openssl enc -aes-256-cbc -d -salt -in $OUT -out $ZIP -pbkdf2 -pass env:FILES_SECURE_AUTH
    unzip -q $ZIP
fi

echo "Cleaning up...."
rm -r $ZIP