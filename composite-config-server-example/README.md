## Composite Spring Cloud Config Server with Spring Cloud Bus client service
Spin up the Vault, H2 and RabbitMQ to work with applications within your IDE
```bash
 docker-compose -f docker-compose-infrastructure.yml up
```

Or you can run the full example with 

```bash
 docker-compose -f docker-compose-infrastructure.yml -f docker-compose-full.yml up
```