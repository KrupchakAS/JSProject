package app.service.impl;


import app.dao.api.DoughDao;
import app.dto.DoughDTO;
import app.entity.Dough;
import app.exception.ObjectExistsException;
import app.service.api.DoughService;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class DoughServiceImpl implements DoughService {

    private static final Logger logger = LoggerFactory.getLogger(DoughServiceImpl.class);

    @Autowired
    private DoughDao doughDao;

    @Autowired
    private ModelMapper modelMapper;

    @Transactional(propagation = Propagation.REQUIRED)
    @Override
    public DoughDTO create(DoughDTO doughDTO) {
        if (doughDTO != null) {
            Dough existDough = doughDao.getByName(doughDTO.getName());
            if (existDough != null) {
                throw new ObjectExistsException(String.format("Dough with name %s already exists", doughDTO.getName()));
            }
            Dough dough = modelMapper.map(doughDTO, Dough.class);
            doughDao.create(dough);
            doughDTO.setId(dough.getId());
        }
        logger.debug(String.format("Successfully saved dough"));
        return doughDTO;
    }

    @Transactional(propagation = Propagation.REQUIRED)
    @Override
    public void update(DoughDTO doughDTO) {
        Dough corn = doughDao.getById(doughDTO.getId());
        if (corn != null)
            doughDao.update(modelMapper.map(doughDTO, Dough.class));
        logger.debug(String.format("Successfully updated dough"));
    }

    @Transactional(propagation = Propagation.REQUIRED)
    @Override
    public void delete(DoughDTO doughDTO) {
        if (doughDTO != null)
            doughDao.delete(modelMapper.map(doughDTO, Dough.class));
        logger.debug(String.format("Successfully deleted dough"));
    }

    @Transactional(readOnly = true)
    @Override
    public DoughDTO getById(Integer id) {
        Dough dough = doughDao.getById(id);
        if (dough != null) {
            return modelMapper.map(dough, DoughDTO.class);
        } else {
            return null;
        }
    }

    @Transactional(readOnly = true)
    @Override
    public DoughDTO getByName(String name) {
        Dough dough = doughDao.getByName(name);
        if (dough != null) {
            return modelMapper.map(dough, DoughDTO.class);
        } else {
            return null;
        }
    }

    @Transactional(readOnly = true)
    @Override
    public List<DoughDTO> getAll() {
        List<Dough> cornList = doughDao.getAll();
        if (cornList != null) {
            return cornList.stream().map(corn -> modelMapper.map(corn, DoughDTO.class)).collect(Collectors.toList());
        } else {
            return null;
        }
    }

    @Transactional(readOnly = true)
    @Override
    public DoughDTO getLastDough() {
        List<Dough> doughList = doughDao.getAll();
        DoughDTO doughDTO = modelMapper.map(doughList.get(doughList.size() - 1), DoughDTO.class);
        if (doughDTO != null) {
            return doughDTO;
        } else {
            return null;
        }
    }
}
