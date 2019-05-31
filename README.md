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

### Running

To get the full pack up on your Docker instance run at the first time `docker-compose up --build`

For the followings execution use `docker-compose start` instead.

### Developing

I strongly recommend you to use VSCode as your "IDE", is insteresting to have the 
following extensions to work well with JEE environment:

- Docker (by Microsoft)
- Java Dependency Viewer (by Microsoft)
- Java Extension Pack (by Microsoft)
- Maven for Java (by Microsoft)
- [Language Support for Java](https://github.com/redhat-developer/vscode-java) (by Red Hat) you have to install this one manually

When hit "Open Folder" on VSCode pick `bookstore-jee`. After modifications, or just to try, 
you can send the code to the running instance of WildFly 16.0 by using these two tasks:

- task deploy ejb
- task deploy jsf

To run a task press `Ctrl + P` and type the name above listed, or just task to see all options.

> Note: The WildFly container is a modified image of the official one, I called it Booksfly.
It imports JDBC jar file and creates a datasource connecting to the also conteinerized PostgreSQL instance.