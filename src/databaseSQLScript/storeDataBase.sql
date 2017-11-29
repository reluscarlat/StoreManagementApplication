create database if not exists storeDataBase;
use storeDataBase;
#describe employees;
#show tables;
#drop database storedatabase;
create table if not exists users(
	user_id tinyint not null primary key auto_increment,
    username varchar(50) not null,
    pass varchar(50) not null,
    employ_first_name varchar(50) not null,
    employ_name varchar(50) not null,
    role enum('admin','normal') default'normal'
);

insert into users values(null, '', '', '123', 'Andrei','admin' ),
	(null, 'GrAu', 'aaaaFFFFaa', 'Greg', 'Aurel', 'normal'),
    (null, 'CoAl', 'WWRRTTasd', 'Codin', 'Alin', 'normal' );
    
insert into users values (null, 'admin', '123', 'John', 'Cena', 'admin');

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
    nbr_of_products double not null,
    bill_id int not null,
    foreign key(bill_id) references fiscal_bill(id) 
		on update cascade 
        on delete cascade
);

create table if not exists providers(
    provider_name varchar(50) not null primary key,
    phone_number varchar(20) not null,
    email varchar(50) not null
);

create table if not exists stores(
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
	id smallint not null primary key auto_increment,
    country varchar(50) not null,
    state varchar(50),
    district varchar(50),
    city_or_village varchar(50) not null,
    street varchar(50) not null,
    address_number smallint,
    mansion varchar(30) 
);

create table if not exists employees(
	employee_name varchar(50) not null,
    employee_first_name varchar(50) not null,
    cnp char(13) not null primary key ,
    series varchar(5) not null,
    nbr varchar(10) not null,
    job_title varchar(30) not null,
    salary double not null default 1350,
    employment_data date not null,
    address_id smallint not null,
    departament_name varchar(50) not null,
    store_name varchar(50) not null,
    foreign key(address_id) references employee_addresses(id)
		on update cascade
        on delete cascade,
    foreign key(departament_name) references departaments(departament_name)
		on delete cascade
        on update cascade,
    foreign key(store_name) references stores(store_name)
		on delete cascade
        on update cascade
);