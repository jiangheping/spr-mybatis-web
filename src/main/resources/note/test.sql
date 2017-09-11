--oracle数据库
create table person(
id number primary key,
name varchar2(20),
sex varchar2(2),
description varchar2(50));
insert into person values(1,'周杰伦','0','台湾著名歌手');
insert into person values(2,'周星驰','0','香港著名喜剧演员');
insert into person values(3,'楼教主','0','码农大神');
insert into person values(4,'林志玲','1','台湾著名演员');

commit;