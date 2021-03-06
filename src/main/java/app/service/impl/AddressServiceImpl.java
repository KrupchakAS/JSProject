package app.service.impl;

import app.dao.api.AddressDao;
import app.dto.AddressDTO;
import app.entity.Address;
import app.exception.MinLengthFieldException;
import app.service.api.AddressService;


import org.apache.log4j.Logger;
import org.modelmapper.ModelMapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class AddressServiceImpl implements AddressService {

    private static final Logger logger = Logger.getLogger(AddressServiceImpl.class);

    @Autowired
    private AddressDao addressDao;

    @Autowired
    private ModelMapper modelMapper;

    @Transactional(propagation = Propagation.REQUIRED)
    @Override
    public AddressDTO create(AddressDTO addressDTO) {
        if (addressDTO != null) {
            Address address = modelMapper.map(addressDTO, Address.class);
            addressDao.create(address);
            addressDTO.setId(address.getId());
            logger.info("Successfully saved address");
        }
        return addressDTO;
    }

    @Transactional(propagation = Propagation.REQUIRED)
    @Override
    public void update(AddressDTO addressDTO) {
        Address address = addressDao.getById(addressDTO.getId());
        if (address != null)
            addressDao.update(modelMapper.map(addressDTO, Address.class));
        logger.info("Successfully updated address");
    }

    @Transactional(propagation = Propagation.REQUIRED)
    @Override
    public void delete(AddressDTO addressDTO) {
        if (addressDTO != null)
            addressDao.delete(modelMapper.map(addressDTO, Address.class));
        logger.info("Successfully deleted address");
    }

    @Transactional(readOnly = true)
    @Override
    public AddressDTO getById(Integer id) {
        Address address = addressDao.getById(id);
        if (address != null) {
            return modelMapper.map(address, AddressDTO.class);
        } else {
            return null;
        }
    }

    @Transactional(readOnly = true)
    @Override
    public List<AddressDTO> getAll() {
        List<Address> addressList = addressDao.getAll();
        if (addressList != null) {
            return addressList.stream().map(address -> modelMapper.map(address, AddressDTO.class)).collect(Collectors.toList());
        }
        return null;
    }

    @Transactional(readOnly = true)
    @Override
    public List<AddressDTO> getAddressesByUserId(Integer id) {
        List<Address> addressList = addressDao.getAddressesByUserId(id);
        if (addressList != null) {
            return addressList.stream().map(address -> modelMapper.map(address, AddressDTO.class)).collect(Collectors.toList());
        }
        return null;
    }

    @Override
    public void checkAddressFields(AddressDTO addressDTO) {
        if (addressDTO.getCity().length() < 2) {
            throw new MinLengthFieldException("Field City can not be less 2 characters");
        }
        if (addressDTO.getStreet().length() < 2) {
            throw new MinLengthFieldException("Field Street can not be less 2 characters");
        }
        if (addressDTO.getPostCode().length() < 4) {
            throw new MinLengthFieldException("Field PostCode can not be less 4 characters");
        }
        if (addressDTO.getHouseNumber() == null) {
            throw new MinLengthFieldException("Field HouseNumber can not be empty");
        }
        if (addressDTO.getApartmentNumber() == null) {
            throw new MinLengthFieldException("Field ApartmentNumber can not be empty");
        }
    }
}
