build:
	javac functions/*.java

clean:
	-rm -f functions/*.class functions.jar

jar: build
	jar cvf functions.jar -C functions/ .
