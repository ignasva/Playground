import java.io.File

import ml.dmlc.xgboost4j.scala.spark.XGBoost
import org.apache.log4j.{Level, Logger}
import org.apache.spark.ml.feature.VectorAssembler
import org.apache.spark.sql.SparkSession

/**
  * Created by I-Vadaisa on 14/11/2016.
  */
object Main extends App {

  val path2data = "./data"

  val n_iter = 10
  val n_workers = 4

  val params = Map("eta" -> 0.023f,
    "max_depth" -> 10,
    "min_child_weight" -> 3.0,
    "subsample" -> 1.0,
    "colsample_bytree" -> 0.82,
    "colsample_bylevel" -> 0.9,
    "base_score" -> 0.005,
    "eval_metric" -> "auc",
    "seed" -> 49,
    "silent" -> 1,
    "objective" -> "binary:logistic")

  Logger.getLogger("org").setLevel(Level.WARN)

  val ss = SparkSession.builder()
    .appName("xgb-on-s")
    .master("local[*]")
    .getOrCreate()

  val raw = ss
    .read
    .options(Map("header" -> "true", "inferSchema" -> "true", "nullValue" -> "0"))
    .csv(new File(path2data, "train_numeric.csv").getPath)
    .na
    .fill(0d)

  val assembler = new VectorAssembler()
    .setInputCols(raw.columns.filter(n => n != "Id" & n != "Response"))
    .setOutputCol("features")

  val reshaped = assembler
    .transform(raw)
    .withColumn("label", raw("Response").cast("double"))
    .select("label", "features")

  val mod = XGBoost.trainWithDataFrame(reshaped, params, n_iter, n_workers, useExternalMemory = true)

  println(mod.setExternalMemory(true).transform(reshaped).count())

  ss.stop()
}
