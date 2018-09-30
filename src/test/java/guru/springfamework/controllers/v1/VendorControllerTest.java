package guru.springfamework.controllers.v1;

import guru.springfamework.api.v1.model.VendorDTO;
import guru.springfamework.api.v1.model.VendorListDTO;
import guru.springfamework.controllers.RestResponseEntityExceptionHandler;
import guru.springfamework.domain.Vendor;
import guru.springfamework.services.VendorService;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.Arrays;

import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasSize;
import static org.junit.Assert.*;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class VendorControllerTest {

    private static final String NAME = "TEST";
    @Mock
    VendorService vendorService;

    @InjectMocks
    VendorController vendorController;

    MockMvc mockMvc;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);

        mockMvc = MockMvcBuilders.standaloneSetup(vendorController)
                .setControllerAdvice(RestResponseEntityExceptionHandler.class)
                .build();
    }

    @Test
    public void getVendors() throws Exception {
        // given
        VendorDTO vendorDTO1 = new VendorDTO();
        vendorDTO1.setId(1L);

        VendorDTO vendorDTO2 = new VendorDTO();
        vendorDTO2.setId(2L);

        //when
        when(vendorService.getVendors()).thenReturn(new VendorListDTO(Arrays.asList(vendorDTO1, vendorDTO2)));

        //then
        mockMvc.perform(get("/api/v1/vendors").contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.vendors", hasSize(2)));
    }

    @Test
    public void getVendorById() throws Exception {
        // given
        VendorDTO vendorDTO1 = new VendorDTO();
        vendorDTO1.setId(1L);
        vendorDTO1.setName(NAME);

        // when
        when(vendorService.getVendorById(1L)).thenReturn(vendorDTO1);

        // then
        mockMvc.perform(get("/api/v1/vendors/1").contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name", equalTo(NAME)));
    }

    @Test
    public void createVendor() {
    }

    @Test
    public void updateVendor() {
    }

    @Test
    public void deleteVendor() {
    }
}