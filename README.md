# Java EE - Bookstore

This is a simple bookstore application created to prove understanding of Java EE 
environments and concepts. It's proposed by IMD0409 course in 2019.1 semester. 

## Development Environment

It uses a Docker powered database (PostgreSQL), pgAdmin and container server (Wildfly).
Make sure you have docker and docker-compose. Note: that environment doesn't deploys 
the application by default. Services provided:

- Wildfly 16
  - 8080: http services
  - 9990: HAL Management Console
- PostgreSQL
  - 5432: pgSQL port
- PGadmin
  - 8081: Admin console