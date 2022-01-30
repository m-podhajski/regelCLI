# regelCLI

Command Line Interface for [Regel](https://github.com/utopia-group/regel) - Regular Expression Generation from Examples and Language


Make sure you have installed dependencies for [regel](https://github.com/utopia-group/regel) and `gradle`.

To start you need to install regel and copy `dataset`, `pretrained_models` and `module-classes` to the working directory (make sure you are in `regelCLI` directory):
```shell
git clone https://github.com/utopia-group/regel
cd regel
cd sempre
./pull-dependencies core
./pull-dependencies corenlp
./pull-dependencies freebase
./pull-dependencies tables
ant regex
ant sketch
cd ..
cd resnax
ant resnax 
cd ..
cp -r sempre/dataset ..      
cp -r sempre/pretrained_models ..
cp sempre/module-classes.txt ..  
cd ..
mkdir -p regex/data/_tmp
gradle build
cp build/libs/regelCLI-1.0-SNAPSHOT.jar .
java -jar regelCLI-1.0-SNAPSHOT.jar      
```
