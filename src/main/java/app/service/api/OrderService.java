package app.service.api;

import app.dto.OrderDTO;

import java.util.List;

public interface OrderService {

    void create(OrderDTO orderDTO);

    void update(OrderDTO orderDTO);

    void delete(OrderDTO orderDTO);

    OrderDTO getById(Integer id);

    OrderDTO getByName(String name);

    List<OrderDTO> getAll();
}
