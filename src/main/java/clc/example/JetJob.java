package clc.example;

import com.hazelcast.core.Hazelcast;
import com.hazelcast.jet.pipeline.Pipeline;
import com.hazelcast.jet.pipeline.Sinks;
import com.hazelcast.jet.pipeline.test.TestSources;
import org.hashids.Hashids;

public class JetJob {
    public static void main(String[] args) {
        final var salt = (args.length > 0)? args[0] : "my-salt";
        var pipeline = Pipeline.create();
        pipeline.readFrom(TestSources.itemStream(1))
                .withoutTimestamps()
                .map(e -> new Hashids(salt).encode(e.sequence()))
                .map(id -> String.format("\n\n=== ID: %s ===\n\n", id))
                .writeTo(Sinks.logger());
        var hz = Hazelcast.bootstrappedInstance();
        hz.getJet().newJob(pipeline);
    }
}
