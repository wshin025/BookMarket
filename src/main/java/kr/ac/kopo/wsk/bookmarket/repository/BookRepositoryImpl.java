package kr.ac.kopo.wsk.bookmarket.repository;


import kr.ac.kopo.wsk.bookmarket.domain.Book;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.*;

@Repository
public class BookRepositoryImpl implements BookRepository {
    private List<Book> listofBooks = new ArrayList<>();

    public BookRepositoryImpl() {
        Book book1 = new Book();
        book1.setBookId("isbn0001");
        book1.setName("스프링부트완전정복");
        book1.setUnitPrice(BigDecimal.valueOf(35000));
        book1.setAuthor("송미영");
        book1.setDescription("스프링 부트는 스프링을 기반으로 쉽고 빠르게 웹 애플리케이션을 개발할 수 있는 도구이다. 이 책에서는 스프링 부트의 기본 개념을 쉽게 이해하고 다양한 실습 예제로 빠르게 익힐 수 있다. 그리고 단계별 실습을 따라 하다 보면 도서 쇼핑몰 구축 프로젝트를 완성할 수 있다. 개념-실습-프로젝트의 3단계 학습으로 스프링 부트를 제대로 익힌다면 개발 시간을 단축하고 생산성을 높일 수 있는 개발자로 성장할 수 있다.\n" +
                " 책의 일부 내용을 미리 읽어보실 수 있습니다.");
        book1.setPublisher("길벗캠퍼스");
        book1.setCategory("IT교재");
        book1.setUnitPrice(new BigDecimal(1000));
        book1.setReleaseDate("2024/12/31");
        book1.setCondition("신규도서");

        Book book2 = new Book();
        book2.setBookId("isbn0002");
        book2.setName("혼자 공부하는 머신러닝 딥러닝");
        book2.setUnitPrice(BigDecimal.valueOf(25000));
        book2.setAuthor("박해선");
        book2.setDescription("베타리딩 과정을 통해 입문자에게 적절한 난이도, 분량, 학습 요소 등을 고민하고 반영했다. " +
                "어려운 용어와 개념은 한 번 더 풀어 쓰고, 복잡한 설명은 눈에 잘 들어오는 그림으로 풀어 냈다. " +
                "‘혼자 공부해본’ 여러 입문자의 마음과 눈높이가 책 곳곳에 반영된 것이 이 책의 가장 큰 장점이다.");
        book2.setPublisher("한빛아카데미");
        book2.setCategory("IT전문서");
        book2.setUnitPrice(new BigDecimal(1000));
        book2.setReleaseDate("2020/12/21");
        book2.setCondition("신규도서");

        Book book3 = new Book();
        book3.setBookId("isbn0003");
        book3.setName("안드로이드프로그래밍");
        book3.setUnitPrice(BigDecimal.valueOf(34000));
        book3.setAuthor("우재남");
        book3.setDescription("이 책은 대학교나 IT 전문학원의 안드로이드 프로그래밍 과목 수강생을 대상으로 한다. " +
                "기본적인 프로그래밍을 접해본 독자라면 Java 기초부터 시작하여 안드로이드 앱 개발까지 한번에 학습할 수 있도록 구성되어 있다. " +
                "특별히 이번 9판에서는 버전업을 적용하여 JDK 17, Android 14.0(U), Android Studio Hedgehog에서 실습할 수 있다. 또한 연습문제 일부를 변경하였으며, " +
                "13장에 ‘경기도 맛집 찾기 앱 만들기’ 프로젝트를 추가하였다." );
        book3.setPublisher("한빛아카데미");
        book3.setCategory("IT교재");
        book3.setUnitPrice(new BigDecimal(1000));
        book3.setReleaseDate("2024/1/19");
        book3.setCondition("신규도서");

        listofBooks.add(book1);
        listofBooks.add(book2);
        listofBooks.add(book3);
    }


    @Override
    public List<Book> getAllBookList() {
        return listofBooks;
    }

    @Override
    public Book getBookById(String bookId) {
        Book bookinfo = null;
        for (Book book : listofBooks) {
            if (book!= null && book.getBookId()!=null &&book.getBookId().equals(bookId)) {
                bookinfo = book;
                break;
            }
        }
        if (bookinfo==null) {
            throw new IllegalArgumentException("도서번호가"+ bookId + "인 해당 도서를 찾을 수 없습니다.");
        }
        return bookinfo;
    }

    @Override
    public List<Book> getBookListByCategory(String category) {
        List<Book> booksByCategory = new ArrayList<Book>();
        for (int i = 0 ; i < listofBooks.size() ; i++) {
            Book book = listofBooks.get(i);
            if (book.getCategory()!=null &&book.getCategory().equals(category)) {
                booksByCategory.add(book);
            }
        }
        return booksByCategory;
    }

    @Override
    public Set<Book> getBooksByCategory(Map<String, List<String>> filter) {
        Set<Book> booksByPublisher = new HashSet<Book>();
        Set<Book> booksByCategory = new HashSet<Book>();
        Set<String> booksByFilter = filter.keySet();

        if (booksByFilter.contains("publisher")) {
            for (int i = 0; i < filter.get("publisher").size(); i++) {
                String publisherName = filter.get("publisher").get(i);
                for (Book book : listofBooks) {
                    if (publisherName.equals(book.getPublisher())) {
                        booksByPublisher.add(book);
                    }
                }
            }
        }
        if (booksByFilter.contains("category")) {
            for (int i = 0; i < filter.get("category").size(); i++) {
                String categoryName = filter.get("category").get(i);
                List<Book>list = getBookListByCategory(categoryName);
                booksByCategory.addAll(list);
            }
        }

//저장된 요소 중에서 2 Set 의 비교하여 같은 값만 남기고 나머지는 제거하는 역할 (교집합만 남김)
        booksByCategory.retainAll(booksByPublisher);

            return Set.of();
    }

    @Override
    public Set<Book> getBookListByFilter(Map<String, List<String>> filter) {

        return Set.of();
    }
}




