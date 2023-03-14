**SENG 438 - Software Testing, Reliability, and Quality**

**Lab. Report \#4 – Mutation Testing and Web app testing**

| Group \#:      |     |
| -------------- | --- |
| Student Names: |     |
|                |     |
|                |     |
|                |     |

# Introduction

# Analysis of 10 Mutants of the Range class

## Range.getCentralValue()

```java
public double getCentralValue() {
        return this.lower / 2.0 + this.upper / 2.0;
    }
```

1. getCentralValue : Replaced double division with multiplication → KILLED
2. getCentralValue : Replaced double division with multiplication → KILLED
3. getCentralValue : Replaced double addition with subtraction → KILLED
4. getCentralValue : replaced double return with 0.0d for org/jfree/data/Range::getCentralValue → KILLED

## Range.intersects()

```java
public boolean intersects(Range range) {
        return intersects(range.getLowerBound(), range.getUpperBound());
    }
```

5. intersects : replaced boolean return with false for org/jfree/data/Range::intersects → KILLED
6. intersects : replaced boolean return with true for org/jfree/data/Range::intersects → KILLED

## Range.contains()

```java
public boolean contains(double value) {
        return (value >= this.lower && value <= this.upper);
    }
```

7. contains : replaced boolean return with false for org/jfree/data/Range::contains → KILLED
8. contains : replaced boolean return with true for org/jfree/data/Range::contains → KILLED

9. contains : negated conditional → KILLED
10. contains : negated conditional → KILLED

# Report all the statistics and the mutation score for each test class

## RangeTest from assignment 3

<img src="./media/RangeTest_mut_A3.PNG" />

- mutation score is 67%
- line coverage is 91%
- test strength is 73%

**DataUtilities Mutation Summary With old given Tests**

![image](https://user-images.githubusercontent.com/72403820/224467896-14401670-1e0e-4628-b7d3-0768333c2c5c.png)

![image](https://user-images.githubusercontent.com/72403820/224467912-1e8c936b-5fcd-4faa-9d57-5eb3678f8fef.png)

**DataUtilities Mutation Summary With Assignment 3 Tests**

![image](https://user-images.githubusercontent.com/72403820/224466714-06433873-8d38-4cce-abcb-b5d5717433a7.png)

![image](https://user-images.githubusercontent.com/72403820/224466735-d6599a06-db1b-4c58-b5be-7551ea0de6ee.png)

**DataUtilities Mutation Summary With New Updated Tests**

![image](https://user-images.githubusercontent.com/72403820/224470855-30af52f4-38f3-4fa6-941e-21890cfcc5d8.png)

![image](https://user-images.githubusercontent.com/72403820/224470871-5d7f7d2e-5ddd-459d-b9c7-7095e425eed3.png)


# Analysis drawn on the effectiveness of each of the test classes

# A discussion on the effect of equivalent mutants on mutation score accuracy

Equivalent Mutants reduce the accuracy of the mutation score because the survival of a equivalent mutant does not indicate poor tests but will still result in a lower ratio of mutants to killed mutants. For example one equivalent mutant we found in DataUtilities was mutant 104. In the DataUtilities source code we have the following line as a part of equal():
`for (int i = 0; i < source.length; i++)`
And Mutant 104 replaces the `<` with `!=`. This does not change the functionality since in both cases the loop will iterate by 1 and eventually stop when `i==source.length` and since i is only ever iterated by 1, it will never go above source.length which means both loops are functionally equivalent and the mutant will survive. This mutant surviving will lower the mutant score despite it being functionally indentical to the expected behaviour.

# A discussion of what could have been done to improve the mutation score of the test suites

- For Range class, we lose most of the mutation score in the mutants called "changed condition boundary". However, the tool does not provide the detail of the mutant. Thus, we need to conduct deep analysis of some condition statement to cover almost every possible outcome to earn the score.

# Why do we need mutation testing? Advantages and disadvantages of mutation testing

# Explain your SELENUIM test case design process

# Explain the use of assertions and checkpoints

# how did you test each functionaity with different test data

# Discuss advantages and disadvantages of Selenium vs. Sikulix

# How the team work/effort was divided and managed

# Difficulties encountered, challenges overcome, and lessons learned

# Comments/feedback on the lab itself
