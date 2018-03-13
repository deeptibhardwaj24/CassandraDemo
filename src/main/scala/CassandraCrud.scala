import com.datastax.driver.core.Session

import scala.collection.JavaConverters._


object CassandraCrud extends App with CassandraProvider {


  // Connect to the cluster and keyspace "knoldusTest"
  println("\n\n*********Cluster Information *************")
  println("\n\n Cluster Name is: " + cassandraSession.getCluster.getClusterName)
  println("\n\n Cluster Configuration is: " + cassandraSession.getCluster.getConfiguration.getQueryOptions.getConsistencyLevel)
  println("\n\n Cluster Metadata is: " + cassandraSession.getCluster.getMetadata.getAllHosts.toString)
  // Retrieve all User details from Users table

  println("\n\n*********Retrieve User Data Example *************")
  getUsersAllDetails(cassandraSession)

  // Insert new User into users table
  println("\n\n*********Insert User Data Example *************")
  cassandraSession.execute(
    "INSERT INTO users (id, address, name) VALUES (11104, 'USA', 'Stuart')")

  getUsersAllDetails(cassandraSession)

  // Close Cluster and Session objects
  println("\n\nIs Cluster Closed :" + cassandraSession.isClosed)
  println("\n\nIs Session Closed :" + cassandraSession.isClosed)
  cassandraSession.close()
  println("\n\nIs Cluster Closed :" + cassandraSession.isClosed)
  println("\n\nIs Session Closed :" + cassandraSession.isClosed)

  private def getUsersAllDetails(inSession: Session): Unit = {
    inSession.execute(s"CREATE TABLE IF NOT EXISTS users (id int PRIMARY KEY, address text, name text) ")

    // Use select to get the users table data
    val results = inSession.execute("SELECT * FROM users").asScala.toList
    for(row <- results)
      {
        println("data: -"+row)
      }
    }
}
