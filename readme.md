#   Instagram Clone Backend

This test project is part of an assignment from Commsult, designed to showcase the development of a full-stack Instagram clone. It includes two projects: a mobile application (using Flutter) and a backend service (using Springboot). The mobile app replicates core Instagram features, such as posting, commenting, and liking, while the backend handles data storage, authentication, and API functionality.

##  Tech Stack and Libraries

- Java with Springboot and Hibernate JPA
- MySQL Database
- Lombok
- JSON Web Token and Springboot Security
- Java Faker
- Docker

##  Services

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

##  How To Run
1. Clone the repository

```
git clone https://github.com/wendywinata128/be-assignment.git
```

2. Set Up Mysql in your computer, Here's an example of how I do this with Docker:
```
docker run -e ALLOW_EMPTY_PASSWORD=true --name mysql_commsult_test -p 3306:3306  mysql
```

3.  Open database.sql and either import the file or run the SQL commands manually to create the necessary database and tables.

4. Start the Spring Boot application. On the first run, it will execute the seeder located in DatabaseSeeder.java to populate initial data.

5.  You can start exploring the APIs. Here’s an example of a sample user you can use for login:
```
{
    "username": "codi.ziemann",
    "password": "dummy123"
}
```

##  API Endpoints Documentation
Api documentations can be found at file [Api Documentation](api-documentation.md)

##  Authentication
The authentication and authorization is maintained using JWT token.

## Contact

- Email: wendywinata128@gmail.com
- LinkedIn: https://www.linkedin.com/in/wendyyy/
