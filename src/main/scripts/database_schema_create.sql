-- Might need to do 'use sfg_dev;' first

create table category (id bigint not null auto_increment, name varchar(255), primary key (id)) engine=InnoDB;
create table ingredient (id bigint not null auto_increment, amount decimal(19,2), name varchar(255), recipe_id bigint, unit_of_measure_id bigint, primary key (id)) engine=InnoDB;
create table notes (id bigint not null auto_increment, notes longtext, recipe_id bigint, primary key (id)) engine=InnoDB;
create table recipe (id bigint not null auto_increment, cook_instructions longtext, cook_time_mins integer, description varchar(255), difficulty varchar(255), image longblob, name varchar(255), prep_time_mins integer, source varchar(255), url varchar(255), yield varchar(255), notes_id bigint, primary key (id)) engine=InnoDB;
create table recipe_category (recipe_id bigint not null, category_id bigint not null, primary key (recipe_id, category_id)) engine=InnoDB;
create table unit_of_measure (id bigint not null auto_increment, name varchar(255), unit varchar(255), primary key (id)) engine=InnoDB;
alter table ingredient add constraint FKj0s4ywmqqqw4h5iommigh5yja foreign key (recipe_id) references recipe (id);
alter table ingredient add constraint FK15ttfoaomqy1bbpo251fuidxw foreign key (unit_of_measure_id) references unit_of_measure (id);
alter table notes add constraint FKdbfsiv21ocsbt63sd6fg0t3c8 foreign key (recipe_id) references recipe (id);
alter table recipe add constraint FK37al6kcbdasgfnut9xokktie9 foreign key (notes_id) references notes (id);
alter table recipe_category add constraint FKqsi87i8d4qqdehlv2eiwvpwb foreign key (category_id) references category (id);
alter table recipe_category add constraint FKcqlqnvfyarhieewfeayk3v25v foreign key (recipe_id) references recipe (id);
