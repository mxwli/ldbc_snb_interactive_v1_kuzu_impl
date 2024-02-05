## Loading the data:
run:
```
export KUZU_CSV_DIR=<your data directory>
```

Pre-generated datasets can be found through [this](https://ldbcouncil.org/data-sets-surf-repository/snb-interactive-v1-datagen-v100) link. Select datasets with the format `social_network-sf\<SF\>-CsvComposite-LongDateFormatter``

then run:
```
scripts/load-in-one-step.sh
```

to load the database.

Then, set the substitution parameter directory (you can also find them [here](https://ldbcouncil.org/data-sets-surf-repository/snb-interactive-v1-datagen-v100)) as well as the update stream directory (which you can find in the pre generated data folder) and the scale factory parameter in your desired driver/<>.properties file, then run the corresponding driver/<>.sh file

For example, if you wish to run the benchmark, set the properties in driver/benchmark.properties and run driver/benchmark.sh

To back up the database, you can simply back up the generated database/ directory
