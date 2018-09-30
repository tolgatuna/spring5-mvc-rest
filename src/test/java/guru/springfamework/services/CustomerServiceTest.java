package guru.springfamework.services;

import guru.springfamework.api.v1.mapper.CustomerMapper;
import guru.springfamework.api.v1.model.CustomerDTO;
import guru.springfamework.domain.Customer;
import guru.springfamework.repositories.CustomerRepository;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

public class CustomerServiceTest {

    @Mock
    CustomerRepository customerRepository;

    CustomerService customerService;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);

        customerService = new CustomerServiceImpl(CustomerMapper.INSTANCE, customerRepository);
    }

    @Test
    public void testGetAllCustomers() {
        Customer customer1 = new Customer();
        customer1.setId(1L);
        customer1.setFirstName("Customer 1");
        customer1.setLastName("Customer 1 - L");

        Customer customer2 = new Customer();
        customer2.setId(2L);
        customer2.setFirstName("Customer 2");
        customer2.setLastName("Customer 2 - L");

        List<Customer> customers = new ArrayList<>();
        customers.add(customer1);
        customers.add(customer2);

        when(customerRepository.findAll()).thenReturn(customers);

        assertEquals(customerService.getAllCustomers().getCustomers().size(), 2);

    }

    @Test
    public void testGetCustomerByFirstNameAndLastName() {
        // given
        Customer customer = new Customer();
        customer.setId(1L);
        customer.setFirstName("Customer");
        customer.setLastName("Customer Last");

        //when
        when(customerRepository.findCustomerByFirstNameAndLastName(anyString(), anyString())).thenReturn(customer);

        // then
        CustomerDTO customerDTO = customerService.getCustomerByFirstNameAndLastName("Customer", "Customer Last");
        assertNotNull(customerDTO);
        assertEquals(customerDTO.getId(), customer.getId());
        assertEquals(customerDTO.getLastName(), customer.getLastName());

    }
}
