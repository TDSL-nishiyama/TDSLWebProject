TABLE user 
INTO OUTFILE 'C:\pleiades\2023-06\workspace\TDSLWebProject\etc\各種テーブルのサンプルデータ\user.csv'
FIELDS TERMINATED BY ','
OPTIONALLY ENCLOSED BY '"'
ESCAPED BY ''
LINES TERMINATED BY '\n';

mysqldump -u root -p -t -TC:\pleiades\2023-06\workspace\TDSLWebProject\etc\各種テーブルのサンプルデータ\user.csv tdsl_master user;