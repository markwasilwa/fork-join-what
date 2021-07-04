package ke.co.grammar;

import org.hamcrest.core.IsEqual;
import org.junit.Assert;
import org.junit.Test;

import java.util.*;
import java.util.concurrent.ForkJoinPool;

public class ResponseLengthCalculatorTest {

    @Test
    public void shouldReturnEmptyForEmptyList() {
        ResponseLengthCalculator responseLengthCalculator = new ResponseLengthCalculator(Collections.emptyList());
        ForkJoinPool pool = new ForkJoinPool();

        pool.submit(responseLengthCalculator);

        Map<String, Integer> result = responseLengthCalculator.join();
//        Assert.assertThat(result, IsEqual.equalTo(new ArrayList<>()));
        Assert.assertThat(result.size(), IsEqual.equalTo(0));
    }

    @Test
    public void shouldHandle200Ok() {
        ResponseLengthCalculator responseLengthCalculator =
                new ResponseLengthCalculator(List.of("http://httpstat.us/200"));
        ForkJoinPool pool = new ForkJoinPool();
        pool.submit(responseLengthCalculator);

        Map<String, Integer> result = responseLengthCalculator.join();
//        assertThat(result)
//                .hasSize(1)
//                .containsKeys("http://httpstat.us/200")
//                .containsValue(0);
        Assert.assertThat(result.size(), IsEqual.equalTo(1));
    }

    @Test
    public void shouldFetchResponseForDifferentResponseStatus() {
        ResponseLengthCalculator responseLengthCalculator = new ResponseLengthCalculator(List.of(
                "http://httpstat.us/200",
                "http://httpstat.us/302",
                "http://httpstat.us/404",
                "http://httpstat.us/502"
        ));
        ForkJoinPool pool = new ForkJoinPool();

        pool.submit(responseLengthCalculator);

        Map<String, Integer> result = responseLengthCalculator.join();
//        assertThat(result)
//                .hasSize(4);
        Assert.assertThat(result.size(), IsEqual.equalTo(4));
    }

}
