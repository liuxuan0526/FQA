package test;

import java.util.List;

import org.ansj.domain.Term;
import org.ansj.splitWord.analysis.NlpAnalysis;

public class test {
	public static void main(String[] args) {
		 List<Term> parse = NlpAnalysis.parse("����9ʱ��ϰ��ƽ�����ǿ���ŵ½�������ɽ�����ɽ���Ÿ����������εȣ��ڰ������л�����������ͬ־������ǰ����Ĭ����������ͬ־���������Ϲ�����������ͬ־����һһ���֣���ʾο�ʡ���������������˻�Ȧ��������ͬ־������ʾ������");
		 System.out.println(parse);
	}
}
