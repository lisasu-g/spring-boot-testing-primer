package de.rieckpil.blog.customer;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.time.ZonedDateTime;

public class CustomerDto {

  @JsonProperty("customerName")
  private final String name;

  @JsonProperty("member_since")
  @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "MM-dd HH:mm")
  private final ZonedDateTime joinedAt;

  public CustomerDto(Customer customer) {
    this.name = customer.getName();
    this.joinedAt = customer.getJoinedAt();
  }

  public String getName() {
    return name;
  }

  public ZonedDateTime getJoinedAt() {
    return joinedAt;
  }
}
