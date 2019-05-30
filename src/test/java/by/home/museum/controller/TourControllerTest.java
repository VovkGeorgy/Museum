package by.home.museum.controller;

import by.home.museum.entity.TourEntity;
import by.home.museum.service.TourService;
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
@ContextConfiguration(classes = {TourControllerTestConfig.class})
@WebAppConfiguration
public class TourControllerTest {

    private MockMvc mockMvc;

    @Autowired
    private TourService tourService;

    @Autowired
    private TourController tourController;

    @Autowired
    private ObjectMapper objectMapper;

    @Before
    public void setUp() {
        mockMvc = MockMvcBuilders.standaloneSetup(tourController).build();
    }

    @Test
    public void getTours() throws Exception {

        List<TourEntity> tours = new ArrayList<>();
        tours.add(new TourEntity("TestToutTheme1", "SomeTypeOfExhibits1", (short) 3, 999.99, "https://www.louvre.fr/some_image1"));
        tours.add(new TourEntity("TestToutTheme2", "SomeTypeOfExhibits2", (short) 2, 99.99, "https://www.louvre.fr/some_image12"));
        tours.add(new TourEntity("TestToutTheme3", "SomeTypeOfExhibits3", (short) 1, 9.99, "https://www.louvre.fr/some_image13"));
        when(tourService.findAll()).thenReturn(tours);
        mockMvc.perform(get("/tour/tours"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.length()").value(tours.size()))
                .andExpect(jsonPath("$[0].theme").value(tours.get(0).getTheme()));
        verify(tourService, times(1)).findAll();
//        verifyNoMoreInteractions(tourService);
    }

    @Test
    public void getTour() throws Exception {
        TourEntity testTour = new TourEntity("TestToutTheme1", "SomeTypeOfExhibits1", (short) 3, 999.99, "https://www.louvre.fr/some_image1");
        when(tourService.findOne((long) 1)).thenReturn(testTour);
        mockMvc.perform(get("/tour/tours/{tourId}", 1))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.tourId").value(testTour.getTourId()))
                .andExpect(jsonPath("$.theme").value(testTour.getTheme()));
        verify(tourService, times(2)).findOne((long) 1);
    }

    @Test
    public void addTour() throws Exception {
        TourEntity testTour = new TourEntity("TestToutTheme1", "SomeTypeOfExhibits1", (short) 3, 999.99, "https://www.louvre.fr/some_image1");
        String jsonTestTour = objectMapper.writeValueAsString(testTour);
        when(tourService.save(testTour)).thenReturn(testTour);
        mockMvc.perform(post("/tour/tours/add").contentType(MediaType.APPLICATION_JSON).content(jsonTestTour))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").value(testTour))
                .andExpect(jsonPath("$.theme").value(testTour.getTheme()));
        verify(tourService, times(1)).save(testTour);
        verifyNoMoreInteractions(tourService);
    }

    @Test
    public void updateTour() throws Exception {
        TourEntity testTour = new TourEntity("TestToutTheme1", "SomeTypeOfExhibits1", (short) 3, 999.99, "https://www.louvre.fr/some_image1");
        String jsonTestTour = objectMapper.writeValueAsString(testTour);
        when(tourService.save(testTour)).thenReturn(testTour);
        mockMvc.perform(post("/tour/tours/update/{tourId}", 1).contentType(MediaType.APPLICATION_JSON).content(jsonTestTour))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.theme").value(testTour.getTheme()));
        verify(tourService, times(2)).save(testTour);
        verifyNoMoreInteractions(tourService);
    }

    @Test
    public void deteleTour() throws Exception {
        TourEntity testTour = new TourEntity("TestToutTheme1", "SomeTypeOfExhibits1", (short) 3, 999.99, "https://www.louvre.fr/some_image1");
        when(tourService.findOne((long) 1)).thenReturn(testTour);
        mockMvc.perform(post("/tour/tours/delete/{tourId}", 1))
                .andExpect(status().isOk());
        verify(tourService, times(1)).findOne((long) 1);
        verify(tourService, times(1)).delete(testTour);
        verifyNoMoreInteractions(tourService);
    }
}