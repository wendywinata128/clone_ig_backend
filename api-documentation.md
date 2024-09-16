## Authentication

`POST /user/login` Login and getting user Token

**body**

```
{
    "username": "codi.ziemann",
    "password": "dummy123"
}
```

`POST /user/register` Sign up new user

**body**

```
{
    "username": "wendy128",
    "name": "Wendy Winata",
    "email": "wendywinata128@gmail.com",
    "password": "wendy123"
}
```

`GET /user/` Get Current Login User

## Post

`GET /posts/home` Get Post in User Home

**Query Params**

```
page:0
sort:createddt,desc
```

`POST /posts` Create Post

**Body Form Data**

```
    "photo": File,
    "description":  "Nisi voluptate ex proident elit a"
```

`PATCH /posts/:postId/likes`
  **Params**

```
    postId: 12
```

## Comments

`GET /comments/:postId` Get Comments based on Post

**Params**

```
postId: 1
```

`POST /comments/:postId` Add Comments

**Params**

```
postId: 1
```

**Body**

```
{
    "description": "contoh comment"
}
```

## User

`GET user/:username` Profile Details

`GET user/stories` Current User Stories

`POST user/3/follow` Follow User

`DELETE user/3/unfollow` Unfollow User

`GET user/search/:keyword` Search User

## File
`GET /files/post/:fileName` Get Post Files

**Params**

```
fileName: dummy.jpg
```
