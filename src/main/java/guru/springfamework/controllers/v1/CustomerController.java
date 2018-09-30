package guru.springfamework.controllers.v1;

import guru.springfamework.api.v1.model.CustomerDTO;
import guru.springfamework.api.v1.model.CustomerListDTO;
import guru.springfamework.services.CustomerService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Service
@RequestMapping("/api/v1/customers/")
public class CustomerController {
    CustomerService customerService;

    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    @GetMapping
    public ResponseEntity<CustomerListDTO> getCustomers() {
        return new ResponseEntity<>(
                customerService.getAllCustomers(),
                HttpStatus.OK
        );
    }

    @GetMapping("{firstName}/{lastName}")
    public ResponseEntity<CustomerDTO> getCustomerByNameAndLastName(@PathVariable("firstName") String firstName, @PathVariable("lastName") String lastName) {
        return new ResponseEntity<>(
                customerService.getCustomerByFirstNameAndLastName(firstName, lastName),
                HttpStatus.OK
        );
    }
}
