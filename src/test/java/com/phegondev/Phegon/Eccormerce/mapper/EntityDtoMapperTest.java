package com.phegondev.Phegon.Eccormerce.mapper;

import com.phegondev.Phegon.Eccormerce.dto.*;
import com.phegondev.Phegon.Eccormerce.entity.*;
import com.phegondev.Phegon.Eccormerce.enums.OrderStatus;
import com.phegondev.Phegon.Eccormerce.enums.UserRole;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class EntityDtoMapperTest {

    private final EntityDtoMapper mapper = new EntityDtoMapper();

    @Test
    void testMapUserToDtoBasic() {
        User user = new User();
        user.setId(1L);
        user.setEmail("john.doe@example.com");
        user.setPhoneNumber("1234567890");
        user.setRole(UserRole.USER);
        user.setName("John Doe");

        UserDto userDto = mapper.mapUserToDtoBasic(user);

        assertEquals(1L, userDto.getId());
        assertEquals("john.doe@example.com", userDto.getEmail());
        assertEquals("1234567890", userDto.getPhoneNumber());
        assertEquals("USER", userDto.getRole());
        assertEquals("John Doe", userDto.getName());
    }

    @Test
    void testMapAddressToDtoBasic() {
        Address address = new Address();
        address.setId(1L);
        address.setCity("New York");
        address.setStreet("5th Avenue");
        address.setState("NY");
        address.setCountry("USA");
        address.setZipCode("10001");

        AddressDto addressDto = mapper.mapAddressToDtoBasic(address);

        assertEquals(1L, addressDto.getId());
        assertEquals("New York", addressDto.getCity());
        assertEquals("5th Avenue", addressDto.getStreet());
        assertEquals("NY", addressDto.getState());
        assertEquals("USA", addressDto.getCountry());
        assertEquals("10001", addressDto.getZipCode());
    }

    @Test
    void testMapCategoryToDtoBasic() {
        Category category = new Category();
        category.setId(1L);
        category.setName("Electronics");

        CategoryDto categoryDto = mapper.mapCategoryToDtoBasic(category);

        assertEquals(1L, categoryDto.getId());
        assertEquals("Electronics", categoryDto.getName());
    }

    @Test
    void testMapOrderItemToDtoBasic() {
        OrderItem orderItem = new OrderItem();
        orderItem.setId(1L);
        orderItem.setQuantity(2);
        orderItem.setPrice(BigDecimal.valueOf(200));
        orderItem.setStatus(OrderStatus.PENDING);

        OrderItemDto orderItemDto = mapper.mapOrderItemToDtoBasic(orderItem);

        assertEquals(1L, orderItemDto.getId());
        assertEquals(2, orderItemDto.getQuantity());
        assertEquals(BigDecimal.valueOf(200), orderItemDto.getPrice());
        assertEquals("PENDING", orderItemDto.getStatus());
        assertNotNull(orderItemDto.getCreatedAt());
    }

    @Test
    void testMapProductToDtoBasic() {
        Product product = new Product();
        product.setId(1L);
        product.setName("Smartphone");
        product.setDescription("Latest model");
        product.setPrice(BigDecimal.valueOf(799.99));
        product.setImageUrl("image.jpg");

        ProductDto productDto = mapper.mapProductToDtoBasic(product);

        assertEquals(1L, productDto.getId());
        assertEquals("Smartphone", productDto.getName());
        assertEquals("Latest model", productDto.getDescription());
        assertEquals(BigDecimal.valueOf(799.99), productDto.getPrice());
        assertEquals("image.jpg", productDto.getImageUrl());
    }

    @Test
    void testMapUserToDtoPlusAddress() {
        User user = new User();
        user.setId(1L);
        user.setEmail("john.doe@example.com");
        user.setPhoneNumber("1234567890");
        user.setRole(UserRole.USER);
        user.setName("John Doe");

        Address address = new Address();
        address.setId(1L);
        address.setCity("New York");
        address.setStreet("5th Avenue");
        user.setAddress(address);

        UserDto userDto = mapper.mapUserToDtoPlusAddress(user);

        assertNotNull(userDto.getAddress());
        assertEquals("New York", userDto.getAddress().getCity());
        assertEquals("5th Avenue", userDto.getAddress().getStreet());
    }

    @Test
    void testMapUserToDtoPlusAddressAndOrderHistory() {
        User user = new User();
        user.setId(1L);
        user.setEmail("john.doe@example.com");
        user.setRole(UserRole.USER); // Ensure role is set to avoid NullPointerException

        OrderItem orderItem = new OrderItem();
        orderItem.setId(1L);
        orderItem.setQuantity(2);
        orderItem.setPrice(BigDecimal.valueOf(200));
        orderItem.setStatus(OrderStatus.PENDING);

        user.setOrderItemList(List.of(orderItem));

        UserDto userDto = mapper.mapUserToDtoPlusAddressAndOrderHistory(user);

        assertNotNull(userDto.getOrderItemList());
        assertEquals(1, userDto.getOrderItemList().size());
        assertEquals(1L, userDto.getOrderItemList().get(0).getId());
    }
}
