# Housing Movie Backend
Backend Repository for housing movie website.

# Latest Update - 22nd Jan, 2021

## Server Detail
**API urls now available on AWS | Heroku in this following link.**
```
https://housing-movie.herokuapp.com
```

- Base URL: https://housing-movie.herokuapp.com
- Port: Environment port or 8080

## Environment Requirements
- Kotlin 200+
- Java Compiler Oracle 11+ Or OpenJDK accept class method 52+
- Postgres 12+

## Development Rules (Deprecated)

1. Replace the **key.pem** with your key file name in the [deploy.sh](./deployment_scripts/deploy.sh) file

2. To update application to the newer version, change the **`deploy.sh`** of deployment folder 
    ```
    # Specify the version, it must be identical and same as version inside pom.xml
   
    version = "0.0.1"

3. Only change the action on a single pull request that fully described.

## Application Brief Description

- The application is developed using **Spring Boot Framework** and preserve by Json Web Token **(JWT)** 
- **Postgres SQL** is a database engine that will be used as the Relational Database. 
- This source is strictly follow **Clear Architecture** Pattern.
- All the commit must follow the **commit rules**
- CI/CD with custom scripts and automatically deploy via the support of **Heroku**
## Available Functionalities

### Commons
   - ping guest
   - ping admin
   - ping subscribers
   
### Resources Management
   - Upload resources
   - Download resources
   
### Authentication & Authorization
   - Using JWT bearer token
      - Access Token: 1 day expired
      - Refresh Token: 1 week expired
   - Authorization with 3 different roles
      1. Guest
      2. Subscriber
      3. Administrators
   
### Others Application APIS
   ```
   /movies
   /genres
   /discounts
   /episodes
   /orders
   /statistic
   /purchase
   /plans
   /users
   /comment
   
   # And others followed features
   ```

## Commit rules

### Semantic Commit Messages

Commit rule of this project

Format: `<type>(<scope>): <subject>`

`<scope>` is optional

### Example

```
feat: add hat wobble
^--^  ^------------^
|     |
|     +-> Summary in present tense.
|
+-------> Type: chore, docs, feat, fix, refactor, style, or test.
```

More Examples:

- `feat`: (new feature for the user, not a new feature for build script)
- `fix`: (bug fix for the user, not a fix to a build script)
- `docs`: (changes to the documentation)
- `style`: (formatting, missing semi colons, etc; no production code change)
- `refactor`: (refactoring production code, eg. renaming a variable)
- `test`: (adding missing tests, refactoring tests; no production code change)
- `chore`: (updating grunt tasks etc; no production code change)

