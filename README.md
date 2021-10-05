# cost_man

## About the projects

This projects goal is to keep your AWS Budget. In order to do that , resources will be shut down based on a priority queue if the budget is close to be exceeded. 


## Status

This project is under development, and it Is NOT finished. 

## Prerequisite

1. AWS CLI 

2. PSQL 

3. Linux (for now) 

## Project setup

### 1. Add data to the config.properties file 
    1.1 ACCOUNT_ID = Your 12 digit AWS Account ID
     
    1.2 ROLE_ARN = arn:aws:iam::000000000000:role/some_role
        1.2.1 The Role should have the following permission policy's attached: ce:* , budgets:* , sts:AssumeRole and ec2:*
     
    1.3 You also need to opt in for the "AWS Cost Explorer Hourly and Resource Level Data" on the AWS site
    


### 2. Add data to the db.properties file 
    1.1 Create a local Postgres Database called "costman" and run the make script at path (src/main/resources/db_scripts)
    1.2 Set up environment variables for USER , PWD and Connectionstring to a Postgres SQL Server
    1.3 Add the environment variable key as value to the db.properties file 
    
            


