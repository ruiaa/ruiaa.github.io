表名、列名：反引号与双引号
mysql
INSERT INTO `files`(`fileName`, `fileType`) VALUES ('ff', 'f');
oracle
INSERT INTO "files"("fileName", "fileType") VALUES ('ff', 'f');

在Oracle中： 

　　双引号的作用是：假如建立对象的时候，对象名、字段名加双引号，则示意Oracle将严格区分大小写，否则Oracl都默认大写。

　　而单引号则示意：这个加了单引号的字段是一个字类似字符串，并不区分大小写。