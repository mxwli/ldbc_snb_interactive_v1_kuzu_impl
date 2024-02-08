
import pandas as pd
import sys
import csv as _csv

csv = sys.argv[1]
output = sys.argv[2]

header = open(csv, 'r').readline().replace('\n','').split('|')
skipFlag = True
for item in header:
	if item.endswith('.id'):
		skipFlag = len(header) <= 2
		break
	elif item == 'language' or item == 'email':
		skipFlag = False
		break

if skipFlag:
	print('skipping ' + csv + ' reformatting')
	open(output, 'w').write('\n'.join([line.replace('\n', '') for line in open(csv, 'r')][1:]))
	exit(0)

df = pd.read_csv(csv, sep='\|', engine='python')
columns = df.columns.tolist()
columns = sorted(columns, key=lambda x: not x.endswith('.id'))
df = df[columns]

if 'language' in columns and pd.api.types.is_string_dtype(df['language']):
	df['language'] = df['language'].map(lambda x: '[' + ','.join([f'\"{i}\"' for i in x.split(';')]) + ']')

if 'email' in columns:
	df['email'] = df['email'].map(lambda x: '[' + ','.join([f'\"{i}\"' for i in x.split(';')]) + ']')

df.to_csv(path_or_buf=output, sep='|', index=False, header=False, quoting=_csv.QUOTE_NONE)
