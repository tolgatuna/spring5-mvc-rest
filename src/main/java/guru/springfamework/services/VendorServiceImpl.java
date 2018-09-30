package guru.springfamework.services;

import guru.springfamework.api.v1.mapper.VendorMapper;
import guru.springfamework.api.v1.model.VendorDTO;
import guru.springfamework.api.v1.model.VendorListDTO;
import guru.springfamework.domain.Vendor;
import guru.springfamework.repositories.VendorRepository;
import org.springframework.stereotype.Service;

import java.util.stream.Collectors;

@Service
public class VendorServiceImpl implements VendorService {

    private final VendorRepository vendorRepository;
    private final VendorMapper vendorMapper;

    public VendorServiceImpl(VendorRepository vendorRepository, VendorMapper vendorMapper) {
        this.vendorRepository = vendorRepository;
        this.vendorMapper = vendorMapper;
    }

    @Override
    public VendorListDTO getVendors() {
        return new VendorListDTO(
                vendorRepository.findAll().stream().map(vendorMapper::vendorToVendorDTO).collect(Collectors.toList())
        );
    }

    @Override
    public VendorDTO getVendorById(Long id) {
        return vendorRepository.findById(id)
                .map(vendorMapper::vendorToVendorDTO)
                .orElseThrow(ResourceNotFoundException::new);
    }

    @Override
    public VendorDTO getVendorByName(String name) {
        return vendorRepository.findByName(name)
                .map(vendorMapper::vendorToVendorDTO)
                .orElseThrow(ResourceNotFoundException::new);
    }

    @Override
    public VendorDTO createNewVendor(VendorDTO vendorDTO) {
        return vendorMapper.vendorToVendorDTO(
                vendorRepository.save(vendorMapper.vendorDTOToVendor(vendorDTO))
        );
    }

    @Override
    public VendorDTO updateVendorById(Long id, VendorDTO vendorDTO) {
        return vendorMapper.vendorToVendorDTO(
                vendorRepository.save(vendorMapper.vendorDTOToVendor(vendorDTO))
        );
    }

    @Override
    public void deleteVendorById(Long id) {
        vendorRepository.deleteById(id);
    }
}
