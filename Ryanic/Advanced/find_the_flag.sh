#!/bin/bash
dir=$1
function recursive {
    if [ $2 -eq 3 ]; then
	return
    fi
    direct=`ls -A $1 2>/dev/null`
    for dir in $direct; do
        fulldir=${1}/${dir}
        if [ -d $fulldir ]; then
            recursive $fulldir $(( $2+1 ))
		else 
			res=`ls -A $fulldir | grep Flag 2>/dev/null`
			if [[ $res ]]; then
				echo $fulldir
			fi
        fi
    done
}

recursive $dir 0
