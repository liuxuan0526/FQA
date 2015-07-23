package tools.nlp.chinese;

import java.util.Properties;

import edu.stanford.nlp.ie.crf.CRFClassifier;

public class segmenter {
	private static CRFClassifier seg=null;
	
	private static CRFClassifier getSegmentClassifier() {
		Properties props = new Properties();  
        props.setProperty("sighanCorporaDict", "edu/stanford/nlp/models/segmenter/chinese/");  
        props.setProperty("serDictionary", "edu/stanford/nlp/models/segmenter/chinese/dict-chris6.ser.gz");  
        props.setProperty("inputEncoding", "UTF-8");  
        props.setProperty("sighanPostProcessing", "true");  
        CRFClassifier classifier = new CRFClassifier(props);
        classifier.loadClassifierNoExceptions("edu/stanford/nlp/models/segmenter/chinese/ctb.gz", props);  
        classifier.flags.setProperties(props);  
        return classifier;
	}
	
	public static void loadsegmenter() {
		if(seg==null) seg=getSegmentClassifier();
	}
	
	public static String[] doseg(String rawtext) {
		if(seg==null) seg=getSegmentClassifier();
		String[] strs = (String[]) seg.segmentString(rawtext).toArray();  
		return strs;
	}
}
