#### Clean Architecture
- Domain defines all domain models, UserGateway is for major logic data logics.
  major for how to persistent data to data repository
- Persistent keeps all data repository implementations, models and configuration
- UseCases keep all use cases in this case, just create user and load all users
- Web all web components, such as controller, RPC service, and others
- Application all common services such as IDGenerator, EventSourceServices...
- Kafka used to output event source 

In this case, we are using MongoDB as persistent data source
