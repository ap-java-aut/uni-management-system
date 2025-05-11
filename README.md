# University Management System

## Deployment guide

1. First of all, install all the dependencies specified in [pom.xml](pom.xml)
2. Specify `YOUR_DB_NAME`, `YOUR_USERNAME` and `YOUR_PASSWORD` in the [hibernate.cfg.xml.template](src/main/resources/hibernate.cfg.xml.template) file, change the whole database connection url if necessary.
3. Initialize your database using scripts specified in [db directory](src/main/resources/db)
4. Build and run this program