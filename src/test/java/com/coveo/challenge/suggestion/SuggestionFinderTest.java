package com.coveo.challenge.suggestion;

import com.coveo.challenge.calculator.CityScoreCalculator;
import com.coveo.challenge.io.LineParser;
import com.coveo.challenge.model.City;
import com.coveo.challenge.model.Point;
import com.coveo.challenge.model.Suggestion;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import static com.google.common.truth.Truth.assertThat;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class SuggestionFinderTest {

    private static final String LINE = "line";
    private static final String QUERY = "query";
    private static final Double SEARCH_LATITUDE = 42.123;
    private static final Double SEARCH_LONGITUDE = 45.845;
    private static final String NAME = "name";
    private static final Double LATITUDE = 45.0;
    private static final Double LONGITUDE = -75.0;
    private static final double ANY_SCORE = 0.5;

    @InjectMocks
    private SuggestionFinder underTest;

    @Mock
    private LineParser lineParser;

    @Mock
    private CityScoreCalculator cityScoreCalculator;

    @Test
    public void whenGetSuggestion_givenCity_thenShouldReturnExpectedSuggestion() {
        City city = city();
        when(lineParser.parse(LINE)).thenReturn(city);
        when(cityScoreCalculator.calculate(QUERY, SEARCH_LATITUDE, SEARCH_LONGITUDE, city)).thenReturn(ANY_SCORE);

        Suggestion actual = underTest.getSuggestion(LINE, QUERY, SEARCH_LATITUDE, SEARCH_LONGITUDE);

        assertThat(actual.getName()).isEqualTo(NAME);
        assertThat(actual.getLatitude()).isEqualTo(LATITUDE);
        assertThat(actual.getLongitude()).isEqualTo(LONGITUDE);
        assertThat(actual.getLatitude()).isEqualTo(ANY_SCORE);
    }

    private City city() {
        return City.builder()
                .name(NAME)
                .coordinates(new Point(LATITUDE, LONGITUDE))
                .build();
    }

}