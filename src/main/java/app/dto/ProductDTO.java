package app.dto;


import org.hibernate.validator.constraints.NotBlank;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.List;

public class ProductDTO {

    private Integer id;
    @Size(min = 2, max = 16, message = "Field must be between 2 and 16 characters.")
    private String name;

    @NotBlank
    private String description;

    private String image;

    @Size(min = 1, max = 5, message = "Field must be between 1 and 5 digits.")
    private Float price;

    @Size(min = 1, max = 5, message = "Field must be between 1 and 5 digits.")
    private Float workPrice;

    @Size(min = 1, max = 5, message = "Field must be between 1 and 5 digits.")
    private Short weight;

    @Size(min = 1, max = 5, message = "Field must be between 1 and 5 digits.")
    private Short quantity;

    @Size(min = 1, max = 5, message = "Field must be between 1 and 5 digits.")
    private Short calories;

    private UserDTO user;

    @NotNull
    private CategoryDTO category;

    private List<OrderDTO> orderList;

    private FillingDTO filling;

    private DoughDTO dough;

    private List<SprinkleDTO> sprinkleList;

    public Integer getId() { return id; }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Float getWorkPrice() {
        return workPrice;
    }

    public void setWorkPrice(Float workPrice) {
        this.workPrice = workPrice;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public Float getPrice() {
        return price;
    }

    public void setPrice(Float price) {
        this.price = price;
    }

    public Short getWeight() {
        return weight;
    }

    public void setWeight(Short weight) {
        this.weight = weight;
    }

    public Short getQuantity() {
        return quantity;
    }

    public void setQuantity(Short quantity) {
        this.quantity = quantity;
    }

    public Short getCalories() {
        return calories;
    }

    public void setCalories(Short calories) {
        this.calories = calories;
    }

    public UserDTO getUserDTO() {
        return user;
    }

    public void setUserDTO(UserDTO userDTO) {
        this.user = userDTO;
    }

    public CategoryDTO getCategory() {
        return category;
    }

    public void setCategory(CategoryDTO category) {
        this.category = category;
    }

    public List<OrderDTO> getOrderList() {
        return orderList;
    }

    public void setOrderList(List<OrderDTO> orderList) {
        this.orderList = orderList;
    }

    public FillingDTO getFilling() {
        return filling;
    }

    public void setFilling(FillingDTO filling) {
        this.filling = filling;
    }

    public DoughDTO getDough() {
        return dough;
    }

    public void setDough(DoughDTO dough) {
        this.dough = dough;
    }

    public List<SprinkleDTO> getSprinkleList() {
        return sprinkleList;
    }

    public void setSprinkleList(List<SprinkleDTO> sprinkleList) {
        this.sprinkleList = sprinkleList;
    }
}
