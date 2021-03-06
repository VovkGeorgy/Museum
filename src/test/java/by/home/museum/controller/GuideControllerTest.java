package by.home.museum.controller;

import by.home.museum.entity.GuideEntity;
import by.home.museum.entity.UsersEntity;
import by.home.museum.service.GuideService;
import by.home.museum.service.UserService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {GuideControllerTestConfig.class})
@WebAppConfiguration
public class GuideControllerTest {

    private MockMvc mockMvc;

    @Autowired
    private GuideService guideService;

    @Autowired
    private GuideController guideController;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private UserService userService;

    @Before
    public void setUp() {
        mockMvc = MockMvcBuilders.standaloneSetup(guideController).build();
    }

    @Test
    public void getGuides() throws Exception {

        List<GuideEntity> guides = new ArrayList<>();
        guides.add(new GuideEntity("username1","passss","TestFio1", (short) 50, (short)1, "TST,STS,TTT",(long)13));
        guides.add(new GuideEntity("username2","passss1","TestFio7", (short) 99, (short)100, "TST,STS,TTT",(long)33));
        guides.add(new GuideEntity("username3","passss2","TestFio44", (short) 40, (short)20, "TST,STS,TTT",(long)35));
        when(guideService.findAll()).thenReturn(guides);
        mockMvc.perform(get("/guide/guides"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.length()").value(guides.size()))
                .andExpect(jsonPath("$[0].fio").value(guides.get(0).getFio()));
        verify(guideService, times(1)).findAll();
    }

    @Test
    public void getGuide() throws Exception {
        GuideEntity testGuide = new GuideEntity("username1","passss","TestFio1999", (short) 199, (short)5, "ABCDEFG",(long)102);
        when(guideService.findOne((long) 1)).thenReturn(testGuide);
        mockMvc.perform(get("/guide/guides/{guideId}", 1))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.guideId").value(testGuide.getGuideId()))
                .andExpect(jsonPath("$.fio").value(testGuide.getFio()));
        verify(guideService, times(2)).findOne((long) 1);
    }

    @Test
    public void addGuide() throws Exception {
        GuideEntity testGuide = new GuideEntity("username1","passss","Test_Fio", (short) 29, (short)0, "ABC,DEF,G",(long)2111);
        String jsonTestGuide = objectMapper.writeValueAsString(testGuide);
        when(guideService.save(testGuide)).thenReturn(testGuide);
        mockMvc.perform(post("/guide/guides/add").contentType(MediaType.APPLICATION_JSON).content(jsonTestGuide))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").value(testGuide))
                .andExpect(jsonPath("$.languages").value(testGuide.getLanguages()));
        verify(guideService, times(1)).save(testGuide);
        verifyNoMoreInteractions(guideService);
    }

    @Test
    public void updateGuide() throws Exception {
        GuideEntity testGuide = new GuideEntity("username1","passss","Testing-guide-fio", (short) 98, (short)33, "sasdasdasd",(long)14562);
        UsersEntity newUser = new UsersEntity(testGuide.getUsername(), testGuide.getPassword());
        String jsonTestGuide = objectMapper.writeValueAsString(testGuide);
        when(guideService.save(testGuide)).thenReturn(testGuide);
        when(userService.findByUsername(testGuide.getUsername())).thenReturn(newUser);
        mockMvc.perform(post("/guide/guides/update/{guideId}", 1).contentType(MediaType.APPLICATION_JSON).content(jsonTestGuide))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.password").value(testGuide.getPassword()));
        verify(guideService, times(1)).save(testGuide);
        verifyNoMoreInteractions(guideService);
    }

    @Test
    public void deteleGuide() throws Exception {
        GuideEntity testGuide = new GuideEntity("username1","passss","SomeFio", (short) 222, (short)13, "lang1, lang2, lang3",(long)1);
        when(guideService.findOne((long) 1)).thenReturn(testGuide);
        mockMvc.perform(post("/guide/guides/delete/{guideId}", 1))
                .andExpect(status().isOk());
        verify(guideService, times(1)).findOne((long) 1);
        verify(guideService, times(1)).delete(testGuide);
        verifyNoMoreInteractions(guideService);
    }
}