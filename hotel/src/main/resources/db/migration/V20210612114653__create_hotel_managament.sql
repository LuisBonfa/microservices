create table booking (
  id uuid primary key default uuid_generate_v4(),
  user_id uuid not null,
  check_in timestamp with time zone null,
  check_out timestamp with time zone null,
  "begin" timestamp with time zone not null,
  "end" timestamp with time zone not null,
  status varchar(10) not null check (status in ('new', 'accepted', 'rejected', 'canceled')),
  created_at timestamp with time zone not null default now(),
  updated_at timestamp with time zone null default now()
);

create table bill(
    id uuid primary key default uuid_generate_v4(),
    booking_id uuid not null references "booking"(id),
    dailies_price float not null,
    garage_price float not null,
    products_data jsonb not null,
    products_price float not null,
    total float not null,
    status varchar(10) not null check (status in ('waiting', 'paid')),
    created_at timestamp with time zone not null default now(),
    updated_at timestamp with time zone null default now()
);
