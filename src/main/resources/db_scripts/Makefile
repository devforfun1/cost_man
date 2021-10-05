SHELL=/bin/bash

table:
	psql -d costman < tables/prio_queue_type.sql
inserts:table
	psql -d costman < inserts/insert_prio_queue_type.sql 
