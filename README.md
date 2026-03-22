# Small Java Programs

A collection of Java exercises covering data structures, algorithms, recursion, streams, file I/O, math, and date/time utilities.

## Quick Start

### Prerequisites
- Java 11+
- JUnit 4.13.2 (already in [lib/](lib/))

### Compile

```bash
javac -cp ".:lib/junit-4.13.2.jar:lib/hamcrest-core-1.3.jar" *.java
```

### Run Tests

```bash
TEST_CLASSES=($(ls *Test*.java | sed 's/.java//'))
java -cp ".:lib/junit-4.13.2.jar:lib/hamcrest-core-1.3.jar" org.junit.runner.JUnitCore ${TEST_CLASSES[@]}
```

## Program Visual Gallery

---

### [AccessCountArrayList.java](AccessCountArrayList.java)

#### What is an Access Count ArrayList?
An `AccessCountArrayList` is a regular Java `ArrayList` with one extra feature: it counts how many times each element position has been accessed. Every call to `get(i)` or `set(i, value)` increments a counter for that index, allowing you to track which elements are touched most often.

#### How Access Tracking Works
The class extends `ArrayList` and overrides `get` and `set`. Before delegating to the parent, it increments an access counter stored in a parallel list.

##### Tracking Steps
- A second list stores one integer counter per element, initialized to zero
- Every `get(i)` call increments `counter[i]`, then returns the element
- Every `set(i, value)` call increments `counter[i]`, then stores the new value
- `getAccessCount(i)` returns the total number of accesses for that index

