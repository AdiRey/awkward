package pl.awkward.gender;

import org.assertj.core.util.Lists;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import pl.awkward.security.jwt.JwtManageComponent;
import pl.awkward.shared.baseStuff.BaseConverter;

import java.time.LocalDateTime;
import java.util.Optional;

import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@ExtendWith(SpringExtension.class)
@WebMvcTest(controllers = GenderController.class)
class GenderControllerTest {

    private final static String PREFIX_URL = "/api/genders";

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private GenderRepository genderRepository;

    @MockBean
    private BaseConverter<Gender, GenderDto> genderConverter;

    @MockBean
    private BaseConverter<Gender, GenderShowDto> genderShowConverter;

    @MockBean
    private UserDetailsService userDetailsService;

    @MockBean
    private JwtManageComponent jwtManageComponent;

    private final static String MAN = "Męska";

    private final static String WOMAN = "Żeńska";

    @BeforeEach
    void setUp() {
        this.converterSetting();

        Gender man = new Gender();
        man.setId(1L);
        man.setGender(MAN);
        man.setActive(true);
        man.setAddDate(LocalDateTime.now());

        Gender woman = new Gender();
        woman.setId(1L);
        woman.setGender(WOMAN);
        woman.setActive(true);
        woman.setAddDate(LocalDateTime.now());

        when(this.genderRepository.findAll()).thenReturn(Lists.newArrayList(man, woman));
        when(this.genderRepository.findById(1L)).thenReturn(Optional.of(man));
        when(this.genderRepository.findById(2L)).thenReturn(Optional.of(woman));

    }

    @Test
    void get_both_genders_return_showDto() throws Exception {
        this.mockMvc
                .perform(get(PREFIX_URL))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$", hasSize(2)))
                .andExpect(jsonPath("$[0].gender", is(MAN)))
                .andExpect(jsonPath("$[1].gender", is(WOMAN)))
                .andDo(print());
    }

    @Test
    void get_both_genders_return_allData() throws Exception{
        this.mockMvc
                .perform(get(PREFIX_URL + "/allData"))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$", hasSize(2)))
                .andExpect(jsonPath("$[0].active", is(true)))
                .andExpect(jsonPath("$[1].active", is(true)))
                .andExpect(jsonPath("$[0].gender", is(MAN)))
                .andExpect(jsonPath("$[1].gender", is(WOMAN)))
                .andDo(print());
    }

    @Test
    void get_oneGender_return_man_showDto() throws Exception{
        this.mockMvc
                .perform(get(PREFIX_URL + "/{id}",1L))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.gender", is(MAN)))
                .andDo(print());
    }

    @Test
    void get_oneGender_return_woman_showDto() throws Exception{
        this.mockMvc
                .perform(get(PREFIX_URL + "/{id}",2L))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.gender", is(WOMAN)))
                .andDo(print());
    }

    @Test
    void get_oneGender_return_man_allData() throws Exception{
        this.mockMvc
                .perform(get(PREFIX_URL + "/{id}/allData",1L))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.active", is(true)))
                .andExpect(jsonPath("$.gender", is(MAN)))
                .andDo(print());
    }

    @Test
    void get_oneGender_return_woman_allData() throws Exception{
        this.mockMvc
                .perform(get(PREFIX_URL + "/{id}/allData",2L))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.active", is(true)))
                .andExpect(jsonPath("$.gender", is(WOMAN)))
                .andDo(print());
    }




    private void converterSetting() {
        when(this.genderConverter.toEntity()).thenReturn(dto -> {
            if (dto == null)
                return null;

            Gender gender = new Gender();

            gender.setId(dto.getId());
            gender.setGender(dto.getGender());
            gender.setActive(dto.getActive());
            gender.setAddDate(dto.getAddDate());
            gender.setDeleteDate(dto.getDeleteDate());

            return gender;
        });

        when(this.genderConverter.toDto()).thenReturn(gender -> {
            if (gender == null)
                return null;

            GenderDto dto = new GenderDto();

            dto.setId(gender.getId());
            dto.setGender(gender.getGender());
            dto.setActive(gender.getActive());
            dto.setAddDate(gender.getAddDate());
            dto.setDeleteDate(gender.getDeleteDate());

            return dto;
        });

        when(this.genderShowConverter.toEntity()).thenReturn(dto -> {
            if (dto == null)
                return null;

            Gender gender = new Gender();

            gender.setId(dto.getId());
            gender.setGender(dto.getGender());

            return gender;
        });

        when(this.genderShowConverter.toDto()).thenReturn(gender -> {
            if (gender == null)
                return null;

            GenderShowDto dto = new GenderShowDto();

            dto.setId(gender.getId());
            dto.setGender(gender.getGender());

            return dto;
        });
    }


}
