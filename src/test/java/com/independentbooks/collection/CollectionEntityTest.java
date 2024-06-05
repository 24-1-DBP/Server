package com.independentbooks.collection;

/*
@SpringBootTest
@Transactional
public class CollectionEntityTest {

    @Autowired
    EntityManager em;

    @Autowired
    CollectionService collectionService;

    @Autowired
    CollectionRepository collectionRepository;


    @Test
    void collectionCreateTest() {
        User user = new User("userA", "testUser");
        em.persist(user);

        Content content = new Content();
        content.setContentType(ContentType.COLLECTION);
        em.persist(content);

        Book book = new Book("JPA", "kim", "kim_pub", "ISBN_Test", "Info_test", 3000L, LocalDateTime.of(2024, 6, 4, 21, 15, 30), "이미지");
        em.persist(book);

        List<Book> books = new ArrayList<>();
        books.add(book);

        Collection collection = Collection.createCollection(user, content, "테스트 컬렉션", books);
        em.persist(collection);

        List<Collection> collections = collectionService.findAll();
        System.out.println(collections.size());
        System.out.println(collections.get(0).getDescription());
        System.out.println(collections.get(0).getId());

    }
}*/