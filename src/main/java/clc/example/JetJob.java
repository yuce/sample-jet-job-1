package clc.example;

import com.hazelcast.core.Hazelcast;
import com.hazelcast.jet.pipeline.Pipeline;
import com.hazelcast.jet.pipeline.Sinks;
import com.hazelcast.jet.pipeline.test.TestSources;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.io.IOException;

public class JetJob {
    public static void main(String[] args) throws IOException {
        var pipeline = Pipeline.create();
        pipeline.readFrom(TestSources.items(args))
                .map(url -> Jsoup.connect(url).get().title())
                .setLocalParallelism(1)
                .writeTo(Sinks.logger());
        var hz = Hazelcast.bootstrappedInstance();
        hz.getJet().newJob(pipeline);
    }
}
