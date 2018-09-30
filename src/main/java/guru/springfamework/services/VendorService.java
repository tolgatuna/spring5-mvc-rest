package guru.springfamework.services;

import guru.springfamework.api.v1.model.VendorDTO;
import guru.springfamework.api.v1.model.VendorListDTO;

public interface VendorService {

    VendorListDTO getVendors();

    VendorDTO getVendorById(Long id);

    VendorDTO getVendorByName(String name);

    VendorDTO createNewVendor(VendorDTO vendorDTO);

    VendorDTO updateVendorById(Long id, VendorDTO vendorDTO);

    void deleteVendorById(Long id);
}
