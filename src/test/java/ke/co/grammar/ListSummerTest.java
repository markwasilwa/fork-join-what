package ke.co.grammar;

import org.hamcrest.core.IsEqual;
import org.junit.Test;

import java.util.List;
import java.util.concurrent.ForkJoinPool;

import static org.junit.Assert.assertThat;

public class ListSummerTest {

    @Test
    public void shouldSumEmptyList() {
        ListSummer summer = new ListSummer(List.of());
        ForkJoinPool forkJoinPool = new ForkJoinPool();
        forkJoinPool.submit(summer);

        int result = summer.join();

        assertThat(result, IsEqual.equalTo(0));
    }

    @Test
    public void shouldSumListWithOneElement() {
        ListSummer summer = new ListSummer(List.of(5));
        ForkJoinPool forkJoinPool = new ForkJoinPool();
        forkJoinPool.submit(summer);

        int result = summer.join();

        assertThat(result, IsEqual.equalTo(5));
    }

    @Test
    public void shouldSumListWithMultipleElement() {
        ListSummer summer = new ListSummer(List.of(
                1, 2, 3, 4, 5, 6, 7, 8, 9
        ));
        ForkJoinPool forkJoinPool = new ForkJoinPool();
        forkJoinPool.submit(summer);

        int result = summer.join();

        assertThat(result, IsEqual.equalTo(45));
    }


}
