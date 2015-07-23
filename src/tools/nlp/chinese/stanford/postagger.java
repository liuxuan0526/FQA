package tools.nlp.chinese.stanford;

import java.util.ArrayList;
import java.util.List;

import edu.stanford.nlp.parser.lexparser.LexicalizedParser;
import edu.stanford.nlp.trees.Tree;

public class postagger {
	private static LexicalizedParser lp=null;
	private static LexicalizedParser getParser() {
		LexicalizedParser tmp;
		String parserModel = "edu/stanford/nlp/models/lexparser/chinesePCFG.ser.gz";
		tmp = LexicalizedParser.loadModel(parserModel);
		return tmp;
	}
	public static void dfs(Tree t, List<String> l) {
		if(t.isPhrasal()||t.isPrePreTerminal()) {
			for (Tree tmpt:t.getChildrenAsList()) {
				dfs(tmpt,l);
			}
		} else if(t.isPreTerminal()) {
			l.add(t.value());
		}
	}
	public static String[] dotag(String[] strs) {
		if(lp==null) lp=getParser();
		StringBuffer buffer=new StringBuffer();
		for (String s:strs) {
			buffer.append(s+" ");
		}
		Tree tree=lp.parse(buffer.toString());
		tree.pennPrint();
		List<String> list=new ArrayList<String>();
		dfs(tree,list);
		String[] ret=new String[list.size()];
		int i=0;
		for (String s:list) {
			ret[i]=s;
			++i;
		}
		return ret;
	}
}
