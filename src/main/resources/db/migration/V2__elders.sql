create table finances (id bigint not null auto_increment, balance decimal(19,2), elder_id bigint, primary key (id)) engine=InnoDB;
create table finances_invoices (finance_id bigint not null, invoices_id bigint not null) engine=InnoDB;
create table invoices (id bigint not null auto_increment, amount decimal(19,2) not null, description varchar(255), primary key (id)) engine=InnoDB;
alter table finances_invoices add constraint UK_9416eo8oj6qst2wg4j1x4c7mc unique (invoices_id);
alter table finances add constraint FKkiqgvmahu5fxwvaw25n7i54tu foreign key (elder_id) references elders (id);
alter table finances_invoices add constraint FKeu4ty08q66g5vsv6dbnoybl3 foreign key (invoices_id) references invoices (id);
alter table finances_invoices add constraint FKh2o7i479odedv0cqdiuv1gx9g foreign key (finance_id) references finances (id);