#/bin/sh
rsync -azvh -e 'ssh -p 2222' $1@pwnable.kr:~  .
