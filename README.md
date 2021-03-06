# quarkus-demo project

This project uses Quarkus, the Supersonic Subatomic Java Framework.

If you want to learn more about Quarkus, please visit its website: https://quarkus.io/ .

## Running the application in dev mode

You can run your application in dev mode that enables live coding using:
```
./mvnw quarkus:dev
```

## Packaging and running the application

The application can be packaged using `./mvnw package`.
It produces the `quarkus-demo-1.0.0-SNAPSHOT-runner.jar` file in the `/target` directory.
Be aware that it’s not an _über-jar_ as the dependencies are copied into the `target/lib` directory.

The application is now runnable using `java -jar target/quarkus-demo-1.0.0-SNAPSHOT-runner.jar`.

## Creating a native executable

You can create a native executable using: `./mvnw package -Pnative`.

Or, if you don't have GraalVM installed, you can run the native executable build in a container using: `./mvnw package -Pnative -Dquarkus.native.container-build=true`.

You can then execute your native executable with: `./target/quarkus-demo-1.0.0-SNAPSHOT-runner`

If you want to learn more about building native executables, please consult https://quarkus.io/guides/building-native-image.


## Used CLI commands

    mvn package -Pnative
    
    sam local start-api --template target/sam.native.yaml
    
    sam package --template-file target/sam.native.yaml --output-template-file packaged.yaml --s3-bucket dasniko-quarkus-demo
    
    sam deploy --template-file packaged.yaml --stack-name hbt-quarkus --capabilities CAPABILITY_IAM

    
    
    docker network create sam-local
    docker run --network sam-local --name dynamodb -d -p 8000:8000 amazon/dynamodb-local
    
    aws dynamodb create-table --table-name quarkus-books --attribute-definitions AttributeName=id,AttributeType=S --key-schema AttributeName=id,KeyType=HASH --provisioned-throughput ReadCapacityUnits=5,WriteCapacityUnits=5 --endpoint-url http://localhost:8000
    
    sam local start-api --template target/sam.native.yaml --docker-network sam-local
    