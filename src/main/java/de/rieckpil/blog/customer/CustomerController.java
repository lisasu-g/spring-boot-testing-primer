package de.rieckpil.blog.customer;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

// 测试的主体,本测试类
@RestController
@RequestMapping("/api/customers")
public class CustomerController {

  private final CustomerService customerService;

  public CustomerController(CustomerService customerService) {
    this.customerService = customerService;
  }

  @GetMapping
  public List<Customer> getAll() {
    return customerService.getAllCustomers();
  }

  @GetMapping("/{id}")
  public Customer getById(@PathVariable("id") Long id) {
    return customerService.getCustomerById(id);
  }
}
