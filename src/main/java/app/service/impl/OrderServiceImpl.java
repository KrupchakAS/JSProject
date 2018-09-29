package app.service.impl;

import app.dao.api.OrderDao;
import app.dto.AddressDTO;
import app.dto.OrderDTO;
import app.dto.ProductDTO;
import app.dto.UserDTO;
import app.entity.Address;
import app.entity.Order;
import app.entity.Product;
import app.entity.User;
import app.entity.enums.Converter.OrderStatusConverter;
import app.entity.enums.Converter.PaymentStatusConverter;
import app.service.api.OrderService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import app.entity.enums.Converter.DeliveryOptionConverter;
import app.entity.enums.Converter.PaymentOptionConverter;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class OrderServiceImpl implements OrderService {

    private static final Logger logger = LogManager.getLogger(OrderServiceImpl.class);

    @Autowired
    private OrderDao orderDao;

    @Autowired
    private ModelMapper modelMapper;

    @Transactional(propagation = Propagation.REQUIRED)
    @Override
    public void create(OrderDTO orderDTO) {
        if (orderDTO != null) {
            orderDTO.setPurchaseDate(new Date());

            Order order = new Order();
            order.setTotalPrice(orderDTO.getTotalPrice());
            List<Product> productList = orderDTO.getProductList().stream().map(productDTO -> modelMapper.map(productDTO, Product.class)).collect(Collectors.toList());
            order.setProductList(productList);
            if (orderDTO.getAddress() != null) {
                order.setAddress(modelMapper.map(orderDTO.getAddress(), Address.class));
            }
            order.setUser(modelMapper.map(orderDTO.getUserDTO(), User.class));
            order.setPurchaseDate(orderDTO.getPurchaseDate());
            order.setDeliveryOption(new DeliveryOptionConverter().convertToDatabaseColumn(orderDTO.getDeliveryOption()));
            order.setPaymentOption(new PaymentOptionConverter().convertToDatabaseColumn(orderDTO.getPaymentOption()));
            order.setOrderStatus((byte) 1);
            if (new PaymentOptionConverter().convertToDatabaseColumn(orderDTO.getPaymentOption()) == 1) {
                order.setPaymentStatus((byte) 1);
            } else {
                order.setPaymentStatus((byte) 2);
            }
            orderDao.create(order);
        }
        logger.info("Successfully saved order");
    }

    @Transactional(propagation = Propagation.REQUIRED)
    @Override
    public void updateStatuses(OrderDTO orderDTO) {
        Order order = orderDao.getById(orderDTO.getId());
        if (order != null) {
            order.setDeliveryOption(new DeliveryOptionConverter().convertToDatabaseColumn(orderDTO.getDeliveryOption()));
            order.setPaymentOption(new PaymentOptionConverter().convertToDatabaseColumn(orderDTO.getPaymentOption()));
            order.setOrderStatus(new OrderStatusConverter().convertToDatabaseColumn(orderDTO.getOrderStatus()));
            order.setPaymentStatus(new PaymentStatusConverter().convertToDatabaseColumn(orderDTO.getPaymentStatus()));
            orderDao.update(order);
        }
        logger.info("Successfully updated order");
    }

    @Transactional(propagation = Propagation.REQUIRED)
    @Override
    public void update(OrderDTO orderDTO) {
        Order order = orderDao.getById(orderDTO.getId());
        if (order != null) {
            order.setTotalPrice(orderDTO.getTotalPrice());
            List<Product> productList = orderDTO.getProductList().stream().map(productDTO -> modelMapper.map(productDTO, Product.class)).collect(Collectors.toList());
            order.setProductList(productList);
            order.setPurchaseDate(orderDTO.getPurchaseDate());
            if(orderDTO.getAddress() != null) {
                order.setAddress(modelMapper.map(orderDTO.getAddress(), Address.class));
            }
            order.setUser(modelMapper.map(orderDTO.getUserDTO(), User.class));
            order.setDeliveryOption(new DeliveryOptionConverter().convertToDatabaseColumn(orderDTO.getDeliveryOption()));
            order.setPaymentOption(new PaymentOptionConverter().convertToDatabaseColumn(orderDTO.getPaymentOption()));
            order.setOrderStatus((byte) 1);
            order.setPaymentStatus((byte) 1);
            if (new PaymentOptionConverter().convertToDatabaseColumn(orderDTO.getPaymentOption()) == 1) {
                order.setPaymentStatus((byte) 1);
            } else {
                order.setPaymentStatus((byte) 2);
            }
            orderDao.update(order);
        }
        logger.info("Successfully updated order");
    }

    @Transactional(propagation = Propagation.REQUIRED)
    @Override
    public void delete(OrderDTO orderDTO) {
        if (orderDTO != null)
            orderDao.delete(modelMapper.map(orderDTO, Order.class));
        logger.info("Successfully deleted order");
    }

    @Transactional(readOnly = true)
    @Override
    public OrderDTO getById(Integer id) {
        Order order = orderDao.getById(id);
        OrderDTO orderDTO = new OrderDTO();
        if(order.getAddress()!= null) {
            orderDTO.setAddress(modelMapper.map(order.getAddress(), AddressDTO.class));
        }
        orderDTO.setId(order.getId());
        orderDTO.setPurchaseDate(order.getPurchaseDate());
        orderDTO.setTotalPrice(order.getTotalPrice());
        orderDTO.setProductList(order.getProductList().stream().map(product -> modelMapper.map(product, ProductDTO.class)).collect(Collectors.toList()));
        orderDTO.setUserDTO(modelMapper.map(order.getUser(), UserDTO.class));
        orderDTO.setPaymentOption(new PaymentOptionConverter().convertToEntityAttribute(order.getPaymentOption()));
        orderDTO.setDeliveryOption(new DeliveryOptionConverter().convertToEntityAttribute(order.getDeliveryOption()));
        orderDTO.setOrderStatus(new OrderStatusConverter().convertToEntityAttribute(order.getOrderStatus()));
        orderDTO.setPaymentStatus(new PaymentStatusConverter().convertToEntityAttribute(order.getPaymentStatus()));
        return orderDTO;
    }

    @Transactional(readOnly = true)
    @Override
    public List<OrderDTO> getAll() {
        List<Order> orderList = orderDao.getAll();
        List<OrderDTO> orderDTOList = new ArrayList<>();
        if (orderList != null) {
            for (int i = 0; i < orderList.size(); i++) {
                OrderDTO orderDTO = new OrderDTO();

                orderDTO.setPaymentOption(new PaymentOptionConverter().convertToEntityAttribute(orderList.get(i).getPaymentOption()));
                orderDTO.setDeliveryOption(new DeliveryOptionConverter().convertToEntityAttribute(orderList.get(i).getDeliveryOption()));
                orderDTO.setOrderStatus(new OrderStatusConverter().convertToEntityAttribute(orderList.get(i).getOrderStatus()));
                orderDTO.setPaymentStatus(new PaymentStatusConverter().convertToEntityAttribute(orderList.get(i).getPaymentStatus()));

                orderDTO.setId(orderList.get(i).getId());

                List<ProductDTO> productList = orderList.get(i).getProductList().stream().map(product -> modelMapper.map(product, ProductDTO.class)).collect(Collectors.toList());
                orderDTO.setProductList(productList);

                orderDTO.setPurchaseDate(orderList.get(i).getPurchaseDate());
                orderDTO.setTotalPrice(orderList.get(i).getTotalPrice());
                if (orderList.get(i).getAddress() != null) {
                    AddressDTO addressDTO = modelMapper.map(orderList.get(i).getAddress(), AddressDTO.class);
                    orderDTO.setAddress(addressDTO);
                }
                UserDTO userDTO = modelMapper.map(orderList.get(i).getUser(), UserDTO.class);
                orderDTO.setUserDTO(userDTO);
                orderDTOList.add(orderDTO);
            }
            return orderDTOList;
        } else {
            return null;
        }
    }

    @Transactional(readOnly = true)
    @Override
    public List<OrderDTO> getOrdersByUserId(Integer userId) {
        List<Order> orderList = orderDao.getOrdersByUserId(userId);
        List<OrderDTO> orderDTOList = new ArrayList<>();
        if (orderList != null) {
            for (int i = 0; i < orderList.size(); i++) {
                OrderDTO orderDTO = new OrderDTO();
                orderDTO.setId(orderList.get(i).getId());

                orderDTO.setPaymentOption(new PaymentOptionConverter().convertToEntityAttribute(orderList.get(i).getPaymentStatus()));
                orderDTO.setDeliveryOption(new DeliveryOptionConverter().convertToEntityAttribute(orderList.get(i).getDeliveryOption()));
                orderDTO.setOrderStatus(new OrderStatusConverter().convertToEntityAttribute(orderList.get(i).getOrderStatus()));
                orderDTO.setPaymentStatus(new PaymentStatusConverter().convertToEntityAttribute(orderList.get(i).getPaymentStatus()));

                List<ProductDTO> productList = orderList.get(i).getProductList().stream().map(product -> modelMapper.map(product, ProductDTO.class)).collect(Collectors.toList());
                orderDTO.setProductList(productList);
                if (orderList.get(i).getAddress() != null) {
                    AddressDTO addressDTO = modelMapper.map(orderList.get(i).getAddress(), AddressDTO.class);
                    orderDTO.setAddress(addressDTO);
                }
                orderDTO.setPurchaseDate(orderList.get(i).getPurchaseDate());
                orderDTO.setTotalPrice(orderList.get(i).getTotalPrice());
                UserDTO userDTO = modelMapper.map(orderList.get(i).getUser(), UserDTO.class);
                orderDTO.setUserDTO(userDTO);

                orderDTOList.add(orderDTO);
            }
            return orderDTOList;
        } else {
            return null;
        }
    }
    @Transactional(readOnly = true)
    @Override
    public Float getProceedsForLastMonth() {
        List<Order> orderList = orderDao.getOrdersForMonth();
        Float proceeds = 0.0f;
        for (int i =0; i< orderList.size();i++){
            proceeds += orderList.get(i).getTotalPrice();
        }
        return proceeds;
    }
    @Transactional(readOnly = true)
    @Override
    public Float getProceedsForLastWeek() {
        List<Order> orderList = orderDao.getOrdersForWeek();
        Float proceeds = 0.0f;
        for (int i =0; i< orderList.size();i++){
            proceeds += orderList.get(i).getTotalPrice();
        }
        return proceeds;
    }
}
