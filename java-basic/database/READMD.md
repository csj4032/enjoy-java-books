# Database 소개
### Isolation Level

| Isolation Level  | Transaction | Dirty Reads  | Non-Repeatable Reads  |  Phantom Reads  |
|---|---|---|---|---|
| TRANSACTION_NONE | Not supported | Not applicable | Not applicable | Not applicable |
| TRANSACTION_READ_COMMITTED | Supported | Prevented | Allowed | Allowed |
| TRANSACTION_READ_UNCOMMITTED | Supported | Allowed | Allowed | Allowed |
| TRANSACTION_REPEATABLE_READ | Supported | Prevented | Prevented | Allowed |
| TRANSACTION_SERIALIZABLE | Supported | Prevented | Prevented | Prevented |

* PROPAGATION_REQUIRED (0) : 
* PROPAGATION_SUPPORTS (1) :
* PROPAGATION_MANDATORY (2) :
* PROPAGATION_REQUIRES_NEW (3) : 
* PROPAGATION_NOT_SUPPORTED (4) : 
* PROPAGATION_NEVER (5) : 
* PROPAGATION_NESTED (6) :
* ISOLATION_DEFAULT (-1) :

* https://docs.oracle.com/javase/tutorial/jdbc/basics/transactions.html
* https://en.wikipedia.org/wiki/Isolation_(database_systems)
* https://docs.spring.io/spring/docs/5.1.6.RELEASE/spring-framework-reference/data-access.html#tx-propagation