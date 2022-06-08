mkdir -p target/resources
cp -R ./src/resources target/
javac -d target/ ./src/java/edu/school21/printer/app/Main.java ./src/java/edu/school21/printer/logic/ConvertImage.java
jar cvmf ./src/manifest.txt ./target/images-to-chars-printer.jar -C target ./edu/school21/printer
java -jar ./target/images-to-chars-printer.jar . 0