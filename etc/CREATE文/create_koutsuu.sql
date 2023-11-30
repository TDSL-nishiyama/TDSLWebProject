CREATE TABLE koutsuu
(no INT PRIMARY KEY
,id INT NOT NULL
,sendmailaddress VARCHAR(100) NOT NULL
,kukan1_start VARCHAR(100) NOT NULL,kukan1_end VARCHAR(100) NOT NULL,kingaku1 VARCHAR(100) NOT NULL
,kukan2_start VARCHAR(100) ,kukan2_end VARCHAR(100),kingaku2 VARCHAR(100)
,bikou VARCHAR(1000)
);