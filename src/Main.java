import java.util.HashSet;
import java.util.Set;

public class Main {
    private final static String EMPTY_STRING = "";

    public static String toCamelCase(String str, final boolean capitalizeFirstLetter, final char... delimiters) {
        if (str == null || str.isEmpty()) {
            return str;
        }
        str = str.toLowerCase();
        final int strLen = str.length();
        final int[] newCodePoints = new int[strLen];
        int outOffset = 0;

        final Set<Integer> delimiterSet = generateDelimiterSet(delimiters);

        boolean capitalizeNext = false;
        if (capitalizeFirstLetter) {
            capitalizeNext = true;
        }

        // Changes start here
        int startIndex = 0;
        while (startIndex < strLen && delimiterSet.contains(str.codePointAt(startIndex))) {
            startIndex += Character.charCount(str.codePointAt(startIndex));
        }

        for (int index = startIndex;index < strLen;) { // Start the loop to create the string
            final int codePoint = str.codePointAt(index);

            if (delimiterSet.contains(codePoint)) {
                capitalizeNext = true;
                // Note the removed code here
                index += Character.charCount(codePoint);
            } else if (capitalizeNext) {
                final int titleCaseCodePoint = Character.toTitleCase(codePoint);
                newCodePoints[outOffset++] = titleCaseCodePoint;
                index += Character.charCount(titleCaseCodePoint);
                capitalizeNext = false;
            } else {
                newCodePoints[outOffset++] = codePoint;
                index += Character.charCount(codePoint);
            }
        }
        if (outOffset != 0) {
            return new String(newCodePoints, 0, outOffset);
        }

        // Note the EMPTY_STRING here, this is to deal with the case of only delimiters for as far as I've found the only
        // time this return is reached is when exclusively delimiters are passed in, which would imply it would be tested
        // and part of the spec, but I can't find it in the description
        return EMPTY_STRING;
    }

    /**
     * Converts an array of delimiters to a hash set of code points. Code point of
     * space(32) is added as the default value. The generated hash set provides O(1)
     * lookup time.
     *
     * @param delimiters set of characters to determine capitalization, null means
     *                   whitespace
     * @return Set<Integer>
     */
    private static Set<Integer> generateDelimiterSet(final char[] delimiters) {
        final Set<Integer> delimiterHashSet = new HashSet<>();
        delimiterHashSet.add(Character.codePointAt(new char[] { ' ' }, 0));
        if (delimiters == null || delimiters.length == 0) {
            return delimiterHashSet;
        }
        for (int index = 0; index < delimiters.length; index++) {
            delimiterHashSet.add(Character.codePointAt(delimiters, index));
        }
        return delimiterHashSet;
    }
}