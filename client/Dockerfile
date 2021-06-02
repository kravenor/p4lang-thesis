FROM openjdk:7

COPY ./*.java /usr/src/myapp/
WORKDIR /usr/src/myapp
RUN javac *.java 
CMD ["java", "Main" ]
