#!/bin/bash

for f in ../../textures/spell/*; do
    name="$(basename ${f%.*})";
    echo -n "
{
	\"parent\": \"minecraft:item/generated\",
	\"textures\": {
		\"layer0\": \"psi:spell/${name}\"
	}
}
" > ${name}.json
done
