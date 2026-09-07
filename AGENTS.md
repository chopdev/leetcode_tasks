# LeetCode Solution Intake

When the user sends a solved LeetCode implementation, add it to this repository and finish the Git workflow without asking them to repeat these instructions.

## Input and classification

- Treat the supplied LeetCode URL, title/number, and implementation as the source of truth.
- Identify the primary algorithm/topic and use the matching existing top-level topic directory (for example, `monotonic_stack`, `sliding_window`, `graph`, or `dynamic_programming`). Create a new topic directory only when no suitable existing category applies.
- Create the solution directory as `<topic>/<problem_number>_<lowercase_problem_slug>/`. For example: `monotonic_stack/402_remove_k_digits/`.
- If the task is not a numbered LeetCode problem, use a clear lowercase underscore-separated slug consistent with nearby solutions.

## Solution file

- Create `Solution.java` in the solution directory.
- Preserve the user's implementation and algorithm. Add only what is needed to make it a complete, compilable LeetCode-style Java submission: imports, the `class Solution` wrapper, and harmless formatting/syntax fixes.
- Start every file with a block comment containing the problem number and title, the canonical LeetCode URL, and a concise problem description beneath the URL. Prefer a short paraphrase; use user-supplied problem text when available rather than copying a full external statement.
- Include only imports actually required by the submitted code.
- Keep the class in the default package and name it `Solution`.

Example header:

```java
/*
402. Remove K Digits
https://leetcode.com/problems/remove-k-digits/description/

Given a non-negative integer represented as a string, remove k digits so the
remaining number is as small as possible.
*/
```

## Verification and delivery

- Inspect the working tree before editing and do not stage or alter unrelated user changes.
- Verify the new Java file when practical (for example, compile it into a temporary output directory so no `.class` files are left in the repository).
- Stage only the newly added solution directory for a solution intake.
- Commit with the solution directory basename as the message, such as `402_remove_k_digits`.
- Push the current branch to `origin` after a successful commit.
- Report the created path, verification result, commit, and push outcome concisely.
