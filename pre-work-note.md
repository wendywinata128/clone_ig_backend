## Page

### Login Page
### Home Page
### Caption + Expanding Captions (Post)
### Comment + Expanding Comments (Comment)

##  Feature
-   Make the Layout and UI of the mobile app like Instagram as similar as possible.
-   Create new post/ stories
-   Reply comments
-   Explore

##  Goals
-   Create a login page connected to MySQL user table. #
-   After the authentication succeeded, redirect to the home page. In this page, stories are shown on
top (doesn't need to be clickable), and posts with captions and comments are shown.
-   Posts need to have an image, caption, number of likes, and comments. #
-   Add a “feature” on each post where the users can like the post. #
-   Add a “feature” on the comments section where users can insert new data into the comment table #

## Rules
1. Use Flutter framework for front-end development, using Provider is a must.
2. Use Java Framework for back-end development, using SpringBoot is a plus.
3. Use MySQL for the database.
4. You’re allowed to use any type of server.
5. You’re allowed to use any type of ui-library.
6. You’re allowed to use any build tools if needed.
7. You’re allowed to use any online/ offline documentation.
8. Good UI & UX design will be a plus point.
9. Using/ applying design patterns will be a plus point.
10. Creating a test for the implemented code will be a plus point.
11. “Clean code” basic principles must be applied. #SOLID

##  Entity
-   User        (ID, email, password, username, name, avatar)
-   Post        (ID, photo, description, user_id/author_id)
-   Comment     (ID, description, author_id, post_id)

-   Followers   (ID, user_id, followers_id, type)
-   Stories     (ID, photo, user_id/author_id)
-   Love        (ID, post_id, user_id)

