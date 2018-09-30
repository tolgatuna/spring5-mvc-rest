package guru.springfamework.services;

import guru.springfamework.api.v1.mapper.VendorMapper;
import guru.springfamework.api.v1.model.VendorListDTO;
import guru.springfamework.domain.Vendor;
import guru.springfamework.repositories.VendorRepository;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;

public class VendorServiceTest {

    public static final String VENDOR_1 = "Vendor1";
    public static final String VENDOR_2 = "vendor2";
    @Mock
    VendorRepository vendorRepository;

    VendorMapper vendorMapper;

    VendorService vendorService;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
        vendorMapper = VendorMapper.INSTANCE;
        vendorService = new VendorServiceImpl(vendorRepository, vendorMapper);
    }

    @Test
    public void getVendors() {
        Vendor vendor1 = new Vendor();
        vendor1.setId(1L);
        vendor1.setName(VENDOR_1);

        Vendor vendor2 = new Vendor();
        vendor2.setId(2L);
        vendor2.setName(VENDOR_2);

        List<Vendor> vendors = Arrays.asList(vendor1, vendor2);

        when(vendorRepository.findAll()).thenReturn(vendors);

        VendorListDTO listDTO = vendorService.getVendors();

        assertEquals(2, listDTO.getVendors().size());
    }

    @Test
    public void getVendorById() {
        Vendor vendor1 = new Vendor();
        vendor1.setId(1L);
        vendor1.setName(VENDOR_1);

        when(vendorRepository.findById(1L)).thenReturn(Optional.of(vendor1));

        vendorService.getVendorById(1L);

        assertEquals(vendor1.getId(), Long.valueOf(1L));
        assertEquals(vendor1.getName(), VENDOR_1);
    }

    @Test
    public void getVendorByName() {
        Vendor vendor1 = new Vendor();
        vendor1.setId(1L);
        vendor1.setName(VENDOR_1);

        when(vendorRepository.findByName(VENDOR_1)).thenReturn(Optional.of(vendor1));

        assertEquals(vendor1.getId(), Long.valueOf(1L));
        assertEquals(vendor1.getName(), VENDOR_1);
    }

    @Test
    public void createNewVendor() {
        Vendor vendor1 = new Vendor();
        vendor1.setId(1L);
        vendor1.setName(VENDOR_1);

        when(vendorRepository.save(vendor1)).thenReturn(vendor1);

        assertEquals(vendor1.getId(), Long.valueOf(1L));
        assertEquals(vendor1.getName(), VENDOR_1);
    }

    @Test
    public void deleteVendorById() {

        Long id = 1L;

        vendorService.deleteVendorById(id);

        verify(vendorRepository, times(1)).deleteById(anyLong());
    }
}