# HW3-Weka
Create a single perceptron network... No it's not a transformer.

To compile and run this script run the following:
```
git clone https://github.com/CodeCorrupt/HW3-Weka.git
git checkout run_from_term
javac -cp weka.jar *.java
java -cp .:weka.jar SimpleWeka <<Input File>> <<# of training epochs>> <<Learning Constant>>
```

The input file *must* match the same format at `simple.arff`