![Linked list element structure](https://upload.wikimedia.org/wikipedia/commons/thumb/6/6d/Singly-linked-list.svg/800px-Singly-linked-list.svg.png)

---

### [FileProcessor.java](FileProcessor.java)

#### What is a File Processor?
`FileProcessor` is a reusable template for reading text files line by line. It defines a fixed three-phase skeleton - setup, per-line processing, and teardown - while letting subclasses fill in the logic for each phase. This prevents duplicating the file-reading boilerplate in every program that needs it.

#### The Template Method Pattern
This is an example of the *template method* design pattern: the overall algorithm is fixed in the base class, but individual steps are overridden per use case.

##### Processing Steps
- `startFile()` is called once before the first line is read
- Each line is passed to `processLine(String line)` in order from top to bottom
- `endFile()` is called once after the last line has been processed

![Unix I/O pipeline](https://upload.wikimedia.org/wikipedia/commons/thumb/f/f6/Pipeline.svg/800px-Pipeline.svg.png)

---

### [FilterWriter.java](FilterWriter.java)

#### What is a FilterWriter?
`FilterWriter` is a character-level output filter. It wraps any Java `Writer` and lets you decide - one character at a time - whether to pass each character through to the underlying writer or silently discard it. This is useful for transformations like removing double spaces, stripping punctuation, or collapsing repeated characters.

#### How the Filter Works
The decision is made by a `BiPredicate<Character, Character>` that receives the **previous** character and the **current** character as inputs. If the predicate returns `true`, the character is forwarded; if `false`, it is dropped.

##### Filtering Steps
- The writer starts with no previous character (`null`)
- For each incoming character, the predicate `test(prev, current)` is evaluated
- If the predicate returns `true`, the character is forwarded to the wrapped writer
- The previous-character pointer is updated to the current character regardless of outcome

![Character filter pipeline](https://upload.wikimedia.org/wikipedia/commons/thumb/f/f6/Pipeline.svg/800px-Pipeline.svg.png)

---

### [Fraction.java](Fraction.java)

#### What is a Fraction?
A fraction represents an exact rational number - a numerator divided by a denominator. Unlike floating-point numbers, fractions never lose precision because all arithmetic is exact. `Fraction` stores both values as `BigInteger`, so arbitrarily large numerators and denominators are supported without overflow.

In mathematical terms: a fraction `p/q` where `gcd(p, q) = 1` is said to be in *lowest terms*.

#### Fraction Normalization Using GCD
After every arithmetic operation, the result is automatically reduced to lowest terms using the greatest common divisor (GCD) via the Euclidean algorithm.

For example: `6/8` → `gcd(6, 8) = 2` → divide both by 2 → `3/4`

##### Normalization Steps
- Compute `gcd = BigInteger.gcd(numerator, denominator)`
- Divide both numerator and denominator by the GCD
- If the denominator is negative, flip the signs of both so the denominator is always positive
- The fraction is now fully reduced and in canonical form

![Euclidean GCD algorithm animation](https://upload.wikimedia.org/wikipedia/commons/e/e2/Euclidean_algorithm_252_105_animation_flipped.gif)

#### Fraction Arithmetic
Once normalized, fractions support exact addition, subtraction, multiplication, and division:
- `a/b + c/d = (a·d + c·b) / (b·d)`, then reduce
- `a/b × c/d = (a·c) / (b·d)`, then reduce

---

### [Head.java](Head.java)

#### What is the Head Component?
`Head` is a custom Swing painting component that draws a cartoon face. The face has two states: **neutral** (resting expression) and **happy** (smiling). The expression switches automatically in response to mouse movement - no clicking required.

#### How the Expression Changes
Java's `MouseListener` interface allows the component to react to mouse events without polling. The component repaints itself each time its state changes.

##### Event Steps
- Mouse enters the component bounds → state switches to **happy** → `repaint()` is called
- Mouse exits the component bounds → state switches to **neutral** → `repaint()` is called
- `paintComponent(Graphics g)` reads the current state and draws the appropriate face

---

### [HeadMain.java](HeadMain.java)

#### What is HeadMain?
`HeadMain` is the application entry point that opens a Swing window containing four `Head` components arranged in a 2×2 grid. Each face responds to mouse events independently.

##### Launch Steps
- Create a `JFrame` with a title
- Set the layout manager to `GridLayout(2, 2)`
- Instantiate and add four `Head` panels to the frame
- Call `pack()` and `setVisible(true)` to display the window

---

### [Lissajous.java](Lissajous.java)

#### What is a Lissajous Curve?
A Lissajous curve is the path traced by a point whose `x` and `y` positions each oscillate sinusoidally but at different frequencies and with a phase offset between them. The shape depends entirely on the ratio of the two frequencies and the phase angle delta.

In mathematical terms:
- `x(t) = sin(a·t + δ)`
- `y(t) = sin(b·t)`

When `a/b` is a simple integer ratio like 1:2 or 3:2, the curve closes into a recognizable figure-eight or pretzel shape. Irrational ratios produce curves that never close.

#### Plotting the Curve
The curve is sampled at many values of `t` from 0 to 2π. Each `(x, y)` pair is scaled to fit the panel and connected to the previous point with a line segment.

##### Drawing Steps
- Accept parameters `a`, `b` (frequency integers) and `delta` (phase offset in radians)
- Iterate `t` from 0 to 2π in small increments
- For each `t`, compute `x = sin(a·t + delta)` and `y = sin(b·t)`
- Scale the unit-range coordinates to the panel's pixel dimensions
- Connect consecutive points using `drawLine`

![Lissajous curves animation](https://upload.wikimedia.org/wikipedia/commons/a/a8/Scratch-lissajous.gif)

---

### [LissajousMain.java](LissajousMain.java)

#### What is LissajousMain?
`LissajousMain` is the entry point that constructs the Swing window and adds the `Lissajous` drawing panel to it.

##### Launch Steps
- Create a `JFrame`
- Instantiate a `Lissajous` panel with the desired `a`, `b`, and `delta` parameters
- Add the panel to the frame and call `setVisible(true)`

---

### [ArrayAlgorithms.java](ArrayAlgorithms.java)

#### What is ArrayAlgorithms?
`ArrayAlgorithms` is a set of array manipulation exercises exploring combinatorics, 2D grid patterns, and order statistics.

#### Falling Power
The falling power `n^(k)` is the descending product `n × (n−1) × (n−2) × … × (n−k+1)`. It counts the number of ordered arrangements (permutations) of `k` items chosen from `n`.

For example: `fallingPower(5, 3) = 5 × 4 × 3 = 60`

#### Creating a ZigZag Matrix
A zigzag matrix fills a 2D grid with consecutive integers, but alternates the fill direction on each row - left-to-right on even rows, right-to-left on odd rows.

##### ZigZag Fill Steps
- Even-numbered rows are filled left to right with consecutive integers
- Odd-numbered rows are filled right to left with consecutive integers
- A single counter `start` increments with every cell regardless of direction

#### Counting Inversions
An inversion in an array is a pair `(i, j)` where `i < j` but `arr[i] > arr[j]`. The inversion count measures how far an array is from being sorted - a sorted array has zero inversions.

##### Inversion Count Steps
- Compare every pair of positions `(i, j)` where `i < j`
- If `arr[i] > arr[j]`, the pair is an inversion - increment the count
- Return the total count after all pairs are checked

![Insertion sort revealing inversions](https://upload.wikimedia.org/wikipedia/commons/0/0f/Insertion-sort-example-300px.gif)

---

### [StringAndSequence.java](StringAndSequence.java)

#### What is StringAndSequence?
`StringAndSequence` covers string deduplication, counting safe squares on a chessboard with rooks, and generating the Recaman integer sequence.

#### Removing Consecutive Duplicates
`removeDuplicates` collapses any run of identical consecutive characters into a single character. For example: `"aabbbcc"` → `"abc"`.

#### Unique Characters
`uniqueCharacters` returns a string containing each character from the input exactly once, in the order it first appeared. For example: `"banana"` → `"ban"`.

#### Safe Squares for Rooks
Given a chessboard of size `n×n` with rooks already placed, a square is **safe** if no rook can attack it. A rook attacks every square in its row and its column. The number of safe squares equals `(rows with no rook) × (columns with no rook)`.

##### Rook Safety Steps
- Scan the board and mark every row and every column that contains at least one rook
- Count the number of unmarked rows (`safeRows`) and unmarked columns (`safeCols`)
- Multiply: `safeRows × safeCols` is the total number of safe squares

![Rook attack patterns on a chessboard](https://upload.wikimedia.org/wikipedia/commons/5/54/Rook_%28chess%29_movements.gif)

#### The Recaman Sequence
The Recaman sequence is defined by a deceptively simple rule: at each step `i`, try to go *backwards* by `i`. If the result is positive and has not appeared in the sequence before, use it; otherwise go *forwards* by `i`.

The sequence begins: 0, 1, 3, 6, 2, 7, 13, 20, 12, 21, 11, …

##### Recaman Steps
- Set `a[0] = 0`
- For each step `i ≥ 1`, compute `back = a[i−1] − i`
- If `back > 0` and `back` has not appeared in the sequence yet, set `a[i] = back`
- Otherwise set `a[i] = a[i−1] + i` (go forwards)

![Recaman sequence arc diagram](https://upload.wikimedia.org/wikipedia/commons/thumb/7/70/Recaman%27s_sequence.svg/800px-Recaman%27s_sequence.svg.png)

---

### [ReversalAlgorithms.java](ReversalAlgorithms.java)

#### What is ReversalAlgorithms?
`ReversalAlgorithms` is a set of reversal exercises: reversing subarrays in place, scrambling strings with a sequence of pancake flips, and reversing the vowels inside a string while leaving consonants untouched.

#### Pancake Scramble
In a pancake scramble, you repeatedly take the first `k` characters of the string and reverse them, starting at `k = 2` and incrementing `k` each round. It is related to pancake sorting but used here as a deterministic string transformation.

For example: `"abcd"` →
- k=2: reverse first 2 → `"bacd"`
- k=3: reverse first 3 → `"acbd"`
- k=4: reverse first 4 → `"dbca"`

##### Pancake Scramble Steps
- Start with `k = 2`
- Reverse the first `k` characters of the current string
- Increment `k` and repeat until `k` equals the full length of the string

![Pancake sort animation](https://upload.wikimedia.org/wikipedia/commons/0/04/PancakeSort_jaredwf.gif)

#### Reversing Ascending Subarrays
This method finds every maximal ascending run in the array and reverses it in place. An ascending run is a contiguous sequence where each element is greater than the one before it.

#### Reversing Vowels
The vowels in the string are collected in order, reversed, and put back into the same vowel positions. All consonants stay exactly where they are. The case of each vowel slot in the original string is preserved - if position `i` held an uppercase vowel, it still holds an uppercase vowel after the reversal.

For example: `"Hello World"` → vowels are `e, o, o` → reversed: `o, o, e` → result: `"Hollo Werld"`

---

### [ListAlgorithms.java](ListAlgorithms.java)

#### What is ListAlgorithms?
`ListAlgorithms` contains a sliding-window median filter, a combinatorial number theory function, and a custom frequency-based sorter.

#### Running Median of Three
At each position `i ≥ 2`, the median of the three consecutive elements `items[i−2]`, `items[i−1]`, `items[i]` is computed and emitted. This acts as a smoothing filter that suppresses isolated spikes in a sequence.

For example: `[1, 5, 3, 7, 2]` → `[1, 5, 3, 5, 3]`

##### Median-of-Three Steps
- The first two elements are passed through unchanged
- For each subsequent element, identify the minimum and maximum of the three-element window
- The median is `sum − min − max` (the middle value without sorting)

![Running median / sliding window](https://upload.wikimedia.org/wikipedia/commons/4/4d/Moving_average_sine_and_polynom_with_a_larger_interval.gif)

#### First Missing Positive
Returns the smallest positive integer (1, 2, 3, …) that does not appear in the list. For example: `[3, 1, 4, 1, 5, 9]` → `2`.

#### Sort by Element Frequency
Sorts a list so that the most frequent elements come first. Elements with equal frequency are placed in ascending numeric order. For example: `[4, 4, 2, 2, 2, 1]` → `[2, 2, 2, 4, 4, 1]`.

#### Factor Factorial
Computes the complete prime factorization of `n!` by factorizing every integer from 2 to `n` and merging all prime factors into one sorted list.

---

### [BigIntegerProblems.java](BigIntegerProblems.java)

#### What is BigIntegerProblems?
`BigIntegerProblems` explores two number theory puzzles using `BigInteger`: decomposing any integer as a sum of Fibonacci numbers (Zeckendorf's theorem), and finding the smallest number composed entirely of 7s and 0s that is divisible by `n`.

#### Zeckendorf's Theorem
Every positive integer can be written *uniquely* as a sum of non-consecutive Fibonacci numbers. A greedy algorithm finds this decomposition by always subtracting the largest Fibonacci number that fits.

For example: `100 = 89 + 8 + 3`

##### Fibonacci Sum (Zeckendorf) Steps
- Extend the Fibonacci sequence until the largest term is ≥ `n`
- Greedily subtract the largest Fibonacci number `≤ n` from `n` and record it
- Repeat until `n` reaches zero
- Return the collected Fibonacci numbers in descending order

![Zeckendorf decomposition](https://upload.wikimedia.org/wikipedia/commons/thumb/d/d2/Zeckendorf_representations.svg/800px-Zeckendorf_representations.svg.png)

#### Seven-Zero Numbers
`sevenZero(n)` finds the smallest positive integer consisting only of the digits `7` and `0` (such as 7, 77, 700, 770, 7700, …) that is exactly divisible by `n`.

##### Seven-Zero Steps
- Generate candidate numbers in order: 7, 77, 700, 770, 7000, 7700, …
- For each candidate, check `candidate mod n == 0` using `BigInteger.mod()`
- Return the first candidate that passes the divisibility check

---

### [Backtracking.java](Backtracking.java)

#### What is Backtracking?
`Backtracking` solves two combinatorial problems using *recursive backtracking*: expressing a number as a sum of distinct perfect cubes, and generating all strings of a given length that avoid a list of forbidden substrings.

#### What is Recursive Backtracking?
Backtracking builds a solution one choice at a time. After each step it checks whether the partial solution can still lead to a valid answer. If not, it *backtracks* - undoes the last choice and tries the next alternative. This systematically explores all possibilities without re-examining dead ends.

##### Backtracking Steps
- Start with an empty partial solution
- Try each possible extension of the current partial solution
- If any constraint is violated, immediately undo and try the next option
- If all constraints are satisfied and the solution is complete, record it
- Continue until all branches have been explored

![N-queens backtracking animation](https://upload.wikimedia.org/wikipedia/commons/1/1f/Eight-queens-animation.gif)

#### Sum of Distinct Cubes
`sumOfDistinctCubes(n)` finds a set of distinct positive integers `{c₁, c₂, …}` such that `c₁³ + c₂³ + … = n`.

For example: `36 = 3³ + 2³ + 1³` → returns `[3, 2, 1]`

#### Forbidden Substrings
`forbiddenSubstrings(alphabet, n, tabu)` returns all strings of length `n` over `alphabet` that do not contain any string from the `tabu` list as a contiguous substring. Strings are built character by character and pruned as soon as a forbidden pattern appears.

---

### [GameMath.java](GameMath.java)

#### What is GameMath?
`GameMath` analyzes two mathematical structures: detecting which integers can be written as a sum of two distinct squares, and computing winning and losing positions in a subtraction game.

#### Sum of Two Distinct Squares
`sumOfTwoDistinctSquares(n)` returns a boolean array where `result[k]` is `true` if `k` can be written as `i² + j²` for two *distinct* positive integers `i ≠ j`.

For example: `5 = 1² + 2²`, `13 = 2² + 3²`.

##### Detection Steps
- Iterate over all pairs `(i, j)` where `1 ≤ i < j`
- If `i² + j² < n`, mark `result[i² + j²] = true`
- Stop once the smallest possible sum for the current `i` already exceeds `n`

#### The Subtract Square Game
Two players alternate subtracting any perfect square (1, 4, 9, 16, …) from a shared positive integer. The player who reduces the number to zero wins. `subtractSquare(n)` labels every position from 0 to `n−1` as a **winning** (W) or **losing** (L) position.

##### Game Analysis Steps
- `position[0]` is a **losing** position - the player to move has no valid move
- For each `i > 0`, try subtracting every perfect square `k²` where `k² ≤ i`
- If *any* resulting position `i − k²` is a **losing** position for the opponent, then `i` is a **winning** position
- If *all* resulting positions are **winning** for the opponent, then `i` is a **losing** position

---

### [DissimilarityMetrics.java](DissimilarityMetrics.java)

#### What is DissimilarityMetrics?
`DissimilarityMetrics` measures how different two binary vectors are from each other. It implements six dissimilarity metrics used in statistics, data mining, and bioinformatics.

#### Binary Vector Comparison
Given two boolean arrays `v1` and `v2` of the same length, each pair of corresponding bits falls into one of four categories:

- `n11` - both `true` (both vectors agree: present)
- `n10` - v1 `true`, v2 `false` (disagree)
- `n01` - v1 `false`, v2 `true` (disagree)
- `n00` - both `false` (both vectors agree: absent)

The disagreements (`n10 + n01`) drive dissimilarity. A dissimilarity of 0 means the vectors are identical.

#### The Six Dissimilarity Metrics
| Metric | Formula |
|---|---|
| Matching | `(n10 + n01) / total` |
| Jaccard | `(n10 + n01) / (n11 + n10 + n01)` |
| Dice | `(n10 + n01) / (2·n11 + n10 + n01)` |
| Rogers-Tanimoto | `2·(n10+n01) / (n11 + 2·(n10+n01) + n00)` |
| Russell-Rao | `(n10 + n01 + n00) / total` |
| Sokal-Sneath | `2·(n10+n01) / (n11 + 2·(n10+n01))` |

All results are returned as exact `Fraction` values.

![Venn diagram of set overlap](https://upload.wikimedia.org/wikipedia/commons/thumb/9/99/Venn_A_intersect_B.svg/800px-Venn_A_intersect_B.svg.png)

---

### [Polynomial.java](Polynomial.java)

#### What is a Polynomial?
A polynomial is a mathematical expression built from terms, where each term is a coefficient multiplied by a variable raised to a non-negative integer power.

For example: `3x² − 2x + 1`

`Polynomial` stores coefficients as a `BigInteger` array where index `i` holds the coefficient of `xⁱ`. The class is immutable - every arithmetic operation returns a brand new `Polynomial` instead of modifying the original.

#### Polynomial Arithmetic

##### Addition
To add two polynomials, add the coefficients at each matching degree. If one polynomial has fewer terms, treat the missing coefficients as zero.

`(3x² + 2x) + (x² − 1) = 4x² + 2x − 1`

##### Multiplication
To multiply two polynomials, multiply every term of the first by every term of the second and collect like terms. A term `a·xⁱ` times `b·xʲ` contributes `a·b` to the coefficient of `x^(i+j)`.

`(x + 1) × (x − 1) = x² − 1`

##### Evaluation
To evaluate `P(x)` at a specific value, substitute the value and sum all terms. Horner's method computes this efficiently without repeated exponentiation:
`P(x) = (…((aₙ·x + aₙ₋₁)·x + aₙ₋₂)·x + … + a₀)`

![Degree-2 polynomial curve](https://upload.wikimedia.org/wikipedia/commons/thumb/f/f2/Polynomialdeg2.svg/800px-Polynomialdeg2.svg.png)

---

### [StreamExercises.java](StreamExercises.java)

#### What are Java Streams?
Java Streams provide a declarative way to process sequences of data. Instead of writing explicit loops, you describe a *pipeline* of operations - filter, transform, sort, collect - and the Stream API applies them in order. Streams do not store data; they describe what should happen to it.

#### The Stream Pipeline
Every stream has three parts:
1. A **source** (a file, a list, a generator)
2. Zero or more **intermediate operations** that transform the data (lazy - not executed until needed)
3. A **terminal operation** that triggers execution and produces a result

##### Pipeline Steps
- **Source**: read all lines from a text file into a stream
- **Tokenize**: split each line into words using `flatMap(line -> Arrays.stream(line.split(...)))`
- **Normalize**: convert each token to lowercase with `map(String::toLowerCase)`
- **Filter**: keep only tokens that match a predicate with `filter(...)`
- **Distinct**: remove duplicate tokens with `distinct()`
- **Sort**: alphabetically order results with `sorted()`
- **Collect**: gather into a `List` with `collect(Collectors.toList())`

![Software pipeline diagram](https://upload.wikimedia.org/wikipedia/commons/thumb/f/f6/Pipeline.svg/800px-Pipeline.svg.png)

---

### [Tail.java](Tail.java)

#### What is the Tail Command?
The Unix `tail` command prints the last `n` lines of a file. It is useful when a file is too large to read in full and you only care about the most recent entries - such as the latest messages in a log file.

`Tail.java` implements this behavior using a rolling buffer that only ever holds the last `n` lines in memory.

#### The Rolling Buffer Algorithm
Instead of storing the entire file, the buffer is capped at `n` entries. When a new line arrives and the buffer is already full, the oldest line is evicted to make room.

##### Rolling Buffer Steps
- Initialize a buffer with a maximum capacity of `n` lines
- For each new line read, append it to the end of the buffer
- If the buffer size now exceeds `n`, remove the element at the front (the oldest line)
- When the file is fully read, the buffer contains exactly the last `n` lines in order

![Rolling window animation](https://upload.wikimedia.org/wikipedia/commons/4/4d/Moving_average_sine_and_polynom_with_a_larger_interval.gif)

---

### [TimeProblems.java](TimeProblems.java)

#### What is TimeProblems?
`TimeProblems` is a collection of date and time utilities built on the Java 8 `java.time` API. It solves three classic calendar problems: timezone conversion, next-weekday projection, and counting Friday the 13th occurrences in a year.

#### Timezone Conversion
`toMontreal` converts any `ZonedDateTime` into Montreal's local time (`America/Montreal`). Daylight saving time transitions are handled automatically by the `java.time` library - you just specify the target zone and it does the rest.

#### Next Weekday Projection
Given a starting date and a target day of the week (e.g., next Friday), this computes the next occurrence of that weekday at or after the start date. It uses `TemporalAdjusters.nextOrSame(DayOfWeek)` from the `java.time` API.

#### Counting Fridays the 13th
The method counts how many Friday the 13ths occur in a given year by checking every month: construct the 13th of that month and test whether `getDayOfWeek() == FRIDAY`.

![World time zone map](https://upload.wikimedia.org/wikipedia/commons/8/88/Standard_time_zones_of_the_world.png)

---

### [WordCount.java](WordCount.java)

#### What is WordCount?
`WordCount` reads a text file and reports three statistics: the total number of **lines**, the total number of **words**, and the total number of **characters**. It replicates the behavior of the Unix `wc` command.

`WordCount` extends `FileProcessor`, using its three-phase template to process the file one line at a time without duplicating any file-reading logic.

#### How Counting Works
All three counters start at zero. For each line delivered by `FileProcessor`:

##### Counting Steps
- Increment the **line counter** by 1
- Split the line on whitespace and increment the **word counter** by the number of tokens produced
- Increment the **character counter** by the length of the line plus 1 for the newline character
- After all lines are processed, retrieve the totals via `getLines()`, `getWords()`, and `getCharacters()`

---

### [Primes.java](Primes.java)

#### What is a Prime Number?
A prime number has only two factors: one and itself. A factor divides the number
perfectly leaving no remainder behind.

In mathematical terms: number % factor = 0

#### Finding The kth Prime Number
The method `kthPrime` searches for the 1st – 30,000th prime number in less than one
minute from the infinite sequence of prime numbers (Primes.java goes up to 50,000).

##### Sieve of Eratosthenes Algorithm
- Start at the prime number 2 and eliminate all other multiples of 2, thus eliminating composite numbers
- Move on to 3 and eliminate all other multiples of 3
- Move on to 5 and eliminate all other multiples of 5, etc.
- After the list of primes is created, the kth prime is obtained from the list

![Sieve_of_Eratosthenes_animation](https://user-images.githubusercontent.com/59797227/105047827-d26d9f00-5a38-11eb-8242-3ca2cbfda342.gif)

#### Prime Factors of an Integer Algorithm
The returned list of the method needs to contain all the prime factors in
ascending sorted order. For example, if n = 220, [2, 2, 5, 11] is returned.

- If the integer is even, list all occurrences of 2 that divide the integer. The integer n is repeatedly divided by 2 and each successful division is listed.
- If the integer is odd, n is repeatedly divided by 3 and each successful division is listed.
- If the number is not divisible by 3 move on to the next odd prime number until the square root of the integer n is reached.
- When the integer cannot be divided anymore and a prime number is left, list that prime number.

![prime-factorization-calculator](https://user-images.githubusercontent.com/59797227/105271225-21b0ee00-5b65-11eb-83e0-d6bf88bb5f00.gif)

---

## Notes

- Problem statement PDFs and some source assignment artifacts are not included.
- Tests in this repository validate the behavior of all major implementations.

Kudos to the authors of the gifs and diagrams!

https://user-images.githubusercontent.com/59797227/105047827-d26d9f00-5a38-11eb-8242-3ca2cbfda342.gif

https://www.dadsworksheets.com/prime-factorization-calculator.html

https://commons.wikimedia.org/wiki/File:Singly-linked-list.svg

https://commons.wikimedia.org/wiki/File:Pipeline.svg

https://commons.wikimedia.org/wiki/File:Euclidean_algorithm_252_105_animation_flipped.gif

https://commons.wikimedia.org/wiki/File:Scratch-lissajous.gif

https://commons.wikimedia.org/wiki/File:Insertion-sort-example-300px.gif

https://commons.wikimedia.org/wiki/File:Recaman%27s_sequence.svg

https://commons.wikimedia.org/wiki/File:PancakeSort_jaredwf.gif

https://commons.wikimedia.org/wiki/File:MovingAverage.GIF

https://commons.wikimedia.org/wiki/File:Zeckendorf_representations.svg

https://commons.wikimedia.org/wiki/File:Eight-queens-animation.gif

https://commons.wikimedia.org/wiki/File:Venn_A_intersect_B.svg

https://commons.wikimedia.org/wiki/File:Polynomialdeg2.svg

https://commons.wikimedia.org/wiki/File:Standard_time_zones_of_the_world.png
