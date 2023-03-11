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

# Analysis drawn on the effectiveness of each of the test classes

# A discussion on the effect of equivalent mutants on mutation score accuracy

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
