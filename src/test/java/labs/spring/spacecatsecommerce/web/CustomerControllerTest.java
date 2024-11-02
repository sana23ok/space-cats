package labs.spring.spacecatsecommerce.web;

import labs.spring.spacecatsecommerce.domain.CustomerDetails;
import labs.spring.spacecatsecommerce.dto.CustomerDetailsDto;
import labs.spring.spacecatsecommerce.dto.CustomerDetailsListDto;
import labs.spring.spacecatsecommerce.service.CustomerService;
import labs.spring.spacecatsecommerce.service.mapper.CustomDetailsMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.Collections;

import static org.hamcrest.Matchers.*;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

public class CustomerControllerTest {

    private MockMvc mockMvc;

    @Mock
    private CustomerService customerService;

    @Mock
    private CustomDetailsMapper customDetailsMapper;

    @InjectMocks
    private CustomerController customerController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        this.mockMvc = MockMvcBuilders.standaloneSetup(customerController).build();
    }

    @Test
    void createCustomer_invalidData_shouldReturnBadRequest() throws Exception {
        mockMvc.perform(post("/customers")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"name\":\"\",\"address\":\"\",\"phoneNumber\":\"\",\"email\":\"\",\"preferredChannel\":[]}"))
                .andExpect(status().isBadRequest());
    }
}
