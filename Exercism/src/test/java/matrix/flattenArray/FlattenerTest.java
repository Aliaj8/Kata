package matrix.flattenArray;

import org.junit.jupiter.api.Test;

import static java.util.Arrays.asList;
import static java.util.Collections.emptyList;
import static java.util.Collections.singletonList;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class FlattenerTest {
    @Test
    void should_flat_list_is_preserved() {
        Flattener flattener = new Flattener();
        assertEquals(asList(0, '1', "two"), flattener.flatten(asList(0, '1', "two")));
    }

    @Test
    void a_single_level_of_nesting_with_noNulls() {
        Flattener flattener = new Flattener();
        flattener.flatten(asList(1, asList('2', 3, 4, 5, "six", "7"), 8));
    }
    @Test
    public void five_levels_of_nesting_with_noNulls() {
        Flattener flattener = new Flattener();
        assertEquals(
                asList(0, '2', 2, "three", '8', 100, "four", 50, "-2"),
                flattener.flatten(asList(0,
                        '2',
                        asList(asList(2, "three"),
                                '8',
                                100,
                                "four",
                                singletonList(singletonList(singletonList(50)))), "-2")));
    }

    @Test
    void six_levels_of_nesting_with_noNulls() {
        Flattener flattener = new Flattener();
        assertEquals(
                asList("one", '2', 3, '4', 5, "six", 7, "8"),
                flattener.flatten(asList("one",
                        asList('2',
                                singletonList(singletonList(3)),
                                asList('4',
                                        singletonList(singletonList(5))), "six", 7), "8")));
    }

    @Test
    void six_levels_of_nesting_with_nulls() {
        Flattener flattener = new Flattener();
        assertEquals(
                asList("0", 2, "two", '3', "8", "one hundred", "negative two"),
                flattener.flatten(asList("0",
                        2,
                        asList(asList("two", '3'),
                                "8",
                                singletonList(singletonList("one hundred")),
                                null,
                                singletonList(singletonList(null))),
                        "negative two")));
    }

    @Test
    void nested_lists_full_Of_nulls_Only() {
        Flattener flattener = new Flattener();
        assertEquals(emptyList(),
                flattener.flatten(asList(null,
                        singletonList(singletonList(singletonList(null))),
                        null,
                        null,
                        asList(asList(null, null), null), null)));
    }
}
