## Composite (Vault/JDBC/Local Files) Spring Cloud Config Server with Spring Cloud Bus and client service
Spin up the Vault, H2 and RabbitMQ to work with applications within your IDE
```bash
 docker-compose -f docker-compose-infrastructure.yml up
```

Or you can run the full example with 

```bash
 docker-compose -f docker-compose-infrastructure.yml -f docker-compose-apps.yml up
```

Open [http://localhost:8080/info](http://localhost:8080/info) to see the current value of the setting

Change settings:
  - Play with Vault parameters: [http://localhost:8200/ui/vault/auth](http://localhost:8200/ui/vault/auth) token is vault_root
  - Change parameters via H2 console: [http://localhost:81/](http://localhost:81/)
      ```sql
      UPDATE properties
      SET value = 'someValueDatabaseV12'
      WHERE application = 'CompositeConfigurableService'
        AND key = 'someSetting';
      ```
Priorities are the next:
  - Vault
  - JDBC
  - Files

Refresh application configuration:
```bash
curl -X POST http://localhost:8080/actuator/refresh
```

Refresh all applications:
```bash
curl -X POST http://localhost:8080/actuator/bus-refresh
```

[![How to run the example locally](http://img.youtube.com/vi/EI0pt8GJ0cY/0.jpg)](http://www.youtube.com/watch?v=EI0pt8GJ0cY)