package com.infy.util;

import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;

import opennlp.tools.chunker.ChunkSample;
import opennlp.tools.chunker.ChunkerME;
import opennlp.tools.chunker.ChunkerModel;
import opennlp.tools.cmdline.CmdLineUtil;
import opennlp.tools.cmdline.PerformanceMonitor;
import opennlp.tools.cmdline.chunker.ChunkerModelLoader;
import opennlp.tools.postag.POSSample;
import opennlp.tools.util.InvalidFormatException;
import opennlp.tools.util.ObjectStream;
import opennlp.tools.util.PlainTextByLineStream;

public class Chunker {

	public static void main(String[] args) {
		  
		    ChunkerModel model = new ChunkerModelLoader().load(new File(args[0]));

		    ChunkerME chunker = new ChunkerME(model, ChunkerME.DEFAULT_BEAM_SIZE);

		    ObjectStream<String> lineStream =
		      new PlainTextByLineStream(new InputStreamReader(System.in));

		    PerformanceMonitor perfMon = new PerformanceMonitor(System.err, "sent");
		    perfMon.start();

		    try {
		      String line;
		      while ((line = lineStream.read()) != null) {

		        POSSample posSample;
		        try {
		          posSample = POSSample.parse(line);
		        } catch (InvalidFormatException e) {
		          System.err.println("Invalid format:");
		          System.err.println(line);
		          continue;
		        }

		        String[] chunks = chunker.chunk(posSample.getSentence(),
		            posSample.getTags());

		        System.out.println(new ChunkSample(posSample.getSentence(),
		            posSample.getTags(), chunks).nicePrint());

		        perfMon.incrementCounter();
		      }
		    }
		    catch (IOException e) {
		      CmdLineUtil.handleStdinIoError(e);
		    }

		    perfMon.stopAndPrintFinalResult();
		  }
		
	
	
}
