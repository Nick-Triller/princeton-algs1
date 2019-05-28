# Assignments

This directory contains all assignment solutions for Princeton's Algorithms 1 on Coursera.

| Module      | Assignment                             | Specification                                                                        | Grade |
| ----------- | -------------------------------------- | ------------------------------------------------------------------------------------ | ----- |
| assignment1 | Percolation                            | [Specification](http://coursera.cs.princeton.edu/algs4/assignments/percolation.html) | 100 % |
| assignment2 | Deques and Randomized Queues           | [Specification](http://coursera.cs.princeton.edu/algs4/assignments/queues.html)      | 100 % |
| assignment3 | Pattern Recognition (collinear points) | [Specification](http://coursera.cs.princeton.edu/algs4/assignments/collinear.html)   | 100 % |
| assignment4 | 8 Puzzle                               | [Specification](http://coursera.cs.princeton.edu/algs4/assignments/8puzzle.html)     | 100 % |
| assignment5 | Kd-Trees                               | [Specification](http://coursera.cs.princeton.edu/algs4/assignments/kdtree.html)      | 100 % |

## Project structure

All assignments are separate maven modules. 

The root pom.xml (in assignments) aggregates all assignment modules and contains logic 
that creates the submission zip file in the `target` directory on `mvn package`.


## Tests

Unit tests can be executed via maven-surefire-plugin.

```bash
cd assignments
mvn test
```

## Dependencies

All dependencies are defined in the root pom.xml. 
Submodules don't add any additional dependencies.

| GroupID           | ArtefactID           | Description                                                          |
| ----------------- | -------------------- | -------------------------------------------------------------------- |
| org.junit.jupiter | junit-jupiter-engine | Unit test framework                                                  |
| edu.princeton.cs  | algs4                | algs4 provided by the course from https://dl.bintray.com/algs4/maven |
