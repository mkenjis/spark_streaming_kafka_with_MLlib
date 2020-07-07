// mkdir /KfkProducer

// KafkaProducerWrapper.scala

package kfkProducerWrapper

// import org.apache.kafka.clients.producer.Producer
import org.apache.kafka.clients.producer.KafkaProducer
import org.apache.kafka.clients.producer.ProducerRecord

class KafkaProducerWrapper(brokerList: String) {
  val producerProps = {
    val prop = new java.util.Properties
    prop.put("bootstrap.servers", brokerList)
    prop.put("key.serializer", "org.apache.kafka.common.serialization.StringSerializer")
    prop.put("value.serializer", "org.apache.kafka.common.serialization.StringSerializer")
    prop
  }
  val p = new KafkaProducer[String, String](producerProps)

  def send(topic: String, key: String, value: String) {
    // val obs1 = value.map(r => r._2.split(",").map(x => x.toDouble))
    // val vetv = obs1.map(x => "x0 = " + x(0) + ", x1 = " + x(1) + ", x2 = " + x(2) + ", x3 = " + x(3))
    // p.send(new ProducerRecord(topic, key, vetv))
	p.send(new ProducerRecord(topic, key, "Resultado = (" + key + "):" + value))
  }
}

object KafkaProducerWrapper {
  var brokerList = ""
  lazy val instance = new KafkaProducerWrapper(brokerList)
}

--------------------------------
// kfkproducer.sbt

name := "kfk-producer"

version := "1.0.0"

scalaVersion := "2.11.8"

libraryDependencies ++= Seq(
  "org.apache.spark" %% "spark-core" % "2.3.2" % "provided",
  "org.apache.spark" %% "spark-sql" % "2.3.2" % "provided",
  "org.apache.spark" %% "spark-streaming" % "2.3.2" % "provided",
  "org.apache.spark" %% "spark-sql-kafka-0-10" % "2.3.2" % "provided",
  "org.apache.spark" %% "spark-streaming-kafka-0-10" % "2.3.2" % "provided"
)

-----build jar file with sbt ----------

cd KfkProducer
sbt package
