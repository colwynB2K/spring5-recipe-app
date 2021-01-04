## Use to run mysql db docker imqge, optionql if you're not using q local mysqldb' ||
# docker run --name mysql -p 3306:3306 -e MYSQL_ALLOW_EMPTY_PASSWORD=yep -d mysql
# docker run --name mysql -p 3306:3306 -e MYSQL_ALLOW_EMPTY_PASSWORD=yep -v C:\tools\dockerdata\mysql:/var/lib/mysql -d mysql

# Connect to mysql and run as root user
# Create Databases
CREATE DATABASE sfg_dev;
CREATE DATABASE sfg_prod;

# Create database service accounts
CREATE USER 'sfg_dev_user'@'localhost' IDENTIFIED BY 'devguru';
# When using Docker Containers you will need to allow connection from other hosts (as you will be connecting from outsidde the container), % is a wildcard meaning ANY in SQL
CREATE USER 'sfg_dev_user'@'%' IDENTIFIED BY 'devguru';
CREATE USER 'sfg_prod_user'@'localhost' IDENTIFIED BY 'prodguru';
CREATE USER 'sfg_prod_user'@'%' IDENTIFIED BY 'prodguru';

# Database grants
GRANT INSERT ON sfg_dev.* to 'sfg_dev_user'@'localhost';
GRANT SELECT ON sfg_dev.* to 'sfg_dev_user'@'localhost';
GRANT UPDATE ON sfg_dev.* to 'sfg_dev_user'@'localhost';
GRANT DELETE ON sfg_dev.* to 'sfg_dev_user'@'localhost';
GRANT INSERT ON sfg_dev.* to 'sfg_dev_user'@'%';
GRANT SELECT ON sfg_dev.* to 'sfg_dev_user'@'%';
GRANT UPDATE ON sfg_dev.* to 'sfg_dev_user'@'%';
GRANT DELETE ON sfg_dev.* to 'sfg_dev_user'@'%';
GRANT INSERT ON sfg_prod.* to 'sfg_prod_user'@'localhost';
GRANT SELECT ON sfg_prod.* to 'sfg_prod_user'@'localhost';
GRANT UPDATE ON sfg_prod.* to 'sfg_prod_user'@'localhost';
GRANT DELETE ON sfg_prod.* to 'sfg_prod_user'@'localhost';
GRANT INSERT ON sfg_prod.* to 'sfg_prod_user'@'%';
GRANT SELECT ON sfg_prod.* to 'sfg_prod_user'@'%';
GRANT UPDATE ON sfg_prod.* to 'sfg_prod_user'@'%';
GRANT DELETE ON sfg_prod.* to 'sfg_prod_user'@'%';