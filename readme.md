# Instagram Clone Backend

This test project is part of an assignment from Commsult, designed to showcase the development of a full-stack Instagram clone. It includes two projects: a mobile application (using Flutter) and a backend service (using Springboot). The mobile app replicates core Instagram features, such as posting, commenting, and liking, while the backend handles data storage, authentication, and API functionality.

## Tech Stack and Libraries

- Java with Springboot and Hibernate JPA
- MySQL Database
- Lombok
- JSON Web Token and Springboot Security
- Java Faker
- Docker

## Services

### User Service

1.  Get Current User
2.  Get User details
3.  User Login and User Register
4.  Follow and Unfollow User
5.  Get User Stories (Dummies Data)

### Post Service

1. Get Post Based On User Following
2. Create Post
3. Toggle Post Like

### Comment Service

1. Get Comments based on Post
2. Add Comment

## How To Run

1. Clone the repository

```
git clone https://github.com/wendywinata128/be-assignment.git
```

2. Run mysql in your computer, this is the example with docker :

```
    docker run -e ALLOW_EMPTY_PASSWORD=true --name mysql_commsult_test -p 3306:3306  mysql
```

3. Open database.sql and import or run manually the sql to create database and the tables.

4. Run your springboot, at first times Springboot will run seeder with **DatabaseSeeder.java**.

5. Explore the APIs, example user is
```
{
    "username": "codi.ziemann",
    "password": "dummy123"
}
```

## API Endpoints Documentation
Api documentations can be found at file [Api Documentation](api-documentation.md)

## Contact

- Email: wendywinata128@gmail.com
- LinkedIn: https://www.linkedin.com/in/wendyyy/
