PGPASSWORD=123456 psql -U postgres --command "CREATE USER hotel WITH PASSWORD 'hotel@123';"
PGPASSWORD=123456 psql -U postgres --command "CREATE DATABASE hotel;"
PGPASSWORD=123456 psql -U postgres --command "GRANT ALL PRIVILEGES ON DATABASE hotel TO hotel;"
PGPASSWORD=123456 psql -U postgres -d hotel --command 'CREATE EXTENSION "uuid-ossp";'
PGPASSWORD=123456 psql -U postgres -d hotel --command 'CREATE EXTENSION "pgcrypto";'
PGPASSWORD=123456 psql -U postgres --command 'ALTER DATABASE hotel SET timezone TO "UTC";'
PGPASSWORD=123456 psql -U postgres -d hotel --command "GRANT ALL PRIVILEGES ON ALL TABLES IN SCHEMA public TO hotel;"
PGPASSWORD=123456 psql -U postgres -d hotel --command "GRANT ALL PRIVILEGES ON ALL SEQUENCES IN SCHEMA public TO hotel;"