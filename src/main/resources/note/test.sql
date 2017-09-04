--oracle数据库
create table person(
id number primary key,
name varchar2(20),
description varchar2(50));
insert into person values(1,'周杰伦','台湾著名歌手');
insert into person values(2,'周星驰','香港著名喜剧演员');
insert into person values(3,'楼教主','码农大神');
insert into person values(4,'林志玲','台湾著名演员');

commit;