package com.customer.api.service;

import com.customer.api.controller.data.request.CustomerPartRequest;
import com.customer.api.controller.data.request.CustomerRequest;
import com.customer.api.controller.data.response.CustomerResponse;
import com.customer.api.converter.CustomerConverter;
import com.customer.api.entity.CustomerEntity;
import com.customer.api.enums.CustomerStatus;
import com.customer.api.repository.CustomerRepository;
import com.customer.api.service.impl.CustomerServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.time.LocalDate;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.fail;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;

@ExtendWith(SpringExtension.class)
public class CustomerServiceTest {

    private static final Long CUSTOMER_ID = 100L;
    private static final String VALIDATION_01 = "VALIDATION-01";
    private static final String CUSTOMER_DATA_01 = "CUSTOMER-DATA-01";
    private static final String CUSTOMER_DATA_02 = "CUSTOMER-DATA-02";
    private static final String CUSTOMER_DATA_03 = "CUSTOMER-DATA-03";
    private static final String CUSTOMER_DATA_04 = "CUSTOMER-DATA-04";

    @InjectMocks
    private CustomerServiceImpl service;

    @Mock
    private CustomerRepository repository;

    @Mock
    private CustomerConverter converter;

    private CustomerRequest request;
    private CustomerResponse response;
    private CustomerEntity entity;
    private CustomerPartRequest partRequest;

    @BeforeEach
    public void setUp() {
        request = createRequest();
        entity = createEntity();
        response = createResponse();
        partRequest = createPartRequest();
    }

    @Test
    public void whenCreateCustomerIsSuccess() {

        try {

            when(converter.toCustomerEntity(any())).thenReturn(entity);
            when(repository.saveAndFlush(any())).thenReturn(entity);
            when(converter.toCustomerResponse(any())).thenReturn(response);

            var result = service.createCustomer(request);

            verify(converter).toCustomerEntity(any());
            verify(repository).saveAndFlush(any());
            verify(converter).toCustomerResponse(any());

            assertThat(result).isNotNull();
            assertThat(result.getName()).isEqualTo(response.getName());
            assertThat(result.getEmail()).isEqualTo(response.getEmail());
            assertThat(result.getCpfCnpj()).isEqualTo(response.getCpfCnpj());
            assertThat(result.getBirthDate()).isEqualTo(response.getBirthDate());
            assertThat(result.getCustomerId()).isEqualTo(response.getCustomerId());
            assertThat(result.getStatus()).isEqualTo(response.getStatus());

        } catch (Exception ex) {
            fail("Não deve cair aqui...");
        }
    }

    @Test
    public void whenCreateCustomerEntityNullIsExceptionError() {

        try {

            when(repository.saveAndFlush(any())).thenReturn(null);
            service.createCustomer(request);

            fail("Não deve cair aqui...");

        } catch (Exception ex) {
            verify(repository).saveAndFlush(any());
            assertThat(ex.getMessage()).isEqualToIgnoringCase(CUSTOMER_DATA_01);
        }
    }

    @Test
    public void whenCreateCustomerIsExistsCustomer() {

        try {

            when(repository.findByCpfCnpj(any())).thenReturn(Optional.of(entity));
            service.createCustomer(request);

            fail("Não deve cair aqui...");

        } catch (Exception ex) {
            verify(repository).findByCpfCnpj(any());
            assertThat(ex.getMessage()).isEqualToIgnoringCase(VALIDATION_01);
        }
    }

    @Test
    public void whenUpdateCustomerIsSuccess() {

        try {

            when(repository.findById(anyLong())).thenReturn(Optional.of(entity));
            when(converter.toCustomerEntityUpdate(any(), any())).thenReturn(entity);
            when(repository.saveAndFlush(any())).thenReturn(entity);
            when(converter.toCustomerResponse(any())).thenReturn(response);

            var result = service.updateCustomer(CUSTOMER_ID, request);

            verify(repository).findById(anyLong());
            verify(converter).toCustomerEntityUpdate(any(), any());
            verify(repository).saveAndFlush(any());
            verify(converter).toCustomerResponse(any());

            assertThat(result).isNotNull();
            assertThat(result.getCustomerId()).isEqualTo(response.getCustomerId());

        } catch (Exception ex) {
            fail("Não deve cair aqui...");
        }
    }

    @Test
    public void whenUpdateCustomerEntityNullIsExceptionError() {

        try {

            when(repository.findById(anyLong())).thenReturn(Optional.of(entity));
            when(repository.saveAndFlush(any())).thenReturn(null);
            service.updateCustomer(CUSTOMER_ID, request);

            fail("Não deve cair aqui...");

        } catch (Exception ex) {
            verify(repository).findById(anyLong());
            verify(repository).saveAndFlush(any());
            assertThat(ex.getMessage()).isEqualToIgnoringCase(CUSTOMER_DATA_04);
        }
    }

