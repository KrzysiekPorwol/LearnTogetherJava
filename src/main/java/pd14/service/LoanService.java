package pd14.service;

import pd14.dto.CreateLoanRequest;
import pd14.dto.LoanDto;
import pd14.dto.ReturnBookRequest;

import java.util.List;

public interface LoanService {

  LoanDto borrow(CreateLoanRequest request);

  LoanDto returnBook(ReturnBookRequest request);

  List<LoanDto> findOverdue();
}
