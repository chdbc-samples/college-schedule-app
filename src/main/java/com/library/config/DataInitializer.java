package com.library.config;

import com.library.entity.*;
import com.library.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

@Component
public class DataInitializer implements CommandLineRunner {

    @Autowired private ReaderService readerService;
    @Autowired private BookService bookService;
    @Autowired private LibrarianService librarianService;
    @Autowired private ReadingRoomService roomService;
    @Autowired private BorrowingService borrowingService;

    @Override
    @Transactional
    public void run(String... args) {
        Reader reader1 = new Reader("Іваненко Іван Іванович", 25);
        Reader reader2 = new Reader("Петрова Олена Петрівна", 34);
        readerService.save(reader1);
        readerService.save(reader2);

        Book book1 = new Book("Пасічник В.В., Резніченко В.А. Організація баз даних...", 150.0, "004.7");
        Book book2 = new Book("Васильчук І.А. Програмування на Python...", 180.0, "004");
        bookService.save(book1);
        bookService.save(book2);

        Librarian librarian1 = new Librarian("Кравченко Олексій Іванович");
        Librarian librarian2 = new Librarian("Тимошенко Олена Сергіївна");
        librarianService.save(librarian1);
        librarianService.save(librarian2);

        ReadingRoom room1 = new ReadingRoom("Абонементний зал", null, null);
        ReadingRoom room2 = new ReadingRoom("Читальний зал 2", 20, false);
        roomService.save(room1);
        roomService.save(room2);

        Borrowing b1 = new Borrowing(reader1, book1, librarian1, room1,
                LocalDateTime.of(2024, 9, 12, 14, 30),
                LocalDateTime.of(2024, 9, 19, 12, 0));

        Borrowing b2 = new Borrowing(reader2, book1, librarian2, room2,
                LocalDateTime.of(2024, 9, 13, 10, 0),
                LocalDateTime.of(2024, 9, 13, 15, 30));

        borrowingService.save(b1);
        borrowingService.save(b2);
    }
}
