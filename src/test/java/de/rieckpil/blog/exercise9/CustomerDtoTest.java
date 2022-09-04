package de.rieckpil.blog.exercise9;

import de.rieckpil.blog.customer.Customer;
import de.rieckpil.blog.customer.CustomerDto;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.json.JsonTest;
import org.springframework.boot.test.json.JacksonTester;
import org.springframework.boot.test.json.JsonContent;

import java.time.ZoneId;
import java.time.ZonedDateTime;

import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;


//Create a data transfer object (e.g., a CustomerDto that we could use instead of
// our Customer JPA Entity to expose information about our customers). Use Jackson
// annotations (e.g., @JsonProperty or @JsonFormat) to modify how Jackson serializes
// the Java object to JSON. Next, use @JsonTest to write a meaningful test for it.

@JsonTest
class CustomerDtoTest {

  @Autowired
  private JacksonTester<CustomerDto> json;

  // 这是一个对json字符串的测试
  @Test
  void shouldSerializeWithCustomAttributeNamesAndDateFormat() throws Exception {
    CustomerDto customerDto = new CustomerDto(
      new Customer("Mike", ZonedDateTime.of(2020, 1, 1, 0, 0, 0, 0, ZoneId.of("Europe/Berlin"))));

    JsonContent<CustomerDto> result = json.write(customerDto);

    // 将这个对象序列换字符串
    // 然后判断这个字符串是否有问题
    assertThat(result).hasJsonPathStringValue("@.member_since");
    assertThat(result).hasJsonPathStringValue("@.customerName");
    assertThat(result).extractingJsonPathStringValue("@.member_since").isEqualTo("01-01 00:00");
  }
}
