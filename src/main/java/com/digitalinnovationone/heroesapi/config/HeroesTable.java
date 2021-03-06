package com.digitalinnovationone.heroesapi.config;

import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.client.builder.AwsClientBuilder;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBAsyncClientBuilder;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClient;
import org.socialsignin.spring.data.dynamodb.repository.config.EnableDynamoDBRepositories;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.util.StringUtils;
import com.amazonaws.services.dynamodbv2.model.ScalarAttributeType;
import com.amazonaws.services.dynamodbv2.model.ProvisionedThroughput;
import com.amazonaws.services.dynamodbv2.model.KeyType;
import com.amazonaws.services.dynamodbv2.model.KeySchemaElement;
import com.amazonaws.services.dynamodbv2.model.AttributeDefinition;
import com.amazonaws.services.dynamodbv2.document.DynamoDB;
import com.amazonaws.services.dynamodbv2.document.Table;
import static com.digitalinnovationone.heroesapi.constants.HeroesConstant.REGION_DYNAMO;
import static com.digitalinnovationone.heroesapi.constants.HeroesConstant.ENDPOINT_DYNAMO;

import java.lang.reflect.Array;
import java.util.Arrays;

@Configuration
@EnableDynamoDBRepositories
public class HeroesTable {
    public static void main (String [] args) throws Exception{

        AmazonDynamoDB client = AmazonDynamoDBAsyncClientBuilder.standard()
            .withEndpointConfiguration(new AwsClientBuilder.EndpointConfiguration(REGION_DYNAMO,ENDPOINT_DYNAMO))
            .build();

        DynamoDB dynamoDB = new DynamoDB(client);

        String tableName="Heroes_Table";

        try{
            Table table = dynamoDB.createTable(tableName,
            Arrays.asList(new KeySchemaElement("id", KeyType.HASH)),
            Arrays.asList(new AttributeDefinition("id", ScalarAttributeType.S)),
            new ProvisionedThroughput(5L,5L));
            table.waitForActive();
        }
        catch (Exception e ){
            System.out.println(e.getMessage());
        }
    }
}
