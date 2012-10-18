for line in $(tail -n+4520 restos.txt); do 
	echo resto $line
	name=$(echo $line | sed 's/^.*-\([0-9]*\)$/\1/'); 
	phantomjs --load-images=no guiaoleo-resto.js $line > resto-id-$name; 
	sleep 5;
done
