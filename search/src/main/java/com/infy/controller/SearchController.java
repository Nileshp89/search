package com.infy.controller;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.infy.nlpprocessor.IssueClassifier;
import com.infy.nlpprocessor.TextEntityExtractor;
import com.infy.nlpprocessor.TextPreProcessor;

import gate.Annotation;
import gate.AnnotationSet;
import gate.Document;
import gate.util.GateException;

@Controller
public class SearchController {

	@Autowired
	private TextEntityExtractor textEntityExtractor;

	@Autowired
	private TextPreProcessor textPreProcessor;
	
	@Autowired
	private IssueClassifier issueClasifier;

	@RequestMapping(produces = "application/json", value = "/search", method = RequestMethod.GET)
	public ResponseEntity<Object> greeting(
			@RequestParam(value = "query", required = false) String query, Model model)
			throws GateException, IOException {

		textPreProcessor.POSTagData(query);
		
		issueClasifier.test(query);

		System.out.println(
				"-----------------------------------------------------------------------------------------------");

		Document doc = textEntityExtractor.extractEntities(query);

		AnnotationSet annotationSet = doc.getAnnotations();

		for (Annotation annotation : annotationSet) {

			// System.out.println(annotation.getType());

			if (annotation.getType().equals("Organization")) {

				Integer int1 = annotation.getStartNode().getId();

				Integer int2 = annotation.getEndNode().getId();

				System.out.println(int1 + "-----" + int2);

				for (int i = int1; i < int2; i++) {

					System.out.println(annotationSet.get(i).getFeatures().get("string"));
				}

			}

		}

		ResponseEntity<Object> entity = new ResponseEntity<>(HttpStatus.ACCEPTED);

		return entity;
	}

}