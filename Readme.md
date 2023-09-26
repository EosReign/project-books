Итак. Данное приложение является пробой верстания rest приложения на основе сборщика gradle. 
Что из себя представляет данное приложение, возможно, спросите вы? 
А я вам скажу, что сие творение является реализацией приложения мониторинга заёмов *на время* книг для клиентов, в котором имеется база данных по хранению всех транзакций заёмов и возврата книг, книг и клиентов. 
***Develop***
На данный момент выполнены работы: 
-Созданы:
  -Controllers: TransactionController, BookController, ClientController;
  -DTOs: BookDTO, BooksDTO, ClientDTO, ClientsDTO, TransactionDTO, TransactionsDTO;
  -Entities: Book, Client, Transaction;
  -Mappers: Book(s)Mapper, Client(s)Mapper, Transaction(s)Mapper;
  -Repositories: BookRepository, ClientRepository, TransactionRepository;
  -Services: BookService, BookServiceImpl, ClientService, ClientServiceImpl, TransactionService, TransactionServiceImpl;
  -Dockerfile, docker-compose;
  -Liquibase миграции таблиц в DB;
-Возможные правки:
  -TransactionDTO возможно необходимо заполнение сущностями в виде book_name, client_name;
  -Подправить реализацию выдачи листа транзакций/клиентов/книг не в полном объёме, а частями;
-Необходимо сделать:
  -Реализовать unit-тесты;
  -Подключить Spring Security;
  -Реализовать логирование через AOP;
  
  
  
  

