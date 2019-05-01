# Collection 소개
* 애플리케이션 작성시 객체의 콜렉션을 관리해야 하는 경우가 종종 있음
* 널리 알려져 있는 자료구조(Data Structure)를 바탕으로 객체들을 효율적으로 추가, 삭제, 검색할 수 있도록 java.util 패키지에 컬렉션과 관련된 인터페이스와 클래스를 포함

# Collection 구성
## Iterable
 * 대부분의 코어 인터페이스는 Iterable 인터페이스를 기반으로 함
## Collection
 * 대부분의 코어 인터페이스는 Collection 인터페이스를 기반으로 함
### Set
    * 데이터의 중복을 허용 하지 않음
    * HashSet (정렬 보장 않음) 
        * LinkedHashSet 
    * SortSet (정렬 보장, Comparable 구현)
        * NavigableSet
            * TreeSet
    * EnumSet
### List
    * 순서를 유지하고 저장
    * 데이터의 중복을 허용
    * ArrayList
    * Vector
        * Stack
    * LinkedList (List, Deque)
### Queue
    * BlockingQueue
        * ArrayBlockingQueue
        * DelayQueue
    * PriorityQueue : 우선 순위 큐
    * Deque
        * ArrayDeque : 크기 조절이 가능한 배열 구현
## Map
    * 키와 값의 쌍으로 저장
    * 키는 중복 저장 안 됨
    * SortedMap 
    * TreeMap 
    * HashTable
    * LinkedHashMap
    * HashMap
    * IdentityHashMap
    * WeakHashMap
    * EunmMap