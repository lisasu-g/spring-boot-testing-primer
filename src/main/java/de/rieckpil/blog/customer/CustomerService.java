package de.rieckpil.blog.customer;

import org.springframework.stereotype.Service;

import java.util.List;

// service类
@Service
public class CustomerService {

  private final CustomerRepository customerRepository;

  public CustomerService(CustomerRepository customerRepository) {
    this.customerRepository = customerRepository;
  }

  public List<Customer> getAllCustomers() {
    return customerRepository.findAll();
  }

  public Customer getCustomerById(Long id) {
    return customerRepository
      .findById(id)
      .orElseThrow(CustomerNotFoundException::new);
  }
}
