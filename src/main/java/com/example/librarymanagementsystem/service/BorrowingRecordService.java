package com.example.librarymanagementsystem.service;

import com.example.librarymanagementsystem.model.Book;
import com.example.librarymanagementsystem.model.BorrowingRecord;
import com.example.librarymanagementsystem.model.Patron;
import com.example.librarymanagementsystem.repository.BorrowingRecordRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class BorrowingRecordService {
    @Autowired
    private BorrowingRecordRepository borrowingRecordRepository;

    public List<BorrowingRecord> getAllBorrowingRecords() {
        return borrowingRecordRepository.findAll();
    }

    public Optional<BorrowingRecord> getBorrowingRecordById(Long id) {
        return borrowingRecordRepository.findById(id);
    }

    public BorrowingRecord addBorrowingRecord(Book book, Patron patron) {
        BorrowingRecord borrowingRecord = new BorrowingRecord(book, patron, LocalDate.now(), null);
        return borrowingRecordRepository.save(borrowingRecord);
    }

    public BorrowingRecord returnBook(Long bookId, Long patronId) {
        Optional<BorrowingRecord> recordOptional = borrowingRecordRepository.findAll()
                .stream()
                .filter(record -> record.getBook().getId().equals(bookId) && record.getPatron().getId().equals(patronId)
                        && record.getReturnDate() == null)
                .findFirst();
        if (recordOptional.isPresent()) {
            BorrowingRecord record = recordOptional.get();
            record.setReturnDate(LocalDate.now());
            return borrowingRecordRepository.save(record);
        }
        return null;
    }
}
