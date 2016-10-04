package com.infy.nlpprocessor;

import gate.Annotation;
import gate.AnnotationSet;
import gate.Corpus;
import gate.Document;
import gate.Factory;
import gate.Gate;
import gate.creole.ANNIEConstants;
import gate.creole.ConditionalSerialAnalyserController;
import gate.util.GateException;
import gate.util.persistence.PersistenceManager;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.annotation.PostConstruct;

import org.springframework.stereotype.Component;

@Component
public class TextEntityExtractor {

	private ConditionalSerialAnalyserController annieController;

	@PostConstruct
	public void init() throws GateException, IOException {

		System.out.println("Initiaslising GATE...");
		Gate.init();

		// Load ANNIE plugin
		File gateHome = Gate.getGateHome();
		File pluginsHome = new File(gateHome, "plugins");
		Gate.getCreoleRegister().registerDirectories(new File(pluginsHome, "ANNIE").toURI().toURL());
		System.out.println("...GATE initialised");

		initAnnie();

	}

	public void initAnnie() throws GateException, IOException {
		System.out.println("Initialising ANNIE...");

		// create a serial analyser controller to run ANNIE with
		annieController = (ConditionalSerialAnalyserController) PersistenceManager.loadObjectFromFile(
				new File(new File(Gate.getPluginsHome(), ANNIEConstants.PLUGIN_DIR), ANNIEConstants.DEFAULT_FILE));

		System.out.println("...ANNIE loaded");
	} // initAnnie()

	public void setCorpus(Corpus corpus) {
		annieController.setCorpus(corpus);
	}

	public void execute() throws GateException {
		System.out.println("Running ANNIE...");
		annieController.execute();
		System.out.println("...ANNIE complete");
	}

	public Document extractEntities(String data) throws GateException {

		Corpus corpus = (Corpus) Factory.createResource("gate.corpora.CorpusImpl");

		Document doc = (Document) Factory.newDocument(data);
		corpus.add(doc);

		// tell the pipeline about the corpus and run it
		setCorpus(corpus);
		execute();

		/*for (Annotation annotation : doc.getAnnotations()) {

			System.out.println(annotation.toString());
		}

		System.out.println(
				"-----------------------------------------------------------------------------------------------------");
*/
		return doc;

	}

	public List<String> getEntityData(String entity, Document doc) {

		AnnotationSet annotationSet = doc.getAnnotations();

		List<String> extractedData = new ArrayList<String>();
		for (Annotation annotation : annotationSet) {

			// System.out.println(annotation.getType());

			if (annotation.getType().equals(entity)) {

				Integer int1 = annotation.getStartNode().getId();

				Integer int2 = annotation.getEndNode().getId();

				System.out.println(int1 + "-----" + int2);

				String text = "";
				for (int i = int1; i < int2; i++) {

					text = text + annotationSet.get(i).getFeatures().get("string");
					// System.out.println(annotationSet.get(i).getFeatures().get("string"));
				}
				//System.out.println(text);

				if (!extractedData.contains(text)) {
					extractedData.add(text);
				}

			}

		}

	//	System.out.println(extractedData.size());

		return extractedData;

	}

}
