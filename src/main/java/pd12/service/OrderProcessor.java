package pd12.service;

import pd12.model.Order;
import pd12.model.OrderResult;
import pd12.model.ProcessingReport;
import pd12.model.Store;

import java.util.List;
import java.util.concurrent.CompletableFuture;

public class OrderProcessor {

  public ProcessingReport process(List<Order> orders, Store store) {
    List<CompletableFuture<OrderResult>> futures = orders.stream()
        .map(order -> CompletableFuture
            .supplyAsync(() -> store.tryBuy(order.quantity()))
            .thenApply(accepted -> new OrderResult(order, accepted)))
        .toList();

    List<OrderResult> results = futures.stream()
        .map(CompletableFuture::join)
        .toList();

    int soldQuantity = results.stream()
        .filter(OrderResult::accepted)
        .mapToInt(result -> result.order().quantity())
        .sum();

    return new ProcessingReport(results, soldQuantity, store.getAvailableQuantity());
  }
}
