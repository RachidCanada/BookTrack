package com.example.BookTrack.repositories;

import com.example.BookTrack.models.Book;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookRepository extends JpaRepository<Book, Long> {
}
