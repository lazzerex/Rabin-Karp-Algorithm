import java.util.HashMap;

public class RabinKarp {

    // Function to search for a pattern in a given text using the Rabin-Karp algorithm
    public static void rabinKarp(String text, String pattern, int prime) {
        int M = pattern.length();
        int N = text.length();
        int i, j;
        int patternHash = 0; // Hash value for the pattern
        int textHash = 0; // Hash value for the text
        int h = 1;

        // The value of h would be "pow(d, M-1)%q"
        for (i = 0; i < M - 1; i++) {
            h = (h * 256) % prime;
        }

        // Calculate the hash value of pattern and first window of text
        for (i = 0; i < M; i++) {
            patternHash = (256 * patternHash + pattern.charAt(i)) % prime;
            textHash = (256 * textHash + text.charAt(i)) % prime;
        }

        // Slide the pattern over text one by one
        for (i = 0; i <= N - M; i++) {
            // Check the hash values of the current window of text and pattern.
            if (patternHash == textHash) {
                // Check for characters one by one
                boolean found = true;
                for (j = 0; j < M; j++) {
                    if (text.charAt(i + j) != pattern.charAt(j)) {
                        found = false;
                        break;
                    }
                }
                if (found) {
                    System.out.println("Pattern found at index " + i);
                }
            }

            // Calculate hash value for the next window of text: Remove leading digit,
            // add trailing digit
            if (i < N - M) {
                textHash = (256 * (textHash - text.charAt(i) * h) + text.charAt(i + M)) % prime;

                // We might get negative value of textHash, converting it to positive
                if (textHash < 0) {
                    textHash = textHash + prime;
                }
            }
        }
    }

    public static void main(String[] args) {
        String text = "ABCCDDAEFG";
        String pattern = "CDD";
        int prime = 101; // A prime number

        rabinKarp(text, pattern, prime);
    }
}
