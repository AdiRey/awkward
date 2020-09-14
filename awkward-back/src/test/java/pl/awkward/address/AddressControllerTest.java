package pl.awkward.address;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.MediaType;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import pl.awkward.address.dtos.AddressDto;
import pl.awkward.address.dtos.AddressShowDto;
import pl.awkward.address.model_repo.Address;
import pl.awkward.address.model_repo.AddressRepository;
import pl.awkward.address.web.AddressController;
import pl.awkward.address.web.AddressService;
import pl.awkward.security.jwt.JwtManageComponent;
import pl.awkward.shared.baseStuff.BaseConverter;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.Matchers.hasSize;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


@ExtendWith(SpringExtension.class)
@WebMvcTest(controllers = AddressController.class)
class AddressControllerTest {

    private final static String PREFIX_URL = "/api/addresses";

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private UserDetailsService userDetailsService;

    @MockBean
    private JwtManageComponent jwtManageComponent;

    @MockBean
    private BaseConverter<Address, AddressDto> addressConverter;

    @MockBean
    private BaseConverter<Address, AddressShowDto> addressShowConverter;

    @MockBean
    private AddressService addressService;

    @MockBean
    private AddressRepository repository;

    private final List<Address> addresses = new ArrayList<>();

    private Page<Address> pages;

    @BeforeEach
    void setUp() {
        this.converterSetting();

        Address address = new Address(), address2 = new Address(), address3 = new Address();

        address.setId(1L);
        address.setCountry("Polska");
        address.setCity("Rzeszów");
        address.setActive(true);
        address.setAddDate(LocalDateTime.now());

        address2.setId(2L);
        address2.setCountry("Polska");
        address2.setCity("Jarosław");
        address2.setActive(true);
        address2.setAddDate(LocalDateTime.now());

        address3.setId(3L);
        address3.setCountry("Polska");
        address3.setCity("Przemyśl");
        address3.setActive(true);
        address3.setAddDate(LocalDateTime.now());

        this.addresses.add(address);
        this.addresses.add(address2);
        this.addresses.add(address3);

        pages = new PageImpl<>(this.addresses);

    }

    @AfterEach
    void tearDown() {
    }


    @Test
    void get_all_addresses_defaultParameters_return_page_showDto() throws Exception {

        Sort sort = Sort.by(new Sort.Order(Sort.Direction.ASC, "id"));

        when(this.repository.findAllByActiveIsTrue(PageRequest.of(0,20, sort))).thenReturn(this.pages);

        this.mockMvc
                .perform(get(PREFIX_URL))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.content", hasSize(3)))
                .andDo(print());

    }

    @Test
    void get_all_addresses_page1_return_page_showDto() throws Exception {

        Sort sort = Sort.by(new Sort.Order(Sort.Direction.ASC, "id"));

        when(this.repository.findAllByActiveIsTrue(PageRequest.of(1,20, sort))).thenReturn(Page.empty());

        this.mockMvc
                .perform(get(PREFIX_URL + "?page=1"))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.content", hasSize(0)))
                .andDo(print());

    }

    @Test
    void testGetAll() {
    }

    @Test
    void getAllData() {
    }

    @Test
    void testGetOne() {
    }

    @Test
    void getOneData() {
    }

    @Test
    void testCreate() {
    }

    @Test
    void testDelete() {
    }

    @Test
    void testUpdate() {
    }


    private void converterSetting() {
        when(this.addressConverter.toEntity()).thenReturn(dto -> {
            if (dto == null)
                return null;

            Address address = new Address();

            address.setId(dto.getId());
            address.setCountry(dto.getCountry());
            address.setCity(dto.getCity());
            address.setActive(dto.getActive());
            address.setAddDate(dto.getAddDate());
            address.setDeleteDate(dto.getDeleteDate());

            return address;
        });

        when(this.addressConverter.toDto()).thenReturn(address -> {
            if (address == null)
                return null;

            AddressDto dto = new AddressDto();

            dto.setId(address.getId());
            dto.setCountry(address.getCountry());
            dto.setCity(address.getCity());
            dto.setActive(address.getActive());
            dto.setAddDate(address.getAddDate());
            dto.setDeleteDate(address.getDeleteDate());

            return dto;
        });

        when(this.addressShowConverter.toEntity()).thenReturn(dto -> {
            if (dto == null)
                return null;

            Address address = new Address();

            address.setId(dto.getId());
            address.setCountry(dto.getCountry());
            address.setCity(dto.getCity());

            return address;
        });

        when(this.addressShowConverter.toDto()).thenReturn(address -> {
            if (address == null)
                return null;

            AddressShowDto dto = new AddressShowDto();

            dto.setId(address.getId());
            dto.setCountry(address.getCountry());
            dto.setCity(address.getCity());

            return dto;
        });
    }
}
