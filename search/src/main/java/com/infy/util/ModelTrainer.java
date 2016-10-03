package com.infy.util;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.io.FileUtils;



import opennlp.tools.doccat.DoccatModel;
import opennlp.tools.doccat.DocumentCategorizerME;
import opennlp.tools.doccat.DocumentSample;
import opennlp.tools.doccat.DocumentSampleStream;
import opennlp.tools.util.CollectionObjectStream;
import opennlp.tools.util.ObjectStream;

public class ModelTrainer {

	public ModelTrainer() {
		// TODO Auto-generated constructor stub
	}

	@SuppressWarnings("deprecation")
	public void train() {
		String onlpModelPath = "issuemodel";

		DoccatModel model = null;
		InputStream dataInputStream = null;
		OutputStream onlpModelOutput = null;
		try {

			ArrayList<String> trainingStrings = new ArrayList<String>();
			trainingStrings.addAll(getStringCollection(new File("D:\\SearchTrainingData")));
			ObjectStream<String> lineStream = new CollectionObjectStream<>(
					trainingStrings);
			ObjectStream<DocumentSample> sampleStream = new DocumentSampleStream(
					lineStream);
			model = DocumentCategorizerME.train("en", sampleStream);
		} catch (IOException e) {
			System.err.println(e.getMessage());
		} finally {
			if (dataInputStream != null) {
				try {
					dataInputStream.close();
				} catch (IOException e) {
					System.err.println(e.getMessage());
				}
			}
		}

		try {
			onlpModelOutput = new BufferedOutputStream(new FileOutputStream(
					onlpModelPath));
			model.serialize(onlpModelOutput);
		} catch (IOException e) {
			System.err.println(e.getMessage());
		} finally {
			if (onlpModelOutput != null) {
				try {
					onlpModelOutput.close();
				} catch (IOException e) {
					System.err.println(e.getMessage());
				}
			}
		}
	}

	public ArrayList<String> getStringCollection(File masterFolder)
			throws IOException {

		ArrayList<String> trainString = new ArrayList<String>();
		for (final File fileEntry : masterFolder.listFiles()) {
			if (fileEntry.isDirectory()) {

			} else {
				System.out.println(fileEntry.getName());

				List<String> lines = FileUtils.readLines(fileEntry);

				// System.out.println("Size : " + lines.size());

				trainString.addAll(lines);

			}
		}

		return trainString;

	}
	
	public static void main(String[] args) {
		
		
		ModelTrainer modelTrainer = new ModelTrainer();
		modelTrainer.train();
	}
}
