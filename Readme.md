# QuestionPro Hacker NEWS API

The API provides three endpoints:
- /top-stories: Returns the top 10 stories ranked by score in the last 15 minutes, each with a title, URL, score, time of submission, and the user who submitted it.Caching is enabled for this endpoint, so clients fetching the data will see the same cached data for up to 15 minutes.

- /past-stories: Returns all the stories served previously by the /top-stories endpoint.

- /comments: Returns up to 10 comments on a given story, sorted by the number of child comments. Each comment contains the comment text and the user's Hacker News handle.

The API is implemented in Java using Spring Boot and uses the HackerNewsApiClient library to retrieve data from the Hacker News API. The API responses are serialized to JSON using Jackson.


## Service

```bash
./gradlew bootRun
```

## Postgres DB

Windows:

```bash
psql -U postgres
```

DB Commands:

```bash
CREATE USER "admin" WITH PASSWORD 'admin@123';
CREATE DATABASE "hackernews-db" ENCODING UTF8 TEMPLATE template0;
grant all PRIVILEGES ON DATABASE "hackernews-db" to admin;
```   
If using Postgresql 15 you need to grant admin user with priviledge to public schema:
Connect with root user postgres:

```bash
postgres=# \c hackernews-db
hackernews-db=# GRANT USAGE, CREATE ON SCHEMA public TO admin;
```

## Login cred

```
user: admin
pwd: admin@123
```

To build in one go:

```bash
./gradlew cleanBuild
```