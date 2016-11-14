# An example of running [XGBoost](https://github.com/dmlc/xgboost) on Apache [Spark](http://spark.apache.org/)

2016 November



### Prerequisites

- Scala
- sbt
- XGBoost



### Data Download

This example uses data from the [Bosch competition](https://www.kaggle.com/c/bosch-production-line-performance). The data can be downloaded using [Kaggle-CLI](https://github.com/floydwch/kaggle-cli) ([Kaggle](https://www.kaggle.com/) registration required):

``$ mkdir data; cd data``

``$ kg download -u <username> -p <password> -c bosch-production-line-performance``

``$ unzip '*.zip'``

``$ rm *.zip``



### Resources

- [XGBoost4j Spark documentation](http://dmlc.ml/docs/scaladocs/xgboost4j-spark/index.html#package)
- A more detailed [post](http://www.elenacuoco.com/2016/10/10/scala-spark-xgboost-classification/), which uses the same dataset