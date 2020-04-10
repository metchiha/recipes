#Create Databases
CREATE DATABASE sfg_dev;
CREATE DATABASE sfg_prod;

#Create database service accounts
CREATE USER 'sfg_dev_user'@'192.168.99.100' IDENTIFIED BY 'mekontso';
CREATE USER 'sfg_prod_user'@'192.168.99.100' IDENTIFIED BY 'mekontso';
CREATE USER 'sfg_dev_user'@'%' IDENTIFIED BY 'mekontso';
CREATE USER 'sfg_prod_user'@'%' IDENTIFIED BY 'mekontso';

#Database grants
GRANT SELECT ON sfg_dev.* to 'sfg_dev_user'@'192.168.99.100';
GRANT INSERT ON sfg_dev.* to 'sfg_dev_user'@'192.168.99.100';
GRANT DELETE ON sfg_dev.* to 'sfg_dev_user'@'192.168.99.100';
GRANT UPDATE ON sfg_dev.* to 'sfg_dev_user'@'192.168.99.100';
GRANT SELECT ON sfg_prod.* to 'sfg_prod_user'@'192.168.99.100';
GRANT INSERT ON sfg_prod.* to 'sfg_prod_user'@'192.168.99.100';
GRANT DELETE ON sfg_prod.* to 'sfg_prod_user'@'192.168.99.100';
GRANT UPDATE ON sfg_prod.* to 'sfg_prod_user'@'192.168.99.100';
GRANT SELECT ON sfg_dev.* to 'sfg_dev_user'@'%';
GRANT INSERT ON sfg_dev.* to 'sfg_dev_user'@'%';
GRANT DELETE ON sfg_dev.* to 'sfg_dev_user'@'%';
GRANT UPDATE ON sfg_dev.* to 'sfg_dev_user'@'%';
GRANT SELECT ON sfg_prod.* to 'sfg_prod_user'@'%';
GRANT INSERT ON sfg_prod.* to 'sfg_prod_user'@'%';
GRANT DELETE ON sfg_prod.* to 'sfg_prod_user'@'%';
GRANT UPDATE ON sfg_prod.* to 'sfg_prod_user'@'%';