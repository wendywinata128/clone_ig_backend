## Authentication
### `POST /user/login`
Login and Get User Token

**Body**

```
{
    "username": "codi.ziemann",
    "password": "dummy123"
}
```

### `POST /user/register` 
Register new Account
**Body**

```
{
    "username": "wendy128",
    "name": "Wendy Winata",
    "email": "wendywinata128@gmail.com",
    "password": "wendy123"
}
```

### `GET /user/` 
Get Current Login User

## Post

## # `GET /posts/home` 
Get User Post Data based on User Followings

**Query Params**
```
page:0
sort:createddt,desc
```

### `POST /posts` 
Create Post by User

**Body Form Data**

```
    "photo": File,
    "description":  "Nisi voluptate ex proident elit a"
```

###  `PATCH /posts/:postId/likes`
Toggle Like and Unlike of Post

**Params**
```
    postId: 12
```

## Comments

### `GET /comments/:postId` 
Get Comments based on Post ID

**Params**
```
postId: 1
```

### `POST /comments/:postId` 
Add Comment to Post

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
### `GET user/:username` 
Get Profile Details based on Username
**Params**

```
    username: wndy128
```

### `GET user/stories` 
Get Current User Stories

### `POST user/:userId/follow` 
Follow User
**Params**
```
userId: 1
```

### `DELETE user/:userId/unfollow` 
Unfollow User
**Params**
```
userId: 1
```

### `GET user/search/:keyword` 
Search User based on Username or Name
**Params**

```
keyword: wndy128
```

## File
### `GET /files/post/:fileName` 
Get Post Files

**Params**
```
fileName: dummy.jpg
```
