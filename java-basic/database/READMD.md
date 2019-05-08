# Database 소개

### JDBC 작업 일반적 순서
* Database Connection
* Statement (PreparedStatement)
* Execute
* ResultSet
* Connection, Statement, ResultSet Close
* Exception

### Dao 리팩토링
* Connection 중복
    * Method
    * Class
    * Template Method
    * Factory (IoC)

### Isolation Level

| Isolation Level  | Transaction | Dirty Reads  | Non-Repeatable Reads  |  Phantom Reads  |
|---|---|---|---|---|
| TRANSACTION_NONE | Not supported | Not applicable | Not applicable | Not applicable |
| TRANSACTION_READ_COMMITTED | Supported | Prevented | Allowed | Allowed |
| TRANSACTION_READ_UNCOMMITTED | Supported | Allowed | Allowed | Allowed |
| TRANSACTION_REPEATABLE_READ | Supported | Prevented | Prevented | Allowed |
| TRANSACTION_SERIALIZABLE | Supported | Prevented | Prevented | Prevented |

* PROPAGATION_REQUIRED (0) : Support a current transaction; create a new one if none exists. Analogous to the EJB transaction attribute of the same name.
* PROPAGATION_SUPPORTS (1) : Support a current transaction; execute non-transactionally if none exists. Analogous to the EJB transaction attribute of the same name.
* PROPAGATION_MANDATORY (2) : Support a current transaction; throw an exception if no current transaction exists.
* PROPAGATION_REQUIRES_NEW (3) : Create a new transaction, suspending the current transaction if one exists.
* PROPAGATION_NOT_SUPPORTED (4) : Do not support a current transaction; rather always execute non-transactionally.
* PROPAGATION_NEVER (5) : Do not support a current transaction; throw an exception if a current transaction exists.
* PROPAGATION_NESTED (6) : Execute within a nested transaction if a current transaction exists, behave like PROPAGATION_REQUIRED otherwise.

* https://docs.oracle.com/javase/tutorial/jdbc/basics/transactions.html
* https://en.wikipedia.org/wiki/Isolation_(database_systems)
* https://docs.spring.io/spring/docs/current/javadoc-api/org/springframework/transaction/TransactionDefinition.html
* https://docs.spring.io/spring/docs/5.1.6.RELEASE/spring-framework-reference/data-access.html#tx-propagation