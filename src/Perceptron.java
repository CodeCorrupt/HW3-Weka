/*
 * CAP 4630 - Fall 2015
 * 11/20/2015
 * Brian Boudreaux
 * Tyler Hoyt
 * 
 * Programming Assignment 3
 * 
 * This is our Simple Perceptron for Weka. 
 */

import weka.classifiers.Classifier;
import weka.core.Instance;
import weka.core.Instances;
import weka.core.OptionHandler;
import weka.core.Utils;

public class Perceptron extends Classifier implements OptionHandler
{
	final int BIAS = 1;
	final double THRESHOLD = 0;
	
	String inFile = null;
	int numEpochs = 0;
	double learnConst = 0;
	
	int weightUpdates = 0;
	double weights[] = null;
	
	@Override
	public void buildClassifier(Instances data) throws Exception 
	{
        // The buildClassifier() method will train the classifier using the Perceptron algorithm for
        // the number of epochs, using the learning rate constant, and against the data set, all as
        // specified by by the values retrieved by the setOptions method
		
		getCapabilities().testWithFail(data);
		
		data = new Instances(data);
		data.deleteWithMissingClass();
		
		weights = new double[data.numAttributes()];
		for(int i = 0; i < data.numAttributes(); ++i) //numAtributes should be +1 to include bias, -1 to account for the class. 
		{
		    // Weights theoretically (and shown in practice) can be initialized to random values,
			// we chose to use 0.0 for consistent results. 
			weights[i] = 0.0;
		}

		for(int epoch = 0; epoch < numEpochs; ++epoch) //For each epoch
		{
			System.out.printf("Iteration %d: ", epoch);
			
			for(int i = 0; i < data.numInstances(); ++i) // Itterate over each instance
			{
				Instance inst = data.instance(i);
				double est = predict(inst);              // Perdict the output using current weights
				double act = inst.classValue() * 2 - 1; // Convert the actual 0 or 1 into -1 or 1
				if(est != act)                          // If predicted is not actual
				{
					System.out.printf("0");             // print a 0
					double pm = 0;
					
					if(act == -1 && est == 1)           // If estemate was too high
					{
						pm = -2;                        // Adjust weight down
					}
					else if(act == 1 && est == -1)      // If estemate is low
					{
						pm = 2;                         // Adjust weight up
					}
					
					weights[0] += pm * learnConst;      // Math to adjust Bias weight
					for(int att = 1; att < weights.length; ++att)
					{
						weights[att] += pm * learnConst * inst.value(att - 1); // Peceptron math
					}
					weightUpdates++;                    // count times we updated weights
				}
				else
				{
					System.out.printf("1");             // If we predicted right print a 1
				}
			}

			System.out.printf("\n");                    // new line for fomatting
		}
	}
	
	@Override
	public double[] distributionForInstance(Instance instance) throws Exception
	{
        // The distributionForInstance() method will simply translate the two output classification
        // classes in the input data file (which may be letters or numbers) to the values zero and
        // one, which are used by the Weka API methods to distinguish the classification classes.
		double[] result = new double[2];
		if (predict(instance) == 1) {
			result[0] = 0;
			result[1] = 1;
		} else {
			result[0] = 1;
			result[1] = 0;
		}		
		return result;
	}
	
	public void setOptions(String[] options) throws Exception
	{
		// Use String Utils.getOption(options) to remove each option one at a time
		// After removed custom args. pass the remaining into super class to handle
		
		// F = input file (string)
		String argString = "";
		argString = Utils.getOption('F', options);
	    if (argString.length() != 0) {
	    	inFile = argString;
	    } else {
	    	inFile = null;
	    }
	    // I = number of training epochs (int)
	    argString = Utils.getOption('I', options);
	    if (argString.length() != 0) {
	    	numEpochs = Integer.parseInt(argString);
	    } else {
	    	numEpochs = 1;
	    }
		// L = learning constant (decimal value)
	    argString = Utils.getOption('L', options);
	    if (argString.length() != 0) {
	    	learnConst = (new Double(argString)).doubleValue();
	    } else {
	    	learnConst = 1.0;
	    }
		
		super.setOptions(options);
	}
	
	public String toString()
	{
        // The toString() method must report the following data in the format shown in the out-simple.txt
        // sample output file: (a) the source file; (b) the number of iterations (epochs); (c) the
        // learning rate used; (d) the total number of time that weight updates were performed during
        // training; and (e) the final weight values.
		String ret = "";
		ret += "Source file: " + inFile;
		ret += "\n";
		ret += "\n";
		ret += "Number of iterations: " + numEpochs;
		ret += "\n";
		ret += "Learning rate: " + learnConst;
		ret += "\n";
		ret += "Total # weight updates:" + weightUpdates;
		ret += "\n";
		ret += "\n";
		ret += "Final weights: ";
		ret += "\n";
		ret += showWeights();
		return ret;
	}
	
	private String showWeights()
	{
		String ret = "";
		
		ret += String.format("Bias Weight:\n\t%.2f\n", weights[0]);
		ret += "Input Weights:\n";
		for(int i = 1; i < weights.length; ++i)
		{
			ret += String.format("\t%.2f\n", weights[i]);
		}
		
		return ret;
	}
	
	private double predict(Instance inst)
	{
		double sum = 0;
		int i = 0;
		sum += BIAS * weights[i++];             // Add bias and weight to sum
		for (; i<weights.length; i++)
		{
			sum += weights[i] * inst.value(i-1);// Add the rest of weighted elements to su
		}
		return (sum >= THRESHOLD) ? 1 : -1;     // If the sum is over the threshold (0) return 1 else -1
	}
}
