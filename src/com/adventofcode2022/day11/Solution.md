
#### Note: This page is only to record by understanding and doesn't contain the exact mathematical definitions.

Part 1 was solved with Int types. But, the calculations are reaching bigger numbers so all functions and parameters were changed to Long for Part 2

Part 2 was solved with the help of this [blog](https://aoc.just2good.co.uk/2022/11#part-2) which explains modulo congruence

According to modulo congruence, 'a' is congruent to 'b' if they have the same remainder when divided by an integer 'n'

When the worry level is not divided by a relief value, worry level will be a big number

For instance, from the blog
```
Round 20, monkey 5, first item: 2428899730574776919351574517249403431446194393675444403748819090519729633297989697799769895877
```
The remainder is what decides where the item will be thrown to, and that decides the inspection counter.

So, what we are interested here is to find a smaller number that is congruent to the worry level (w).

Instead of 'w', we save 'w mod n' such that the remainder is the same when divided by the test operation divisor.

Here we need to find the value of 'n', and it is the LCM of all the divisors of our test operation. In the test input data, all our divisors are prime numbers which could just be a multiplication of all of them.

This procedure helps to avoid worry levels from reaching ridiculous levels.