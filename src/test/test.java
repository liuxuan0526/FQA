package test;

import java.util.List;

import org.ansj.domain.Term;
import org.ansj.splitWord.analysis.NlpAnalysis;

public class test {
	public static void main(String[] args) {
		 List<Term> parse = NlpAnalysis.parse("上午9时许，习近平、李克强、张德江、刘云山、王岐山、张高丽、胡锦涛等，在哀乐声中缓步来到万里同志的遗体前肃立默哀，向万里同志的遗体三鞠躬，并与万里同志亲属一一握手，表示慰问。江泽民在外地送了花圈，对万里同志逝世表示哀悼。");
		 System.out.println(parse);
	}
}
