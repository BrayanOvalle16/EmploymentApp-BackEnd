package co.ucentral.creditaplication.factories;

import co.ucentral.creditaplication.configurations.AwsProperties;
import software.amazon.awssdk.auth.credentials.AwsBasicCredentials;
import software.amazon.awssdk.auth.credentials.StaticCredentialsProvider;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.bedrockruntime.BedrockRuntimeClient;
import org.springframework.stereotype.Service;

@Service
public class BedrockClientFactory {

    private final AwsProperties awsProperties;

    public BedrockClientFactory(AwsProperties awsProperties) {
        this.awsProperties = awsProperties;
    }

    public BedrockRuntimeClient createClient() {
        AwsBasicCredentials awsCreds = AwsBasicCredentials.create(
                awsProperties.getAccessKey(),
                awsProperties.getSecretKey()
        );

        return BedrockRuntimeClient.builder()
                .region(Region.of(awsProperties.getRegion()))
                .credentialsProvider(StaticCredentialsProvider.create(awsCreds))
                .build();
    }
}
