import kuzu
import sys

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

