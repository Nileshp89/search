package com.infy.nlpprocessor;

import java.io.File;
import java.io.IOException;
import java.io.StringReader;

import javax.annotation.PostConstruct;

import opennlp.tools.cmdline.postag.POSModelLoader;
import opennlp.tools.postag.POSModel;
import opennlp.tools.postag.POSSample;
import opennlp.tools.postag.POSTaggerME;
import opennlp.tools.tokenize.WhitespaceTokenizer;
import opennlp.tools.util.ObjectStream;
import opennlp.tools.util.PlainTextByLineStream;

import org.springframework.stereotype.Component;

@Component
public class TextPreProcessor {
	
	POSTaggerME tagger;
	
	@PostConstruct
	public void init() throws IOException{
		
		POSModel model = new POSModelLoader()	
		.load(new File("en-pos-maxent.bin"));
		
		 tagger = new POSTaggerME(model);
		 
		String input = "Hi. How are you? This is Mike.";
		
		
		
		
		
	}
	
	
	public void POSTagData(String input) throws IOException{
		
		
		ObjectStream<String> lineStream = new PlainTextByLineStream(
				new StringReader(input));
	 
		
		String line;
		while ((line = lineStream.read()) != null) {
	 
			String whitespaceTokenizerLine[] = WhitespaceTokenizer.INSTANCE
					.tokenize(line);
			String[] tags = tagger.tag(whitespaceTokenizerLine);
	 
			POSSample sample = new POSSample(whitespaceTokenizerLine, tags);
			System.out.println(sample.toString());
	 
			
			
			
		}
		
	}	
	
	
	
	

}
