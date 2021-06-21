CREATE USER hotel WITH PASSWORD 'hotel@123';
CREATE DATABASE hotel; 
GRANT ALL PRIVILEGES ON DATABASE hotel TO hotel; 
\c hotel
CREATE EXTENSION "uuid-ossp"; 
CREATE EXTENSION "pgcrypto"; 
ALTER DATABASE hotel SET timezone TO "UTC";
GRANT ALL PRIVILEGES ON ALL TABLES IN SCHEMA public TO hotel; 
GRANT ALL PRIVILEGES ON ALL SEQUENCES IN SCHEMA public TO hotel; 




