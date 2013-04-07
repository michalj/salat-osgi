salat-osgi
==========

OSGi packaging for Salat and Mongo (Casbah)

How to use it
=============

** In your build.sbt **
```scala
libraryDependencies += "com.novus" % "salat_2.9.1" % "1.9.1"
```
In your OSGi container use salat-osgi bundle from this repo.

** You need to use your own Salat Context **
```scala
import com.novus.salat.dao.SalatDAO
import com.mongodb.casbah.MongoConnection
import com.novus.salat._
import com.novus.salat.annotations._

// Make sure default context is not imported
//import com.novus.salat.global._

package object dao {
  implicit val ctx = new Context {
    val name = "OSGi friendly context"
  }
  
  object SampleDAO extends SalatDAO[Sample, Long](collection = MongoConnection()("sample-db")("sample-collection"))
}
```
