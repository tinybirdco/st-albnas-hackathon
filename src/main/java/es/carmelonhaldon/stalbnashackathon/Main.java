package es.carmelonhaldon.stalbnashackathon;

import java.io.IOException;
import java.net.URL;
import java.util.List;

import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.analysis.core.KeywordAnalyzer;
import org.apache.lucene.document.Document;
import org.apache.lucene.document.Field;
import org.apache.lucene.document.TextField;
import org.apache.lucene.index.DirectoryReader;
import org.apache.lucene.index.IndexReader;
import org.apache.lucene.index.IndexWriter;
import org.apache.lucene.index.IndexWriterConfig;
import org.apache.lucene.index.Term;
import org.apache.lucene.search.FuzzyQuery;
import org.apache.lucene.search.IndexSearcher;
import org.apache.lucene.search.Query;
import org.apache.lucene.search.TopDocs;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.RAMDirectory;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.exc.StreamReadException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DatabindException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class Main {

	public static void main(String[] args) throws StreamReadException, DatabindException, IOException {

		// carmelonhaldon: Compare "St. Albans"...
		String stAlbans = "St. Albans";
		IndexSearcher stAlbansSearcher = indexKeyword(stAlbans);

		// carmelonhaldon: ... against positives...
		List<String> positives = readJson("file:positives.txt");
		System.out.printf("%npositives -> %d%n%n", positives.size());

		for (String positive : positives) {

			Query positiveQuery = new FuzzyQuery(new Term("text", positive));
			TopDocs searchResults = stAlbansSearcher.search(positiveQuery, 1);

			// carmelonhaldon: If found, can be replaced by "St. Albans"...
			System.out.printf("%s -> %s%n", positive, searchResults.totalHits > 0 ? stAlbans : "KO");
		}

		// carmelonhaldon: ... and negatives.
		List<String> negatives = readJson("file:negatives.txt");
		System.out.printf("%nnegatives -> %d%n%n", negatives.size());

		for (String negative : negatives) {

			Query negativeQuery = new FuzzyQuery(new Term("text", negative));
			TopDocs searchResults = stAlbansSearcher.search(negativeQuery, 1);

			// carmelonhaldon: ... If not, "KO".
			System.out.printf("%s -> %s%n", negative, searchResults.totalHits > 0 ? stAlbans : "KO");
		}
	}

	// carmelonhaldon: https://riptutorial.com/lucene/example/20860/hello-world
	// carmelonhaldon: As a "field" to classify, it's better to use the KeywordAnalyzer rathern than the StandardAnalyzer.
	private static IndexSearcher indexKeyword(String keyword) throws IOException {

		Directory directory = new RAMDirectory();
		Analyzer analyzer = new KeywordAnalyzer();

		IndexWriterConfig indexWriterConfig = new IndexWriterConfig(analyzer);
		IndexWriter indexWriter = new IndexWriter(directory, indexWriterConfig);

		Document document = new Document();
		document.add(new TextField("text", keyword, Field.Store.YES));

		indexWriter.addDocument(document);
		indexWriter.close();

		IndexReader indexReader = DirectoryReader.open(directory);
		return new IndexSearcher(indexReader);
	}

	private static List<String> readJson(String json) throws StreamReadException, DatabindException, IOException {

		URL url = new URL(json);

		ObjectMapper objectMapper = new ObjectMapper();
		objectMapper.configure(JsonParser.Feature.ALLOW_SINGLE_QUOTES, true);

		return objectMapper.readValue(url, new TypeReference<List<String>>() {
		});
	}
}
