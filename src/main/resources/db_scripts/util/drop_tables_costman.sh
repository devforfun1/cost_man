#!/bin/bash

psql -d costman -U $PSQL_USER -c "DROP TABLE Prio_Queue_Place ;"
psql -d costman -U $PSQL_USER -c "DROP TABLE Prio_Element ;"
psql -d costman -U $PSQL_USER -c "DROP TABLE Prio_Queue_Selected;"
psql -d costman -U $PSQL_USER -c "DROP TABLE Prio_Queue;"
psql -d costman -U $PSQL_USER -c "DROP TABLE Prio_Queue_Type ;"






