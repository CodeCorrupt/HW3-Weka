import weka.classifiers.Classifier;
import weka.core.Instance;
import weka.core.Instances;
import weka.core.OptionHandler;
import weka.core.Utils;

public class Perceptron extends Classifier implements OptionHandler
{
	
	String inFile = null;
	int numEpochs = 0;
	double learnConst = 0;
	
	@Override
	public void buildClassifier(Instances data) throws Exception 
	{
		//TODO
        // The buildClassifier() method will train the classifier using the Perceptron algorithm for
        // the number of epochs, using the learning rate constant, and against the data set, all as
        // specified by by the values retrieved by the setOptions method
	}

	public double[] distrubutionForInstance(Instance instance)
	{
        // The distributionForInstance() method will simply translate the two output classification
        // classes in the input data file (which may be letters or numbers) to the values zero and
        // one, which are used by the Weka API methods to distinguish the classification classes.
		double[] result = new double[2];
		if (predict(instance) == 1) {
			result[0] = 1;
			result[1] = 0;
		} else {
			result[0] = 0;
			result[1] = 1;
		}		
		return result;
	}
	
	public void setOptions(String[] options) throws Exception
	{
		//TODO
		// The buildClassifier() method must report intermediate results as shown in the out-simple.txt
        // file furnished with this assignment. Specifically, for each training epoch, the classifier
        // must report the epoch number (e.g., “Iteration 0:”) followed by a binary string containing
        // a value of 1 for each data instance that is successfully classified, or a value of 0 if
        // classification is unsuccessful, requiring that the weights be updated.

		//Use String Utils.getOption(options) to remove each option one at a time
		//and do what you want with them.
		
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
		ret += "Source file: " + sourceFile;
		ret += "\n";
		ret += "\n";
		ret += "Number of iterations: " + numIterations;
		ret += "\n";
		ret += "Learning rate: " + lernRate;
		ret += "\n"
		ret += "Total # weight updated:" + weightUpdates;
		ret += "\n"
		ret += "\n"
		ret += "Final weights: " + showWeights();
		return ret;
	}
}
