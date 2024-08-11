create table if not exists Bondio_Order (
    id identity,
    delivery_Name varchar(50) not null,
    delivery_Street varchar(50) not null,
    delivery_City varchar(50) not null,
    delivery_State varchar(2) not null,
    delivery_Zip varchar(10) not null,
    cc_number varchar(16) not null,
    cc_expiration varchar(5) not null,
    cc_cvv varchar(3) not null,
    placed_at timestamp not null
    );

create table if not exists Bondio (
    id identity,
    name varchar(50) not null,
    bondio_order bigint not null,
    bondio_order_key bigint not null,
    created_at timestamp not null
    );

create table if not exists Bondio_Ingredient (
    ingredient varchar(4) not null,
    bondio bigint not null,
    bondio_key bigint not null
    );

create table if not exists Ingredient (
    id varchar(4) not null primary key,
    name varchar(25) not null,
    type varchar(10) not null
    );

alter table Bondio
    add foreign key (bondio_order) references Bondio_Order(id);

alter table Bondio_Ingredient
    add foreign key (ingredient) references Ingredient(id);