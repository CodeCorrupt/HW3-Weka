import weka.classifiers.Classifier;
import weka.core.Instance;
import weka.core.Instances;
import weka.core.OptionHandler;

public class Perceptron extends Classifier implements OptionHandler 
{
	@Override
	public void buildClassifier(Instances data) throws Exception 
	{
		//TODO
	}

	public double[] distrubutionForInstance(Instance instance)
	{
		//TODO
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
		
		//Use String Utils.getOption(options) to remove each option one at a time
		//and do what you want with them.
		
		super.setOptions(options);
	}
	
	public String toString()
	{
		//TODO
		return "Perceptron";
	}
}
