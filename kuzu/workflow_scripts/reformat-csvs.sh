
set -eu
set -o pipefail

cd "$( cd "$( dirname "${BASH_SOURCE[0]}" )" >/dev/null 2>&1 && pwd )"
cd ..

if [ ! -d "${KUZU_CSV_DIR}" ]; then
    echo "Directory ${KUZU_CSV_DIR} does not exist."
    exit 1
fi

mkdir -p test-data/converted/
rm -rf test-data/converted/*
mkdir -p test-data/converted/static
mkdir -p test-data/converted/dynamic

reformat() {
	if python3 workflow_scripts/reformat-csv.py ${KUZU_CSV_DIR}${1}_0_0.csv test-data/converted/$1.csv
	then
		echo "reformatted $1"
	else
		echo "error: unable to reformat $1"
	fi
}
export -f reformat

cat workflow_scripts/csvs.txt | xargs -n 1 -P $(nproc) -I {} bash -c 'reformat "{}"' 