    @Test
    public void whenGetByIdCustomerEntityIsSuccess() {

        try {

            when(repository.findById(anyLong())).thenReturn(Optional.of(entity));
            when(converter.toCustomerResponse(any())).thenReturn(response);

            var result = service.getByIdCustomer(CUSTOMER_ID);

            verify(repository).findById(anyLong());
            verify(converter).toCustomerResponse(any());

            assertThat(result).isNotNull();

        } catch (Exception ex) {
            fail("Não deve cair aqui...");
        }
    }

    @Test
    public void whenUpdatePartCustomerIsSuccess() {

        try {

            when(repository.findById(anyLong())).thenReturn(Optional.of(entity));
            when(converter.toCustomerEntityPartUpdate(any(), any())).thenReturn(entity);
            when(repository.saveAndFlush(any())).thenReturn(entity);
            when(converter.toCustomerResponse(any())).thenReturn(response);

            var result = service.updatePartCustomer(CUSTOMER_ID, partRequest);

            verify(repository).findById(anyLong());
            verify(converter).toCustomerEntityPartUpdate(any(), any());
            verify(repository).saveAndFlush(any());
            verify(converter).toCustomerResponse(any());

            assertThat(result).isNotNull();
            assertThat(result.getCustomerId()).isEqualTo(response.getCustomerId());

        } catch (Exception ex) {
            fail("Não deve cair aqui...");
        }
    }

    @Test
    public void whenUpdatePartCustomerEntityNullIsExceptionError() {

        try {

            when(repository.findById(anyLong())).thenReturn(Optional.of(entity));
            when(converter.toCustomerEntityPartUpdate(any(), any())).thenReturn(entity);
            when(repository.saveAndFlush(any())).thenReturn(null);
            service.updatePartCustomer(CUSTOMER_ID, partRequest);

            fail("Não deve cair aqui...");

        } catch (Exception ex) {
            verify(repository).findById(anyLong());
            verify(converter).toCustomerEntityPartUpdate(any(), any());
            verify(repository).saveAndFlush(any());
            assertThat(ex.getMessage()).isEqualToIgnoringCase(CUSTOMER_DATA_04);
        }
    }

    @Test
    public void whenGetAllCustomerEntityIsSuccess() {

        try {

            Page<CustomerEntity> page = mock(Page.class);
            page.getContent().add(entity);
            Pageable pageable = PageRequest.of(0, 5, Sort.by("asc", "name"));

            when(repository.findAll(pageable)).thenReturn(page);
            when(converter.toCustomerResponse(any())).thenReturn(response);

            var result = service.getAllCustomers(pageable);

            verify(repository).findAll(pageable);

            assertThat(result).isNull();

        } catch (Exception ex) {
            fail("Não deve cair aqui...");
        }
    }

    @Test
    public void whenGetAllCustomerEntityIsError() {

        Pageable pageable = PageRequest.of(0, 5, Sort.by("asc", "name"));

        try {

            when(repository.findAll(pageable)).thenReturn(null);

            service.getAllCustomers(pageable);

            fail("Não deve cair aqui...");

        } catch (Exception ex) {
            verify(repository).findAll(pageable);
            assertThat(ex.getMessage()).isEqualToIgnoringCase(CUSTOMER_DATA_02);
        }
    }

    private CustomerRequest createRequest() {
        return CustomerRequest.builder()
                .name("Washington Wiltenburg")
                .email("washington@email.com")
                .cpfCnpj("12345678910")
                .birthDate(LocalDate.of(1984, 10, 8))
                .build();
    }

    private CustomerEntity createEntity() {
        return CustomerEntity.builder()
                .id(100L)
                .name("Washington Wiltenburg")
                .email("washington@email.com")
                .cpfCnpj("12345678910")
                .birthDate(LocalDate.of(1984, 10, 8))
                .status(CustomerStatus.ACTIVE)
                .build();
    }

    private CustomerResponse createResponse() {
        return CustomerResponse.builder()
                .customerId(100L)
                .name("Washington Wiltenburg")
                .email("washington@email.com")
                .cpfCnpj("12345678910")
                .birthDate(LocalDate.of(1984, 10, 8))
                .age(37)
                .status(CustomerStatus.ACTIVE)
                .build();
    }

    public CustomerPartRequest createPartRequest() {
        return CustomerPartRequest.builder()
                .name("Washington Cézar Wiltenburg")
                .email("washington2@email.com")
                .build();
    }
}