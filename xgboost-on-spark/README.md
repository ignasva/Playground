# An example of running [XGBoost](https://github.com/dmlc/xgboost) on Apache [Spark](http://spark.apache.org/)

2016 November



### Prerequisites

- Scala
- sbt
- XGBoost
- 4 cores available on your local machine




### Quick Start

- Pull the code

  ``$ mkdir Playground; cd Playground``

  ``$ git init``

  ``$ git remote add -f origin https://github.com/ignasva/Playground``

  ``$ git config core.sparseCheckout true``

  ``$ echo 'xgboost-on-spark' >> .git/info/sparse-checkout``

  ``$ git pull origin master``

  ``$ cd xgboost-on-spark``

- Download data from the [Bosch competition](https://www.kaggle.com/c/bosch-production-line-performance). Using [Kaggle-CLI](https://github.com/floydwch/kaggle-cli):

  ``$ mkdir data; cd data``

  ``$ kg download -u <username> -p <password> -c bosch-production-line-performance``

  ``$ unzip '*.zip'``

  ``$ rm *.zip``

  ``$ cd ../``

- Copy the XGBoost *.jar* file

  ``$ mkdir lib``

  ``$ cp /path/to/xgboost4j-spark-x.x-jar-with-dependencies.jar ./lib``


- Build

  ``$ sbt package``

- Run

  ``$ sbt run Main``



### Resources

- [XGBoost4j Spark documentation](http://dmlc.ml/docs/scaladocs/xgboost4j-spark/index.html#package)
- A more detailed [post](http://www.elenacuoco.com/2016/10/10/scala-spark-xgboost-classification/), which uses the same dataset