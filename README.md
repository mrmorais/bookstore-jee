# Java EE - Bookstore

This is a simple bookstore application created to prove understanding of Java EE environments and concepts. It's a project proposed by IMD0409 course in 2019.1 semester.

## Subprojects

<details><summary>Bookstore-Bean</summary>

> a Java EE project which contains EJBs to deal with business domain (Domain classes, Data Acces Objects and EJB endpoints). It persists data using Hibernate and PostgreSQL. It's powered by Wildfly.

</details>
<details><summary>Bookstore-REST</summary>

> a JAX-RS web project that serves a REST service to consume Bookstore-Bean endpoints. It uses JSON on data transfer layer with web.

</details>
<details><summary><strike>Bookstore (Incomplete)</strike></summary>

> a simple HTML project that uses Axios and jQuery to consume Bookstore-REST endpoints.

</details>

## Java EE Concepts

|Concept|Use case|
|---|---|
|EJB 3.x|Almost every DAO and interfaces on Bean project are served by EJB (such as BookBean, BookDaoBean)|
|Message Driven Bean|The application creates two queues corresponding to two MDB queue consumers: DemandQueueMDB and IntentProcessorMDB|
|TimerService|The DemandQueueMDB uses a Schedule timeout to close any open demand entity every minute|
|JAX-RS|All REST project uses JAX-RS services to provide a endpoint layer of data access|
|JPA|Every entity are persisted by Hibernate implementation provided by Wildfly|

## Development Environment

You can use a Docker powered database (PostgreSQL), pgAdmin and container server (Wildfly) on development environment. Make sure you have docker and docker-compose. Note: that environment doesn't deploys the application.

Provided services:

- Wildfly 16
  - 8080: http services
  - 9990: HAL Management Console
- PostgreSQL
  - 5432: pgSQL port
- PGadmin
  - 8081: Admin console

### Running

To get the full app up on your Docker run at the first time `docker-compose up --build`

For the followings execution use `docker-compose start` instead.

### Developing

I strongly recommend you to use VSCode as your "IDE", is insteresting to have the 
following extensions to work well with JEE environment:

- Docker (by Microsoft)
- Java Extension Pack (by Microsoft)
- [Language Support for Java](https://github.com/redhat-developer/vscode-java) (by Red Hat) you have to install this one manually

When hit "Open Folder" on VSCode pick `bookstore-jee`. After modifications, or just to try, 
you can send the code to the running instance of WildFly 16.0 by using these two tasks:

- task deploy ejb
- task deploy jsf

To run a task press `Ctrl + P` and type the name above listed, or just task to see all options.

> Note: The WildFly container is a modified image of the official one, I called it Booksfly.
It imports JDBC jar file and creates a datasource connecting to the also conteinerized PostgreSQL instance.

### Deploying

To make a deploy first install `Bookstore-Bean` on maven local registry:

```
mvn -f Bookstore-Bean clean install
```

Then, deploy `Bookstore-REST`

```
mvn -f Bookstore-REST wildfly:deploy
```

## Troubleshooting

**The code is not been updated on Wildfly container**

If somehow you identified that your code is not going successfully to the container, but you got a BUILD SUCCESS message you can solve it by running a maven clean goal.

**The JNDI got messy**

In that situation try undeploy and deploy it again with the clean command. If still not worked try restart the server (through 9990 HAL console).

**JPA did not created the database correctly**

Try redeploy the Bookstore-EJB project. Before it, try remove and create again the public schema (via 8081 pgAdmin).

## Contributors

| <img src="https://projetos.imd.ufrn.br/uploads/-/system/user/avatar/286/avatar.png?width=90" width="90"/> |
|---|
|[@Morais](https://projetos.imd.ufrn.br/Morais) <br/> Master contributor|