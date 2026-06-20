# Sistem Informasi Koleksi Action Figure dan Gundam

Simple Spring Boot application skeleton to manage collections (Action Figure / Gundam) with MySQL.

Setup

1. Ensure Java 17+ and Maven are installed.
2. Create a MySQL database or run the SQL script `db/init.sql` to create `koleksi_db` and sample data.
3. Update database credentials in `src/main/resources/application.properties`.
4. Run the app:

```bash
mvn spring-boot:run
```

Default server: http://localhost:8080

Next steps: implement controllers, Thymeleaf templates, services, and authentication.
