
set -eu
set -o pipefail

cd "$( cd "$( dirname "${BASH_SOURCE[0]}" )" >/dev/null 2>&1 && pwd )"
cd ..

if [ ! -d "${KUZU_CSV_DIR}" ]; then
    echo "Directory ${KUZU_CSV_DIR} does not exist."
    exit 1
fi

mkdir -p converted/
rm -rf converted/*
mkdir -p converted/static
mkdir -p converted/dynamic

reformat() {
	if python3 scripts/reformat-csv.py ${KUZU_CSV_DIR}${1}_0_0.csv converted/$1.csv
	then
		echo "reformatted $1"
	else
		echo "error: unable to reformat $1"
	fi
}
export -f reformat

cat scripts/csvs.txt | xargs -n 1 -P $(nproc) -I {} bash -c 'reformat "{}"' 
