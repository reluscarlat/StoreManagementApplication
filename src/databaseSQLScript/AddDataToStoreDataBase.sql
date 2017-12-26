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
select * from 
	(select stores_departaments.id, departaments.departament_name, departaments.departament_abbreviation, stores.store_name, stores.phone_number, stores.email
		from departaments left outer join stores_departaments on departaments.departament_name = stores_departaments.departament_name 
		left outer join stores on stores_departaments.store_name = stores.store_name) as myJoin
			where myJoin.store_name is not null 
				order by myJoin.id;
                
insert into employee_addresses values('Andy','Mack','USA','STATE3','D1','CITY1','STREET 1', 15,'house'),
('Ionut','John','CANADA','STATE2','D2','CITY2','STREET 2', 27,'B5'),
('Daniel','Cena','FRANCE','STATE5','D3','CITY3','STREET 3', 43,'house'),
('Razvan','Siso','ROMANIA','BUCHAREST','BUCHAREST','SECTOR 6','Drumul Mihail',29,'B1'),
('Robert','Tiron','SPAIN','STATE1','D5','CITY5','STREET 5',87,'B4');
insert into employee_addresses values('Daniel','Cena','FRANCE','STATE5','D3','CITY3','STREET 3', 43,'house');
select * from employee_addresses;
select * from employees;
#delete from employee_addresses;
#delete from employees;

insert into employees values('Razvan', 'Siso', '1960619567111','TT', '552299', 'Tester', 3900, now() ,'Quality Assurance' , 'Magazin3'),
('Daniel', 'Cena', '1960619567112','TT', '552299', 'Tester', 3900, now() ,'Quality Assurance' , 'Magazin3'),
('Robert','Tiron', '1909233399009','CC', '332211', 'Senior Developer',9000, now(), 'Development','Magazin4');
select employees.* , employee_addresses.country , employee_addresses.state, employee_addresses.district, 
	employee_addresses.city_or_village, employee_addresses.street, employee_addresses.address_number, employee_addresses.mansion
	from employees join employee_addresses 
		where employees.employee_first_name = employee_addresses.employee_first_name and
        employees.employee_last_name = employee_addresses.employee_last_name order by state;
        
select departament_name from (
	select departaments.departament_name, stores.store_name 
    from departaments left outer join stores_departaments on departaments.departament_name = stores_departaments.departament_name 
    left outer join stores on stores_departaments.store_name = stores.store_name
) as myJoin where myJoin.store_name = 'Magazin2' order by departament_name;
