CREATE TABLE skill
(
   id INT PRIMARY KEY,
   sikaku1 VARCHAR (100),
   sikaku1syutokuYMD DATE,
   sikaku2 VARCHAR (100),
   sikaku2syutokuYMD DATE,
   sikaku3 VARCHAR (100),
   sikaku3syutokuYMD DATE,
   c1kikanS DATE,
   c1kikanE DATE,
   carrier1 VARCHAR (1000),
   c1position VARCHAR (100),
   c2kikanS DATE,
   c2kikanE DATE,
   carrier2 VARCHAR (1000),
   c2position VARCHAR (100),
   c3kikanS DATE,
   c3kikanE DATE,
   carrier3 VARCHAR (1000),
   c3position VARCHAR (100),
   timestamp TIMESTAMP
);