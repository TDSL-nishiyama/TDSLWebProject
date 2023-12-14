CREATE TABLE login
(id int PRIMARY KEY
,loginid VARCHAR(200) UNIQUE NOT NULL
,password VARCHAR(200) NOT NULL
,del VARCHAR(2) default ('')
);