package com.project.rawflink;

import com.project.rawflink.events.InputEvent;
import com.project.rawflink.mappers.InputEventToProcessingEventMapper;
import org.apache.flink.api.common.eventtime.WatermarkStrategy;
import org.apache.flink.connector.kafka.source.KafkaSource;
import org.apache.flink.streaming.api.environment.StreamExecutionEnvironment;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Service;

@Service
public class Processor implements ApplicationRunner {

    private final KafkaSource<InputEvent> _kafkaSource;
    private final InputEventToProcessingEventMapper _inputEventToProcessingEventMapper;

    public Processor(KafkaSource<InputEvent> kafkaSource, InputEventToProcessingEventMapper inputEventToProcessingEventMapper) {
        _kafkaSource = kafkaSource;
        _inputEventToProcessingEventMapper = inputEventToProcessingEventMapper;
    }

    @Override
    public void run(ApplicationArguments args) throws Exception {

        final var env = StreamExecutionEnvironment.getExecutionEnvironment();

        var stream = env.fromSource(_kafkaSource, WatermarkStrategy.noWatermarks(), "kafka-source");

        stream
                .map(_inputEventToProcessingEventMapper::toProcessingEvent)
                .map(e -> {
                    System.out.println(e);
                    return e;
                });

        env.execute("Json processing flink job");
    }
}
