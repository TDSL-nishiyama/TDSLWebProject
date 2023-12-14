CREATE TABLE koutsuu
(no INT PRIMARY KEY
,id INT NOT NULL
,sendmailaddress VARCHAR(100) NOT NULL
,riyouhiduke DATETIME NOT NULL
,kukan_start VARCHAR(100) NOT NULL
,kukan_end VARCHAR(100) NOT NULL
,kingaku VARCHAR(100) NOT NULL
,bikou VARCHAR(1000)
,modoshiriyuu VARCHAR(1000) DEFAULT('')
);