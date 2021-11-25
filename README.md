# Housing Movie Backend
Backend Repository for housing movie website.

## Server Detail
**API urls now available on AWS in this following link.**
```
http://ec2-13-251-81-100.ap-southeast-1.compute.amazonaws.com:8080/hello
```

- Base URL: http://ec2-13-251-81-100.ap-southeast-1.compute.amazonaws.com:8080
- Port: 8080

## Environment Requirements
- Kotlin 200+
- Java Compiler Oracle 11+ Or OpenJDK accept class method 52+
- Postgres 12+

## Development Rules

1. To update application to the newer version, change the **`deploy.sh`** of deployment folder 
    ```
    # Specify the version, it must be identical and same as version inside pom.xml
   
    version = "0.0.1"

2. Only change the action on a single pull request that fully described.

## Available APIs


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

