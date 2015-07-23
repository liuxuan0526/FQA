package tools.nlp.chinese;

import edu.stanford.nlp.ie.crf.CRFClassifier;
import edu.stanford.nlp.util.CoreMap;



public class nerdetector {
	public enum nerReps {
		O, MISC_B, MISC_I, ORG_B, ORG_I, GPE_B, GPE_I, PERSON_B, PERSON_I
	};
	private static CRFClassifier ner=null;
	private static CRFClassifier getNerClassifier() {
		String serializedClassifier = "edu/stanford/nlp/models/ner/chinese.misc.distsim.crf.ser.gz";
		CRFClassifier<CoreMap> ner=CRFClassifier.getClassifierNoExceptions(serializedClassifier);
		return ner;
	}
	public static nerReps[] doner(String[] segtext) {
		StringBuffer buf=new StringBuffer();
		for (String s:segtext) {
			buf.append(s+' ');
		}
		if(ner==null) ner=getNerClassifier();
		String str = ner.classifyWithInlineXML(buf.toString());
		System.out.println(str);
		String[] strs = str.split(" ");
		nerReps[] ret = new nerReps[strs.length];
		nerReps last = nerReps.O;
		for (int i=0; i<strs.length; i++) {
			ret[i]=nerReps.O;
			if(strs[i].startsWith("<MISC>")) {
				ret[i]=nerReps.MISC_B;
				last=ret[i];
			} else if(strs[i].startsWith("<ORG>")) {
				ret[i]=nerReps.ORG_B;
				last=ret[i];
			} else if(strs[i].startsWith("<GPE>")) {
				ret[i]=nerReps.GPE_B;
				last=ret[i];
			} else if(strs[i].startsWith("<PERSON>")) {
				ret[i]=nerReps.PERSON_B;
				last=ret[i];
			}
			if(strs[i].endsWith("</MISC>")) {
				last=nerReps.O;
				if(ret[i]==nerReps.O) {
					ret[i]=nerReps.MISC_I;
				} 
			} else if(strs[i].endsWith("</ORG>")) {
				last=nerReps.O;
				if(ret[i]==nerReps.O) {
					ret[i]=nerReps.ORG_I;
				}
			} else if(strs[i].endsWith("</GPE>")) {
				last=nerReps.O;
				if(ret[i]==nerReps.O) {
					ret[i]=nerReps.GPE_I;
				}
			} else if(strs[i].endsWith("</PERSON>")) {
				last=nerReps.O;
				if(ret[i]==nerReps.O) {
					ret[i]=nerReps.PERSON_I;
				}
			}
			if(ret[i]==nerReps.O&&last!=nerReps.O) {
				if(last==nerReps.MISC_B||last==nerReps.MISC_I) {
					ret[i]=nerReps.MISC_I;
					last=nerReps.MISC_I;
				} else if(last==nerReps.ORG_B||last==nerReps.MISC_I) {
					ret[i]=nerReps.ORG_I;
					last=nerReps.ORG_I;
				} else if(last==nerReps.GPE_B||last==nerReps.GPE_I) {
					ret[i]=nerReps.GPE_I;
					last=nerReps.GPE_I;
				} else if(last==nerReps.PERSON_B||last==nerReps.PERSON_I) {
					ret[i]=nerReps.PERSON_I;
					last=nerReps.PERSON_I;
				}
			}
		}
		return ret;
	}
}
