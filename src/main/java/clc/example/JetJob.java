package clc.example;

import com.hazelcast.core.Hazelcast;
import com.hazelcast.jet.pipeline.Pipeline;
import com.hazelcast.jet.pipeline.Sinks;
import com.hazelcast.jet.pipeline.test.TestSources;
import org.hashids.Hashids;

import java.util.AbstractMap;

public class JetJob {
    public static void main(String[] args) {
        final var salt = (args.length > 0)? args[0] : "my-salt";
        final var mapName = (args.length > 1)? args[1] : "my-map";
        var pipeline = Pipeline.create();
        pipeline.readFrom(TestSources.itemStream(1))
                .withoutTimestamps()
                .map(e -> new AbstractMap.SimpleEntry<>(
                        e.sequence(),
                        new Hashids(salt).encode(e.sequence()))
                )
                .writeTo(Sinks.map(mapName,
                        AbstractMap.SimpleEntry::getKey,
                        AbstractMap.SimpleEntry::getValue));
        var hz = Hazelcast.bootstrappedInstance();
        hz.getJet().newJob(pipeline);
    }
}
