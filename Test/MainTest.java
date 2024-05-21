import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;

class MainTest {
    String test(String str, final boolean capitalizeFirstLetter, final char... delimiters) {
//        System.out.println(str);
//        System.out.println(capitalizeFirstLetter);
//        System.out.println(delimiters);
//        System.out.println();
//        return Main.toCamelCase(str, capitalizeFirstLetter, delimiters);
        return Main.toCamelCase(str, capitalizeFirstLetter, delimiters);

    }

    @Test
    void defaults() {
        assertThat(test(null, true)).isEqualTo(null);
        assertThat(test("", true)).isEmpty();
        assertThat(test("aaaaaa", true, 'a')).isEmpty();
        assertThat(test("     ", true)).isEmpty();
    }

    @Test
    void not_first() {
        assertThat(test("hello world", false)).isEqualTo("helloWorld");
        assertThat(test("abc", false, 'a')).isEqualTo("bc");
        assertThat(test("wowbbwie", false, 'b')).isEqualTo("wowWie");
        assertThat(test("wowbcwiebwoocwaa", false, 'b', 'c')).isEqualTo("wowWieWooWaa");
        assertThat(test("HELLOWORLD", false)).isEqualTo("helloworld");
    }

    @Test
    void first() {
        assertThat(test("abac", true, 'a')).isEqualTo("BC");
        assertThat(test("hello world", true, 'a')).isEqualTo("HelloWorld");
        assertThat(test("HELLOWORLD", true)).isEqualTo("Helloworld");
    }

    @Test
    void null_delims() {
        assertThat(Main.toCamelCase("ab c", true, null)).isEqualTo("AbC");
    }

    @Test
    void happy_path() {
        assertThat(test("Hello", true)).isEqualTo("Hello");
        assertThat(test("HelloaWorld", true, 'a')).isEqualTo("HelloWorld");
        assertThat(test("Hello World", true)).isEqualTo("HelloWorld");
    }

    @Test
    void find_default_return() {
        assertThat(test("a", false)).isEqualTo("a");
    }

}