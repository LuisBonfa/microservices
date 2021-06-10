create table "user" (
  id uuid primary key default uuid_generate_v4(),
  password varchar(256) not null,
  name varchar(256) unique not null,
  alias varchar(256) not null,
  tries integer not null,
  email varchar(100) not null,
  phone varchar(15) not null,
  status varchar(10) not null,
  created_at timestamp with time zone not null default now(),
  updated_at timestamp with time zone null default now()
);

create table role (
    id uuid primary key default uuid_generate_v4(),
    name varchar(20) not null,
    status varchar(10) not null,
    created_at timestamp with time zone not null default now()
);

create table user_role(
    id uuid primary key default uuid_generate_v4(),
    userId uuid not null references "user"(id),
    roleId uuid not null references "role"(id),
    created_at timestamp with time zone not null default now()
);

insert into role (name, status) values ('admin', 'enabled');