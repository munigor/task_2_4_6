
# Installation

> Copy file from `src/main/resources/db.properties.example` to `src/main/resources/db.properties`, and change all setting for you own

```
db.driver=com.mysql.cj.jdbc.Driver
db.url=jdbc:mysql://localhost:3306/database
db.username=root
db.password=root
```

## Requirements

Web server [Apache Tomcat 10.1.49](https://tomcat.apache.org/download-10.cgi). Spring plugins was changed for latest stable and all internal dependencies too. Spring MVC plugin required Jakarta Servlet API which work correct with Tomcat 10. 