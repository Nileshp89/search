package com.infy.nlpprocessor;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

import org.springframework.stereotype.Component;

import opennlp.tools.doccat.DoccatModel;
import opennlp.tools.doccat.DocumentCategorizerEvaluator;
import opennlp.tools.doccat.DocumentCategorizerME;
import opennlp.tools.doccat.DocumentSample;
import opennlp.tools.util.InvalidFormatException;

@Component
public class IssueClassifier {

	public String test(String text) throws InvalidFormatException, IOException {
		String classificationModelFilePath = "D:\\LatestCode-Search\\search\\search\\issuemodel";
		InputStream is = new FileInputStream(classificationModelFilePath);
		DoccatModel classificationModel = new DoccatModel(is);
		DocumentCategorizerME classificationME = new DocumentCategorizerME(
				classificationModel);
		DocumentCategorizerEvaluator modelEvaluator = new DocumentCategorizerEvaluator(
				classificationME);
		String expectedDocumentCategory = "";
		String documentContent = text;
		DocumentSample sample = new DocumentSample(expectedDocumentCategory,
				documentContent);
		double[] classDistribution = classificationME
				.categorize(documentContent);
		String predictedCategory = classificationME
				.getBestCategory(classDistribution);
		modelEvaluator.evaluateSample(sample);
		
		System.out.println(predictedCategory);
		
		return predictedCategory;
	}
	
	public static void main(String[] args) throws InvalidFormatException, IOException {
		
		IssueClassifier classifier = new IssueClassifier();
		classifier.test("late fees for wrong reasons");
	}
	
}
