#regelCLI
Command Line Interface for [Regel](https://github.com/utopia-group/regel) - Regular Expression Generation from Examples and Language


To start you need to pull install regel and its dependencies (make sure you are in `regelCLI` directory):
```shell
git clone https://github.com/utopia-group/regel
cd regel
cd sempre
./pull-dependencies core
./pull-dependencies corenlp
./pull-dependencies freebase
./pull-dependencies tables
ant regex
cp sempre/dataset ..      
cp -r sempre/pretrained_models ..
cp sempre/module-classes.txt ..  
gradle build
cp build/libs/regelCLI-1.0-SNAPSHOT.jar .
java -jar regelCLI-1.0-SNAPSHOT.jar      
```
