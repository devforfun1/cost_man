SHELL=/bin/bash

table:
	psql -d costman < tables/prio_queue_type.sql
	psql -d costman < tables/prio_queue.sql
	psql -d costman < tables/prio_element.sql
	psql -d costman < tables/prio_queue_place.sql
	psql -d costman < tables/prio_queue_selected.sql
inserts: table
	psql -d costman < inserts/insert_prio_queue_type.sql
	psql -d costman < inserts/insert_prio_queue.sql
	psql -d costman < inserts/insert_prio_queue_selected.sql
