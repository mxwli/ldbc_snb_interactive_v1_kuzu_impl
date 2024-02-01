## Loading the data:
run:
```
export KUZU_CSV_DIR=<your data directory>
```
Pre-generated datasets can be found through (this)[https://ldbcouncil.org/data-sets-surf-repository/snb-interactive-v1-datagen-v100] link. Select datasets with the format `social_network-sf\<SF\>-CsvComposite-LongDateFormatter``

then run:
```
scripts/load-in-one-step.sh
```

to load the database.

