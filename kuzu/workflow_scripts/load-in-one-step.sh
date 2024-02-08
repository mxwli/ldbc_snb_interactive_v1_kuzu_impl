
set -eu
set -o pipefail

cd "$( cd "$( dirname "${BASH_SOURCE[0]}" )" >/dev/null 2>&1 && pwd )"
cd ..

if [ ! -d "${KUZU_CSV_DIR}" ]; then
    echo "Directory ${KUZU_CSV_DIR} does not exist."
    exit 1
fi

source scripts/setup-venv.sh

scripts/clear-database.sh
scripts/reformat-csvs.sh
python3 scripts/load-database.py
