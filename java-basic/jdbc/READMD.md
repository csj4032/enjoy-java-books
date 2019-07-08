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

#### 순서
1. JDBC를 이용한 등록과 조회
2. 관심사분리 (중복 코드 메소드 추출 Connection)
3. 상속을 통한 DAO 확장
4. Connection 클래스 분리
5. Connection 인터페이스 도입
6. Object Factory 활용

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

### ACID
* 원자성(Atomicity)은 트랜잭션과 관련된 작업들이 부분적으로 실행되다가 중단되지 않는 것을 보장하는 능력이다. 예를 들어, 자금 이체는 성공할 수도 실패할 수도 있지만 보내는 쪽에서 돈을 빼 오는 작업만 성공하고 받는 쪽에 돈을 넣는 작업을 실패해서는 안된다. 원자성은 이와 같이 중간 단계까지 실행되고 실패하는 일이 없도록 하는 것이다.
* 일관성(Consistency)은 트랜잭션이 실행을 성공적으로 완료하면 언제나 일관성 있는 데이터베이스 상태로 유지하는 것을 의미한다. 무결성 제약이 모든 계좌는 잔고가 있어야 한다면 이를 위반하는 트랜잭션은 중단된다.
* 고립성(Isolation)은 트랜잭션을 수행 시 다른 트랜잭션의 연산 작업이 끼어들지 못하도록 보장하는 것을 의미한다. 이것은 트랜잭션 밖에 있는 어떤 연산도 중간 단계의 데이터를 볼 수 없음을 의미한다. 은행 관리자는 이체 작업을 하는 도중에 쿼리를 실행하더라도 특정 계좌간 이체하는 양 쪽을 볼 수 없다. 공식적으로 고립성은 트랜잭션 실행내역은 연속적이어야 함을 의미한다. 성능관련 이유로 인해 이 특성은 가장 유연성 있는 제약 조건이다. 자세한 내용은 관련 문서를 참조해야 한다.
* 지속성(Durability)은 성공적으로 수행된 트랜잭션은 영원히 반영되어야 함을 의미한다. 시스템 문제, DB 일관성 체크 등을 하더라도 유지되어야 함을 의미한다. 전형적으로 모든 트랜잭션은 로그로 남고 시스템 장애 발생 전 상태로 되돌릴 수 있다. 트랜잭션은 로그에 모든 것이 저장된 후에만 commit 상태로 간주될 수 있다.

* https://docs.oracle.com/javase/tutorial/jdbc/basics/transactions.html
* https://en.wikipedia.org/wiki/Isolation_(database_systems)
* https://docs.spring.io/spring/docs/current/javadoc-api/org/springframework/transaction/TransactionDefinition.html
* https://docs.spring.io/spring/docs/5.1.6.RELEASE/spring-framework-reference/data-access.html#tx-propagation