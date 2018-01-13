use storeDataBase;
insert into users values(null, 'a', '', '123', 'Andrei','admin' ),
	(null, 'GrAu1', 'aaaaFFFFaa', 'Greg', 'Aurel', 'normal'),
    (null, 'CoAl1', 'WWRRTTasd', 'Codin', 'Alin', 'normal' );    
insert into users values (null, 'admin', '123', 'John', 'Cena', 'admin');

insert into stores values(null, 'Magazin1', 'address 1' , '0711111111', 'magazin1@gmail.com'),
(null, 'Magazin2', 'address 2' , '0721111111', 'magazin2@gmail.com'),
(null, 'Magazin3', 'address 3' , '0731111111', 'magazin3@gmail.com'),
(null, 'Magazin4', 'address 4' , '0741111111', 'magazin4@gmail.com'),
(null, 'Magazin5', 'address 5' , '0751111111', 'magazin5@gmail.com'),
(null, 'Magazin6', 'address 6' , '0761111111', 'magazin6@gmail.com');
select * from stores;

insert into departaments values(null, 'Development', 'aaaaaaaaaaaaaaaa', 'DEV'),
(null, 'Quality Assurance', 'qqqqqqqqqqqqqqqqqqqqqq', 'QA'),
(null, 'Network', 'nnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnn', 'Net'),
(null, 'Human Resources', 'vvvvvvvvvvvvvvvvv', 'HR'),
(null, 'Financial', '$$$$$$$$$$$$$$$', 'FIN'),
(null, 'Public Relation', 'ppppppppppppppppppp', 'PR');

insert into stores_departaments values(null, 'Magazin1', 'Human Resources'); # add
insert into stores_departaments values(null, 'Magazin2', 'Human Resources');
insert into stores_departaments values(null, 'Magazin1', 'Development');

# ADD
select * from stores;

# SHOW

                
insert into employee_addresses values('Andy','Mack','USA','STATE3','D1','CITY1','STREET 1', 15,'house'),
('Ionut','John','CANADA','STATE2','D2','CITY2','STREET 2', 27,'B5'),
('Daniel','Cena','FRANCE','STATE5','D3','CITY3','STREET 3', 43,'house'),
('Razvan','Siso','ROMANIA','BUCHAREST','BUCHAREST','SECTOR 6','Drumul Mihail',29,'B1'),
('Robert','Tiron','SPAIN','STATE1','D5','CITY5','STREET 5',87,'B4');
insert into employee_addresses values('Daniel','Cena','FRANCE','STATE5','D3','CITY3','STREET 3', 43,'house');
#delete from employee_addresses;
#delete from employees;

insert into employees values('Razvan', 'Siso', '1960619567111','TT', '552299', 'Tester', 3900, now() ,'Quality Assurance' , 'Magazin3'),
('Daniel', 'Cena', '1960619567112','TT', '552299', 'Tester', 3900, now() ,'Quality Assurance' , 'Magazin3'),
('Robert','Tiron', '1909233399009','CC', '332211', 'Senior Developer',9000, now(), 'Development','Magazin4');


insert into providers values('provider1','07234','asdasd@gmail.com',null);
update providers set provider_name = 'aaa', phone_number = '000000234', email ='ceva@ceva.com' where id = 2;

insert into products value('Coca Cola', 'Sucuri', 'Helenic Romania', 'Bautura carbogazoasa racoritoare',
100, 3.75, 5.50, 'Magazin1');
insert into products value('Pepsi', 'Sucuri', 'Helenic Romania', 'Bautura racoritoare',
150, 3.65, 5.30, 'Magazin1');
insert into products value('Cozonac','Alimente','Boromir SRL', 'Ia cu paine',30,6.8,9.10,'Magazin1');


select * from users;
select * from employee_addresses;
select * from employees;
select * from departaments;
select * from stores;
select * from stores_departaments;
select * from products;
select * from providers;

insert into stores_products values (null,'Coca Cola', 'Magazin1');
insert into stores_products values (null,'Cozonac', 'Magazin1'),
(null,'Pepsi' , 'Magazin1');

# SELECT-URI SIMPLE:

# Selectarea datelor despre magazine si departamentele acestora
select * from 
	(select stores_departaments.id, departaments.departament_name, departaments.departament_abbreviation, stores.store_name, stores.phone_number, stores.email
		from departaments left outer join stores_departaments on departaments.departament_name = stores_departaments.departament_name 
		left outer join stores on stores_departaments.store_name = stores.store_name) as myJoin
			where myJoin.store_name is not null 
				order by myJoin.id;

# Furnizorii magazinului 1   
select distinct myJoin.provider_name from 
	(select stores.store_name, products.provider_name from stores left join stores_products on stores.store_name = stores_products.store_name
	left join products on stores_products.product_name = products.product_name
    left join providers on products.provider_name = providers.provider_name) as myJoin 
		where myJoin.store_name= 'Magazin1';
        

# Numarul de angajati cu aceasi nationalitate din magazine; 
select store_name, country as nationalitate, count(country) as 'numar angajati'
	from employee_addresses 
    left join employees on employees.employee_first_name=employee_addresses.employee_first_name and
    employees.employee_last_name=employee_addresses.employee_last_name
		group by nationalitate;

# Adresele angajatilor care lucreaza la departamentul cu o anumita abreviere; 
select departament_abbreviation, employee_addresses.* from departaments 
	left join employees on departaments.departament_name = employees.departament_name
    left join employee_addresses on employees.employee_first_name = employee_addresses.employee_first_name
	and employees.employee_last_name = employee_addresses.employee_last_name 
		where country is not null and departaments.departament_name = 'Quality Assurance';
        
# Afiseaza numele angajatilor si adresele de email ale magazinelor la care lucreaza
select employees.employee_first_name, employees.employee_last_name, stores.email from employees
	left join stores on employees.store_name = stores.store_name;
    
# selectarea datelor despre angajati din tabelel angajati si adrese
select employees.* , employee_addresses.country , employee_addresses.state, employee_addresses.district, 
	employee_addresses.city_or_village, employee_addresses.street, employee_addresses.address_number, employee_addresses.mansion
	from employees join employee_addresses 
		where employees.employee_first_name = employee_addresses.employee_first_name and
        employees.employee_last_name = employee_addresses.employee_last_name order by state;


# SELECT-URI COMPLEXE:

# selectarea angajatilor cu salariu mai mic decat media salariilor
	  select mySelect.employee_first_name, mySelect.employee_last_name, mySelect.salary  
	  from ( select * from employees where salary > 
		(select avg(salary) from employees)) as mySelect;  

# Selectarea tarilor cu mai mult de 2 angajati
	select mySelect.nationalitate , mySelect.numar_angajati from (
		select store_name, country as nationalitate, count(country) as numar_angajati
		from employee_addresses 
		left join employees on employees.employee_first_name=employee_addresses.employee_first_name and
			employees.employee_last_name=employee_addresses.employee_last_name
		group by nationalitate) as mySelect where mySelect.numar_angajati > 2;
        
# selectarea categoriei cu cel mai mare pret mediu al produselor 
	select mySelect.categori, mySelect.average_price from
		(select categori, avg(selling_price) as average_price from products group by categori) as mySelect 
		order by mySelect.average_price desc limit 0,1;
# selectarea departamentelor existente la un anumit magazin
	select departament_name from (
		select departaments.departament_name, stores.store_name 
		from departaments left outer join stores_departaments on departaments.departament_name = stores_departaments.departament_name 
		left outer join stores on stores_departaments.store_name = stores.store_name
	) as myJoin where myJoin.store_name = 'Magazin2' order by departament_name;
    