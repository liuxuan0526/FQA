package tools.nlp.chinese;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import tools.nlp.chinese.nerdetector.nerReps;

public class StringObject {
	public String rawString = null;
	public String[] tokens = null;
	public String[] tags = null;
	public nerReps[] nerreps = null;
	public int length;
	
	public StringObject(String str) {
		rawString = str;
	}
	public boolean construct() {
		tokens=segmenter.doseg(rawString);
		tags=postagger.dotag(tokens);
		nerreps=nerdetector.doner(tokens);
		if(tokens.length==tags.length&&tokens.length==nerreps.length) return true;
		else return false;
	}
	public String toString() {
		String str="";
		for (int i=0;i<tokens.length;i++) {
			str+=tokens[i]+"/"+nerreps[i]+"/"+tags[i]+"\t";
		}
		return str;
	}
	public static void main(String[] args) throws IOException {
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		while(true) {
			String str=br.readLine();
			StringObject so=new StringObject(str);
			if(so.construct()) {
				System.out.println(so.toString());
			} else {
				System.out.println("Construct error!");
			}
		}
	}
}
