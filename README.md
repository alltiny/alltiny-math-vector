alltiny's math-vector is a Java module providing classes for Vector and Matrix supporting common operations. Both support any number of dimensions. I moved both classes into this module for better reusability. Maybe it will become handy for you too? - Let me know.

### Supported Vector Operations ###
- determine vector's length
- normalize vector
- add or substract two vectors
- scale vector by a scalar
- determine scalar product of two vectors
- determine cross product of two vectors

### Supported Matrix Operations
- multiply vector with the matrix
- add two matrices
- multiply two matrices
- retrieve the row or column vector of a matrix

## How to build?
alltiny-math-vector uses [gradle] for building. To compile and publish to your local maven repository use:
```sh
cd alltiny-math-vector
gradle publishToMavenLocal
```
the built module can be retrieve via:
```sh
<groupId>org.alltiny</groupId>
<artifactId>math-vector</artifactId>
<version>1.0.0</version>
```

## How to set up my development environment?
Depending on whether you use IntelliJ IDEA or Eclipse, [gradle] can create the project files for you:
* for IntelliJ IDEA
```sh
cd alltiny-math-vector
gradle idea
```

* for Eclipse
```sh
cd alltiny-math-vector
gradle eclipse
```

---
[gradle]:http://www.gradle.org - An open source building tool, much like maven, but rather more flexible.