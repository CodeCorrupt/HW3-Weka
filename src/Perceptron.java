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
		
		return null;
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
