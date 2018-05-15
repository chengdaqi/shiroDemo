CREATE TABLE permission(
pid INT(11) NOT NULL AUTO_INCREMENT,
NAME VARCHAR(255) NOT NULL DEFAULT '',
url VARCHAR(255) NOT NULL DEFAULT '',
PRIMARY KEY (pid)
)ENGINE INNODB DEFAULT CHARSET=UTF8;

CREATE TABLE USER(
uid INT(11) NOT NULL AUTO_INCREMENT,
username VARCHAR(255) NOT NULL DEFAULT '',
PASSWORD VARCHAR(255) NOT NULL DEFAULT '',
PRIMARY KEY(uid)
)ENGINE INNODB DEFAULT CHARSET = UTF8;

CREATE TABLE Role(
rid INT(11) NOT NULL AUTO_INCREMENT,
rname VARCHAR(255) NOT NULL DEFAULT '',
PRIMARY KEY(rid)
)ENGINE INNODB DEFAULT CHARSET=UTF8;

CREATE TABLE permiddion_role(
pid INT(11) NOT NULL,
rid INT(11) NOT NULL,
KEY idx_pid (pid),
KEY idx_rid (rid)
)ENGINE INNODB DEFAULT CHARSET=UTF8
