package pd14.service;

import pd14.dto.CreateLoanCommand;
import pd14.dto.LoanDto;
import pd14.dto.ReturnBookCommand;

import java.util.List;

public interface LoanService {

  LoanDto borrow(CreateLoanCommand request);

  LoanDto returnBook(ReturnBookCommand request);

  List<LoanDto> findOverdue();
}
