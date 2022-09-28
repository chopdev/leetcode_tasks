import java.util.Arrays;

/**
 * You’re writing part of the code for an online card game. In the game, players pass cards right and left around a table.
 * In your online version, you want to allow users to set up a game with some number of human players and some number of “bot” (automated) players.
 * In order to be fair, you want to spread out the bots as evenly as possible around the table; for example, if there are 9 total players and 3 are bots,
 * every third seat should be a bot, and the rest should be humans.
 *
 * Example
 * Players: 6 humans, 3 bots
 * Seating: human, human, bot, human, human, bot, human, human, bot
 *
 * Goal
 * Write a function which takes the number of human players and the number of bot players, and returns a seating arrangement
 * which satisfies our requirements. To start with, assume that the number of humans is evenly divisible by the number of bots, like in the example (6 is divisible by 3).
 *
 * Part 2
 * Now either edit your function or write a new one to handle the rest of the possible inputs. Remember that we want to spread out the bots as evenly as possible! So in the below example, the longer sequences of humans should be spaced out rather than adjacent to each other.
 *
 * Example for 6 humans (h), 4 bots (B)
 * Invalid: h h B h h B h B h B
 * Valid: h h B h B h h B h B
 *
 *
 * 7h 4b
 * 3h  1h rest 2b
 * 1h 1h rest 1b
 *
 * h b h h b  h  h b h h b
 *
 *
 * 7h 3b
 *
 * 2x h  1 rest
 *
 * b h h b h h h b h h
 * h h b h h h b h h b
 *
 *
 * 17h  13b
 *
 * 1x 4 rest
 *
 * 17 / 4
 * 4x  1 rest
 *
 *
 *
 * */
public class Solution {

    // My solution, works only for divisible
    // O(H+B) time and space
    int[] getArrangement2222(int humansCount, int botsCount) {
        int biggerValue = humansCount >= botsCount ? 1 : 0;
        int smallerValue = humansCount >= botsCount ? 0 : 1;
        int bCount = Math.max(humansCount, botsCount);
        int sCount = Math.min(humansCount, botsCount);

        if (sCount <= 0) {
            int[] res = new int[bCount];
            Arrays.fill(res, biggerValue);
            return res;
        }

        int coef = bCount / sCount;

        int[] res = new int[bCount + sCount];
        for (int i = 0; i < bCount + sCount; ) {
            int tmp = coef;
            while (tmp > 0) {
                tmp --;
                res[i] = biggerValue;
                i ++;
            }
            res[i] = smallerValue;
            i ++;

        }
        return res;
    }

    // My solution
    // O(H+B) time and space
    int[] getArrangement(int humansCount, int botsCount) {
        int biggerValue = humansCount >= botsCount ? 1 : 0;
        int smallerValue = humansCount >= botsCount ? 0 : 1;
        int bCount = Math.max(humansCount, botsCount);
        int sCount = Math.min(humansCount, botsCount);

        int[] res = new int[bCount + sCount];
        Arrays.fill(res, biggerValue);

        if (sCount <= 0) {
            return res;
        }

        double coef = (bCount + sCount) * 1.0 / sCount; // every N values put S

        for (double i = coef - 1; i < bCount + sCount; i += coef) {
            res[(int)Math.round(i)] = smallerValue;
        }
        return res;
    }
}
