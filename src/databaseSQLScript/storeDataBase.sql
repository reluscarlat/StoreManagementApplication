create database if not exists storeDataBase;
use storeDataBase;
#describe employees;
#show tables;
#drop database storedatabase;
create table if not exists users(
	user_id tinyint not null primary key auto_increment,
    username varchar(50) not null unique,
    pass varchar(50) not null,
    employ_first_name varchar(50) not null,
    employ_name varchar(50) not null,
    role enum('admin','normal') default'normal'
);

#truncate table users;

create table if not exists fiscal_bill(
	id int not null primary key auto_increment,
    release_data date,
    release_time time,
    store_name varchar(50) default 'nespecificat' ,
    bill_number int unique not null,
    total_value double default 0.0
);

create table if not exists sold_products(
	id tinyint not null primary key auto_increment,
    product_name varchar(100) not null,
    selling_date date,
    selling_tine time,
    nbr_of_products double not null,
    bill_id int not null,
    foreign key(bill_id) references fiscal_bill(id) 
		on update cascade 
        on delete cascade
);

create table if not exists providers(
	id int unique auto_increment,
    provider_name varchar(50) not null primary key,
    phone_number varchar(20) not null,
    email varchar(50) not null
);

#alter table providers add column id int unique auto_increment;

create table if not exists stores(
	id tinyint not null auto_increment unique,
    store_name varchar(50) not null primary key unique,
    address varchar(150) not null,
    phone_number varchar(20) not null,
    email varchar(50) not null
);

create table if not exists products(
    product_name varchar(50) not null primary key,
    categori varchar(50) not null,
    provider_name varchar(50) not null,
    description varchar(250) default '' not null,
    nbr_of_products smallint not null,
    purchase_price double not null,
    selling_price double not null,
    store_name varchar(50) not null,
    foreign key(provider_name) references providers(provider_name)
		on delete cascade
        on update cascade
);
# N : N
create table if not exists stores_products(
	id bigint not null primary key auto_increment,
    product_name varchar(50) not null,
    store_name varchar(50) not null,
    foreign key(product_name) references products(product_name)
		on delete cascade
        on update cascade,
	foreign key(store_name) references stores(store_name)
		on delete cascade
        on update cascade
);

create table if not exists departaments(
	id int unique auto_increment,
    departament_name varchar(50) not null primary key,
    description varchar(250) not null default '',
    departament_abbreviation varchar(6) not null
);

# N : N 
create table if not exists stores_departaments(
	id smallint not null primary key auto_increment,
    store_name varchar(50) not null,
    departament_name varchar(50) not null,
    foreign key(store_name) references stores(store_name)
		on delete cascade
        on update cascade,
    foreign key(departament_name) references departaments(departament_name)
		on delete cascade
        on update cascade
);

create table if not exists employee_addresses(
	employee_first_name varchar(50) not null,
    employee_last_name varchar(50) not null, 
    country varchar(50) not null,
    state varchar(50) default 'none',
    district varchar(50) default 'none',
    city_or_village varchar(50) not null,
    street varchar(50) not null,
    address_number smallint default 0,
    mansion varchar(30) default 'none',
    primary key(employee_first_name, employee_last_name)
);

create table if not exists employees(
	employee_first_name varchar(50) not null,
    employee_last_name varchar(50) not null,
    cnp char(13) not null unique primary key ,
    series varchar(5) not null,
    nbr varchar(10) not null,
    job_title varchar(30) not null,
    salary double not null default 1350,
    employment_data date not null,
    departament_name varchar(50) not null,
    store_name varchar(50) not null,
    foreign key(employee_first_name, employee_last_name) references employee_addresses(employee_first_name, employee_last_name)
		on update cascade
        on delete cascade,
    foreign key(departament_name) references departaments(departament_name)
		on delete cascade
        on update cascade,
    foreign key(store_name) references stores(store_name)
		on delete cascade
        on update cascade
);