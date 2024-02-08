import sys, os

base_dir = os.path.dirname(os.path.realpath(__file__))

# import kuzu Python API
sys.path.append(os.path.join(base_dir, '..', '..', '..'))
import tools.python_api.build.kuzu as kuzu

db = kuzu.Database('database')
conn = kuzu.Connection(db)

idx = 0
for line in open("scripts/load.cypher", 'r'):
	line = line.replace('\n', '')
	idx += 1
	if line == '':
		continue
	print(f'executing line {idx} containing value {line}')
	conn.execute(line)

