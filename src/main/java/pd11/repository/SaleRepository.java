package pd11.repository;

import pd11.model.Sale;

import java.util.List;

public class SaleRepository {

  private final List<Sale> sales;

  public SaleRepository(List<Sale> sales) {
    this.sales = List.copyOf(sales);
  }

  public List<Sale> findAll() {
    return sales;
  }
}
