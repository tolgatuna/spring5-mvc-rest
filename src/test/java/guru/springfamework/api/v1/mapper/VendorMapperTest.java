package guru.springfamework.api.v1.mapper;

import guru.springfamework.api.v1.model.VendorDTO;
import guru.springfamework.domain.Vendor;
import org.junit.Test;

import static org.junit.Assert.*;

public class VendorMapperTest {

    VendorMapper vendorMapper = VendorMapper.INSTANCE;
    public static final String TEST_VENDOR = "TEST VENDOR";

    @Test
    public void vendorToVendorDTO() {
        Vendor vendor = new Vendor();
        vendor.setId(1L);
        vendor.setName(TEST_VENDOR);
        VendorDTO vendorDTO = vendorMapper.vendorToVendorDTO(vendor);

        assertEquals(vendorDTO.getName(), TEST_VENDOR);
        assertEquals(vendorDTO.getId(), vendor.getId());
    }

    @Test
    public void vendorToVendorDTO1() {
        VendorDTO vendorDTO = new VendorDTO();
        vendorDTO.setId(2L);
        vendorDTO.setName(TEST_VENDOR);

        Vendor vendor = vendorMapper.vendorDTOToVendor(vendorDTO);
        assertEquals(vendor.getName(), TEST_VENDOR);
        assertEquals(vendor.getId(), vendorDTO.getId());

    }
}