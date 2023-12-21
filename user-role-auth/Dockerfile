FROM openjdk:15.0.2-oracle
COPY target/user-role-auth-0.0.1-SNAPSHOT.jar user-role-auth-0.0.1.jar
EXPOSE 8080
ENTRYPOINT ["java","-jar","/user-role-auth-0.0.1.jar"]

# Stage 1: Build the application
#FROM maven:3.9-amazoncorretto-17 as builder

# Copy the project files to the container
#COPY ./pom.xml ./pom.xml

# Download all required dependencies into one layer
#RUN mvn dependency:go-offline -B

# Copy your other files
#COPY ./src ./src

# Build the application
#RUN mvn package -DskipTests

# Stage 2: Create the runtime image
#FROM amazoncorretto:15-alpine

# Copy the built artifact from the builder stage
#COPY --from=builder /target/*.jar /app/user-role-auth.jar

#EXPOSE 8080

# Run the application
#ENTRYPOINT ["java", "-jar", "-XX:+PrintFlagsFinal", "-XX:+UseG1GC", "-XX:MinRAMPercentage=60", "-XX:MaxRAMPercentage=60", "/app/user-role-auth.jar"]